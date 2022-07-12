package com.free.u4.jdbc;

import com.free.u4.domain.Community;

import java.sql.*;
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

    public boolean Create_Community(String title, String content){
        String sql = "insert into COMMUNITY ( title, content) value ('"+title+"', '"+content+"')";
        boolean result = SqlDeleteAndInsert(sql);
        return result;
    }

    public ArrayList<Community> View_Community(){
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
                arraylist.add(community);
            }
            return arraylist;

        }catch(Exception e){
            System.out.println(e);
        }

        return arraylist;
    }

    public Community Detail_Community(int id) {
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
}
