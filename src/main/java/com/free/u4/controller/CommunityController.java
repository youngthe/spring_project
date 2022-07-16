package com.free.u4.controller;

import com.free.u4.domain.Comment;
import com.free.u4.domain.Community;
import com.free.u4.jdbc.CommunityJDBC;
import com.free.u4.utils.ScriptUtils;
import com.free.u4.utils.SessionCheckUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class CommunityController {

    SessionCheckUtils sessionCheck = new SessionCheckUtils();
    @RequestMapping(value = "/community")
    public String Community(Model model, HttpServletRequest request){

        boolean session_check = sessionCheck.Check(request.getSession());
        if(!session_check) {
            return "Register/login";
        }


        ArrayList<Community> arraylist = new ArrayList<Community>();
        CommunityJDBC jdbc = new CommunityJDBC();
        arraylist = jdbc.view_Community();
        if(request.getParameter("search") != null){
            arraylist = jdbc.Search_Community(request.getParameter("search"));
            model.addAttribute("community_list", arraylist);
            return "Community/community";
        }
        model.addAttribute("community_list", arraylist);
        return "Community/community";
    }

    @RequestMapping(value = "/community/write")
    public String community_write(HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {

        boolean session_check = sessionCheck.Check(request.getSession());
        if(!session_check) {
            return "Register/login";
        }

        if(request.getMethod().equals("POST")){
            HttpSession session = request.getSession();
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String writer = (String)session.getAttribute("user");

            CommunityJDBC jdbc = new CommunityJDBC();
            boolean result_write = jdbc.Create_Community(title, content, writer);
            if(result_write){
                httpServletResponse.setContentType("/community");
                ScriptUtils.alert_location(httpServletResponse, "글 작성이 완료되었습니다.", "/community");
                return "";
            }
            else
                ScriptUtils.alert_back(httpServletResponse, "글 작성 실패하였습니다.");

        }

        return "Community/community_write";
    }

    @RequestMapping(value = "/community/write/upload", method = RequestMethod.POST)
    public String community_write(@RequestParam("file") MultipartFile file) throws IOException {


        String file_name = file.getOriginalFilename();
        String file_path = "C:\\spring_project\\src\\main\\webapp\\WEB-INF\\file";
        File target = new File(file_path, file_name);
        FileCopyUtils.copy(file.getBytes(), target);

        return "Community/community";
    }


    @RequestMapping(value = "/community/detail/{id}")
    public String community_detail(@PathVariable int id, Model model, HttpServletRequest request) throws SQLException {

        ArrayList<Comment> comments = new ArrayList<Comment>();
        boolean session_check = sessionCheck.Check(request.getSession());
        if(!session_check) {
            return "Register/login";
        }

        CommunityJDBC jdbc = new CommunityJDBC();
        Community community = jdbc.detail_Community(id);
        comments = jdbc.get_Comments(id);
        model.addAttribute("community", community);
        model.addAttribute("comments", comments);
        return "Community/community_detail";
    }

    @RequestMapping(value = "/community/modify/{Community_id}")
    public String community_modify(@PathVariable int Community_id, Model model, HttpServletRequest request,
                                   HttpServletResponse httpServletResponse) throws SQLException, IOException {


        boolean session_check = sessionCheck.Check(request.getSession());
        if(!session_check) {
            return "Register/login";
        }


        CommunityJDBC jdbc = new CommunityJDBC();
        Community community = jdbc.detail_Community(Community_id);
        model.addAttribute("community", community);
        HttpSession session = request.getSession();
        if(!jdbc.get_Community_Writer(Community_id).equals(session.getAttribute("user"))){
            ScriptUtils.alert_back(httpServletResponse, "수정권한이 없습니다");
            return "";
        }

        if(request.getMethod().equals("POST")){
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            jdbc.modify_Community(Community_id, title, content);
            return "redirect:/community/detail/"+Community_id;
        }
        return "Community/community_modify";

    }

    @RequestMapping(value = "/community/delete/{Community_id}")
    public String Community_Delete(@PathVariable int Community_id, HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {


        boolean session_check = sessionCheck.Check(request.getSession());
        if(!session_check) {
            return "Register/login";
        }

        CommunityJDBC jdbc = new CommunityJDBC();
        Community community = jdbc.detail_Community(Community_id);
        HttpSession session = request.getSession();
        if(!jdbc.get_Community_Writer(Community_id).equals(session.getAttribute("user"))){
            ScriptUtils.alert_back(response, "삭제권한이 없습니다.");
            return "";
        }

        jdbc = new CommunityJDBC();
        boolean delete_result = jdbc.delete_Community(Community_id);
        if(delete_result)
            ScriptUtils.alert_location(response, "삭제되었습니다", "/community");
        else
            ScriptUtils.alert_back(response, "삭제할 수 없습니다.");

        return "redirect:/community";
    }

    @RequestMapping(value="/community/comments/{id}")
    public String comments(@PathVariable int id, HttpServletRequest request){


        boolean session_check = sessionCheck.Check(request.getSession());
        if(!session_check) {
            return "Register/login";
        }

        String comment = request.getParameter("comments");
        HttpSession session = request.getSession();
        String writer = (String)session.getAttribute("user");

        CommunityJDBC jdbc = new CommunityJDBC();
        jdbc.set_Comments(id, comment, writer);
        System.out.println(comment);

        return "redirect:/community/detail/"+id;
    }

    @RequestMapping(value="/community/comment/delete/{comment_id}")
    public String comment_delete(@PathVariable int comment_id,
                                 HttpServletResponse response, HttpServletRequest request) throws IOException {

        CommunityJDBC jdbc = new CommunityJDBC();

        HttpSession session = request.getSession();
        if(!jdbc.get_Comment_Writer(comment_id).equals(session.getAttribute("user"))){
            ScriptUtils.alert_back(response, "삭제권한이 없습니다.");
            return "";
        }

        jdbc.delete_comment(comment_id);

        ScriptUtils.alert_location(response, "삭제 완료","/community");
        return "redirect:/community";
    }
}
