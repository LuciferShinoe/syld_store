<section class="my_account_area pt--80 pb--55 bg--white">
    <div class="container">

        <div class="row">
            <div class="col-lg-6 col-12">
                <div class="my__account__wrapper">
                    <h3 class="account__title">Register</h3>
                    <form method="post" action="/auth/user/save" modelAttribute="UserClient">
                        <div class="account__form">
                            <div class="input__box">
                                <label>Email address <span>*</span></label>
                                <input type="email" name="Email">
                            </div>
                            <div class="input__box">
                                <label>Username <span>*</span></label>
                                <input type="text" name="UserName">
                            </div>
                            <div class="input__box">
                                <label>Password<span>*</span></label>
                                <input type="password" name="Password">
                            </div>
                            <div class="input__box">
                                <label>RePassword<span>*</span></label>
                                <input type="Password" name="RePassword">
                            </div>
                            <div class="input__box">
                                <label>Address <span>*</span></label>
                                <input type="text" name="Address">
                            </div>
                            <div class="input__box">
                                <label>Number Phone <span>*</span></label>
                                <input type="text" name="NumberPhone">
                            </div>
                            <div class="form__btn">
                                <button type="submit" clas="btn btn-primary btn-block mb-4 g-3">Register</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<script>
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#fileImage').attr('src', e.target.result).width(100);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
</script>