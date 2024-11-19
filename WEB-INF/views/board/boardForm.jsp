<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-10-14
  Time: 오후 4:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>게시판 목록</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"/>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script type="text/javascript" src="${contextPath}/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
</head>
<body>
<div class="container px-4 ms-5">
  <h1 class="mt-4">새글 작성 창</h1>
  <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
    <ol class="breadcrumb mb-5">
      <li class="breadcrumb-item"><a href="#">Home</a></li>
      <li class="breadcrumb-item"><a href="${contextPath}/board/listArticles.do">일반게시판</a></li>
      <li class="breadcrumb-item active" aria-current="page">새글작성</li>
    </ol>
  </nav>

  <form id="frm" action="${contextPath}/board/addArticle.do" method="post" enctype="multipart/form-data">
    <input type="hidden" name="writer" value="${member.id}">
    <div class="row align-items-center">
      <div class="input-group mb-3">
        <span class="input-group-text" id="basic-addon3">글제목</span>
        <input name="title" type="text" class="form-control" aria-describedby="basic-addon3"/>
      </div>
    </div>
    <div class="row align-items-center mb-3">
        <textarea name="content" id="ir1" rows="15" class="form-control me-3" aria-describedby="basic-addon4" style="width: 98%"></textarea>
    </div>

    <div class="input-group my-1">
      <input id="file_1" name="file_1" type="file" class="form-control" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01" aria-label="Upload">
      <button class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon01">삭제하기</button>
    </div>
    <div id="d_file"></div>

    <div class="d-grid gap-2 d-md-flex justify-content-md-center mt-5">
      <button class="btn btn-info me-md-2" type="button" onclick="file_add()">파일추가</button>
      <button id="savebutton" class="btn btn-success me-md-2" type="button">저장하기</button>
      <!--<button class="btn btn-success me-md-2" type="submit">저장하기</button>-->
      <a href="${contextPath}/board/listArticles.do">
        <button class="btn btn-secondary" type="button">목록보기</button>
      </a>
    </div>
  </form>
  <script type="text/javascript">
    // file 태그 추가
    var cnt = 2;
    function file_add() {
      $("#d_file").append("<div class='input-group my-1'>"
              + " <input id='file_" + cnt + "' name='file_" + cnt + "' type='file' class='form-control' id='inputGroupFile01' aria-describedby='inputGroupFileAddon01' aria-label='Upload'>"
              + " <button id='count_"+ cnt +"' class='btn btn-outline-secondary' type='button' id='inputGroupFileAddon01' onclick='delUploadDiv("+cnt+")'>삭제하기</button> </div>"
      );
      cnt++;
    }
    function delUploadDiv(cnt) {
      $("#file_" + cnt + "").remove();
      $("#count_" + cnt + "").remove();
      cnt--;
    }

    // SmartEditor2 설정
    $(function () {
      var oEditors = [];
      nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "ir1",
        sSkinURI: "${contextPath}/resources/se2/SmartEditor2Skin.html",
        fCreator: "createSEditor2",
        htParams : {
          // 툴바 사용 여부 (true/false)
          bUseToolbar : true,
          // 입력창 크기 조절바 사용 여부 (true/false)
          bUseVaerticalResizer : true,
          // 모드 탭(Editor | HTML | TEXT) 사용 여부(true/false)
          bUseModeChanger: false,
        }
      });

      // 전송버튼 클릭 이벤트
      $("#savebutton").click(function () {
        // id가 ir1인 textarea에 에디터에서 대입
        oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);

        // 이부분에 에디터 validation 검증

        // form submit
        $("#frm").submit();
      })
    })
  </script>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</html>
