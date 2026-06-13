package org.example;

import java.sql.*;

public class DeletePortfolio {

    public static void main(String[] args){

        try{

            Connection con =
                    DatabaseUtil.getConnection();


            String sql =
                    "DELETE FROM portfolio WHERE coin_name=?";


            PreparedStatement ps =
                    con.prepareStatement(sql);


            ps.setString(1,"Ethereum");


            ps.executeUpdate();


            System.out.println("Coin Deleted");


            con.close();


        }catch(Exception e){

            e.printStackTrace();

        }

    }

}