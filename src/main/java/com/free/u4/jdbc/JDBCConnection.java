package com.free.u4.jdbc;

import com.free.u4.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDBCConnection {


    public ArrayList getMember() {
        ArrayList<Member> arrayList = new ArrayList<Member>();
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "test");
//            Connection con = dataSource.getConnection();
            System.out.println(con);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Member");
            while (rs.next()) {
                Member temp = new Member();
                temp.setId(rs.getLong(1));
                temp.setName(rs.getString(2));
                arrayList.add(temp);
            }
            con.close();
            return arrayList;
        }catch(Exception e){
            System.out.println("error");
        }
        return arrayList;
    }

    public void insertName(String name) {

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "test");
            System.out.println(con);
            String sql = "insert into MEMBER (name) values ('"+name+"')";
            System.out.println(sql);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
            System.out.println("success");
        }catch(Exception e){
            System.out.println("error");
        }
    }

    public void deleteID(String id){

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "test");
            String sql = "delete from MEMBER where id = '"+id+"'";
            System.out.println(sql);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
            System.out.println("success");
        }catch(Exception e){
            System.out.println("error");
        }
    }
}