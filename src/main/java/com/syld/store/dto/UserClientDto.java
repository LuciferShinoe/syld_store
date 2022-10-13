package com.syld.store.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class UserClientDto {
    Long id;
    @NotEmpty(message = "Không được để trống trường email")
    @Email(message = "Định dạng email")
    String email;
    @NotEmpty(message = "Không được để trống trường username !")
    String username;
    String address;
    String role_name = "role_user";
    @NotEmpty(message = "Không được để trống trường mật khẩu !")
    String password;
    @NotEmpty(message ="Không được để trống trường nhâp lại mật khẩu !")
    String rePassword;
    String phoneNumber;
}
