<%@ taglib prefix="spForm" uri="http://www.springframework.org/tags/form" %>
<style>
    .disabled{
        background-color: rgba(0,0,0,0.1)!important;
        border-color: rgba(0,0,0,0.1) !important;
        color: rgba(0,0,0,0.1) !important;
    }

</style>
<section class="my_account_area pt--80 pb--55 bg--white">
    <div class="container">
        <div class="row">
            <div class="col-4 "></div>
            <div class="col-5 ">
                <div class="my__account__wrapper" >
                    <h3 class="account__title" >Login</h3>
                    <p style="color: red" class="lead fw-normal mb-0 me-3">${message}</p> <br>
                    <%--@elvariable id="user" type=""--%>
                    <spForm:form action="login" id="loginForm" method="post" modelAttribute="user">
                        <div class="account__form">
                            <div class="input__box">
                                <label>Username or email address <span>*</span></label>
                                <spForm:input path="email" id="email" type="email"/>
                                <spForm:errors path="email"/>
                                <span id="email_err" style="color: red;font-size: 12px"></span>
                            </div>
                            <div class="input__box">
                                <label>Password<span>*</span></label>
                                <spForm:input id="password" path="password" type="password"/>
                                <spForm:errors path="password"/>
                                <span id="password_err" style="color: red;font-size: 12px"></span>
                            </div>
                            <div class="form__btn">
                                <button id="sendBtn">Login</button>
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
<script>

    let emailValid = document.getElementById("email")
    let passwordValid = document.getElementById("password")
    let emailValid_ = false;
    let passwordValid_ = false;
    let isValid = false

    document.getElementById("loginForm").onsubmit = (e)=>{
        if (!isValid){
            e.preventDefault()
        }else {
            e.preventDefault()

            $.ajax({
                url:"${pageContext.request.contextPath}/auth/valid_email",
                method:"post",
                data:JSON.stringify(document.getElementById("email").value.trim()),
                success:(res)=>{
                    console.log(res)
                }
            })
        }
    }

    const sendBtn = document.getElementById("sendBtn").onclick = (e)=>{
        if (document.getElementById("email").value === ""){
            document.getElementById("email_err").textContent = "Please fill this field."
        }
        if (document.getElementById("password").value === ""){
            document.getElementById("password_err").textContent = "Please fill this field."
        }
    }

    const handleValidEmail = (event) => {

        let value = event.target.value
        if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(value)){
            emailValid_ = false
            document.getElementById("email_err").textContent = "Email Incorrect Format!"
        }else {
            emailValid_ = true
            isValid = emailValid_ && passwordValid_
            document.getElementById("email_err").textContent = ""
        }
    }
    const handleValidPassword = (event) => {

        let value = event.target.value
        if (value.length < 6){
            passwordValid_ = false
            document.getElementById("password_err").textContent = "Password length must more than 6"
        }else {
            passwordValid_ = true
            isValid = emailValid_ && passwordValid_
            document.getElementById("password_err").textContent = ""
        }
    }
    emailValid.addEventListener("input",handleValidEmail)
    passwordValid.addEventListener("input",handleValidPassword)
</script>