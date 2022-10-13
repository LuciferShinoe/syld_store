<%@ taglib prefix="spForm" uri="http://www.springframework.org/tags/form" %>
<section class="my_account_area pt--80 pb--55 bg--white">
    <div class="container">
        <div class="row">
            <div class="col-4 "></div>
            <div class="col-5 ">
                <div class="my__account__wrapper" >
                    <h3 class="account__title" >Login</h3>
                    <p style="color: red" class="lead fw-normal mb-0 me-3">${message}</p> <br>
                    <%--@elvariable id="user" type=""--%>
                    <spForm:form action="login" method="post" modelAttribute="user">
                        <div class="account__form">
                            <div class="input__box">
                                <label>Username or email address <span>*</span></label>
                                <spForm:input path="email" type="email"/>
                                <spForm:errors path="email"/>
                            </div>
                            <div class="input__box">
                                <label>Password<span>*</span></label>
                                <spForm:input path="password" type="password"/>
                                <spForm:errors path="password"/>
                            </div>
                            <div class="form__btn">
                                <button>Login</button>
                                <label class="label-for-checkbox">
                                    <input id="rememberme" class="input-checkbox" name="rememberme"
                                           value="forever" type="checkbox">
                                    <span>Remember me</span>
                                </label>
                            </div>
                            <a class="forget_pass" href="#">Lost your password?</a>
                            <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? </p><a class="Register" href="register">Register.</a>
                        </div>
                    </spForm:form>
                </div>
            </div>
        </div>
        <div class="col-4 "></div>
    </div>
</section>