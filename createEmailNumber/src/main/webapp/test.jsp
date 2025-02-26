<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>인증코드 발급 페이지.</title>
</head>
<body>
<h1>인증코드 발급</h1>
<br/>
<form action="EmailServlet" method="post">
    <label for="email">이메일:</label>
    <input type="email" id="email" name="email" required>
    <button type="submit">인증 메일 보내기</button>
</form>
</body>

</html>