package com.free.u4.controller;

import com.free.u4.jdbc.RegisterJDBC;
import com.free.u4.utils.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class RegisterController {


    @RequestMapping(value="/login")
    public String login(HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse, Model model) throws IOException {


        HttpSession session = httpServletRequest.getSession();
        String sessionCheck = (String)session.getAttribute("user");
        System.out.println(sessionCheck);

        if(sessionCheck != null ){
            model.addAttribute("user", sessionCheck);
            return "Register/main";
        }

        if(httpServletRequest.getMethod().equals("POST")){
            String id = httpServletRequest.getParameter("id");
            String pw = httpServletRequest.getParameter("pw");

            RegisterJDBC jdbc = new RegisterJDBC();
            if(jdbc.loginCheck(id, pw)){
                session.setAttribute("user", id);
                ScriptUtils.alert(httpServletResponse, "로그인 성공하셨습니다.");
                return "Register/main";
            }else{
                ScriptUtils.alert(httpServletResponse, "아이디가 존재하지 않습니다");
            }
        }
        return "Register/login";
    }

    @RequestMapping(value = "/main")
    public String session(HttpServletRequest httpServletRequest){

        if(httpServletRequest.getMethod().equals("POST")) {
            HttpSession session = httpServletRequest.getSession();
            session.invalidate();
            return "Register/login";
        }

        return "Register/main";
    }
}
