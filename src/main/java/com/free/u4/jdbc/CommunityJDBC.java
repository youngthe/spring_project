package com.free.u4.jdbc;

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
        }
        return false;
    }

    public boolean Create_Community(String title, String content, String writer){
        LocalDate now = LocalDate.now();
        String sql = "insert into COMMUNITY ( title, content, writer, Date) value ('"+title+"', '"+content+"' , '"+writer+"', '"+now+"')";
        System.out.println(now);
        boolean result = SqlDeleteAndInsert(sql);
        return result;
    }

    public ArrayList<Community> view_Community(){
        ArrayList<Community> arraylist = new ArrayList<>();
        String sql = "select * from COMMUNITY";
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

        try{
            Connection con = dbcon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                community.setID(rs.getInt(1));
                community.setTitle(rs.getString(2));
                community.setContent(rs.getString(3));
            }else{
                System.out.println("don't exist Community");
            }

            return community;
        }catch(Exception e) {
            System.out.println(e);
        }
        return community;
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

    public boolean delete_community(int id) throws SQLException{
        String sql = "delete from community where id = '"+id+"'";
        Connection con = dbcon();
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        return true;
    }
}
