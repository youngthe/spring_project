package com.free.u4.controller;

import com.free.u4.jdbc.RegisterJDBC;
import com.free.u4.utils.ScriptUtils;
import org.hibernate.hql.spi.id.cte.CteValuesListBulkIdStrategy;
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
    public String main(HttpServletRequest httpServletRequest){

        if(httpServletRequest.getMethod().equals("POST")) {
            HttpSession session = httpServletRequest.getSession();
            session.invalidate();
            return "Register/login";
        }

        return "Register/main";
    }

    @RequestMapping(value = "/register")
    public String register(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException{

        if(httpServletRequest.getMethod().equals("POST"))
        {
            String id = httpServletRequest.getParameter("id");
            String pw = httpServletRequest.getParameter("pw1");
            System.out.println(id);
            System.out.println(pw);
            RegisterJDBC register = new RegisterJDBC();
            boolean result = register.Register(id, pw);
            if(result){
                ScriptUtils.alert(httpServletResponse, "회원가입이 되었습니다.");
                return "Register/login";
            }
            else
                ScriptUtils.alert(httpServletResponse, "아이디가 이미 존재합니다.");
        }
        return "Register/register";
    }
}
