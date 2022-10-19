<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .bottom-information {
        width: 100% !important;
    }

    div.dataTables_wrapper div.dataTables_info, div.dataTables_wrapper div.dataTables_paginate {
        padding: 0 16px !important;
    }
</style>
<div class="ec-content-wrapper">
    <div class="content">
        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
            <div>
                <h1>List Color</h1>
                <p class="breadcrumbs"><span><a href="home">Home</a></span>
                    <span><i class="mdi mdi-chevron-right"></i></span> List Color</p>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/admin/colors/create" class="btn btn-primary"> Add
                    Color</a>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="card card-default">
                    <div class="card-body">
                        <div class="table-responsive" style="overflow-x: hidden">
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
                                    <td>${color.color_code}</td>
                                    <td>${color.color_name}</td>
                                    <td>${color.state?"Active":"Deleted"}</td>

                                    <td>
                                        <div class="btn-group">
                                            <button type="button"
                                                    class="btn btn-outline-success"><a href="${pageContext.request.contextPath}/admin/colors/${color.color_name}">Info</a>
                                            </button>
                                            <button type="button"
                                                    class="btn btn-outline-success dropdown-toggle dropdown-toggle-split"
                                                    data-bs-toggle="dropdown" aria-haspopup="true"
                                                    aria-expanded="false" data-display="static">
                                                <span class="sr-only">Info</span>
                                            </button>

                                            <div class="dropdown-menu">
                                                <a class="dropdown-item"
                                                   href="${pageContext.request.contextPath}/admin/colors/update/${color.color_name}">Edit</a>
                                                <c:if test="${color.state}">
                                                    <a class="dropdown-item"
                                                       href="${pageContext.request.contextPath}/admin/colors/remove/${color.id}">Delete</a>
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
</div>
<!-- End Content Wrapper -->
