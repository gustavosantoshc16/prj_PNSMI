package DAO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author NOTEHOME
 */
public class MinhaData {
    Date d;
    SimpleDateFormat formatoBr = new SimpleDateFormat("dd MMMM yyyy  HH:mm:ss");
    //SimpleDateFormat formatohHr = new SimpleDateFormat("HH:mm:ss");
    
    
    public MinhaData(Date d){
        this.d = d;        
    }
    
    public String getDataHora(){
        return formatoBr.format(d);        
    }
    
    public String getDiaSemana(){
        //SimpleDateFormat formatoBr = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        //Date d = new Date();
        String diaSemana = "";
        switch (d.getDay()){
            case 0:
                diaSemana = "Domingo";
                break;
            case 1:
                diaSemana = "Segunda-feira";
                break;
            case 2:
                diaSemana = "Terça-feira";
                break;
            case 3:
                diaSemana = "Quarta-feira";
                break;
            case 4:
                diaSemana = "Quinta-feira";
                break;
            case 5:
                diaSemana = "Sexta-feira";
                break;
            case 6:
                diaSemana = "Sábado";
                break;
            default:
                break;            
    }
        return diaSemana;
    }

}
