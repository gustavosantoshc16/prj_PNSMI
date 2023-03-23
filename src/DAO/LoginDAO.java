/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.UsuarioDTO;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author NOTEHOME
 */
public class LoginDAO {

    Connection conn;

    public ResultSet autenticacaoUsuario(UsuarioDTO objusuariodto) {
        conn = new ConexaoDAO().conectaDB();

        try {
//            String sql = "select id_usuario, usuario, cpf,login, senha, desc_perfil"
//                    + "from usuarios  inner join perfil_usuario on usuarios.perfil_usuario_id_perfil = perfil_usuario.id_perfil "
//                    + "where cpf=? and senha=?";


//        String sql = "select id_usuario, usuario, cpf,login, senha, desc_perfil from usuarios AS usu";
//                sql += "inner join perfil_usuario As peusu";
//                sql += "on usu.perfil_usuario_id_perfil  peusu.id_perfil";
//                sql += "where usu.cpf=? and usu.senha=?";

            String sql = "select * from usuarios where cpf=? and senha=?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, objusuariodto.getCpf());
            pst.setString(2, objusuariodto.getSenha());

            ResultSet rs = pst.executeQuery();
            return rs;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + erro);
            return null;
        }
    }

}
