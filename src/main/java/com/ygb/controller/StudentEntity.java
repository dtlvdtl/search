
package com.ygb.controller;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "学生")



public class StudentEntity implements Serializable{
    @Id
    @Column(name = "学号")
    private int id;

    @Column(name = "姓名")
    private String name;

    @Column(name = "院别")
    private String faculty;

    @Column(name = "总义工时")
    private float sumYgs;

    public void setId(int id) {
        this.id = id;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSumYgs(float sumYgs) {
        this.sumYgs = sumYgs;
    }

    public String getname(){
        return name;
    }
    public String getfaculty(){
        return faculty;
    }
    public int getid(){
        return id;
    }
    public float getsumYgs(){
        return sumYgs;
    }

}
