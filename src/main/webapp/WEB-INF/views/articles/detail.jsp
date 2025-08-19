<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head><title>글 상세</title></head>
<body>
<h1>${article.title}</h1>
<p>작성자: ${article.user.name}</p>
<p>작성일: ${article.formattedCreatedAt}</p>
<p>내용: ${article.content}</p>

<a href="/articles">목록으로</a>
<c:if test="${canEditDeleteArticle}">
    <a href="/articles/${article.id}/edit">수정</a>

    <form action="/articles/${article.id}" method="post" style="display:inline;" onsubmit="return confirm('정말로 삭제하시겠습니까?');">
        <input type="hidden" name="_method" value="delete"/>
        <button type="submit">삭제</button>
    </form>
</c:if>

<hr/>

<h2>댓글</h2>
<div id="comments-container">
    <c:forEach var="comment" items="${comments}">
        <div id="comment-${comment.id}">
            <p>${comment.content}</p>
            <c:if test="${comment.canEditDelete}">
                <button onclick="deleteComment(${comment.id})">Delete</button>
            </c:if>
        </div>
    </c:forEach>
</div>

<h3>댓글 내용 입력</h3>
<form id="comment-form">
    <textarea id="comment-content" rows="4" cols="50"></textarea><br>
    <button type="submit">댓글 달기</button>
</form>

<script>
    

    function renderComment(container, comment) {
        // Clear existing content
        container.innerHTML = '';

        const contentP = document.createElement('p');
        contentP.textContent = comment.content;

        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.onclick = () => deleteComment(comment.id);

        container.appendChild(contentP);
        container.appendChild(deleteButton);
    }

    document.getElementById('comment-form').addEventListener('submit', function(e) {
        e.preventDefault();
        const content = document.getElementById('comment-content').value;
        const articleId = ${article.id};
        console.log('Article ID:', articleId);
        fetch('/api/articles/' + articleId + '/comments', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ content: content })
        })
        .then(response => response.json())
        .then(comment => {
            const commentsContainer = document.getElementById('comments-container');
            const commentDiv = document.createElement('div');
            commentDiv.id = `comment-${comment.id}`;
            
            renderComment(commentDiv, comment);

            commentsContainer.appendChild(commentDiv);
            document.getElementById('comment-content').value = '';
        });
    });

    

    function deleteComment(commentId) {
        if (confirm('댓글을 삭제하시겠습니까?')) {
            const articleId = ${article.id};
            fetch('/api/articles/' + articleId + '/comments/' + commentId, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    const commentDiv = document.getElementById(`comment-${commentId}`);
                    commentDiv.remove();
                } else {
                    alert('Failed to delete comment.');
                }
            });
        }
    }
</script>
</body>
</html>