/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author usuario
 */
public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/happy_feet_veterinaria";
    private static final String USER = "root";
    private static final String PASS = "kike170410*";
    
    public static Connection conectar(){
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Conexion DB - Error intentando conectar a la BD: " + e.getMessage());
            return null;
        }       
    }
}
