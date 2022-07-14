package com.free.u4.utils;

import javax.servlet.http.HttpSession;

public class SessionCheckUtils {

    public boolean Check(HttpSession session){
        String user = (String) session.getAttribute("user");


        if(user == null){
            return false;
        }
        return true;
    }
}
