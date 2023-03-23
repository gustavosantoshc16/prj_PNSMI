package DAO;
//package br.com.pnsmi.utilitarios;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author NOTEHOME
 */
public class MinhaHora {
    Date h;    
    SimpleDateFormat formatoHr = new SimpleDateFormat("HH:mm:ss");
    
    public MinhaHora(Date h){
        this.h = h;        
    }
    
    public String getDataHora(){
        return formatoHr.format(h);        
    }
    
}
