package com.free.u4.jdbc;


import com.free.u4.domain.User;
import com.free.u4.utils.ScriptUtils;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class RegisterJDBC {

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

    public boolean loginCheck(String id, String pw){
        String sql = "select * from user where id='"+id+"' and password='"+pw+"'";
        try{
            Connection con = dbcon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("login error");
            return false;
        }
    }

    public boolean Register(String id, String pw){
        String id_Check_sql = "select id from user where id = '"+id+"'";
        System.out.println(id_Check_sql);
        String sql = "insert into user(id, password) value ('"+id+"', '"+pw+"')";
        System.out.println(sql);

        try{
            Connection con = dbcon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(id_Check_sql);
            if(rs.next()){
                System.out.println("exist id");
                return false;
            }
            stmt.executeUpdate(sql);
            con.close();
            System.out.println("register success");
            return true;
        }catch (Exception e){
            System.out.println(e);
            System.out.println("register error");
            return false;
        }
    }

    public ArrayList<User> user_info(){
        ArrayList<User> users = new ArrayList<User>();
        String sql = "select num, id from user";
        try{
            Connection con = dbcon();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                User user = new User();
                user.setNum(rs.getInt(1));
                user.setId(rs.getString(2));
                users.add(user);
            }
            con.close();
            System.out.println("return user_info success");
            return users;

        }catch(Exception e){
            System.out.println("user_info error");
            System.out.println(e);
        }

        return users;
    }

    public boolean user_delete(int num){
        String sql = "delete from USER where num = '"+num+"'";
        try{
            Connection con = dbcon();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
            System.out.println("success delete");
            return true;
        }catch(Exception e){
            System.out.println("error delete");
            return false;
        }

    }


}
