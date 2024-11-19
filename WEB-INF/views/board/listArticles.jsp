<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-10-14
  Time: 오후 2:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>게시판 목록</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
    />
</head>
<body>
<%-- 수정 --%>
<div class="container px-4 ms-5">
    <h1 class="mt-4">AISW 게시판</h1>
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb mb-5">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">일반게시판</li>
        </ol>
    </nav>

    <div class="card mb-4">
        <div class="card-header bg-warning-subtle py-2">
            <i class="fas fa-table me-1"></i>
            일반 게시판
            <!-- 회원인 경우 새글작성 버튼 활성화 -->
            <c:if test="${isLogOn == true && member != null}">
            <span class="position-absolute top-0 end-0 mt-1 pe-3">
            <a href="${contextPath}/board/boardForm.do">
                <button class="btn btn-sm btn-success" type="button">새글작성</button>
            </a>
            </span>
            </c:if>
        </div>
        <div class="card-body">
            <table id="datatablesSimple">
                <thead class="bg-info">
                <tr>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>조회수</th>
                    <th>작성자</th>
                    <th>작성일</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>조회수</th>
                    <th>작성자</th>
                    <th>작성일</th>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach var="article" items="${articlesList}">
                <tr>
                    <td>${article.articleNO}</td>
                    <td>
                        <span class="d-inline-block text-truncate" style="max-width: 150px;">
                            <a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
                        </span>
                    </td>
                    <td>${article.hitCnt}</td>
                    <td>${article.writer}</td>
                    <td>${article.writeDate}</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</html>
