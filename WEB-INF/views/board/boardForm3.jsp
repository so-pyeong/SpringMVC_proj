<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("UTF-8"); %>

<form action="${contextPath}/board/addArticle.do" method="post" enctype="multipart/form-data">
    <input type="text" name="title">
    <textarea name="content" rows="10"></textarea>
    <input type="file" name="file_1">
    <button type="submit">save</button>
</form>