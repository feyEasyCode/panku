package com.panku.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: uaike
 * @create: 2020-11-25
 */
@Getter
@AllArgsConstructor
public enum RegexEnum {

    /**
     * email
     */
    REGULAR_EMAIL("email","regular.email.filter"),
    /**
     * mobile
     */
    REGULAR_MOBILE("mobile","regular.mobile.filter"),
    /**
     * BASESITR
     */
    REGULAR_BASESITE("baseSite","regular.baseSite.filter");

    private String code;
    private String filterKey;

    public static RegexEnum get(String code)
    {
        RegexEnum regexEnum = null;
        switch (code)
        {
            case "email":
                regexEnum = RegexEnum.REGULAR_EMAIL;
                break;
            case "mobile":
                regexEnum = RegexEnum.REGULAR_MOBILE;
                break;
            case "baseSite":
                regexEnum = RegexEnum.REGULAR_BASESITE;
                break;
        }
        return regexEnum;
    }
}
