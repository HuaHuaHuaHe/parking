package com.swpu.hth.entity;

public class UserDO {
    //用户名
    private String username;
    //密码
    private String pwd;
    //车牌号
    private String license;
    //性别
    private String sex;
    //电话
    private String tel;
    //车龄
    private int drive_age;
    //车型（大小）
    private int cartype;

    public String getUsername() {
        return username;
    }

    public UserDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPwd() {
        return pwd;
    }

    public UserDO setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    public String getLicense() {
        return license;
    }

    public UserDO setLicense(String license) {
        this.license = license;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public UserDO setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public UserDO setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public int getDrive_age() {
        return drive_age;
    }

    public UserDO setDrive_age(int drive_age) {
        this.drive_age = drive_age;
        return this;
    }

    public int getCartype() {
        return cartype;
    }

    public UserDO setCartype(int cartype) {
        this.cartype = cartype;
        return this;
    }
}
