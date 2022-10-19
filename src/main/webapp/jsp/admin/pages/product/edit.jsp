<%@ taglib prefix="spForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .form-control {
        border: 2px solid #ced4da !important;
    }

    .colors input {
        width: 43px;
        height: 46px;
        border: none !important;
    }

    .sizes {
        height: 46px;
    }

    .sizes div {
        height: 0 !important;
    }

    .ec-vendor-uploads .thumb-upload {
        border-radius: 8px !important;
        padding: 0 !important;
        box-shadow: 0 0 8px rgba(0, 0, 0, 0.6) !important;
        overflow: hidden;
    }

    .avatar-preview {
        padding: 0 !important;
        transition: .2s ease;
        transform: scale(1.1);
    }

    .thumb-preview {
        padding: 0 !important;
        transform: scale(1.1);
    }

    .image-thumb-preview {
        object-fit: cover;

    }

    .avatar-upload {
        padding: 0 !important;
        overflow: hidden;
        transition: .2s ease;
        border-radius: 8px !important;
        box-shadow: 0 0 8px rgba(0, 0, 0, 0.6) !important;
    }

    .avatar-upload:hover .avatar-preview {
        transform: scale(1.2);
        transition: .2s ease;

    }
</style>
<div class="ec-content-wrapper">
    <div class="content">
        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
            <div>
                <h1>Add Product</h1>
                <p class="breadcrumbs"><span><a href="${pageContext.request.contextPath}/admin">Home</a></span>
                    <span><i class="mdi mdi-chevron-right"></i></span>Product</p>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/admin/products" class="btn btn-primary"> View All
                </a>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="card card-default">
                    <div class="card-header card-header-border-bottom">
                        <h2>Add Product</h2>
                    </div>

                    <div class="card-body">
                        <%--@elvariable id="product" type=""--%>
                        <spForm:form class="row ec-vendor-uploads" action="${pageContext.request.contextPath}/admin/products/update" modelAttribute="product" enctype="multipart/form-data">
                            <spForm:input path="id" hidden="hidden" value="${single_product.id}"/>
                            <div class="col-lg-4">
                                <div class="ec-vendor-img-upload">
                                    <div class="ec-vendor-main-img">
                                        <div class="avatar-upload">
                                            <div class="avatar-edit">
                                                <spForm:input path="update_images" id="${single_product.images_con[0].id}"/>
                                                <spForm:input path="files" type='file' data-id="${single_product.images_con[0].id}"
                                                              cssStyle="border: 2px solid #ced4da!important;"
                                                              id="imageUpload" cssClass="ec-image-upload upload-image"
                                                              accept=".png, .jpg, .jpeg"/>
                                                <label for="imageUpload"><img
                                                        src="${pageContext.request.contextPath}/assets/admin/img/icons/edit.svg"
                                                        class="svg_img header_svg" alt="edit"/></label>
                                            </div>
                                            <div class="avatar-preview ec-preview">
                                                <div class="imagePreview ec-div-preview">
                                                    <img class="ec-image-preview"
                                                         src="${pageContext.request.contextPath}${single_product.images_con[0].path}"
                                                         alt="edit"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="thumb-upload-set colo-md-12">
                                            <c:forEach var="image" varStatus="loop" items="${single_product.images_con}">
                                                <c:if test="${loop.index > 0}">
                                                    <div class="thumb-upload">
                                                        <div class="thumb-edit">

                                                            <spForm:input path="update_images" id="${image.id}" hidden="hidden"/>

                                                            <spForm:input path="files" type='file' data-id="${image.id}" id="thumbUpload${loop.index}"
                                                                          cssClass="ec-image-upload upload-image"
                                                                          accept=".png, .jpg, .jpeg"/>
                                                            <label for="imageUpload"><img
                                                                    src="${pageContext.request.contextPath}/assets/admin/img/icons/edit.svg"
                                                                    class="svg_img header_svg" alt="edit"/></label>
                                                        </div>
                                                        <div class="thumb-preview ec-preview">
                                                            <div class="image-thumb-preview">
                                                                <img class="image-thumb-preview ec-image-preview"
                                                                     style="object-fit: cover"
                                                                     src="${pageContext.request.contextPath}${image.path}"
                                                                     alt="edit"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-8">
                                <div class="ec-vendor-upload-detail row">
                                    <div class="col-md-6">
                                        <spForm:label path="product_name"
                                                      class="form-label">Product name</spForm:label>
                                        <spForm:input path="product_name" cssClass="form-control slug-title"
                                                      cssStyle="border: 2px solid #ced4da!important"
                                                      required="true" value="${single_product.product_name}"/>
                                        <spForm:errors path="product_name" cssStyle="color: red;font-size: 12px;"/>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label">Select Categories</label>
                                        <spForm:select path="category_id"
                                                       cssStyle="border: 2px solid #ced4da!important;"
                                                       cssClass="form-select">
                                            <c:forEach var="category_parent" items="${categories}">
                                                <optgroup label="${category_parent.parent.category_name}">
                                                    <c:forEach var="child" items="${category_parent.children}">
                                                        <option value="${child.id}" ${child.id == single_product.category.id ? "selected='selected'":""}>${child.category_name}</option>
                                                    </c:forEach>
                                                </optgroup>
                                            </c:forEach>
                                        </spForm:select>
                                    </div>

                                    <div class="col-md-6">
                                        <spForm:label path="slug"
                                                      cssClass="col-12 col-form-label">Slug</spForm:label>
                                        <div class="col-12">
                                            <spForm:input path="slug"
                                                          cssStyle="border: 2px solid #ced4da!important;"
                                                          cssClass="form-control here set-slug" type="text"
                                                          required="true" value="${single_product.slug}"/>
                                            <spForm:errors path="slug" cssStyle="color: red;font-size: 12px;"/>

                                        </div>
                                    </div>
                                    <div class="col-md-6 mt-3">
                                        <label class="form-label">Select Brand</label>
                                        <spForm:select path="brand_id"
                                                       cssStyle="border: 2px solid #ced4da!important;"
                                                       cssClass="form-select">
                                            <c:forEach var="brand" items="${brands}">
                                                <option value="${brand.id}" ${brand.id == single_product.brand.id ? "selected='selected'":''}>${brand.brand_name}</option>
                                            </c:forEach>
                                        </spForm:select>
                                    </div>
                                    <div class="col-md-12">
                                        <spForm:label path="product_desc"
                                                      class="form-label">Sort Description</spForm:label>
                                        <spForm:input path="product_desc" id="product_desc" hidden="hidden"/>
                                        <textarea path="product_desc" id="desc" cssClass="form-control"
                                                         cssStyle="border: 2px solid #ced4da!important;" rows="2"
                                                  required="true"></textarea>
                                    </div>
                                        <%--                                        colors--%>
                                    <div class="col-md-4 mb-25  mt-3">
                                        <label class="form-label">Colors</label>
                                        <div class="row justify-content-evenly align-items-center colors">
                                            <c:forEach var="color" items="${single_product.colors_con}">
                                                <spForm:input path="colors" type="color"
                                                              class="form-control form-control-color"
                                                              id="exampleColorInput1" value="${color.color_code}"
                                                              title="Choose your color" />
                                            </c:forEach>
                                        </div>
                                    </div>

                                    <div class="col-md-8 mb-25  mt-3">
                                        <spForm:label path="sizes" class="form-label">Size</spForm:label>
                                        <div class="form-checkbox-box d-flex justify-content-start align-items-center sizes">
                                            <c:forEach var="size" items="${sizes}" varStatus="loop">
                                                <c:forEach var="size_in" items="${single_product.sizes}">
                                                    <c:if test="${size.id == size_in.id}">
                                                        <c:set var="checked_id" scope="session" value="${size_in.id}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <div class="form-check form-check-inline ">
                                                    <c:if test="${checked_id == size.id}">
                                                        <spForm:checkbox path="sizes" value="${size.size_name}"
                                                                         checked="checked"/>
                                                        <label>${size.size_name}</label>
                                                    </c:if>
                                                    <c:if test="${checked_id != size.id}">
                                                        <spForm:checkbox path="sizes" value="${size.size_name}"/>
                                                        <label>${size.size_name}</label>
                                                    </c:if>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>

                                    <div class="col-md-6">
                                        <label class="form-label">Price <span>( In USD )</span></label>
                                        <spForm:input path="product_price" type="number" min="1" value="${single_product.product_price}"
                                                      cssStyle="border: 2px solid #ced4da!important;"
                                                      cssClass="form-control" required="true"/>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="form-label">Quantity</label>
                                        <spForm:input path="product_quantity" type="number" min="1" value="${single_product.product_quantity}"
                                                      cssStyle="border: 2px solid #ced4da!important;"
                                                      cssClass="form-control" required="true"/>
                                    </div>
                                    <div class="col-md-12 my-3">
                                        <label class="form-label">Full Detail</label>
                                        <spForm:input path="product_detail" id="detail" hidden="hidden"/>
                                        <textarea id="edit_detail"
                                                  cssStyle="border: 2px solid #ced4da!important;"
                                                  cssClass="form-control" rows="4"
                                                  required="true"></textarea>
                                    </div>
                                    <div class="col-md-12 mb-3">
                                        <label class="form-label">Product Tags <span>( Type and
																make comma to separate tags )</span></label>
                                        <c:set var="tags" value=""/>
                                        <c:forEach var="tag" items="${single_product.tags}">
                                            <c:set var="tag_name" scope="request" value="${tag.tag_name}"/>
                                            <c:set var="tags" value="${tags + ',' + tag_name}"/>
                                            <h1>${tag_name}</h1>
                                        </c:forEach>
                                        <spForm:input path="group_tag"
                                                      cssStyle="border: 2px solid #ced4da!important;"
                                                      required="true" type="text" cssClass="form-control"
                                                      name="group_tag" value="${tags}"
                                                      placeholder="Type tab name here and it auto create!"/>
                                    </div>
                                    <div class="col-md-12">
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                </div>
                            </div>
                        </spForm:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("product_name").addEventListener("input",(e)=>{
        e.preventDefault();
    })
    CKEDITOR.replace('edit_detail',{
        on:{
            contentDom: function() {
                this.editable().on( 'input', function( evt ) {
                    console.log(document.getElementById("detail"))
                    document.getElementById('detail').value = CKEDITOR.instances.edit_detail.getData();
                } );
            }
        }
    })
    CKEDITOR.replace('desc',{
        on:{
            contentDom: function() {
                this.editable().on( 'input', function( evt ) {
                    console.log(document.getElementById("product_desc"))
                    document.getElementById('product_desc').value = CKEDITOR.instances.desc.getData();
                } );
            }
        }
    })
    CKEDITOR.instances['edit_detail'].setData('${single_product.product_detail}')
    CKEDITOR.instances['desc'].setData('${single_product.product_desc}')

    const handleSetup = e => {
        let input = document.getElementById(e.target.dataset.id)
        let fileName = e.target.files[0].name
        let value = e.target.dataset.id + "--" + fileName
        input.value = value;
        console.log(input)
    }

    let uploadInput = document.querySelectorAll(".upload-image");

    console.log(uploadInput)
    Array.from(uploadInput).forEach((val,index) => {
        val.addEventListener('change',handleSetup)
    })
</script>

