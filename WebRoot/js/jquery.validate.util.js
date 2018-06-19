$(document).ready(function() {
    // 身份证号码验证
    jQuery.validator.addMethod("isIdCardNo", function(value, element) {
        var card = /^[1-9]([0-9]{14}|[0-9]{17}|[0-9]{16}[Xx])$/;
        return this.optional(element) || card.test(value);
    }, "请正确输入您的身份证号码");

    // 用户名验证
    jQuery.validator.addMethod("isUserName", function(value, element) {
        var username = /^[\u4e00-\u9fa5][a-zA-Z0-9]{6,25}$/;
        return this.optional(element) || username.test(value);
    }, "5-25个字符：英文、数字或中文均可。");


    // 手机号码验证  
    jQuery.validator.addMethod("isMobile", function(value, element) {
        var phone = /^[1][3,4,5,8]\d{9}$/;
        return this.optional(element) || (phone.test(value));
    }, "请正确填写您的手机号码！");

    // 电话号码验证    
    jQuery.validator.addMethod("isPhone", function(value, element) {
        var tel = /^\d{7,12}$/;
        return this.optional(element) || (tel.test(value));
    }, "请正确填写您的电话号码");

    // 英文字符
    jQuery.validator.addMethod("enChar", function(value, element) {
        var enchar = /^[a-zA-Z_][a-zA-Z0-9_]*$/;
        return this.optional(element) || (enchar.test(value));
    }, "请填写英文字符");

    // 英文字符
    jQuery.validator.addMethod("pwd", function(value, element) {
        var pwd = /^[a-zA-Z0-9!@#$%*()_+]{6,20}$/;
        return this.optional(element) || (pwd.test(value));
    }, "请使用字母加数字的组合密码");


    // 中文字符
    jQuery.validator.addMethod("cnChar", function(value, element) {
        var cnchar = /^[\u4e00-\u9fa5]*$/;
        return this.optional(element) || (cnchar.test(value));
    }, "请填写中文字符");

    // 邮政编码验证    
    jQuery.validator.addMethod("isZipCode", function(value, element) {
        var tel = /^[0-9]{6}$/;
        return this.optional(element) || (tel.test(value));
    }, "请正确填写您的邮政编码");
    //不能包含特殊字符"&","<",">"
    jQuery.validator.addMethod("isCompanyName", function(value, element) {
        var tel = /^[^&<>]+$/;
        return this.optional(element) || (tel.test(value));
    }, "请检查下您的内容里面是否有&,<,>请删除");

    jQuery.validator.addMethod("isNotZero", function(value, element) {
        var tel = /^[1-9]+[0-9]*$/;
        return this.optional(element) || (tel.test(value));
    }, "对不起，不能填0，请重新填写");

    jQuery.validator.addMethod("isIdCard", function(value, element) {
        var tel0 = /^\d{15}$/;
        var result0 = this.optional(element) || (tel0.test(value));
        var tel1 = /^\d{17}[0-9X]$/;
        var result1 = this.optional(element) || (tel1.test(value));
        return (result0 || result1);
    }, "请正确填写您的身份证号");

    jQuery.validator.addMethod("isEmail", function(value, element) {
        var email = /^[0-9a-zA-Z][a-zA-Z0-9\._-]{1,}@[a-zA-Z0-9-]{1,}[a-zA-Z0-9]\.[a-zA-Z0-9\.]{1,}[a-zA-Z]$/;
        return this.optional(element) || (email.test(value));
    }, "请检查您的email地址");

    // 验证两次输入值是否不相同
    jQuery.validator.addMethod("notEqualTo", function(value, element, param) {
        return value != $(param).val();
    }, $.validator.format("两次输入不能相同"));

    // 是否选择了一个选项
    jQuery.validator.addMethod("isSelected", function(value, element) {
        return !(value == "");
    }, "请选择一个选项");
});