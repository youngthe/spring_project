package com.free.u4.jdbc;


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
}
