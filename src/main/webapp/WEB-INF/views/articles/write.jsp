<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>글 작성</title></head>
<body>
<h1>글 작성</h1>
<form action="/articles/write" method="post">
    <div>
        <label>제목:</label><br/>
        <input type="text" name="title" required/>
    </div>
    <div>
        <label>작성자:</label><br/>
        <input type="text" name="author" value="${authentication.name}" />
    </div>
    <div>
        <label>내용:</label><br/>
        <textarea name="content" rows="10" cols="50" required></textarea>
    </div>
    <div>
        <input type="submit" value="작성"/>
    </div>
</form>
<a href="/articles">목록으로</a>
</body>
</html>
