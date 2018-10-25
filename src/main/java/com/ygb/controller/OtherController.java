package com.ygb.controller;

import com.ygb.jpa.OtherJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping(value = "/other")
public class OtherController {
    @Autowired
    private OtherJPA otherJPA;

    @RequestMapping(value="/getboard", method= RequestMethod.POST)
    public @ResponseBody OtherEntity findOtherName(String boardname) {
        OtherEntity contact = otherJPA.findByOtherName(boardname);

        return contact;
    }
    @Transactional // 事务
    @RequestMapping(value="/setboard", method= RequestMethod.GET)
    public @ResponseBody OtherEntity setOthere(String content) {
        OtherEntity other = new OtherEntity();
        other.setOtherName("公告栏");
        other.setContent(content);
        OtherEntity contact = otherJPA.save(other);
        return contact;
    }
}
