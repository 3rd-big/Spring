/**
 * 
 */
//interest 여러개 찍은거 한줄 String으로 만들어서 데이터 보내주기
var idCheckButt=false;
function createForm(obj){
	var str="";
	for(var i=0; i<obj.interest.length; i++){
		if(obj.interest[i].checked==true){
			str += obj.interest[i].value + ",";
		}
	}
	obj.resultInterest.value=str;
	
}

function idCheck(root){//아이디 중복 확인을 누르면 입력한 아이디를 가지고 들어가야한다.
   if($("input[name = 'id']").val() == ""){
      alert("아이디를 입력하세요");
      $("input[name = 'id']").focus();
      return false;
   }
   
   var url = root+"/member/idCheck.do?id=" + $("input[name = 'id']").val();
   
   window.open(url, "", "width = 250, height = 150");
}


