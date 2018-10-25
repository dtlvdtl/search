package com.ygb.controller;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "其他")
public class OtherEntity implements Serializable{
    @Id
    @Column(name = "其他名称")
    private String otherName;

    @Column(name = "内容")
    private String content;

    public String getContent() {
        return content;

    }

    public String getOtherName() {
        return otherName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

}
