package com.example.web.entity;

import java.util.Date;

public class UserInfo {
    private Long id;

    private String username;

    private String password;

    private String true_name;

    private String email;

    private String company;

    private String position;

    private String tel;

    private String number;

    private Integer rank;

    private Integer is_admin;

    private Long super_id;

    private Integer is_auto_login;

    private Integer is_effective;

    private Date gmt_created;

    private Long user_created;

    private Date gmt_modified;

    private Long user_modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name == null ? null : true_name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Integer is_admin) {
        this.is_admin = is_admin;
    }

    public Long getSuper_id() {
        return super_id;
    }

    public void setSuper_id(Long super_id) {
        this.super_id = super_id;
    }

    public Integer getIs_auto_login() {
        return is_auto_login;
    }

    public void setIs_auto_login(Integer is_auto_login) {
        this.is_auto_login = is_auto_login;
    }

    public Integer getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(Integer is_effective) {
        this.is_effective = is_effective;
    }

    public Date getGmt_created() {
        return gmt_created;
    }

    public void setGmt_created(Date gmt_created) {
        this.gmt_created = gmt_created;
    }

    public Long getUser_created() {
        return user_created;
    }

    public void setUser_created(Long user_created) {
        this.user_created = user_created;
    }

    public Date getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(Date gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public Long getUser_modified() {
        return user_modified;
    }

    public void setUser_modified(Long user_modified) {
        this.user_modified = user_modified;
    }
}