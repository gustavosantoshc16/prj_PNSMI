
package DAO;

import DTO.BatizadosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class BatizadosDAO {
    
    SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    ArrayList<BatizadosDTO> lista = new ArrayList<>();
    
    
    
    public ArrayList<BatizadosDTO> ListarBatizados() {
        ArrayList<BatizadosDTO> batizados = new ArrayList<BatizadosDTO>();
    
        String sql = "select * from batizados ";

        try {
            conn = new ConexaoDAO().conectaDB();
            pst = conn.prepareStatement(sql);            
            rs = pst.executeQuery();

            while (rs.next()) {
                BatizadosDTO objBatizadosDTO = new BatizadosDTO();                
                                
                objBatizadosDTO.setIdBatizado(rs.getInt("IdBatizado"));
                objBatizadosDTO.setNome(rs.getString("Nome"));
                objBatizadosDTO.setDataNasc(rs.getDate("DataNasc"));
                objBatizadosDTO.setSexo(rs.getString("Sexo"));
                objBatizadosDTO.setNaturalidade(rs.getString("Naturalidade"));
                objBatizadosDTO.setUF(rs.getString("UF"));
                objBatizadosDTO.setEndereco(rs.getString("Endereco"));
                objBatizadosDTO.setNrResid(rs.getString("NrResid"));
                objBatizadosDTO.setBairro(rs.getString("Bairro"));
                objBatizadosDTO.setComplemento(rs.getString("Complemento"));
                objBatizadosDTO.setCEP(rs.getString("CEP"));
                objBatizadosDTO.setNomeMae(rs.getString("NomeMae"));
                objBatizadosDTO.setNomePai(rs.getString("NomePai"));
                objBatizadosDTO.setNomeMadrinha(rs.getString("NomeMadrinha"));
                objBatizadosDTO.setNomePadrinho(rs.getString("NomePadrinho"));
                objBatizadosDTO.setIdBatizado(rs.getInt("DataBatizado"));
                objBatizadosDTO.setLocalBatizado(rs.getString("LocalBatizado"));
                objBatizadosDTO.setCelebrante(rs.getString("Celebrante"));
                objBatizadosDTO.setLivro(rs.getString("Livro"));
                objBatizadosDTO.setFolha(rs.getString("Folha"));
                objBatizadosDTO.setNumero(rs.getString("Numero"));
                objBatizadosDTO.setNomeLivro(rs.getString("NomeLivro"));
                objBatizadosDTO.setNomeMaeLivro(rs.getString("NomeMaeLivro"));
                objBatizadosDTO.setNomePaiLivro(rs.getString("NomePaiLivro"));
                objBatizadosDTO.setNomeMadrinhaLivro(rs.getString("NomeMadrinhaLivro"));
                objBatizadosDTO.setNomePadrinhoLivro(rs.getString("NomePadrinhoLivro"));
                objBatizadosDTO.setObservacoes(rs.getString("Observacoes"));

                lista.add(objBatizadosDTO);
                
            }
            rs.close();
            pst.close();
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "BatizadosDAO Listar: " + erro);
        }
        return lista;
    }
    
    
    
}
