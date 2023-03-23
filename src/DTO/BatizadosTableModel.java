package DTO;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rg088
 */
public class BatizadosTableModel extends AbstractTableModel {

    //private List<BatizadosDTO> dados = new ArrayList<>();
    //private String[] colunas = {"Registro", "Nome", "DataNasc", "Sexo", "Naturalidade", "UF", "Endereco", "NrResid", "Bairro", "Complemento", "CEP", "NomeMae", "NomePai", "NomeMadrinha", "NomePadrinho", "DataBatizado", "LocalBatizado", "Celebrante", "Livro", "Folha", "Nr", "NomeLivro", "NomeMaeLivro", "NomePaiLivro", "NomeMadrinhaLivro", "NomePadrinhoLivro", "Observacoes"};
    private List<BatizadosDTO> lista;

    private final int COL_ID = 0;
    private final int COL_NOME = 1;
    private final int COL_DATANASC = 2;
    private final int COL_SEXO = 3;
    private final int COL_NATURALIDADE = 4;
    private final int COL_UF = 5;
    private final int COL_ENDERECO = 6;
    private final int COL_NrRESID = 7;
    private final int COL_BAIRRO = 8;
    private final int COL_COMPLEM = 9;
    private final int COL_CEP = 10;
    private final int COL_NOMEMAE = 11;
    private final int COL_NOMEPAI = 12;
    private final int COL_NOMEMADRINHA = 13;
    private final int COL_NOMEPADRINHO = 14;
    private final int COL_DATABATIZADO = 15;
    private final int COL_LOCALBATIZADO = 16;
    private final int COL_CELEBRANTE = 17;
    private final int COL_LIVRO = 18;
    private final int COL_FOLHA = 19;
    private final int COL_Nr = 20;
    private final int COL_NOMELIVRO = 21;
    private final int COL_NOMEMAELIVRO = 22;
    private final int COL_NOMEPAILIVRO = 23;
    private final int COL_NOMEMADRINHALIVRO = 24;
    private final int COL_NOMEPADRINHOLIVRO = 25;
    private final int COL_OBSERVACOES = 26;

    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //return dados.size();
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //return colunas.length;
        return 27;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        switch (coluna) {

            case 0:
                return lista.get(linha).getIdBatizado();
            case 1:
                return lista.get(linha).getNome();
            case 2:
                return lista.get(linha).getNome();
            case 3:
                return lista.get(linha).getDataNasc();
            case 4:
                return lista.get(linha).getSexo();
            case 5:
                return lista.get(linha).getNaturalidade();
            case 6:
                return lista.get(linha).getUF();
            case 7:
                return lista.get(linha).getEndereco();
            case 8:
                return lista.get(linha).getNrResid();
            case 9:
                return lista.get(linha).getBairro();
            case 10:
                return lista.get(linha).getComplemento();
            case 11:
                return lista.get(linha).getCEP();
            case 12:
                return lista.get(linha).getNomeMae();
            case 13:
                return lista.get(linha).getNomePai();
            case 14:
                return lista.get(linha).getNomeMadrinha();
            case 15:
                return lista.get(linha).getNomePadrinho();
            case 16:
                return lista.get(linha).getDataBatizado();
            case 17:
                return lista.get(linha).getLocalBatizado();
            case 18:
                return lista.get(linha).getCelebrante();
            case 19:
                return lista.get(linha).getLivro();
            case 20:
                return lista.get(linha).getFolha();
            case 21:
                return lista.get(linha).getNumero();
            case 22:
                return lista.get(linha).getNomeLivro();
            case 23:
                return lista.get(linha).getNomeMadrinhaLivro();
            case 24:
                return lista.get(linha).getNomePaiLivro();
            case 25:
                return lista.get(linha).getNomeMadrinhaLivro();
            case 26:
                return lista.get(linha).getNomePadrinhoLivro();
            case 27:
                return lista.get(linha).getObservacoes();
        }

        return null;
    }

    
    public void addRow(BatizadosDTO ba){
          this.lista.add(ba);
          this.fireTableDataChanged();
          
      }
    
    
}
