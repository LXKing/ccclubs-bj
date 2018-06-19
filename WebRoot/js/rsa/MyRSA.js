var key;
//初始化 MyRSa
function initMyRSA(){
	setMaxDigits(131);
	key = new RSAKeyPair("10001","","9d14916c775da4eab629ca84d33cd73991f537bc71b7f70e269b06d2329fee4112bba0c7f8884f83be30b570c3624e2d2157f000cdd9979596283619665a8c73cbaa34dffbbd7599f31e9f6b953c5051dcfadf289e6f25308f78a4a5f7573584ef3291ffd0bd19e84e324cc2fcc8556e6a2fc545a3749ba3641292055f717919");
}

//加密字段 换回数据
function myRSA(data){
	return encryptedString(key, base64encode(strUnicode2Ansi(data)));
}