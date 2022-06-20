package com.free.u4;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class JDBCTest {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root", "test");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("show databases");

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

        }catch(Exception e){
            System.out.println("error");
        }
    }
}