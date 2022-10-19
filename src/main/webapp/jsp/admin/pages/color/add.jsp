<%@ taglib prefix="spForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ec-content-wrapper">
    <div class="content">
        <div class="breadcrumb-wrapper d-flex align-items-center justify-content-between">
            <div>
                <h1>Add Colors</h1>
                <p class="breadcrumbs"><span><a href="${pageContext.request.contextPath}/home">Home</a></span>
                    <span><i class="mdi mdi-chevron-right"></i></span>Brand</p>
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
                        <h2>Add Colors</h2>
                    </div>

                    <%--@elvariable id="colors" type=""--%>
                    <spForm:form modelAttribute="colors" action="${pageContext.request.contextPath}/admin/colors/create">
                        <div class="form-group row">
                            <spForm:label for="color_name" path="color_name" class="col-12 col-form-label">Color Name</spForm:label>
                            <div class="col-12">
                                <spForm:input path="color_name"  class="form-control here slug-title" type="text"/>
                                <spForm:errors path="color_name"/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <spForm:label for="color_code" path="color_code" class="col-12 col-form-label">Code Color</spForm:label>
                            <div class="col-12">
                                <spForm:input path="color_code" type="color"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <button name="submit" type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </div>
                        </div>
                        </spForm:form>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- End Content -->
</div>
<!-- End Content Wrapper -->
<script>
    let imageUploadInput = document.getElementById("imageUpload")
    let previewer = document.getElementById("img-preview")

    const handleLoadPreview = (event) => {
        let url = URL.createObjectURL(event.target.files[0])
        previewer.setAttribute("src",url)
    }
    imageUploadInput.addEventListener("change", handleLoadPreview)

</script>