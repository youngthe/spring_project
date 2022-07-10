package com.free.u4.jdbc;

import com.free.u4.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import javax.validation.constraints.Null;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDBCConnection {
    
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

    public ArrayList getMember() {

        ArrayList<Member> arrayList = new ArrayList<Member>();

        try{
            Connection con = dbcon();
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

        } catch (Exception e){
            System.out.println("select member error");
        }
        return arrayList;
    }

    public void insertName(String name) {

        String sql = "insert into MEMBER (name) values ('"+name+"')";

        boolean result = SqlDeleteAndInsert(sql);
        if(result)
            System.out.println("insert success");
        else
            System.out.println("sql error");

    }

    public void deleteID(String id){

            String sql = "delete from MEMBER where id = '"+id+"'";

            boolean result = SqlDeleteAndInsert(sql);
            if(result)
                System.out.println("delete success");
            else
                System.out.println("delete error");

    }

}