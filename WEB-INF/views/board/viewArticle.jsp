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
    <title>글보기 창</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"/>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
</head>
<body>
<div class="container px-4 ms-5">
    <h1 class="mt-4">상세글 보기</h1>
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb mb-5">
            <li class="breadcrumb-item"><a href="#">Home</a></li>
            <li class="breadcrumb-item"><a href="${contextPath}/board/listArticles.do">일반게시판</a></li>
            <li class="breadcrumb-item active" aria-current="page">상세글보기</li>
        </ol>
    </nav>

    <form id="frm" method="post" action="${contextPath}/board/modifyArticle.do" enctype="multipart/form-data">
        <input type="hidden" name="articleNO" value="${articleView.articleNO}">
        <div class="row align-items-center">
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">글번호</span>
                <input type="text" class="form-control" aria-describedby="basic-addon1" value="${articleView.articleNO}" readonly>
                <span class="input-group-text" id="basic-addon2">작성일</span>
                <input type="text" class="form-control" aria-describedby="basic-addon2" value="${articleView.writeDate}" readonly>
            </div>
        </div>
        <div class="row align-items-center">
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon3">글제목</span>
                <input name="title" type="text" class="form-control" aria-describedby="basic-addon3" value="${articleView.title}">
            </div>
        </div>
        <!-- 회원인 경우 버튼 활성화 => 변경 : 작성자만 삭제 가능 -->
        <c:choose>
            <c:when test="${isLogOn == true && member != null && member.id == articleView.writer}">
                <div class="row align-items-center mb-3">
                    <textarea name="content" id="ir1" rows="10" class="form-control me-3" aria-describedby="basic-addon4" style="width: 98%;">
                        ${articleView.content}
                    </textarea>
                </div>
            </c:when>
            <c:otherwise>
                <div class="card">
                    <div class="card-body" style="height: 350px;width: 98%;overflow-y: scroll;">${articleView.content}</div>
                </div>
                <!--<div class="row align-items-center mb-3">
                    <textarea name="content" id="ir1" rows="10" class="form-control me-3" aria-describedby="basic-addon4" style="width: 98%;" readonly>
                            ${articleView.content}
                    </textarea>
                </div>-->
            </c:otherwise>
        </c:choose>

        <%-- 파일 다운로드 --%>
        <div class="row align-items-center">
            <c:forEach var="row" items="${fileList}" varStatus="status">
                <c:set var="sts" value="${status.end}"/>
                <!--<input type="hidden" value="${row.IDX}" id="idx">-->
                <div class="input-group my-1">
                    <span class="input-group-text" id="basic-addon5">첨부파일</span>
                    <label class="card card-body pb-0">
                        <p>
                            <a href="${contextPath}/common/downloadFile.do?idx=${row.IDX}">${row.ORIGINALFILENAME}</a> (${row.FILE_SIZE}Byte)
                            <!-- 회원인 경우 버튼 활성화 => 변경 : 작성자만 삭제 가능 -->
                            <c:if test="${isLogOn == true && member != null && member.id == articleView.writer}">
                            <a href="${contextPath}/common/deleteFile.do?idx=${row.IDX}&articleNO=${articleView.articleNO}">
                                <button class="btn btn-outline-danger btn-sm align-items-end" type="button">삭제하기</button>
                            </a>
                            </c:if>
                        </p>
                    </label>
                </div>
            </c:forEach>
        </div>

        <div id="d_file"></div>

        <div class="d-grid gap-2 d-md-flex justify-content-md-center mt-3">
            <!-- 회원인 경우 버튼 활성화 => 변경 : 작성자만 파일 변경 가능 -->
            <c:if test="${isLogOn == true && member != null && member.id == articleView.writer}">
            <button class="btn btn-info me-md-2" type="button" onclick="file_add()">파일추가</button>
            <button id="savebutton" class="btn btn-success me-md-2" type="button">수정하기</button>
            <a href="${contextPath}/board/removeArticle.do?articleNO=${articleView.articleNO}">
                <button class="btn btn-danger" type="button">삭제하기</button>
            </a>
            </c:if>
            <a href="${contextPath}/board/listArticles.do">
                <button class="btn btn-secondary" type="button">목록보기</button>
            </a>
        </div>
    </form>

    <script type="text/javascript">
        // file 태그 추가
        var cnt = ${sts} + 1;
        function file_add() {
            $("#d_file").append("<div class='input-group my-1'>"
                + " <input id='file_" + cnt + "'name='file_" + cnt + "' type='file' class='form-control' id='inputGroupFile01' aria-describedby='inputGroupFileAddon01' aria-label='Upload'>"
                + " <button id='count_"+ cnt +"' class='btn btn-outline-secondary' type='button' id='inputGroupFileAddon01' onclick='delUploadDiv("+cnt+")'>삭제하기</button> </div>"
            );
            cnt++;
        }
        function delUploadDiv(cnt) {
            $("#file_" + cnt + "").remove();
            $("#count_" + cnt + "").remove();
            cnt--;
        }
        // Smart Editor
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

            // 전송버트 클릭 이벤트
            $("#savebutton").click(function () {
                // id가 ir1인 textarea에 에디터에서 대입
                oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);

                // 이부분에 에디터 validation 검증

                // form submit
                $("#frm").submit();
            })
        })
    </script>

    <%-- 수정(ver3) --%>
    <!-- 댓글 기능 구현 -->
    <div class="container mt-2">
        <div class="h4 pb-2 ps-2 text-primary-emphasis border-bottom border-danger">
            Comment
        </div>

        <!-- 댓글 작성 기능 -->
        <form method="get" action="${contextPath}/comment/addComment.do">
            <input type="hidden" name="parentNO" value="${parentNO}" />
            <div class="row bg-secondary-subtle mx-1 mb-0 border border-black mt-2 p-2">
                <div class="col">
                    <h6><b>댓글 작성 창</b></h6>
                </div>
                <div class="col">
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <button class="btn btn-outline-primary btn-sm" type="submit">댓글저장</button>
                    </div>
                </div>
            </div>
            <div class="row mx-1 mt-0">
                <textarea class="border-top-0" name="content" class="form-control" rows="4"></textarea>
            </div>
        </form>
        <hr/>

        <!-- 댓글 리스트 -->
        <c:forEach var="comment" items="${commentList}">
            <!-- 댓글 리스트 -->
            <form method="get" action="${contextPath}/comment/modifyComment.do">
                <input type="hidden" name="commentNO" value="${comment.commentNO}" />
                <input type="hidden" name="parentNO" value="${parentNO}" />
                <div class="row bg-warning-subtle mx-1 mb-0 p-2 border border-black">
                    <div class="col">
                        <h6><b>댓글번호 : ${comment.commentNO}&nbsp;(작성일 : ${comment.writeDate})</b></h6>
                    </div>
                    <div class="col">
                        <!-- 회원인 경우 버튼 활성화 => 변경 : 글 작성자만 변경 가능 -->
                        <c:if test="${isLogOn == true && member != null && member.id == articleView.writer}">
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <button class="btn btn-outline-success me-md-2 btn-sm" type="submit">수정</button>
                            <a href="${contextPath}/comment/removeCommnet.do?commentNO=${comment.commentNO}&parentNO=${parentNO}">
                                <button class="btn btn-outline-danger btn-sm" type="button">삭제</button>
                            </a>
                        </div>
                        </c:if>
                    </div>
                </div>
                <div class="row mx-1 mt-0 mb-2">
                    <textarea class="border-top-0" name="content" class="form-control" rows="3">${comment.content}</textarea>
                </div>
            </form>
        </c:forEach>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</html>
