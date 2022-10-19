<div class="ec-left-sidebar ec-bg-sidebar">
    <div id="sidebar" class="sidebar ec-sidebar-footer">

        <div class="ec-brand">
            <a href="index.html" title="Ekka">
                <img class="ec-brand-icon" src="${pageContext.request.contextPath}/assets/admin/img/logo/ec-site-logo.png" alt="" />
                <span class="ec-brand-name text-truncate">Ekka</span>
            </a>
        </div>

        <!-- begin sidebar scrollbar -->
        <div class="ec-navigation" data-simplebar>
            <!-- sidebar menu -->
            <ul class="nav sidebar-inner" id="sidebar-menu">
                <!-- Dashboard -->
                <li class="active">
                    <a class="sidenav-item-link" href="index.html">
                        <i class="mdi mdi-view-dashboard-outline"></i>
                        <span class="nav-text">Dashboard</span>
                    </a>
                    <hr>
                </li>

                <!-- Vendors -->
                <li class="has-sub">
                    <a class="sidenav-item-link" href="javascript:void(0)">
                        <i class="mdi mdi-account-group-outline"></i>
                        <span class="nav-text">Vendors</span> <b class="caret"></b>
                    </a>
                    <div class="collapse">
                        <ul class="sub-menu" id="vendors" data-parent="#sidebar-menu">
                            <li class="">
                                <a class="sidenav-item-link" href="../../../../../../../ekka-admin/vendor-card.html">
                                    <span class="nav-text">Vendor Grid</span>
                                </a>
                            </li>

                            <li class="">
                                <a class="sidenav-item-link" href="../../../../../../../ekka-admin/vendor-list.html">
                                    <span class="nav-text">Vendor List</span>
                                </a>
                            </li>
                            <li class="">
                                <a class="sidenav-item-link" href="../../../../../../../ekka-admin/vendor-profile.html">
                                    <span class="nav-text">Vendors Profile</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>

                <!-- Users -->
                <li class="has-sub">
                    <a class="sidenav-item-link" href="javascript:void(0)">
                        <i class="mdi mdi-account-group"></i>
                        <span class="nav-text">Users</span> <b class="caret"></b>
                    </a>
                    <div class="collapse">
                        <ul class="sub-menu" id="users" data-parent="#sidebar-menu">
                            <li>
                                <a class="sidenav-item-link" href="../../../../../../../ekka-admin/user-card.html">
                                    <span class="nav-text">User Grid</span>
                                </a>
                            </li>

                            <li class="">
                                <a class="sidenav-item-link" href="../../../../../../../ekka-admin/user-list.html">
                                    <span class="nav-text">User List</span>
                                </a>
                            </li>
                            <li class="">
                                <a class="sidenav-item-link" href="../../../../../../../ekka-admin/user-profile.html">
                                    <span class="nav-text">Users Profile</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <hr>
                </li>

                <!-- Category -->
                <li class="has-sub">
                    <a class="sidenav-item-link" href="javascript:void(0)">
                        <i class="mdi mdi-dns-outline"></i>
                        <span class="nav-text">Categories</span> <b class="caret"></b>
                    </a>
                    <div class="collapse">
                        <ul class="sub-menu" id="category" data-parent="#sidebar-menu">
                            <li class="">
                                <a class="sidenav-item-link" href="${pageContext.request.contextPath}/admin/categories/create">
                                    <span class="nav-text">Add Category</span>
                                </a>
                            </li>
                            <li class="">
                                <a class="sidenav-item-link" href="${pageContext.request.contextPath}/admin/categories">
                                    <span class="nav-text">List Category</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>

                <%--Colors--%>
                <li class="has-sub">
                    <a class="sidenav-item-link" href="javascript:void(0)">
                        <i class="mdi mdi-dns-outline"></i>
                        <span class="nav-text">Colors & Size</span> <b class="caret"></b>
                    </a>
                    <div class="collapse">
                        <ul class="sub-menu" id="color" data-parent="#sidebar-menu">
                            <li class="">
                                <a class="sidenav-item-link" href="${pageContext.request.contextPath}/admin/colors">
                                    <span class="nav-text">List Colors</span>
                                </a>
                            </li>
                            <li class="">
                                <a class="sidenav-item-link" href="${pageContext.request.contextPath}/admin/sizes">
                                    <span class="nav-text">List Sizes</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>

                <!-- Products -->
                <li class="has-sub">
                    <a class="sidenav-item-link" href="javascript:void(0)">
                        <i class="mdi mdi-palette-advanced"></i>
                        <span class="nav-text">Products</span> <b class="caret"></b>
                    </a>
                    <div class="collapse">
                        <ul class="sub-menu" id="products" data-parent="#sidebar-menu">
                            <li class="">
                                <a class="sidenav-item-link" href="${pageContext.request.contextPath}/admin/products/create">
                                    <span class="nav-text">Add Product</span>
                                </a>
                            </li>
                            <li class="">
                                <a class="sidenav-item-link" href="${pageContext.request.contextPath}/admin/products">
                                    <span class="nav-text">List Product</span>
                                </a>
                            </li>
                            <li class="">
                                <a class="sidenav-item-link" href="${pageContext.request.contextPath}/admin/products/gird">
                                    <span class="nav-text">Grid Product</span>
                                </a>
                            </li>

                        </ul>
                    </div>
                </li>

                <!-- Orders -->
                <li class="has-sub">
                    <a class="sidenav-item-link" href="javascript:void(0)">
                        <i class="mdi mdi-cart"></i>
                        <span class="nav-text">Orders</span> <b class="caret"></b>
                    </a>
                    <div class="collapse">
                        <ul class="sub-menu" id="orders" data-parent="#sidebar-menu">
                            <li class="">
                                <a class="sidenav-item-link" href="../../../../../../../ekka-admin/new-order.html">
                                    <span class="nav-text">New Order</span>
                                </a>
                            </li>
                            <li class="">
                                <a class="sidenav-item-link" href="../../../../../../../ekka-admin/order-history.html">
                                    <span class="nav-text">Order History</span>
                                </a>
                            </li>
                            <li class="">
                                <a class="sidenav-item-link" href="../../../../../../../ekka-admin/order-detail.html">
                                    <span class="nav-text">Order Detail</span>
                                </a>
                            </li>
                            <li class="">
                                <a class="sidenav-item-link" href="../../../../../../../ekka-admin/invoice.html">
                                    <span class="nav-text">Invoice</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>

                <!-- Reviews -->
                <li>
                    <a class="sidenav-item-link" href="../../../../../../../ekka-admin/review-list.html">
                        <i class="mdi mdi-star-half"></i>
                        <span class="nav-text">Reviews</span>
                    </a>
                </li>

                <!-- Brands -->
                <li>
                    <a class="sidenav-item-link" href="${pageContext.request.contextPath}/admin/brands">
                        <i class="mdi mdi-tag-faces"></i>
                        <span class="nav-text">Brands</span>
                    </a>
                    <hr>
                </li>

                <!-- Authentication -->
                <li class="has-sub">
                    <a class="sidenav-item-link" href="javascript:void(0)">
                        <i class="mdi mdi-login"></i>
                        <span class="nav-text">Authentication</span> <b class="caret"></b>
                    </a>
                    <div class="collapse">
                        <ul class="sub-menu" id="authentication" data-parent="#sidebar-menu">
                            <li class="">
                                <a href="../../../../../../../ekka-admin/sign-in.html">
                                    <span class="nav-text">Sign In</span>
                                </a>
                            </li>
                            <li class="">
                                <a href="../../../../../../../ekka-admin/sign-up.html">
                                    <span class="nav-text">Sign Up</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>

                <!-- Icons -->
                <li class="has-sub">
                    <a class="sidenav-item-link" href="javascript:void(0)">
                        <i class="mdi mdi-diamond-stone"></i>
                        <span class="nav-text">Icons</span> <b class="caret"></b>
                    </a>
                    <div class="collapse">
                        <ul class="sub-menu" id="icons" data-parent="#sidebar-menu">
                            <li class="">
                                <a class="sidenav-item-link" href="../../../../../../../ekka-admin/material-icon.html">
                                    <span class="nav-text">Material Icon</span>
                                </a>
                            </li>
                            <li class="">
                                <a class="sidenav-item-link" href="../../../../../../../ekka-admin/font-awsome-icons.html">
                                    <span class="nav-text">Font Awsome Icon</span>
                                </a>
                            </li>
                            <li class="">
                                <a class="sidenav-item-link" href="../../../../../../../ekka-admin/flag-icon.html">
                                    <span class="nav-text">Flag Icon</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>

                <!-- Other Pages -->
                <li class="has-sub">
                    <a class="sidenav-item-link" href="javascript:void(0)">
                        <i class="mdi mdi-image-filter-none"></i>
                        <span class="nav-text">Other Pages</span> <b class="caret"></b>
                    </a>
                    <div class="collapse">
                        <ul class="sub-menu" id="otherpages" data-parent="#sidebar-menu">
                            <li class="has-sub">
                                <a href="../../../../../../../ekka-admin/404.html">404 Page</a>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
