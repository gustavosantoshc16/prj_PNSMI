/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.*;
//
///**
// *
// * @author NOTEHOME
// */
//public class ConexaoDAO {
//    
//    public Connection conectaDB(){
//        Connection conn = null;
//        
//        try {
//            String driver = "com.mysql.cj.jdbc.Driver";
//            //String url = "jdbc:mysql://localhost:3306/db_pastoral?user=root&password=160607";
//            String url = "jdbc:mysql://localhost:3306/db_pastoral?";
//            String user = "root";
//            String password = "160607";
//            conn = DriverManager.getConnection(url);
//          
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null,"conexaoDAO" + e.getMessage());
//            
//        }
//        return conn;
//    }
//    
//}

//import java.sql.*;

/**
 *
 * @author NOTEHOME
 */
public class ConexaoDAO {

//metodo responsavel por estabelecer a conexao com o BD
    public static Connection conectaDB() {
        java.sql.Connection conexao = null;
        
        //a linha abaixo chama o drive importado para biblioteca
        String driver = "com.mysql.cj.jdbc.Driver";

        //armazenando informações referente ao BD
        String url = "jdbc:mysql://localhost:3306/db_pastoral?characterEnconding=utf-8";
        String user = "dba";
        String password = "160607";

        //Estabelecer a conexao com BD
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            //linha abaixo server de apoio para esclarecer o erro
            //System.out.println("e");
            return null;
        }

    }

}
