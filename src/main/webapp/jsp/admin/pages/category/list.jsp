<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .bottom-information {
        width: 100% !important;
    }

    div.dataTables_wrapper div.dataTables_info, div.dataTables_wrapper div.dataTables_paginate {
        padding: 0 16px!important;
    }
</style>
<div class="ec-content-wrapper">
    <div class="content">
        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
            <div>
                <h1>Category</h1>
                <p class="breadcrumbs"><span><a href="index.html">Home</a></span>
                    <span><i class="mdi mdi-chevron-right"></i></span>Category</p>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/admin/categories/create" class="btn btn-primary"> Add
                    Category</a>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="card card-default">
                    <div class="card-body">
                        <div class="table-responsive" style="overflow-x: hidden">
                            <table id="responsive-data-table" class="table"
                                   style="width:100%">
                                <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Name</th>
                                    <th>Slug</th>
                                    <th>State</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="category" items="${categories}">
                                    <tr>
                                        <td><img style="box-shadow: 1px 1px 6px rgba(0,0,0,0.2)" class="tbl-thumb"
                                                 src="${pageContext.request.contextPath}${category.category_thumbnail}"
                                                 alt="Product Image"/></td>
                                        <td>${category.category_name}</td>
                                        <td>${category.category_slug}</td>
                                        <td style="color: ${category.state?'blue':'red'}">${category.state?"Active":"Deleted"}</td>
                                        <td>
                                            <div class="btn-group mb-1">
                                                <button type="button"
                                                        class="btn btn-outline-success"><a href="${pageContext.request.contextPath}/admin/categories/${category.category_slug}">Info</a>
                                                </button>
                                                <button type="button"
                                                        class="btn btn-outline-success dropdown-toggle dropdown-toggle-split"
                                                        data-bs-toggle="dropdown" aria-haspopup="true"
                                                        aria-expanded="false" data-display="static">
                                                    <span class="sr-only">Info</span>
                                                </button>

                                                <div class="dropdown-menu">
                                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/categories/update/${category.category_slug}">Edit</a>
                                                    <c:if test="${category.state}">
                                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/categories/remove/${category.id}">Delete</a>
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

