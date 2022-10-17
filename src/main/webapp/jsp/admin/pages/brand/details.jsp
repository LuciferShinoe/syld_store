<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ec-content-wrapper">
    <div class="content">
        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
            <div>
                <h1>Brand Detail</h1>
                <p class="breadcrumbs"><span><a href="index.html">Home</a></span>
                    <span><i class="mdi mdi-chevron-right"></i></span>Brand
                </p>
            </div>
            <div>
                <a href="product-list.html" class="btn btn-primary"> View All
                </a>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="card card-default">
                    <div class="card-header card-header-border-bottom">
                        <h2>Brand Detail</h2>
                    </div>

                    <div class="card-body product-detail">
                        <div class="row">
                            <div class="col-xl-4 col-lg-6">
                                <div class="row">
                                    <div class="single-pro-img">
                                        <div class="single-product-scroll">
                                            <div class="single-product-cover">
                                                <div class="single-slide zoom-image-hover">
                                                    <img class="img-responsive"
                                                         src="${pageContext.request.contextPath}${brand_detail.brand_lo}"
                                                         alt="">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-5 col-lg-6">
                                <div class="row product-overview">
                                    <div class="col-12 col-md-6">
                                        <label class="form-label">Category name</label>
                                        <input class="form-control slug-title"
                                               value=" ${category_detail.category_name}" readonly>

                                    </div>
                                    <div class="col-12 col-md-6">
                                        <label class="form-label">Category slug</label>
                                        <input class="form-control slug-title"
                                               value=" ${category_detail.category_slug}" readonly>

                                    </div>
                                    <div class="col-12 col-md-6 mt-3">
                                        <label class="form-label">Parent</label>
                                        <c:if test="${category_detail.parent_id == 'parent'}">
                                            <input class="form-control slug-title"
                                                   value="${category_detail.category_name}" readonly>
                                        </c:if>
                                        <c:if test="${category_detail.parent_id != 'parent'}">
                                            <input class="form-control slug-title"
                                                   value="${parent.category_name}" readonly>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- End Content -->
</div>
<!-- End Content Wrapper -->
