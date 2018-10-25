package com.ygb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "活动统计表")



public class UserEntity implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "学号")
    private String id;

    @Column(name = "姓名")
    private String name;

    @Column(name = "性别")
    private String six;

    @Column(name = "活动时间")
    private String time;

    @Column(name = "活动名称")
    private String actname;

    @Column(name = "活动义工时")
    private int ygs_size;

    public String getname(){
        return name;
    }
    public String getsix(){
        return six;
    }
    public String getid(){
        return id;
    }
    public String gettime(){
        return time;
    }
    public String getAct_name(){
        return actname;
    }
    public int getygs_size(){
        return ygs_size;
    }

}
