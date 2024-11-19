<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>회원 정보 창</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"/>
</head>
<body>
<div class="container">
  <div class="row" style="margin: 30px; width: 800px">
    <div class="text-primary text-center">
      <h3>회원 정보 상세 보기</h3>
    </div>
    <form method="get" action="${contextPath}/member/modifyMember.do">
      <input type="hidden" name="id" value="${memberView.id}">
      <input type="hidden" name="pwd" value="${memberView.pwd}">
      <div class="row align-items-center" style="padding: 10px; margin-bottom: 30px">
        <div class="col-auto">
          <label class="col-form-label">아이디</label>
        </div>
        <div class="col-auto">
          <input class="form-control" type="text" value="${memberView.id}" disabled readonly/>
        </div>
        <div class="col-auto">
          <label class="col-form-label">가입일</label>
        </div>
        <div class="col-auto">
          <input class="form-control" type="text" value="${memberView.joinDate}" disabled readonly/>
        </div>
      </div>
      <div class="mb-3">
        <label class="form-label text-start">이름</label>
        <input name="name" type="text" class="form-control" value="${memberView.name}" />
      </div>
      <div class="mb-3">
        <label class="form-label">이메일</label>
        <input type="email" name="email" class="form-control" value="${memberView.email}" />
      </div>
      <div class="d-grid gap-2 d-md-flex justify-content-md-center">
        <button class="btn btn-success me-md-2" type="submit">수정하기</button>
        <a href="${contextPath}/member/removeMember.do?id=${memberView.id}">
          <button class="btn btn-danger" type="button">탈퇴하기</button>
        </a>
        <a href="${contextPath}/member/listMembers.do">
          <button class="btn btn-secondary" type="button">취소</button>
        </a>
      </div>
    </form>
  </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</html>
