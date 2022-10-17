<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spForm" uri="http://www.springframework.org/tags/form" %>
<!-- CONTENT WRAPPER -->
<div class="ec-content-wrapper">
    <div class="content">
        <div class="breadcrumb-wrapper breadcrumb-wrapper-2 breadcrumb-contacts">
            <h1>Color</h1>
            <p class="breadcrumbs"><span><a href="home">Home</a></span>
                <span><i class="mdi mdi-chevron-right"></i></span>Color</p>
        </div>
        <div class="row">
            <div class="col-xl-4 col-lg-12">
                <div class="ec-cat-list card card-default mb-24px">
                    <div class="card-body">
                        <div class="ec-cat-form">
                            <h4>Add New Color</h4>

                            <%--@elvariable id="color" type=""--%>
                            <spForm:form modelAttribute="color" action="${pageContext.request.contextPath}/admin/colors/create">
                                <div class="form-group row">
                                    <label for="color_name" class="col-12 col-form-label">Color Name</label>
                                    <div class="col-12">
                                        <spForm:input path="color_name"  class="form-control here slug-title" type="text"/>
                                        <spForm:errors path="color_name"    />
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="color_code" class="col-12 col-form-label">Code Color</label>
                                    <div class="col-12">
                                        <spForm:input path="color_code" type="color"/>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12">
                                        <button name="submit" type="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                </div>

                            </spForm:form>

                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-8 col-lg-12">
                <div class="ec-cat-list card card-default">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table id="responsive-data-table" class="table">
                                <thead>
                                <tr>
                                    <th>Color Code</th>
                                    <th>Color Name</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach var="color" items="${colors}">
                                    <td>${color.colorCode}</td>
                                    <td>${color.name}</td>
                                    <td>${color.state?"Active":"Deleted"}</td>

                                    <td>
                                        <div class="btn-group">
                                            <button type="button"
                                                    class="btn btn-outline-success"><a href="${pageContext.request.contextPath}/admin/colors/${color.color_slug}">Info</a>
                                            </button>
                                            <button type="button"
                                                    class="btn btn-outline-success dropdown-toggle dropdown-toggle-split"
                                                    data-bs-toggle="dropdown" aria-haspopup="true"
                                                    aria-expanded="false" data-display="static">
                                                <span class="sr-only">Info</span>
                                            </button>

                                            <div class="dropdown-menu">
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/colors/update/${color.color_slug}">Edit</a>
                                                <c:if test="${color.state}">
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/colors/remove/${color.id}">Delete</a>
                                                </c:if>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- End Content -->
</div> <!-- End Content Wrapper -->
