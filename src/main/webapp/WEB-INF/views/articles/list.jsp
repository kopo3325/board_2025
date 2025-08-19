<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<html>
<head><title>게시판 목록</title></head>
<body>
<h1>게시판</h1>
<a href="/articles/write">글쓰기</a>

<form action="/articles" method="get">
    <select name="searchType">
        <option value="title" <c:if test="${searchType eq 'title'}">selected</c:if>>제목</option>
        <option value="author" <c:if test="${searchType eq 'author'}">selected</c:if>>작성자</option>
    </select>
    <input type="text" name="keyword" value="${keyword}" placeholder="검색어 입력"/>
    <button type="submit">검색</button>
</form>

<table border="1" width="100%">
    <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
    </thead>
    <tbody>
        <c:if test="${empty articlePage.content}">
            <tr>
                <td colspan="4" style="text-align: center;">게시글이 없습니다.</td>
            </tr>
        </c:if>
        <c:forEach var="article" items="${articlePage.content}">
            <tr>
                <td>${article.id}</td>
                <td><a href="/articles/${article.id}">${article.title}</a></td>
                <td>${article.user.name}</td>
                <td>${article.formattedCreatedAt}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<c:if test="${articlePage.totalPages > 0}">
<div style="text-align: center;">
    <c:url var="baseUrl" value="/articles"/>
    <c:set var="searchParams" value=""/>
    <c:if test="${not empty searchType}">
        <c:set var="searchParams" value="${searchParams}&searchType=${searchType}"/>
    </c:if>
    <c:if test="${not empty keyword}">
        <c:set var="searchParams" value="${searchParams}&keyword=${keyword}"/>
    </c:if>

    <c:if test="${!articlePage.first}">
        <a href="${baseUrl}?page=0${searchParams}">처음으로</a>
    </c:if>

    <c:if test="${articlePage.hasPrevious()}">
        <a href="${baseUrl}?page=${articlePage.number - 1}${searchParams}">이전</a>
    </c:if>

    <c:forEach begin="0" end="${articlePage.totalPages - 1}" var="i">
        <c:choose>
            <c:when test="${i == articlePage.number}">
                <strong>${i + 1}</strong>
            </c:when>
            <c:otherwise>
                <a href="${baseUrl}?page=${i}${searchParams}">${i + 1}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${articlePage.hasNext()}">
        <a href="${baseUrl}?page=${articlePage.number + 1}${searchParams}">다음</a>
    </c:if>

    <c:if test="${!articlePage.last}">
        <a href="${baseUrl}?page=${articlePage.totalPages - 1}${searchParams}">맨끝으로</a>
    </c:if>
</div>
</c:if>

</body>
</html>