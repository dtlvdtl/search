
package com.ygb.controller;

//import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "活动")
//数据库表
@IdClass(ActivityKey.class)

public class ActivityEntity implements Serializable{
    @Id
    @Column(name = "学号")
    private int acid;
    @Id
    @Column(name = "活动名称")
    private String acName;
    @Id
    @Column(name = "活动时间")
    private String time;

    @Column(name = "服务时长")
    private float ygs;

    public void setAcid(int acid){
        this.acid = acid;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setYgs(float ygs) {
        this.ygs = ygs;
    }

    public String getAcName(){
        return this.acName;
    }
    public String getTime(){
        return this.time;
    }

    public int getAcid(){
        return this.acid;
    }
    public float getYgs(){
        return this.ygs;
    }

}
