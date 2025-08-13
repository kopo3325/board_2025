<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>글 상세</title></head>
<body>
<h1>${article.title}</h1>
<p>작성자: ${article.author}</p>
<p>작성일: ${article.createdAt}</p>
<hr/>
<div>${article.content}</div>
<a href="/articles">목록으로</a>
<a href="/articles/${article.id}/edit">수정</a>

<form action="/articles/${article.id}" method="post" style="display:inline;" onsubmit="return confirm('정말로 삭제하시겠습니까?');">
    <input type="hidden" name="_method" value="delete"/>
    <button type="submit">삭제</button>
</form>
</body>
</html>
