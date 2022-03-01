package com.orphan.common.constants;

public class ValidationConstants {
    public static final String IDENTIFICATION_PATTERN = "Số CMND không đúng định dạng";
    public static final String EMAIL_PATTERN = "Email không đúng định dạng";
    public static final String PHONE_PATTERN = "Số điện thoại không đúng định dạng";
    public static final String PASSWORD_PATTERN="Password cần độ dài từ 8 - 15 kí tự," +
           "Có ít nhất 1 kí tự thường, 1 kí tự viết hoa và 1 chữ số," +
           "Có 1 trong các kí tự đặc biệt sau (! # $ @ _ + , ? . - )";
    public static final String EMAIL_CONFLICT="Email đã được sử dụng";
    public static final String IDENTIFICATION_CONFLICT="Số CMND đã được sử dụng";
    public static final String EMAIL_NOTEMPTY="Email không được để trống";
    public static final String PHONE_NOTEMPTY="Số điện thoại không được để trống";
    public static final String IDENTIFICATION_NOTEMPTY="Số CMND không được để trống";
}
