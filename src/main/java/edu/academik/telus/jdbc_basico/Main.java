/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.academik.telus.jdbc_basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 50232
 */
public class Main {
    public static void main(String []args){
         Connection conn = null;

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registro?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Sadmywaleska2020");

            System.out.println("Base de datos conectada!!!");

            ejecutarQuery1(conn);

            conn.close();

        } catch (SQLException ex) {

            System.err.println(ex.getMessage());

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex1) {
                    System.err.println(ex1.getMessage());
                }
            }
        }
        
        
    
    }
    
    private static void ejecutarQuery1(Connection conn) throws SQLException {

        String queryString = "select* from persona";

        // try with resource ---> el va  a cerrar el stmt
        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {

            try (ResultSet rs = stmt.executeQuery()) {

                int totalColumnas = rs.getMetaData().getColumnCount();

                while (rs.next()) {

                    for (int i = 1; i <= totalColumnas; i++) {

                        System.out.print(rs.getObject(i));

                        System.out.print("\t");
                    }

                    System.out.println();
                }

            }

        }

    }
        
    
}
