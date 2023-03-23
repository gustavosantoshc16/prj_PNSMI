/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.UsuarioDTO;
import DTO.UsuariosTableModel;
import VIEW.FrmTelaUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.sql.*;
import javax.swing.JTable;

/**
 *
 * @author NOTEHOME
 */
public class UsuarioDAO {

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    Statement st;
    
    ArrayList<UsuarioDTO> listadeUsuarios = new ArrayList<>();

    private UsuariosTableModel tbmUsuaurio;
    
    
    public void cadastrarUsuario(UsuarioDTO objusuariodto) {
        String sql = "insert into usuarios(usuario,cpf,login,senha,perfil_usuario_id_perfil) VALUES (?,?,?,?,?)";

        conn = new ConexaoDAO().conectaDB();

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, objusuariodto.getUsuario());
            pst.setString(2, objusuariodto.getCpf());
            pst.setString(3, objusuariodto.getLogin());
            pst.setString(4, objusuariodto.getSenha());
            pst.setString(5, objusuariodto.getPerfil_usuario_id_perfil());

//            pst.execute();
//            pst.close();
            //validação dos campos obrigatorios
            if (//(txtUsuId.getText().isEmpty())||
                    (objusuariodto.getUsuario().isEmpty())
                    || (objusuariodto.getCpf().isEmpty())
                    || (objusuariodto.getLogin().isEmpty())
                    || (objusuariodto.getSenha().isEmpty())
                    || (objusuariodto.getPerfil_usuario_id_perfil().isEmpty())) {

                JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");

            } else {

                //a linha abaixo atualiza a tbl usuarios com os dados do formulario
                //a estrutura abaixo é usada para confirmar a inserção dos dados na tbl
                int adicionado = pst.executeUpdate();
                //a linha abaixo de apoio de entendimento da logica
                //System.out.println(adicionado);

                if (adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");
                    //txtUsuId.setText(null);
                    objusuariodto.setUsuario(null);
                    objusuariodto.setCpf(null);
                    objusuariodto.setLogin(null);
                    objusuariodto.setSenha(null);
                    objusuariodto.setPerfil_usuario_id_perfil(null);

                    pst.execute();
                    pst.close();

                    //limpar();
                    //btnUsuCreate.setEnabled(false);
                    //carregaTabela();
                }
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO Cadastrar:" + erro);
        }
    }

    
//    public ArrayList<UsuarioDTO> PesquisarUsuario() {
//
//        String sql = "select * from usuarios ";
//
//        conn = new ConexaoDAO().conectaDB();
//
//        try {
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            while (rs.next()) {
//                UsuarioDTO objUsuarioDTO = new UsuarioDTO();
//                objUsuarioDTO.setId_usuario(rs.getInt("id_usuario"));
//                objUsuarioDTO.setUsuario(rs.getString("usuario"));
//                objUsuarioDTO.setCpf(rs.getString("cpf"));
//                objUsuarioDTO.setLogin(rs.getString("login"));
//                objUsuarioDTO.setSenha(rs.getString("senha"));
//                objUsuarioDTO.setPerfil_usuario_id_perfil(rs.getString("perfil_usuario_id_perfil"));
//
//                listadeUsuarios.add(objUsuarioDTO);
//
//            }
//        } catch (Exception erro) {
//            JOptionPane.showMessageDialog(null, "UsuarioDAO Pesquisar: " + erro);
//        }
//        return listadeUsuarios;
//    }

    
//    public ArrayList<UsuarioDTO> ListarUsuario() {
//
//        try {
//            //ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
//
//            String sql = "select * from usuarios";
//
//            //conn = new ConexaoDAO().conectaDB();
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            while (rs.next()) {
//                UsuarioDTO objUsuarioDTO = new UsuarioDTO();
//
//                objUsuarioDTO.setId_usuario(rs.getInt("id_usuario"));
//                objUsuarioDTO.setUsuario(rs.getString("usuario"));
//                objUsuarioDTO.setCpf(rs.getString("cpf"));
//                objUsuarioDTO.setLogin(rs.getString("login"));
//                objUsuarioDTO.setSenha(rs.getString("senha"));
//                objUsuarioDTO.setPerfil_usuario_id_perfil(rs.getString("perfil_usuario_id_perfil"));
//
////                rs.getInt("id_usuario");
////                rs.getString("usuario");
////                rs.getString("cpf");
////                rs.getString("login");
////                rs.getString("senha");
////                rs.getString("perfil_usuario_id_perfil");
//                listadeUsuarios.add(objUsuarioDTO);
//            }
//            rs.close();
//            pst.close();
//
//        } catch (SQLException erro) {
//            JOptionPane.showMessageDialog(null, "UsuarioDAO ListarUsuario: " + erro);
//        }
//        return listadeUsuarios;
//    }
    
   

    

}
