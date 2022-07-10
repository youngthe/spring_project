package com.free.u4.controller;

import com.free.u4.jdbc.JDBCConnection;
import com.free.u4.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@Controller
public class MemberController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){

        return "member/main";
    }


    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Model model){
        ArrayList<Member> arraylist;
        JDBCConnection jdbc = new JDBCConnection();
        arraylist = jdbc.getMember();
        model.addAttribute("list", arraylist);
        return "member/view";
    }


    @RequestMapping(value = "/addMember")
    public String setMember(HttpServletRequest httpServletRequest){

        if (httpServletRequest.getMethod().equals("GET")){
            return "member/addMember";
        } else {
            String name = httpServletRequest.getParameter("name");
            System.out.println(name);
            JDBCConnection jdbc = new JDBCConnection();
            jdbc.insertName(name);
        }

        return "member/addMember";
    }


    @RequestMapping(value = "/deleteMember")
    public String deleteMember(HttpServletRequest httpServletRequest){
        if(httpServletRequest.getMethod().equals("GET")){
            return "member/deleteMember";
        }else{
            String id = httpServletRequest.getParameter("id");
            System.out.println(id);
            JDBCConnection jdbc = new JDBCConnection();
            jdbc.deleteID(id);

        }
        return "member/deleteMember";
    }
}
