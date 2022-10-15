<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <%--styles--%>
    <jsp:include page="admin_header.jsp"/>
</head>
<body class="ec-header-fixed ec-sidebar-fixed ec-sidebar-light ec-header-light" id="body">

<div class="wrapper">
<%--    side bar--%>
    <jsp:include page="admin_menu.jsp"/>
<%--    end--%>

    <div class="ec-page-wrapper">
        <%--    top nav--%>
        <jsp:include page="top_nav.jsp"/>
        <%--    end --%>
        <jsp:include page="main_content.jsp"/>

        <jsp:include page="main_footer.jsp"/>
    </div>

</div>
<%--footer--%>
<jsp:include page="scripts.jsp"/>
</body>
</html>