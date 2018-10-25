package com.ygb.controller;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ygb.jpa.*;
import jdk.nashorn.internal.parser.JSONParser;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserJPA userJPA;
    @Autowired
    private UserPasswordJPA userPasswordJPA;
    @Autowired
    private ActivityJPA activityJPA;
    @Autowired
    private StudentJPA studentJPA;

    public ObjectMapper ObjectMapper(){
        ObjectMapper objectMapper= new ObjectMapper();
        return objectMapper;
    }
    @Autowired
    public final ObjectMapper mapper = new ObjectMapper();
    /////////////////// 查询活动信息  ///////////
    //   查询活动名称
    @RequestMapping(value="/byactname", method=RequestMethod.GET)
    public @ResponseBody List<ActivityEntity> findByAct_name(String acName) {
        List<ActivityEntity> contact = activityJPA.findByAcName(acName);
        return contact;
    }
    //   查询活动学号
    @RequestMapping(value="/byactid", method=RequestMethod.GET)
    public @ResponseBody List<ActivityEntity> findByAct_id(int acid) {
        List<ActivityEntity> contact = activityJPA.findByAcid(acid);
        return contact;
    }

    ////////////// 查询学生信息 ////////////////////

    //    查询学号
    @RequestMapping(value="/byid", method=RequestMethod.GET)
    public @ResponseBody List<StudentEntity> findById(int id) {
        List<StudentEntity> contact = studentJPA.findById(id);
        return contact;
    }
    //    查询姓名
    @RequestMapping(value="/byname", method=RequestMethod.GET)
    public @ResponseBody List<StudentEntity> findByName(String name) {
        List<StudentEntity> contact = studentJPA.findByName(name);
        return contact;
    }
    //查询，通过name、id查询一条
    @RequestMapping(value="/bynameid", method=RequestMethod.GET)
    public @ResponseBody StudentEntity findByNameAndId(HttpServletRequest request) {
//        UserEntity contact = userJPA.findByName(name);
        String name = request.getParameter("name");
        int id = Integer.parseInt(request.getParameter("id"));

        StudentEntity contact = studentJPA.findByNameAndId(name,id);
//        if(userJPA.existsByUserAndPassword(name, id)){
//            return userJPA.findByNameAndId(name, id);
//        }
        return contact ;
    }
    /*
    *查询用户列表方法
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)// 网页的网址获取信息
    public List<UserEntity> list(){
        return userJPA.findAll(); // 查询表内所有数据
    }
    /*
     *添加、更新用户方法
     * @param entity
     * @return
     */
    /////////////////////   更新   ///////////////////////////
    @Transactional // 事务
    @RequestMapping(value = "/studentsave",method = RequestMethod.GET)
    public boolean studentsave(HttpServletRequest request) {// 要添加throws Exception
        if (!studentJPA.existsById(Integer.parseInt(request.getParameter("id")))) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setId(Integer.parseInt(request.getParameter("id")));
            System.out.print(request.getParameter("id"));
            System.out.print(request.getParameter("name"));
            System.out.print(request.getParameter("faculty"));
            studentEntity.setName(request.getParameter("name"));
            studentEntity.setFaculty(request.getParameter("faculty"));
            studentEntity.setSumYgs(0);
            studentJPA.save(studentEntity);
        }
        return true; // 更新表内数据
    }
    @Transactional // 事务
    @RequestMapping(value = "/activitysave",method = RequestMethod.POST)
    public boolean activitysave(HttpServletRequest request) {// 要添加throws Exception

        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setAcid(Integer.parseInt(request.getParameter("acid")));
        activityEntity.setAcName(request.getParameter("acName"));
        activityEntity.setTime(request.getParameter("time"));
        activityEntity.setYgs(Float.parseFloat(request.getParameter("ygs")));
        activityJPA.save(activityEntity);

        return true; // 更新表内数据
    }
    @Transactional // 事务
    @RequestMapping(value = "/activitysaves",method = RequestMethod.POST)
    public boolean activitysaves(@RequestBody List<ActivityEntity> activity) {// 要添加throws Exception

        for(int i =0; i < activity.size();i++){
            activityJPA.save(activity.get(i));
        }

        return true; // 更新表内数据
    }
    @Transactional // 事务
    @RequestMapping(value = "/studentsaves",method = RequestMethod.POST)
    public boolean studentsaves(@RequestBody List<StudentEntity> student) {// 要添加throws Exception

        for(int i =0; i < student.size();i++){
            studentJPA.save(student.get(i));
        }

        return true; // 更新表内数据
    }
    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public UserEntity save(UserEntity entity){
        return userJPA.save(entity); // 更新表内数据
    }
    /*
     * 删除用户方法
     * @param id 用户编号
     * @return
     */
    ////////////////////////  删除  ////////////////////////////
    @Modifying
    @Transactional // 事务
    @RequestMapping(value = "/deleteac",method = RequestMethod.POST)
    public boolean deleteAc(@RequestBody List<ActivityEntity> activity){
        for(int i =0; i < activity.size();i++) {
            activityJPA.deleteByAcidAndAcNameAndTime(activity.get(i).getAcid(), activity.get(i).getAcName(), activity.get(i).getTime());
        }
        return true;
    }

    @Transactional // 事务
    @RequestMapping(value = "/deleteuser",method = RequestMethod.POST)
    public boolean deleteUser( HttpServletRequest request){

        String[] user = request.getParameterValues("user[]");
        for (int i=0; i < user.length; i++){
            userPasswordJPA.deleteByUser(user[i]);
        }

        return true;
    }
    ///////////////////////   权限控制  //////////////////////////////
    @RequestMapping(value = "/power", method = {RequestMethod.GET})
    public int power(HttpServletRequest request){
        UserPasswordEntity r = userPasswordJPA.findByUser((String) request.getSession().getAttribute("name"));
        return r.getPower();
    }
    @RequestMapping(value = "/getpower", method = {RequestMethod.GET})
    public List<UserPasswordEntity> getpower(){
        List<UserPasswordEntity> r = userPasswordJPA.findAll();
        return r;
    }
    @Transactional // 事务
    @RequestMapping(value = "/addpower", method = {RequestMethod.POST})
    public boolean addpower(UserPasswordEntity user){
        if (user.equals("666666")){
            user.setPower(1);
        }
        userPasswordJPA.save(user);
        return true;
    }

    /////////////////////////// 登录 /////////////////////////////////////////

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public boolean login(HttpServletRequest request, RedirectAttributes model){
        //验证账号密码，如果符合则改变session里的状态，并重定向到主页

        if (userPasswordJPA.existsByUserAndPassword(request.getParameter("user"),request.getParameter("password")) ){
            request.getSession().setAttribute("isLogin","yes");
            request.getSession().setAttribute("name",request.getParameter("user"));
            return true;
        }else {
            request.getSession().removeAttribute("name");
            request.getSession().removeAttribute("isLogin");
            //密码错误则重定向回登录页，并返回错误，因为是重定向所要要用到RedirectAttributes
            model.addFlashAttribute("error","密码错误");
            return false;
        }
    }
    //登出，移除登录状态并重定向的登录页
    @RequestMapping(value = "/loginOut", method = {RequestMethod.GET})
    public boolean loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute("isLogin");
        request.getSession().removeAttribute("name");
        return true;
    }

}
