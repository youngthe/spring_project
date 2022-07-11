package com.free.u4.jdbc;


import com.free.u4.utils.ScriptUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
}
