package com.panku.entity;

public class HomeCommon {

    private String describe;

    private String url;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "HomeCommon{" +
                "describe='" + describe + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
