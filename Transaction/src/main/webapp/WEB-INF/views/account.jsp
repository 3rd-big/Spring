<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>홍길동의 계좌</h1>
    <form action="transfer" method="get">
    	<label>이체금액 : <input type="text" name="money"></label>
    	<label>이체할 계좌번호 : <input type="text" name="accountNum" value="5678-1234"></label>
    	<button type="submit">이체</button>
    </form>
</body>
</html>