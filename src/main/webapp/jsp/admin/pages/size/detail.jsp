<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ec-content-wrapper">
    <div class="content">
        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
            <div>
                <h1>Product Detail</h1>
                <p class="breadcrumbs"><span><a href="index.html">Home</a></span>
                    <span><i class="mdi mdi-chevron-right"></i></span>Category
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
                        <h2>Product Detail</h2>
                    </div>

                    <div class="card-body product-detail">
                        <div class="row">
                            <div class="col-xl-5 col-lg-6">
                                <div class="row product-overview">
                                    <div class="col-12 col-md-6">
                                        <label class="form-label">Category name</label>
                                        <input class="form-control slug-title"
                                               value=" ${size_detail.size_name}" readonly>
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
