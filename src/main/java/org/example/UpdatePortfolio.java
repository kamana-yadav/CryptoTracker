package org.example;

import java.sql.*;

public class UpdatePortfolio {

    public static void main(String[] args) {

        try {

            Connection con = DatabaseUtil.getConnection();

            String sql =
                    "UPDATE portfolio SET quantity=? WHERE coin_name=?";


            PreparedStatement ps =
                    con.prepareStatement(sql);


            ps.setDouble(1,1.0);
            ps.setString(2,"Bitcoin");


            ps.executeUpdate();


            System.out.println("Portfolio Updated");


            con.close();


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}