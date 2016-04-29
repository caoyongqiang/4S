
function carOwnersValidate(){
	if(carOwnersForm.name.value==""){
		alert("请添写员工姓名！");		
		carOwnersForm.name.focus();
		return false;
	}
	if(carOwnersForm.phoneNumber.value==""){
		alert("请添写手机号！");		
		carOwnersForm.phoneNumber.focus();
		return false;
	}
	if(carOwnersForm.idCard.value==""){
		alert("请填写身份证号！");		
		carOwnersForm.idCard.focus();
		return false;
	}
	if(carOwnersForm.purchaseTime.value==""){
		alert("请添写购车时间！");		
		carOwnersForm.purchaseTime.focus();
		return false;
	}
	if(!checkIsValidDate(carOwnersForm.purchaseTime.value)){
		alert("输入的时间不合法！（年-月-日）");
		carOwnersForm.purchaseTime.focus();
		return false;
	}

	return true;
}
function clueValidate(){
	if(clueForm.name.value==""){
		alert("请添写线索姓名！");		
		clueForm.name.focus();
		return false;
	}
	if(clueForm.phoneNumber.value==""){
		alert("请添写手机号！");
		clueForm.phoneNumber.focus();
		return false;
	}
	return true;
}
function userValidate(){
	if(userForm.username.value==""){
		alert("请添写人员姓名！");		
		userForm.username.focus();
		return false;
	}
	if(userForm.phoneNumber.value==""){
		alert("请添写手机号！");		
		userForm.phoneNumber.focus();
		return false;
	}
	if(userForm.idCard.value==""){
		alert("请添写身份证号！");		
		userForm.idCard.focus();
		return false;
	}
	if(userForm.password.value==""){
		alert("请添写登陆密码！");		
		userForm.password.focus();
		return false;
	}
	/*if(CheckDate(userForm.idCard.value)){
		alert("输入的日期不合法！（年-月-日）");
		userForm.idCard.focus();
		return false;
	}*/
	return true;
}

function serviceValidate(){
	if(serviceForm.name.value==""){
		alert("请添写客户名称！");		
		serviceForm.name.focus();
		return false;
	}
	if(serviceForm.phoneNumber.value==""){
		alert("请添写手机号！");		
		serviceForm.phoneNumber.focus();
		return false;
	}
	/*if(serviceForm.begintime.value==""){
		alert("请添写培训开始时间！");		
		serviceForm.begintime.focus();
		return false;
	}
	if(!checkIsValidDate(serviceForm.begintime.value)){
		alert("输入的时间不合法！（年-月-日）");
		serviceForm.begintime.focus();
		return false;
	}

	if(serviceForm.endtime.value==""){
		alert("请添写培训结束时间！");		
		serviceForm.endtime.focus();
		return false;
	}
	if(!checkIsValidDate(serviceForm.endtime.value)){
		alert("输入的时间不合法！（年-月-日）");
		serviceForm.endtime.focus();
		return false;
	}
	if(!checkDateEarlier(serviceForm.begintime.value,serviceForm.endtime.value)){
		alert("起始日期不能晚于结束日期！");
		serviceForm.begintime.focus();
		return false;
	}*/

	if(serviceForm.car.value==""){
		alert("请添写车型！");		
		serviceForm.car.focus();
		return false;
	}
	if(serviceForm.plateNumber.value==""){
		alert("请添写车牌号！");		
		serviceForm.plateNumber.focus();
		return false;
	}
	return true;
}

