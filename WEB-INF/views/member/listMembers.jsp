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
            <li class="breadcrumb-item active" aria-current="page">관리자 페이지</li>
            <li class="breadcrumb-item active" aria-current="page">회원 관리</li>
        </ol>
    </nav>

    <div class="card mb-4">
        <div class="card-header bg-warning-subtle py-2">
            <i class="fas fa-table me-1"></i>
            회원 관리
        </div>
        <div class="card-body">
            <table id="datatablesSimple">
                <thead class="bg-info">
                <tr>
                    <th>아이디</th>
                    <th>비밀번호</th>
                    <th>이름</th>
                    <th>이메일</th>
                    <th>가입일</th>
                    <th>수정</th>
                    <th>삭제</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <th>아이디</th>
                    <th>비밀번호</th>
                    <th>이름</th>
                    <th>이메일</th>
                    <th>가입일</th>
                    <th>수정</th>
                    <th>삭제</th>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach var="member" items="${membersList}">
                    <tr>
                        <td>${member.id}</td>
                        <td>${member.pwd}</td>
                        <td>${member.name}</td>
                        <td>${member.email}</td>
                        <td>${member.joinDate}</td>
                        <td><a href="${contextPath}/member/editMember.do?id=${member.id}">수정하기</a></td>
                        <td><a href="${contextPath}/member/removeMember.do?id=${member.id}">삭제하기</a></td>
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
        crossorigin="anonymous">
</script>
</html>
