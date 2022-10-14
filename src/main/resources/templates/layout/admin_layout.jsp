<head>
    <%@include file="./admin_header.jsp"%>
    <title>${title}</title>
</head>
<!--  WRAPPER  -->
<div class="wrapper">
<!-- LEFT MAIN SIDEBAR -->
<div class="ec-left-sidebar ec-bg-sidebar">
    <%@include file="./admin_menu.jsp"%>
</div>
    <!--  PAGE WRAPPER -->
    <div class="ec-content-wrapper">
        <jsp:include page="../pages/${web_content}.jsp"/>
    </div> <!-- End Page Wrapper -->
    <div class="ec-content-wrapper">
        <%@include file="./admin_footer.jsp"%>
    </div>
</div> <!-- End Wrapper -->