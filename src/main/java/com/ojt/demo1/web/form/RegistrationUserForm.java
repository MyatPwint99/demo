package com.ojt.demo1.web.form;

import lombok.Data;

@Data
public class RegistrationUserForm {
    private String username;
    private String phonenumber;
//    private int gender;
    private String gender;
//    private boolean married;
    private Boolean married;
    private String city;
}
