<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>掲示板アプリケーション</h2>

<form:form action="${pageContext.request.contextPath}/bbs/insertArticle" modelAttribute="articleForm" method="post">
	<form:errors path="name" cssStyle="color:red" element="div"/>
	投稿者名:<form:input path="name"/><br>
	<form:errors path="content" cssStyle="color:red" element="div"/>
	投稿内容:<form:textarea  cols="30" rows="3" path="content"/><br>
	<input type="submit" value="記事投稿">
</form:form>
	<hr>

<c:forEach var="article" items="${articleList}">
	記事ID：<c:out value="${article.id}"/><br>
	投稿者名：<c:out value="${article.name}"/><br>
	投稿内容：<c:out value="${article.content}"/><br>
	<form:form action="${pageContext.request.contextPath}/bbs/deleteAllByArticleId" modelAttribute="articleForm" method="post">
	<input type="hidden" name="id" value="${article.id}">
	<input type="submit" value="記事削除">
	</form:form>
	
	
		<c:forEach var="comment" items="${article.commentList}">
		コメントID:<c:out value="${comment.id}" /><br>
		コメント者名:<c:out value="${comment.name}" /><br>
		コメント内容:<c:out value="${comment.content}" /><br>
		<form:form action="${pageContext.request.contextPath}/bbs/deleteComment" modelAttribute="commentForm" method="post">
		<input type="hidden" name="id" value="${comment.id}">
		<input type="submit" value="コメント削除">
		</form:form>
		
		</c:forEach>
		
		<form:form action="${pageContext.request.contextPath}/bbs/insertComment" modelAttribute="commentForm" method="post">
		<c:if test="${article.id == commentForm.articleId}"><form:errors path="name" cssStyle="color:red" element="div"/></c:if>
		コメント者名:<form:input path="name"/><br>
		<c:if test="${article.id == commentForm.articleId}"><form:errors path="content" cssStyle="color:red" element="div"/></c:if>
		コメント内容:<form:input path="content"/><br>
		<input type="hidden" name="articleId" value="${article.id}">
		<input type="submit" value="コメント投稿" ><br>
		</form:form>
	<hr>

</c:forEach>

</body>
</html>