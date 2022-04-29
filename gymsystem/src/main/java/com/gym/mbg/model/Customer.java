package com.gym.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable {
    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "用户姓名")
    private String username;

    @ApiModelProperty(value = "用户性别 1->男，0->女")
    private Integer gender;

    @ApiModelProperty(value = "用户年龄")
    private Integer age;

    @ApiModelProperty(value = "用户头像文件")
    private String portrait;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户银行卡号")
    private String creditCardNumber;

    @ApiModelProperty(value = "用户电话")
    private String phone;

    @ApiModelProperty(value = "用户身份证号")
    private String identityCard;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", gender=").append(gender);
        sb.append(", age=").append(age);
        sb.append(", portrait=").append(portrait);
        sb.append(", email=").append(email);
        sb.append(", creditCardNumber=").append(creditCardNumber);
        sb.append(", phone=").append(phone);
        sb.append(", identityCard=").append(identityCard);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}