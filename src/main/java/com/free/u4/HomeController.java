package com.free.u4;

import com.free.u4.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){

        return "main";
    }
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Model model){
        ArrayList<Member> arraylist;
        JDBCConnection jdbc = new JDBCConnection();
        arraylist = jdbc.DBConnection();
        model.addAttribute("list", arraylist);
        return "view";
    }


    @RequestMapping(value = "/addMember", method = RequestMethod.GET)
    public String addMember(Model model){
        return "addMember";
    }
    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public String setMember(HttpServletRequest httpServletRequest){
        String name = httpServletRequest.getParameter("name");
        System.out.println(name);
        JDBCConnection jdbc = new JDBCConnection();
        jdbc.insertName(name);

        return "addMember";
    }

}
