package com.free.u4;

import com.free.u4.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDBCTest {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource (DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate((dataSource));
    }

    public List<Map<String, Object>> getdbc(){
        return jdbcTemplate.queryForList("select name from Member");
    }

    @Test
    public void testConnection() {

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "test");


            ArrayList<Member> arrayList = new ArrayList<Member>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Member");
            while (rs.next()) {
                Member temp = new Member();
                temp.setId(rs.getLong(1));
                temp.setName(rs.getString(2));
                arrayList.add(temp);
            }

            System.out.println(arrayList.get(2));
        }catch(Exception e){
            System.out.println("error");
        }
    }

    public ArrayList<Member> getdb(){
        ArrayList<Member> arrayList = new ArrayList<Member>();

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "test");
            System.out.println(con);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Member");
            while (rs.next()) {
                Member temp = new Member();
                temp.setId(rs.getLong(1));
                temp.setName(rs.getString(2));
//                System.out.println(temp);
                arrayList.add(temp);
            }

            System.out.println(arrayList.get(2));
            return arrayList;
        }catch(Exception e){
            System.out.println("error");

        }
        return arrayList;
    }
}