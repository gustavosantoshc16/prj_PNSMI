/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author NOTEHOME
 */
public class UsuarioDTO {
    
    private int id_usuario;
    private String usuario,cpf,login,senha,perfil_usuario_id_perfil;
//    private String cpf;
//    private String login;
//    private String senha;
//    private String perfil;

 

//    public UsuarioDTO(int id_usuario, String usuario, String cpf, String login, String senha, String perfil_usuario_id_perfil) {
//        this.id_usuario = id_usuario;
//        this.usuario = usuario;
//        this.cpf = cpf;
//        this.login = login;
//        this.senha = senha;
//        this.perfil_usuario_id_perfil = perfil_usuario_id_perfil;
//    }

//    public UsuarioDTO() {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil_usuario_id_perfil() {
        return perfil_usuario_id_perfil;
    }

    public void setPerfil_usuario_id_perfil(String perfil_usuario_id_perfil) {
        this.perfil_usuario_id_perfil = perfil_usuario_id_perfil;
    }

   

    
    
    
}
