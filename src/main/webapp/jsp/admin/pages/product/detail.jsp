<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ec-content-wrapper">
    <div class="content">
        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
            <div>
                <h1>Product Detail</h1>
                <p class="breadcrumbs"><span><a href="index.html">Home</a></span>
                    <span><i class="mdi mdi-chevron-right"></i></span>Product
                </p>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/admin/products/update/${single_product.id}" class="btn btn-primary"> Edit
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
                            <div class="col-xl-4 col-lg-6">
                                <div class="row">
                                    <div class="single-pro-img">
                                        <div class="single-product-scroll">
                                            <div class="single-product-cover">
                                                <c:forEach var="image" items="${single_product.thumbnails}">
                                                    <div class="single-slide zoom-image-hover">
                                                        <img class="img-responsive"
                                                             src="${pageContext.request.contextPath}${image.path}" alt="">
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div class="single-nav-thumb">
                                                <c:forEach var="image" items="${single_product.thumbnails}">
                                                    <div class="single-slide">
                                                        <img class="img-responsive"
                                                             src="${pageContext.request.contextPath}${image.path}" alt="">
                                                    </div>
                                                </c:forEach>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-5 col-lg-6">
                                <div class="row product-overview">
                                    <div class="col-12">
                                        <h5 class="product-title">${single_product.product_name}</h5>
                                        <p class="product-rate">
                                            <i class="mdi mdi-star is-rated"></i>
                                            <i class="mdi mdi-star is-rated"></i>
                                            <i class="mdi mdi-star is-rated"></i>
                                            <i class="mdi mdi-star is-rated"></i>
                                            <i class="mdi mdi-star"></i>
                                        </p>
                                        <div>
                                            ${single_product.product_desc}
                                        </div>

                                        <div class="ec-ofr">
                                            <h6>Available offers</h6>
                                            <ul>
                                                <li><b>Special Price :</b> Get extra 16% off (price
                                                    inclusive of discount) <a href="#">T&C</a> </li>
                                                <li><b>Bank Offer :</b> 10% off on XYZ Bank Cards, up to
                                                    $12. On orders of $200 and above <a href="#">T&C</a>
                                                </li>
                                                <li><b>Bank Offer :</b> 5% Unlimited Cashback on Ekka XYZ
                                                    Bank Credit Card <a href="#">T&C</a></li>
                                                <li><b>Bank Offer :</b> Flat $50 off on first Ekka Pay Later
                                                    order of $200 and above <a href="#">T&C</a></li>
                                            </ul>
                                        </div>

                                        <p class="product-price">Price: $${single_product.product_price}</p>
                                        <p class="product-sku">Tag:
                                            <c:forEach var="tag" items="${single_product.tags}">
                                                <span class="btn btn-outline-primary">${tag.tag_name}</span>
                                            </c:forEach>
                                        </p>
                                        <ul class="product-size">
                                            <c:forEach var="size" items="${single_product.sizes}">
                                                <li class="size"><span>${size.size_name}</span></li>
                                            </c:forEach>
                                        </ul>
                                        <ul class="product-color">
                                            <c:forEach var="color" items="${single_product.colors}">
                                                <li class="color"><span
                                                        style="background-color:${color.color_code};"></span></li>
                                            </c:forEach>

                                        </ul>
                                        <div class="product-stock">
                                            <div class="stock">
                                                <p class="title">Available</p>
                                                <p class="text">${single_product.product_quantity}</p>
                                            </div>
                                            <div class="stock">
                                                <p class="title">Pending</p>
                                                <p class="text">50</p>
                                            </div>
                                            <div class="stock">
                                                <p class="title">InOrder</p>
                                                <p class="text">20</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row review-rating mt-4">
                            <div class="col-12">
                                <ul class="nav nav-tabs" id="myRatingTab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active"
                                           id="product-detail-tab" data-bs-toggle="tab"
                                           data-bs-target="#productdetail" href="#productdetail" role="tab"
                                           aria-selected="true">
                                            <i class="mdi mdi-library-books mr-1"></i> Detail</a>
                                    </li>

                                    <li class="nav-item">
                                        <a class="nav-link"
                                           id="product-information-tab" data-bs-toggle="tab"
                                           data-bs-target="#productinformation" href="#productinformation"
                                           role="tab" aria-selected="false">
                                            <i class="mdi mdi-information mr-1"></i>Info</a>
                                    </li>

                                    <li class="nav-item">
                                        <a class="nav-link"
                                           id="product-reviews-tab" data-bs-toggle="tab"
                                           data-bs-target="#productreviews" href="#productreviews"
                                           role="tab" aria-selected="false">
                                            <i class="mdi mdi-star-half mr-1"></i> Reviews</a>
                                    </li>
                                </ul>
                                <div class="tab-content" id="myTabContent2">
                                    <div class="tab-pane pt-3 fade show active" id="productdetail"
                                         role="tabpanel">
                                        ${single_product.product_detail}
                                    </div>

                                    <div class="tab-pane pt-3 fade" id="productinformation" role="tabpanel">
                                        <ul>
                                            <li><span>Weight</span> 1000 g</li>
                                            <li><span>Dimensions</span> 35 × 30 × 7 cm</li>
                                            <li><span>Color</span> Black, Pink, Red, White</li>
                                        </ul>
                                    </div>

                                    <div class="tab-pane pt-3 fade" id="productreviews" role="tabpanel">
                                        <div class="ec-t-review-wrapper">
                                            <div class="ec-t-review-item">
                                                <div class="ec-t-review-avtar">
                                                    <img src="assets/img/review-image/1.jpg" alt="">
                                                </div>
                                                <div class="ec-t-review-content">
                                                    <div class="ec-t-review-top">
                                                        <p class="ec-t-review-name">Jeny Doe</p>
                                                        <div class="ec-t-rate">
                                                            <i class="mdi mdi-star is-rated"></i>
                                                            <i class="mdi mdi-star is-rated"></i>
                                                            <i class="mdi mdi-star is-rated"></i>
                                                            <i class="mdi mdi-star is-rated"></i>
                                                            <i class="mdi mdi-star"></i>
                                                        </div>
                                                    </div>
                                                    <div class="ec-t-review-bottom">
                                                        <p>Lorem Ipsum is simply dummy text of the printing
                                                            and
                                                            typesetting industry.
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="ec-t-review-item">
                                                <div class="ec-t-review-avtar">
                                                    <img src="assets/img/review-image/2.jpg" alt="">
                                                </div>
                                                <div class="ec-t-review-content">
                                                    <div class="ec-t-review-top">
                                                        <p class="ec-t-review-name">Linda Morgus</p>
                                                        <div class="ec-t-rate">
                                                            <i class="mdi mdi-star is-rated"></i>
                                                            <i class="mdi mdi-star is-rated"></i>
                                                            <i class="mdi mdi-star is-rated"></i>
                                                            <i class="mdi mdi-star is-rated"></i>
                                                            <i class="mdi mdi-star"></i>
                                                        </div>
                                                    </div>
                                                    <div class="ec-t-review-bottom">
                                                        <p>Lorem Ipsum is simply dummy text of the printing
                                                            and
                                                            typesetting industry.
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- End Content -->
</div> <!-- End Content Wrapper -->
