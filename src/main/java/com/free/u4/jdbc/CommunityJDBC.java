package com.free.u4.jdbc;

import com.free.u4.domain.Comment;
import com.free.u4.domain.Community;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CommunityJDBC {

    public Connection dbcon(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "test");
            return con;
        }catch(Exception e){
            System.out.println("db connection error");
            return con;
        }
    }

    public boolean SqlDeleteAndInsert(String sql){
        try{
            Connection con = dbcon();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
            return true;
        }catch(Exception e){
            System.out.println("Sql Delete or Insert error");
            System.out.println(e);
        }
        return false;
    }

    public boolean Create_Community(String title, String content, String writer){
        LocalDate now = LocalDate.now();
        String sql = "insert into COMMUNITY ( title, content, writer, Date, hits) value ('"+title+"', '"+content+"' , '"+writer+"', '"+now+"', 0)";
        boolean result = SqlDeleteAndInsert(sql);
        return result;
    }

    public ArrayList<Community> view_Community(){
        ArrayList<Community> arraylist = new ArrayList<>();
        String sql = "select * from COMMUNITY order by id DESC";
        try{
            Connection con = dbcon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Community community = new Community();
                community.setID(rs.getInt(1));
                community.setTitle(rs.getString(2));
                community.setContent(rs.getString(3));
                community.setWriter(rs.getString(4));
                community.setDate(rs.getString(5));
                community.setHits(rs.getInt(6));
                arraylist.add(community);
            }
            return arraylist;

        }catch(Exception e){
            System.out.println(e);
        }

        return arraylist;
    }

    public Community detail_Community(int id) {
        String sql = "select * from COMMUNITY where id ='"+id+"'";
        Community community = new Community();
        Increase_Community(id);
        try{
            Connection con = dbcon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                community.setID(rs.getInt(1));
                community.setTitle(rs.getString(2));
                community.setContent(rs.getString(3));
                community.setWriter(rs.getString(4));
                community.setDate(rs.getString(5));
                community.setHits(rs.getInt(6));
            }else{
                System.out.println("don't exist Community");
            }

            return community;
        }catch(Exception e) {
            System.out.println(e);
        }
        return community;
    }

    public boolean Increase_Community(int id){
        String sql = "update community set  hits = hits+1 where id='"+id+"'";
        try{
            Connection con = dbcon();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean modify_Community(int id, String title, String content) throws SQLException{
        String sql = "update community set title='"+title+"', content= '"+content+"' where id="+id;

        Connection con = dbcon();
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        con.close();
        return true;
    }

    public String get_Writer(int id){
        String sql = "select writer from COMMUNITY where id='"+id+"'";
        String writer = null;

        try{
            Connection con = dbcon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                writer = rs.getString(1);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return writer;
    }

    public boolean delete_Community(int id) throws SQLException{
        String sql = "delete from community where id = '"+id+"'";
        Connection con = dbcon();
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        con.close();
        return true;
    }

    public ArrayList<Community> Search_Community(String title){
        String sql = "select * from community where title like '%"+title+"%' order by id DESC";
        ArrayList<Community> arrayList = new ArrayList<Community>();
        try{
            Connection con = dbcon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Community community = new Community();
                community.setID(rs.getInt(1));
                community.setTitle(rs.getString(2));
                community.setContent(rs.getString(3));
                community.setWriter(rs.getString(4));
                community.setDate(rs.getString(5));
                community.setHits(rs.getInt(6));
                arrayList.add(community);
            }
            con.close();

        }catch (Exception e){
            System.out.println(e);
        }
        return arrayList;
    }

    public void set_Comments(int community_id, String comment, String writer){
        LocalDate date = LocalDate.now();
        String sql = "insert into comments (community_id, comment, writer, date) " +
                "value ('"+community_id+"', '"+comment+"', '"+writer+"', '"+date+"')";
        try{
            Connection con = dbcon();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public ArrayList<Comment> get_Comments(int community_id){
        String sql = "select * from comments where community_id = '"+community_id+"'";
        ArrayList<Comment> comments = new ArrayList<>();

        try{
            Connection con = dbcon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Comment comment = new Comment();
                comment.setComment(rs.getString(3));
                comment.setWriter(rs.getString(4));
                comment.setDate(rs.getString(5));
                comments.add(comment);
            }

        }catch(Exception e){
            System.out.println(e);
        }

        return comments;
    }
}
