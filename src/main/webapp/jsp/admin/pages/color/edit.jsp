<%@ taglib prefix="spForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spFOrm" uri="http://www.springframework.org/tags/form" %>
<div class="ec-content-wrapper">
    <div class="content">
        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
            <div>
                <h1>Edit Color</h1>
                <p class="breadcrumbs"><span><a href="${pageContext.request.contextPath}/home">Home</a></span>
                    <span><i class="mdi mdi-chevron-right"></i></span>Color</p>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/admin/colors" class="btn btn-primary"> View All
                </a>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="card card-default">
                    <div class="card-header card-header-border-bottom">
                        <h2>Edit Color</h2>
                    </div>

                    <%--@elvariable id="color_edit" type=""--%>
                    <spForm:form
                                 action="${pageContext.request.contextPath}/admin/colors/update" method="post"
                                 modelAttribute="color_edit">
                    <div class="card-body">
                        <div class="row ec-vendor-uploads">
                            <div class="col-lg-4">
                                <div class="ec-vendor-upload-detail">
                                    <div class="row g-3">
                                        <div class="col-md-12 mb-3">
                                            <spForm:input hidden="hidden" path="id" type="text"
                                                          class="form-control slug-title" value="${color.id}"/>
                                            <spForm:label path="color_name"
                                                          class="form-label">Color Name</spForm:label>
                                            <spForm:input path="color_name" type="text"
                                                          class="form-control slug-title" required="true"/>
                                            <spForm:errors path="color_code" cssStyle="color: red;font-size: 12px"/>
                                            <button type="submit" class="btn btn-block  mt-3 btn-primary">Submit</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                </spForm:form>
            </div>
        </div>
    </div>
</div>
