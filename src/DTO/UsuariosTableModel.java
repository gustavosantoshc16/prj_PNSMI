/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DAO.UsuarioDAO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rg088
 */
public class UsuariosTableModel extends AbstractTableModel {
    
    private List<UsuarioDTO> lista;
    
//    private final int COL_ID = 0;
//    private final int COL_USUARIO = 1;
//    private final int COL_CPF = 2;
//    private final int COL_LOGIN = 3;
//    private final int COL_SENHA = 4;
//    private final int COL_PERFIL = 5;
    
    
    private String[] colunas = {"Reg", "Usuario", "CPF", "Login", "Senha", "Perfil"};
    private List<UsuarioDTO> dados = new ArrayList<>();
    
    
    public UsuariosTableModel(List<UsuarioDTO> lista){
        this.dados = new ArrayList<>();
        //this.lista = lista;
    }

    public UsuariosTableModel() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void addUsuario(UsuarioDTO u){
        this.dados.add(u);
        //this.lista.add(u);
        fireTableDataChanged();        
    }
    
    public void removerUsuario(int linha){
        this.dados.remove(linha);
        //this.lista.remove(linha);
        fireTableDataChanged();
    }
    
    
//    public UsuarioDTO getUsuario(int linha){
//        //return this.dados.get(linha);
//        return this.lista.get(linha);
//    }

    @Override
    public String getColumnName(int column) {
        //return super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
        return colunas[column];                
        
//        switch (column) {
//            case 0:
//                return "id_usuario";
//            case 1:
//                return "usuario";
//            case 2:
//                return "cpf";
//            case 3:
//                return "login";
//            case 4:
//                return "senha";
//            case 5:
//                return "perfil_usuario_id_perfil";        
//        }        
//        return "";        
//    }
    
//        if(column == COL_ID){
//            return "id_usuario";
//        }else if(column == COL_USUARIO){
//                return "usuario";
//        }else if(column == COL_CPF){
//                return "cpf";
//        }else if (column == COL_LOGIN){
//                return "login";
//        }else if (column == COL_SENHA){
//                return "senha";
//        }else if (column == COL_PERFIL){
//                return "perfil_usuario_id_perfil";
//        }
//        return "";
//    }
   
   }
    
    
    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         return lista.size();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         //return colunas.length;
         return 6;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        UsuarioDTO usuario = this.lista.get(linha);
        
//        if(coluna == COL_ID){
//            return usuario.getId_usuario();
//        }else if(coluna == COL_USUARIO){
//            return usuario.getUsuario();
//        }else if (coluna == COL_CPF) {
//            return usuario.getCpf();
//        }else if (coluna == COL_LOGIN) {
//            return usuario.getLogin();
//        }else if (coluna == COL_SENHA) {
//            return usuario.getSenha();
//        }else if (coluna == COL_PERFIL) {
//            return usuario.getPerfil_usuario_id_perfil();
//        }
//         return "-";
        
        switch (coluna) {

            case 0:
                return dados.get(linha).getId_usuario();
            case 1:
                return dados.get(linha).getUsuario();
            case 2:
                return dados.get(linha).getCpf();
            case 3:
                return dados.get(linha).getLogin();
            case 4:
                return dados.get(linha).getSenha();
            case 5:
                return dados.get(linha).getPerfil_usuario_id_perfil();
            
        }        
        return null;        
    }

    
    
      public void addRow(UsuarioDTO us){
          //this.dados.add(us);
          this.lista.add(us);
          this.fireTableDataChanged();
          
      }
}
