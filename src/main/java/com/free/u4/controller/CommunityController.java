package com.free.u4.controller;

import com.free.u4.domain.Community;
import com.free.u4.jdbc.CommunityJDBC;
import com.free.u4.utils.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class CommunityController {

    @RequestMapping(value = "/community")
    public String community(Model model){
        ArrayList<Community> arraylist = new ArrayList<Community>();
        CommunityJDBC jdbc = new CommunityJDBC();
        arraylist = jdbc.view_Community();

        model.addAttribute("community_list", arraylist);
        return "Community/community";
    }


    @RequestMapping(value = "/community/write")
    public String community_write(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        if(httpServletRequest.getMethod().equals("POST")){
            String title = httpServletRequest.getParameter("title");
            String content = httpServletRequest.getParameter("content");
            HttpSession session = httpServletRequest.getSession();
            String writer = (String)session.getAttribute("user");
            CommunityJDBC jdbc = new CommunityJDBC();
            boolean result = jdbc.Create_Community(title, content, writer);
            if(result){
                httpServletResponse.setContentType("/community");
                ScriptUtils.alert_location(httpServletResponse, "글 작성이 완료되었습니다.", "/community");
                return "";
            }
            else
                ScriptUtils.alert_back(httpServletResponse, "글 작성 실패하였습니다.");

        }

        return "Community/community_write";
    }

    @RequestMapping(value = "/community/detail/{id}")
    public String community_detail(@PathVariable int id, Model model) throws SQLException {

        CommunityJDBC jdbc = new CommunityJDBC();
        Community community = jdbc.detail_Community(id);

        model.addAttribute("community", community);

        return "Community/community_detail";
    }

    @RequestMapping(value = "/community/modify/{id}")
    public String community_modify(@PathVariable int id, Model model, HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) throws SQLException, IOException {

        CommunityJDBC jdbc = new CommunityJDBC();
        Community community = jdbc.detail_Community(id);
        model.addAttribute("community", community);
        HttpSession session = httpServletRequest.getSession();

        if(!jdbc.get_Writer(id).equals(session.getAttribute("user"))){
            ScriptUtils.alert_back(httpServletResponse, "수정권한이 없습니다");
            return "";
        }

        if(httpServletRequest.getMethod().equals("POST")){
            String title = httpServletRequest.getParameter("title");
            String content = httpServletRequest.getParameter("content");
            jdbc.modify_Community(id, title, content);
            return "redirect:/community/detail/"+id;
        }
        return "Community/community_modify";

    }

    @RequestMapping(value = "/community/delete/{id}")
    public String Community_Delete(@PathVariable int id, HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {


        CommunityJDBC jdbc = new CommunityJDBC();
        Community community = jdbc.detail_Community(id);
        HttpSession session = request.getSession();
        if(!jdbc.get_Writer(id).equals(session.getAttribute("user"))){
            ScriptUtils.alert_back(response, "삭제권한이 없습니다.");
            return "";
        }

        jdbc = new CommunityJDBC();
        boolean result = jdbc.delete_community(id);
        if(result)
            ScriptUtils.alert_location(response, "삭제되었습니다", "/community");
        else
            ScriptUtils.alert_back(response, "삭제할 수 없습니다.");

        return "redirect:/community";
    }
}