function checkDateEarlier(strStart,strEnd)
{
    if(checkIsValidDate(strStart) == false || checkIsValidDate(strEnd) == false)
        return false;
    //如果有一个输入为空，则通过检验
    if (( strStart == "" ) || ( strEnd == "" ))
        return true;
    var arr1 = strStart.split("-");
    var arr2 = strEnd.split("-");
    var date1 = new Date(arr1[0],parseInt(arr1[1].replace(/^0/,""),10) - 1,arr1[2]);
    var date2 = new Date(arr2[0],parseInt(arr2[1].replace(/^0/,""),10) - 1,arr2[2]);
    if(arr1[1].length == 1)
        arr1[1] = "0" + arr1[1];
    if(arr1[2].length == 1)
        arr1[2] = "0" + arr1[2];
    if(arr2[1].length == 1)
        arr2[1] = "0" + arr2[1];
    if(arr2[2].length == 1)
        arr2[2]="0" + arr2[2];
    var d1 = arr1[0] + arr1[1] + arr1[2];
    var d2 = arr2[0] + arr2[1] + arr2[2];
    if(parseInt(d1,10) > parseInt(d2,10))
       return false;
    else
       return true;
}//~~~
function checkIsValidDate(str)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    var pattern = /^((\d{4})|(\d{2}))-(\d{1,2})-(\d{1,2})$/g;
    if(!pattern.test(str)){
        return false;
	}
    var arrDate = str.split("-");
    if(parseInt(arrDate[0],10) < 100)
        arrDate[0] = 2000 + parseInt(arrDate[0],10) + "";
    var date =  new Date(arrDate[0],(parseInt(arrDate[1],10) -1)+"",arrDate[2]);
    if(date.getFullYear() == arrDate[0]
       && date.getMonth() == (parseInt(arrDate[1],10) -1)+""
       && date.getDate() == arrDate[2]){
        return true;
	}else{
        return false;
	}
}//~~~

function CheckDate(INDate)
{ if (INDate=="")
    {return true;}
	subYY=INDate.substr(0,4)
	if(isNaN(subYY) || subYY<=0){
		return true;
	}
	//转换月份
	if(INDate.indexOf('-',0)!=-1){	separate="-"}
	else{
		if(INDate.indexOf('/',0)!=-1){separate="/"}
		else {return true;}
		}
		area=INDate.indexOf(separate,0)
		subMM=INDate.substr(area+1,INDate.indexOf(separate,area+1)-(area+1))
		if(isNaN(subMM) || subMM<=0){
		return true;
	}
		if(subMM.length<2){subMM="0"+subMM}
	//转换日
	area=INDate.lastIndexOf(separate)
	subDD=INDate.substr(area+1,INDate.length-area-1)
	if(isNaN(subDD) || subDD<=0){
		return true;
	}
	if(eval(subDD)<10){subDD="0"+eval(subDD)}
	NewDate=subYY+"-"+subMM+"-"+subDD
	if(NewDate.length!=10){return true;}
    if(NewDate.substr(4,1)!="-"){return true;}
    if(NewDate.substr(7,1)!="-"){return true;}
	var MM=NewDate.substr(5,2);
	var DD=NewDate.substr(8,2);
	if((subYY%4==0 && subYY%100!=0)||subYY%400==0){ //判断是否为闰年
		if(parseInt(MM)==2){
			if(DD>29){return true;}
		}
	}else{
		if(parseInt(MM)==2){
			if(DD>28){return true;}
		}	
	}
	var mm=new Array(1,3,5,7,8,10,12); //判断每月中的最大天数
	for(i=0;i< mm.length;i++){
		if (parseInt(MM) == mm[i]){
			if(parseInt(DD)>31){return true;}
		}else{
			if(parseInt(DD)>30){return true;}
		}
	}
	if(parseInt(MM)>12){return true;}
   return false;
  }
function repairValidate(){

	if(repairForm.name.value==""){
		alert("请添写姓名！");		
		repairForm.name.focus();
		return false;
	}
	if(repairForm.tel.value==""){
		alert("请填写手机号！");		
		repairForm.tel.focus();
		return false;
	}
	if(repairForm.car.value==""){
		alert("请添写车型！");		
		repairForm.car.focus();
		return false;
	}
	if(repairForm.plateNumber.value==""){
		alert("请添写车牌号！");		
		repairForm.plateNumber.focus();
		return false;
	}
/*	if(repairForm.repairType.value==""){
		alert("请添写维修类型！");		
		repairForm.repairType.focus();
		return false;
	}
	if(repairForm.repairCost.value==""){
		alert("请添写维修金额！");		
		repairForm.repairCost.focus();
		return false;
	}*/
	return true;
}
function adminChecked(){
	if(userForm.isadminhelp.checked){
		document.userForm.isadmin.value="1";
	}else{
		document.userForm.isadmin.value="0";
	}
}
