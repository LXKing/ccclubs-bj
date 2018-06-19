function isChinaIDCard(num) {
    if (num == 111111111111111)
        return false;
    num = num.toUpperCase();
    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。   
    if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
        return false;
    }

    //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。

    //下面分别分析出生日期和校验位
    if (idcard_getAge(num) < 18) {
        return false;
    }
    var len, re;
    len = num.length;
    if (len == 15) {
        re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
        var arrSplit = num.match(re);

        //检查生日日期是否正确 
        var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay) {
            return false;
        }
        else {
            //将15位身份证转成18位 
            //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0, i;
            num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
            for (i = 0; i < 17; i++) {
                nTemp += num.substr(i, 1) * arrInt[i];
            }
            num += arrCh[nTemp % 11];
            return true;
        }
    }
    if (len == 18) {
        re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
        var arrSplit = num.match(re);

        //检查生日日期是否正确 
        var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay) {
            return false;
        }
        else {
            //检验18位身份证的校验码是否正确。 
            //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
            var valnum;
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0, i;
            for (i = 0; i < 17; i++) {
                nTemp += num.substr(i, 1) * arrInt[i];
            }
            valnum = arrCh[nTemp % 11];
            if (valnum != num.substr(17, 1)) {
                return false;
            }
            return true;
        }
    }
    return false;
}
function isValidDate(iY, iM, iD) {
    var a = new Date(iY, iM, iD);
    var y = a.getFullYear();
    var m = a.getMonth();
    var d = a.getDate();
    if (y != iY || m != iM || d != iD) {
        return false;
    }
    return true;
}
function isInteger(str) {
    //if (/[^\d]+$/.test(str)){
    if (!/^\d+$/.test(str)) {
        return false;
    }
    return true;
}
function idcard_getbirthday(id) {
    var id = String(id);
    var birthday;
    if (id.length == 15) {
        birthday = new Date(id.substr(6, 2), id.substr(8, 2) - 1, id.substr(10, 2));
    } else if (id.length == 18) {
        birthday = new Date(id.slice(6, 10), id.slice(10, 12) - 1, id.slice(12, 14));
    } else {
        return false;
    }
    return birthday;
}

function idcard_getAge(id) {
    var id = String(id);
    var age;
    if (id.length == 15) {
        age = (new Date()).getFullYear() - (new Date(id.substr(6, 2), id.substr(8, 2) - 1, id.substr(10, 2))).getFullYear();
    } else if (id.length == 18) {
        age = (new Date()).getFullYear() - (new Date(id.slice(6, 10), id.slice(10, 12) - 1, id.slice(12, 14))).getFullYear();
    } else {
        return false;
    }
    return age;
}