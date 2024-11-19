<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title
</head>
<body>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <c:if test="${isLogOn == true && member.id == 'admin' && member != null}">
                <div class="sb-sidenav-menu-heading">관리자</div>
                <a class="nav-link" href="${contextPath}/member/listMembers.do">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    회원 관리
                </a>

                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                    <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                    레이아웃 설정
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="#">Static Navigation</a>
                        <a class="nav-link" href="#">Light Sidenav</a>
                    </nav>
                </div>
                </c:if>
                <div class="sb-sidenav-menu-heading">Static Pages</div>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                    <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                    Pages
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                    <div class="sb-nav-link-icon"><i class="fa-sharp-duotone fa-solid fa-binary-slash"></i></div>
                    Error
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="${contextPath}/resources/pages/401.html">401 Page</a>
                        <a class="nav-link" href="${contextPath}/resources/pages/404.html">404 Page</a>
                        <a class="nav-link" href="${contextPath}/resources/pages/500.html">500 Page</a>
                    </nav>
                </div>
                <div class="sb-sidenav-menu-heading">Dynamic Page</div>
                <a class="nav-link" href="${contextPath}/resources/pages/charts.html">
                    <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                    Charts
                </a>
                <a class="nav-link" href="${contextPath}/board/listArticles.do">
                    <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                    Board
                </a>
            </div>
        </div>
        <div class="sb-sidenav-footer">
            <c:choose>
                <c:when test="${isLogOn == true && member != null}">
                    <div class="small">Logged in as:</div>
                    ${member.name}
                </c:when>
                <c:otherwise>
                    Log In
                </c:otherwise>
            </c:choose>

        </div>
    </nav>
</div>
</body>
</html>