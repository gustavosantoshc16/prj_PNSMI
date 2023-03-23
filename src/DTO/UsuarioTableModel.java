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
public class UsuarioTableModel extends AbstractTableModel {

    //private ArrayList<UsuarioDTO> dados = new ArrayList<>();
    private String[] colunas = {"Reg", "Usuario", "CPF", "Login", "Senha", "Perfil"};

    private ArrayList<UsuarioDTO> lista;
    private final int COL_ID = 0;
    private final int COL_USUARIO = 1;
    private final int COL_CPF = 2;
    private final int COL_LOGIN = 3;
    private final int COL_SENHA = 4;
    private final int COL_PERFIL = 5;

    public UsuarioTableModel(List<UsuarioDTO> lista) {
        this.lista = new ArrayList<>();
        //this.lista = lista;
    }

    @Override
    public String getColumnName(int column) {
        //return super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
        //return colunas[column];

        switch (column) {
            case 0:
                return "Reg";
            case 1:
                return "Usuario";
            case 2:
                return "CPF";
            case 3:
                return "Login";
            case 4:
                return "Senha";
            case 5:
                return "Perfil_Usuario";
        }
        return null;
    }

    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this.colunas.length;
        //return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        UsuarioDTO us = this.lista.get(rowIndex);

        switch (columnIndex) {
            case COL_ID:
                return us.getId_usuario();
            case COL_USUARIO:
                return us.getUsuario();
            case COL_CPF:
                return us.getId_usuario();
            case COL_LOGIN:
                return us.getLogin();
            case COL_SENHA:
                return us.getSenha();
            case COL_PERFIL:
                return us.getPerfil_usuario_id_perfil();
        }
        return null;
    }

//        if(columnIndex == COL_ID){
//            return usuario.getId_usuario();
//        }else if(columnIndex == COL_USUARIO){
//            return usuario.getUsuario();
//        }else if (columnIndex == COL_CPF) {
//            return usuario.getCpf();
//        }else if (columnIndex == COL_LOGIN) {
//            return usuario.getLogin();
//        }else if (columnIndex == COL_SENHA) {
//            return usuario.getSenha();
//        }else if (columnIndex == COL_PERFIL) {
//            return usuario.getPerfil_usuario_id_perfil();
//        }
//         return "-";
//                 
//        switch (columnIndex) {
//            case 0:
//                return dados.get(rowIndex).getId_usuario();
//            case 1:
//                return dados.get(rowIndex).getUsuario();
//            case 2:
//                return dados.get(rowIndex).getCpf();
//            case 3:
//                return dados.get(rowIndex).getLogin();
//            case 4:
//                return dados.get(rowIndex).getSenha();
//            case 5:
//                return dados.get(rowIndex).getPerfil_usuario_id_perfil();
//            
//        }        
//        return null;
    //}

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //super.setValueAt(aValue, rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
        UsuarioDTO us = this.lista.get(rowIndex);
        
        switch(columnIndex){
            case COL_ID:
                us.setId_usuario((int)aValue);
                break;
            case COL_USUARIO:
                us.setUsuario(String.valueOf(aValue));
            case COL_CPF:
                us.setCpf(String.valueOf(aValue));
            case COL_LOGIN:
                us.setLogin(String.valueOf(aValue));
            case COL_SENHA:
                us.setSenha(String.valueOf(aValue));
            case COL_PERFIL:
                us.setPerfil_usuario_id_perfil(String.valueOf(aValue));
        }
        fireTableDataChanged();
    }
    
    private int indexOf(UsuarioDTO us){
        return this.lista.indexOf(us);
    }
    
    
    public void addRow(UsuarioDTO us) {
        this.lista.add(us);
        this.fireTableDataChanged();
    }

    public void removeRow(int linha){
        UsuarioDTO us = this.lista.get(linha);
        this.lista.remove(linha);
        super.fireTableRowsDeleted(linha, linha);
    }
    
}
