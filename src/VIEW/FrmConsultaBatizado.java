/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import Atxy2k.CustomTextField.RestrictedTextField;
import DAO.MinhaData;
import DAO.ConexaoDAO;

import DAO.CEP;
import DAO.ViaCEP;
import DAO.ViaCEPBase;
import DAO.ViaCEPException;
import DTO.BatizadosDTO;
import DTO.BatizadosTableModel;
import java.awt.Component;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.LayoutStyle.getInstance;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author rg088
 */
public class FrmConsultaBatizado extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    Statement st;

    /**
     * Creates new form FrmTelaBatizado
     */
    public FrmConsultaBatizado() {
        initComponents();
        conn = ConexaoDAO.conectaDB();
        timer1.start();
        carregaTabela();
        //Update_tableb();
        Fill_Date();

        RestrictedTextField validar = new RestrictedTextField(txtCEP);
        validar.setOnlyNums(true);
        validar.setLimit(8);

        //ICONE DA BARRA DE TITULO
        URL barraIcone = getClass().getResource("/ICONS/home.png");
        Image iconeBarra = Toolkit.getDefaultToolkit().getImage(barraIcone);
        //this.setIconImage(iconeBarra);

        desabilitaCampos();
        btnImprimir.setEnabled(false);
        btnLimparCampos.setEnabled(false);
    }

    public void desabilitaCampos() {
        txtNomeBatizando.setEnabled(false);
        DataNasc.setEnabled(false);
        txtCidade.setEnabled(false);
        cboUF.setEnabled(false);
        txtEndereco.setEnabled(false);
        txtNrResid.setEnabled(false);
        txtBairro.setEnabled(false);
        txtComplem.setEnabled(false);
        txtCEP.setEnabled(false);
        txtNomeMae.setEnabled(false);
        txtNomePai.setEnabled(false);
        txtNomeMadrinha.setEnabled(false);
        txtNomePadrinho.setEnabled(false);
        DataBatiz.setEnabled(false);
        txtLocalCelebracao.setEnabled(false);
        txtCelebrante.setEnabled(false);
        txtLivro.setEnabled(false);
        txtFolha.setEnabled(false);
        txtNumero.setEnabled(false);
        txtNomeLivro.setEnabled(false);
        txtNomeMaeLivro.setEnabled(false);
        txtNomePaiLivro.setEnabled(false);
        txtNomeMadrinhaLivro.setEnabled(false);
        txtNomePadrinhoLivro.setEnabled(false);
        txaObs.setEnabled(false);
    }

    public void habilitaCampos() {
        txtNomeBatizando.setEnabled(true);
        DataNasc.setEnabled(true);
        txtCidade.setEnabled(true);
        cboUF.setEnabled(true);
        txtEndereco.setEnabled(true);
        txtNrResid.setEnabled(true);
        txtBairro.setEnabled(true);
        txtComplem.setEnabled(true);
        txtCEP.setEnabled(true);
        txtNomeMae.setEnabled(true);
        txtNomePai.setEnabled(true);
        txtNomeMadrinha.setEnabled(true);
        txtNomePadrinho.setEnabled(true);
        DataBatiz.setEnabled(true);
        txtLocalCelebracao.setEnabled(true);
        txtCelebrante.setEnabled(true);
        txtLivro.setEnabled(true);
        txtFolha.setEnabled(true);
        txtNumero.setEnabled(true);
        txtNomeLivro.setEnabled(true);
        txtNomeMaeLivro.setEnabled(true);
        txtNomePaiLivro.setEnabled(true);
        txtNomeMadrinhaLivro.setEnabled(true);
        txtNomePadrinhoLivro.setEnabled(true);
        txaObs.setEnabled(true);
    }

    private Component getInstance() {
        return this;
    }

    public void carregaTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tblBatizados.getModel();
        modelo.setNumRows(0);
        tblBatizados.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(1).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(2).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(3).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(4).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(5).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(6).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(7).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(8).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(9).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(10).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(11).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(12).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(13).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(14).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(15).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(16).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(17).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(18).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(19).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(20).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(21).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(22).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(23).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(24).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(25).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(26).setPreferredWidth(20);

        try {
            pst = conn.prepareStatement("SELECT IdBatizado,Nome,DATE_FORMAT(DataNasc,'%d-%m-%Y') AS DataNasc,Sexo,Naturalidade,UF,Endereco,NrResid,Bairro,Complemento,CEP,"
                    + "NomeMae,NomePai,NomeMadrinha,NomePadrinho,DATE_FORMAT(DataBatizado,'%d-%m-%Y') AS DataBatizado,LocalBatizado,Celebrante,Livro,Folha,Numero,NomeLivro,NomeMaeLivro,NomePaiLivro,NomeMadrinhaLivro,NomePadrinhoLivro,Observacoes "
                    + "FROM batizados order by IdBatizado;");

            rs = pst.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12),
                    rs.getString(13),
                    rs.getString(14),
                    rs.getString(15),
                    rs.getString(16),
                    rs.getString(17),
                    rs.getString(18),
                    rs.getString(19),
                    rs.getString(20),
                    rs.getString(21),
                    rs.getString(22),
                    rs.getString(23),
                    rs.getString(24),
                    rs.getString(25),
                    rs.getString(26),
                    rs.getString(27),});

            }
            //conexao.close();
        } catch (Exception erro) {
            JOptionPane.showInternalMessageDialog(getInstance(), "Erro ao carregar a tabela de dados: " + erro, "ERRO", JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(null, "Erro ao carregar a tabela de dados: " + e, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    //metodo para setar informações nos campos
    public void setar_campos() {

        habilitaCampos();

        int setar = tblBatizados.getSelectedRow();

        txtIdBatizado.setText(tblBatizados.getModel().getValueAt(setar, 0).toString());
        txtNomeBatizando.setText(tblBatizados.getModel().getValueAt(setar, 1).toString());
        //txtDataNasc.setText(tblBatizados.getModel().getValueAt(setar, 3).toString());

//        Date date = new SimpleDateFormat("dd-MM-yyyy").parse((String)model.getValueAt(selectedRowIndex, 4).toString());
//        Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(selectedRowIndex, 4).toString());
//        dob.setDate(date);
        try {
            String date = (tblBatizados.getModel().getValueAt(setar, 2).toString());
            Date d = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            DataNasc.setDate(d);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        String sexo = (tblBatizados.getModel().getValueAt(setar, 3).toString());
        if (sexo.equals("M")) {
            rbMasculino.setSelected(true);
        } else {
            rbFeminino.setSelected(true);
        }

        txtCidade.setText(tblBatizados.getModel().getValueAt(setar, 4).toString());
        cboUF.setSelectedItem(tblBatizados.getModel().getValueAt(setar, 5).toString());
        txtEndereco.setText(tblBatizados.getModel().getValueAt(setar, 6).toString());
        txtNrResid.setText(tblBatizados.getModel().getValueAt(setar, 7).toString());
        txtBairro.setText(tblBatizados.getModel().getValueAt(setar, 8).toString());
        txtComplem.setText(tblBatizados.getModel().getValueAt(setar, 9).toString());
        txtCEP.setText(tblBatizados.getModel().getValueAt(setar, 10).toString());
        //jTCep.setText(tblBatizados.getModel().getValueAt(setar, 11).toString());
        //cboBatsexo.setSelectedItem(tblBatizandos.getModel().getValueAt(setar, 5).toString());
        txtNomeMae.setText(tblBatizados.getModel().getValueAt(setar, 11).toString());
        txtNomePai.setText(tblBatizados.getModel().getValueAt(setar, 12).toString());
        txtNomeMadrinha.setText(tblBatizados.getModel().getValueAt(setar, 13).toString());
        txtNomePadrinho.setText(tblBatizados.getModel().getValueAt(setar, 14).toString());

        //txtDataCelebra.setText(tblBatizados.getModel().getValueAt(setar, 16).toString());
        try {
            String date = (tblBatizados.getModel().getValueAt(setar, 15).toString());
            Date db = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            DataBatiz.setDate(db);
        } catch (Exception erro) {
            JOptionPane.showInternalMessageDialog(getInstance(), erro);
            //JOptionPane.showMessageDialog(null, e);
        }

        txtLocalCelebracao.setText(tblBatizados.getModel().getValueAt(setar, 16).toString());
        txtCelebrante.setText(tblBatizados.getModel().getValueAt(setar, 17).toString());
        txtLivro.setText(tblBatizados.getModel().getValueAt(setar, 18).toString());
        txtFolha.setText(tblBatizados.getModel().getValueAt(setar, 19).toString());
        txtNumero.setText(tblBatizados.getModel().getValueAt(setar, 20).toString());
        txtNomeLivro.setText(tblBatizados.getModel().getValueAt(setar, 21).toString());
        txtNomeMaeLivro.setText(tblBatizados.getModel().getValueAt(setar, 22).toString());
        txtNomePaiLivro.setText(tblBatizados.getModel().getValueAt(setar, 23).toString());
        txtNomeMadrinhaLivro.setText(tblBatizados.getModel().getValueAt(setar, 24).toString());
        txtNomePadrinhoLivro.setText(tblBatizados.getModel().getValueAt(setar, 25).toString());
        txaObs.setText(tblBatizados.getModel().getValueAt(setar, 26).toString());

        btnImprimir.setEnabled(true);
        btnLimparCampos.setEnabled(true);
    }

    //metodo para pesquisar pelo nome com filtro
    private void pesquisar_batizado() {
        String sql = "SELECT IdBatizado,Nome,DATE_FORMAT(DataNasc,'%d-%m-%Y') AS DataNasc,Sexo,Naturalidade,UF,Endereco,NrResid,Bairro,Complemento,CEP,NomeMae,NomePai,NomeMadrinha,NomePadrinho,DATE_FORMAT(DataBatizado,'%d-%m-%Y') AS DataBatizado,LocalBatizado,Celebrante,Livro,Folha,Numero,NomeLivro,NomeMaeLivro,NomePaiLivro,NomeMadrinhaLivro,NomePadrinhoLivro,Observacoes FROM batizados WHERE Nome like ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txtBatPesquisar.getText() + "%");
            rs = pst.executeQuery();

            //a linha abx usa a biblioteca rs2xml.jar para preencher a tabela
            tblBatizados.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception erro) {
            JOptionPane.showInternalMessageDialog(getInstance(), "PesquisarBatizado:" + erro);
            //JOptionPane.showMessageDialog(null, e);
        } finally {

            try {
                rs.close();
                pst.close();
            } catch (Exception e) {

            }
            desabilitaCampos();
        }
    }

    private void pesquisar_datanasc(String d1, String d2) {

        try {

            if (d1.equals("") || d2.equals("")) {
                pst = conn.prepareStatement("SELECT IdBatizado,Nome,DataNasc,Sexo,Naturalidade,UF,Endereco,NrResid,Bairro,Complemento,CEP,NomeMae,NomePai,NomeMadrinha,NomePadrinho,DataBatizado,LocalBatizado,Celebrante,Livro,Folha,Numero,NomeLivro,NomeMaeLivro,NomePaiLivro,NomeMadrinhaLivro,NomePadrinhoLivro,Observacoes FROM batizados");

            } else {
                pst = conn.prepareStatement("SELECT IdBatizado,Nome,DataNasc,Sexo,Naturalidade,UF,Endereco,NrResid,Bairro,Complemento,CEP,NomeMae,NomePai,NomeMadrinha,NomePadrinho,DataBatizado,LocalBatizado,Celebrante,Livro,Folha,Numero,NomeLivro,NomeMaeLivro,NomePaiLivro,NomeMadrinhaLivro,NomePadrinhoLivro,Observacoes FROM batizados WHERE DataNasc BETWEEN ? And ?");
                pst.setString(1, d1);
                pst.setString(2, d2);
            }
            rs = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tblBatizados.getModel();

            Object[] row;

            while (rs.next()) {
                row = new Object[27];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getDate(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getString(12);
                row[12] = rs.getString(13);
                row[13] = rs.getString(14);
                row[14] = rs.getString(15);
                row[15] = rs.getDate(16);
                row[16] = rs.getString(17);
                row[17] = rs.getString(18);
                row[18] = rs.getString(19);
                row[19] = rs.getString(20);
                row[20] = rs.getString(21);
                row[21] = rs.getString(22);
                row[22] = rs.getString(23);
                row[23] = rs.getString(24);
                row[24] = rs.getString(25);
                row[25] = rs.getString(26);
                row[26] = rs.getString(27);

                modelo.addRow(row);
            }
        } catch (Exception erro) {
            JOptionPane.showInternalMessageDialog(getInstance(), "PesquisaDataNasc:" + erro);
            //JOptionPane.showMessageDialog(null, e);
        } finally {

            try {
                rs.close();
                pst.close();
            } catch (Exception e) {

            }
            desabilitaCampos();
        }
    }

    private void pesquisar_databatizado(String d3, String d4) {
        try {

            if (d3.equals("") || d4.equals("")) {
                pst = conn.prepareStatement("SELECT IdBatizado,Nome,DataNasc,Sexo,Naturalidade,UF,Endereco,NrResid,Bairro,Complemento,CEP,NomeMae,NomePai,NomeMadrinha,NomePadrinho,DataBatizado,LocalBatizado,Celebrante,Livro,Folha,Numero,NomeLivro,NomeMaeLivro,NomePaiLivro,NomeMadrinhaLivro,NomePadrinhoLivro,Observacoes FROM batizados");

            } else {
                pst = conn.prepareStatement("SELECT IdBatizado,Nome,DataNasc,Sexo,Naturalidade,UF,Endereco,NrResid,Bairro,Complemento,CEP,NomeMae,NomePai,NomeMadrinha,NomePadrinho,DataBatizado,LocalBatizado,Celebrante,Livro,Folha,Numero,NomeLivro,NomeMaeLivro,NomePaiLivro,NomeMadrinhaLivro,NomePadrinhoLivro,Observacoes FROM batizados WHERE DataNasc BETWEEN ? And ?");
                pst.setString(3, d3);
                pst.setString(4, d4);
            }
            rs = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tblBatizados.getModel();

            Object[] row;

            while (rs.next()) {
                row = new Object[27];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getDate(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getString(12);
                row[12] = rs.getString(13);
                row[13] = rs.getString(14);
                row[14] = rs.getString(15);
                row[15] = rs.getDate(16);
                row[16] = rs.getString(17);
                row[17] = rs.getString(18);
                row[18] = rs.getString(19);
                row[19] = rs.getString(20);
                row[20] = rs.getString(21);
                row[21] = rs.getString(22);
                row[22] = rs.getString(23);
                row[23] = rs.getString(24);
                row[24] = rs.getString(25);
                row[25] = rs.getString(26);
                row[26] = rs.getString(27);

                modelo.addRow(row);
            }
        } catch (Exception erro) {
            JOptionPane.showInternalMessageDialog(getInstance(), "PesquisaDataBatizado:" + erro);
            //JOptionPane.showMessageDialog(null, e);
        } finally {

            try {
                rs.close();
                pst.close();
            } catch (Exception e) {

            }
            desabilitaCampos();
        }
    }

    public void Fill_Date() {
        Date date = new Date();
//        DataNasc.setDateFormatString("dd-MM-yyyy");
//        DataNasc.setDate(date);
//        DataBatiz.setDateFormatString("dd-MM-yyyy");
//        DataBatiz.setDate(date);

    }

    public void limparPesquisar() {
        txtBatPesquisar.setText(null);
    }

    //metodo para limpar os campos do formulario e da table
    private void limpar() {
        txtBatPesquisar.setText(null);
        txtIdBatizado.setText(null);
        txtNomeBatizando.setText(null);
        //cboBatsexo.setSelectedItem(null);
        //txtDataNasc.setText(null);
        DataNasc.setDate(null);
        txtEndereco.setText(null);
        txtNrResid.setText(null);
        txtBairro.setText(null);
        txtComplem.setText(null);
        txtCEP.setText(null);
        //jTCep.setText(null);
        txtCidade.setText(null);
        cboUF.setSelectedItem(null);
        txtNomeMae.setText(null);
        txtNomePai.setText(null);
        txtNomeMadrinha.setText(null);
        txtNomePadrinho.setText(null);
        //txtDataCelebra.setText(null);
        DataBatiz.setDate(null);
        txtLocalCelebracao.setText(null);
        txtCelebrante.setText(null);
        txtLivro.setText(null);
        txtFolha.setText(null);
        txtNumero.setText(null);
        txtNomeLivro.setText(null);
        txtNomeMaeLivro.setText(null);
        txtNomePaiLivro.setText(null);
        txtNomeMadrinhaLivro.setText(null);
        txtNomePadrinhoLivro.setText(null);
        txaObs.setText(null);
        lblStatusCep.setText(null);
        txtBatPesquisar.setText(null);
        d1.setDate(null);
        d2.setDate(null);
        d3.setDate(null);
        d4.setDate(null);
        
        desabilitaCampos();
        carregaTabela();
        btnImprimir.setEnabled(false);
        btnLimparCampos.setEnabled(false);

        //((DefaultTableModel) tblBatizados.getModel()).setRowCount(0);
    }

    private void buscarCep() {
        String logradouro = "";
        String tipoLogradouro = "";
        String resultado = null;
        String cep = txtCEP.getText();

        try {
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
            SAXReader xml = new SAXReader();
            Document documento = xml.read(url);
            Element root = documento.getRootElement();

            // iterate through child elements of root
            for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
                Element element = it.next();

                if (element.getQualifiedName().equals("cidade")) {
                    txtCidade.setText(element.getText());
                }
                if (element.getQualifiedName().equals("bairro")) {
                    txtBairro.setText(element.getText());
                }
                if (element.getQualifiedName().equals("uf")) {
                    cboUF.setSelectedItem(element.getText());
                }
                if (element.getQualifiedName().equals("tipo_logradouro")) {
                    tipoLogradouro = element.getText();
                }
                if (element.getQualifiedName().equals("logradouro")) {
                    logradouro = element.getText();
                }
                if (element.getQualifiedName().equals("resultado")) {
                    resultado = element.getText();
                    if (resultado.equals("1")) {
                        lblStatusCep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/check.png")));
                    } else {
                        JOptionPane.showMessageDialog(null, "CEP não encontrado!");
                    }
                }
            }
            //SETAR O CAMPO ENDEREÇO
            txtEndereco.setText(tipoLogradouro + " " + logradouro);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void imprimir_declaracao() {

        int confirma = JOptionPane.showInternalConfirmDialog(getInstance(), "Confirma a emissão desta declaração?", "Atenção", JOptionPane.YES_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {

            try {
                
                //usando a class HashMap para criar um filtro
                HashMap filtro = new HashMap();
                filtro.put("IdBatizado", Integer.parseInt(txtIdBatizado.getText()));

                JasperPrint print = JasperFillManager.fillReport("D:/ProjectsNetBeans/reports/DeclaracaoBatismo.jasper", filtro, conn);
                //JasperViewer.viewReport(print, false);
                JasperViewer jv = new JasperViewer(print,false);
                jv.setVisible(true);
                jv.toFront();

            } catch (Exception erro) {
                JOptionPane.showInternalMessageDialog(getInstance(), "ImpressãoDeclaração:" + erro);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timer1 = new org.netbeans.examples.lib.timerbean.Timer();
        jPanel8 = new javax.swing.JPanel();
        jpSexo = new javax.swing.JPanel();
        rbMasculino = new javax.swing.JRadioButton();
        rbFeminino = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNomeBatizando = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cboUF = new javax.swing.JComboBox<>();
        txtIdBatizado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtComplem = new javax.swing.JTextField();
        DataNasc = new com.toedter.calendar.JDateChooser();
        btnBuscarCep = new javax.swing.JButton();
        txtCEP = new javax.swing.JTextField();
        txtNrResid = new javax.swing.JTextField();
        lblStatusCep = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtNomeMae = new javax.swing.JTextField();
        txtNomeMadrinha = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtNomePadrinho = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtNomePai = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtLocalCelebracao = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        txtFolha = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtLivro = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtCelebrante = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        DataBatiz = new com.toedter.calendar.JDateChooser();
        txtNomeMaeLivro = new javax.swing.JTextField();
        txtNomeMadrinhaLivro = new javax.swing.JTextField();
        txtNomePaiLivro = new javax.swing.JTextField();
        txtNomePadrinhoLivro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNomeLivro = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaObs = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtBatPesquisar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        d1 = new com.toedter.calendar.JDateChooser();
        d2 = new com.toedter.calendar.JDateChooser();
        btnPDataNasc = new javax.swing.JButton();
        btnPDataBatizado = new javax.swing.JButton();
        d3 = new com.toedter.calendar.JDateChooser();
        d4 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        lblDataHora = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBatizados = new javax.swing.JTable();
        btnImprimir = new javax.swing.JButton();
        btnLimparCampos = new javax.swing.JButton();

        timer1.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                timer1OnTime(evt);
            }
        });

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Consulta de Batizados");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(1024, 680));
        setVisible(true);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Batizando", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bookman Old Style", 1, 14), new java.awt.Color(0, 153, 153))); // NOI18N

        jpSexo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sexo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        jpSexo.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        rbMasculino.setText("Masculino");
        rbMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMasculinoActionPerformed(evt);
            }
        });

        rbFeminino.setText("Feminino");
        rbFeminino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFemininoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpSexoLayout = new javax.swing.GroupLayout(jpSexo);
        jpSexo.setLayout(jpSexoLayout);
        jpSexoLayout.setHorizontalGroup(
            jpSexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSexoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbMasculino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbFeminino)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpSexoLayout.setVerticalGroup(
            jpSexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSexoLayout.createSequentialGroup()
                .addGroup(jpSexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbMasculino, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbFeminino, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel16.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Código");

        jLabel9.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("* Nome Batizando(a)");
        jLabel9.setToolTipText("");

        txtNomeBatizando.setEditable(false);

        jLabel17.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Data Nascimento");

        jLabel18.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Cidade");

        txtCidade.setEditable(false);

        jLabel21.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("UF");

        cboUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "    ", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", " " }));
        cboUF.setEnabled(false);

        txtIdBatizado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtIdBatizado.setEnabled(false);
        txtIdBatizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBatizadoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Endereço");

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nr. Resid");

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Bairro");

        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Complemento");

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CEP");

        txtEndereco.setEditable(false);

        txtBairro.setEditable(false);

        txtComplem.setEditable(false);

        DataNasc.setDateFormatString("dd-MM-yyyy");
        DataNasc.setEnabled(false);

        btnBuscarCep.setText("Buscar CEP");
        btnBuscarCep.setEnabled(false);
        btnBuscarCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCepActionPerformed(evt);
            }
        });

        txtCEP.setEditable(false);

        txtNrResid.setEditable(false);
        txtNrResid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNrResidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNrResid, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtComplem, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdBatizado, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtNomeBatizando, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jpSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblStatusCep, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarCep))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(78, 78, 78)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboUF, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIdBatizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNomeBatizando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jpSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(jLabel9))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStatusCep, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarCep))))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNrResid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtComplem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel18)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Filiação / Padrinhos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bookman Old Style", 1, 14), new java.awt.Color(0, 153, 153))); // NOI18N

        jLabel26.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("* Nome Mãe");

        txtNomeMae.setEditable(false);

        txtNomeMadrinha.setEditable(false);

        jLabel27.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Nome Madrinha");

        txtNomePadrinho.setEditable(false);

        jLabel28.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Nome Padrinho");

        txtNomePai.setEditable(false);

        jLabel29.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Nome Pai");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomeMadrinha, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomePadrinho, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeMadrinha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomePadrinho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Registro Livro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bookman Old Style", 1, 14), new java.awt.Color(0, 153, 153))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Data Celebração");

        jLabel23.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Local Celebração");

        txtLocalCelebracao.setEditable(false);

        jLabel24.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Nr");

        txtNumero.setEditable(false);

        txtFolha.setEditable(false);

        jLabel25.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Folha");

        txtLivro.setEditable(false);

        jLabel30.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Celebrante");

        txtCelebrante.setEditable(false);

        jLabel31.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Livro");

        DataBatiz.setDateFormatString("dd-MM-yyyy");
        DataBatiz.setEnabled(false);

        txtNomeMaeLivro.setEditable(false);

        txtNomeMadrinhaLivro.setEditable(false);

        txtNomePaiLivro.setEditable(false);

        txtNomePadrinhoLivro.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Mãe");

        jLabel7.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Pai");

        jLabel8.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Madrinha");

        jLabel10.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Padrinho");

        jLabel13.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel13.setText("Nome Batizando(a)");

        txtNomeLivro.setEditable(false);

        txaObs.setEditable(false);
        txaObs.setColumns(20);
        txaObs.setRows(5);
        jScrollPane2.setViewportView(txaObs);

        jLabel12.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Observações");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DataBatiz, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtLocalCelebracao, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(46, 46, 46)))
                .addGap(20, 20, 20))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(154, 154, 154)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtNomeMaeLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNomePaiLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtNomeMadrinhaLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNomePadrinhoLivro)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtCelebrante, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(txtFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addGap(1, 1, 1)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtLocalCelebracao)
                    .addComponent(DataBatiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCelebrante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel30)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFolha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomePaiLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeMaeLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomePadrinhoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeMadrinhaLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bookman Old Style", 1, 14), new java.awt.Color(0, 153, 153))); // NOI18N

        txtBatPesquisar.setPreferredSize(new java.awt.Dimension(6, 10));
        txtBatPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBatPesquisarActionPerformed(evt);
            }
        });
        txtBatPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBatPesquisarKeyReleased(evt);
            }
        });

        jLabel11.setText("Nome:");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel14.setText("Data Nascimento:");

        btnPDataNasc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/search16x16(2).png"))); // NOI18N
        btnPDataNasc.setToolTipText("Pesquisar");
        btnPDataNasc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPDataNasc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDataNascActionPerformed(evt);
            }
        });

        btnPDataBatizado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/search16x16(2).png"))); // NOI18N
        btnPDataBatizado.setToolTipText("Pesquisar");
        btnPDataBatizado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPDataBatizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDataBatizadoActionPerformed(evt);
            }
        });

        jLabel15.setText("Data Batizado:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBatPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(8, 8, 8)
                .addComponent(d1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPDataNasc)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPDataBatizado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPDataBatizado)
                    .addComponent(d4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(d3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtBatPesquisar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnPDataNasc)
                                    .addComponent(d2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(d1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel11)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        lblDataHora.setText("DataHora");
        lblDataHora.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tblBatizados = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return (false);
            }
        };
        tblBatizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Data Nasc", "Sexo", "Natural", "UF", "Endereco", "Nr Resid", "Bairro", "Complem", "CEP", "Mãe", "Pai", "Madrinha", "Padrinho", "Data Batizado", "Local Batizado", "Celebrante", "Livro", "Folha", "Nr", "NomeLivro", "MaeLivro", "PaiLivro", "MadrinhaLivro", "PadrinhoLivro", "Obs"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBatizados.setFocusable(false);
        tblBatizados.getTableHeader().setReorderingAllowed(false);
        tblBatizados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBatizadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBatizados);

        btnImprimir.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/print16x16.png"))); // NOI18N
        btnImprimir.setText("Imprimir Declaração");
        btnImprimir.setToolTipText("Imprimir Declaração");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnLimparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/clear16x16.png"))); // NOI18N
        btnLimparCampos.setText("Limpar Campos");
        btnLimparCampos.setToolTipText("Limpar Campos");
        btnLimparCampos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparCamposActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(btnImprimir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLimparCampos)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLimparCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1330, 680);
    }// </editor-fold>//GEN-END:initComponents

    private void rbMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMasculinoActionPerformed
        // TODO add your handling code here:
        sexo = "M";
        rbMasculino.setSelected(true);
        rbFeminino.setSelected(false);
    }//GEN-LAST:event_rbMasculinoActionPerformed

    private void rbFemininoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFemininoActionPerformed
        // TODO add your handling code here:
        sexo = "F";
        rbFeminino.setSelected(true);
        rbMasculino.setSelected(false);
    }//GEN-LAST:event_rbFemininoActionPerformed

    private void txtIdBatizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBatizadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBatizadoActionPerformed

    private void btnBuscarCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCepActionPerformed
        buscarCep();
    }//GEN-LAST:event_btnBuscarCepActionPerformed

    private void txtBatPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBatPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBatPesquisarActionPerformed

    private void txtBatPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBatPesquisarKeyReleased
        pesquisar_batizado();
    }//GEN-LAST:event_txtBatPesquisarKeyReleased

    private void tblBatizadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBatizadosMouseClicked
        // chamar metodo setar_campos
        setar_campos();
    }//GEN-LAST:event_tblBatizadosMouseClicked

    private void txtNrResidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNrResidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNrResidActionPerformed

    private void timer1OnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timer1OnTime
        MinhaData minha = new MinhaData(new java.util.Date());
        lblDataHora.setText(minha.getDiaSemana() + ", " + minha.getDataHora());
    }//GEN-LAST:event_timer1OnTime

    private void btnPDataNascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDataNascActionPerformed

        try {
            tblBatizados.setModel(new DefaultTableModel(null, new Object[]{"IdBatizado", "Nome", "DataNasc", "Sexo", "Naturalidade", "UF", "Endereco", "NrResid", "Bairro", "Complemento", "CEP", "NomeMae", "NomePai", "NomeMadrinha", "NomePadrinho", "DataBatizado", "LoacalBatizado", "Celebrante", "Livro", "Folha", "Numero", "NomeLivro", "NomeMaeLivro", "NomePaiLivro", "NomeMadrinhaLivro", "NomePadrinhoLivro", "Observacoes", "",}));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = sdf.format(d1.getDate());
            String date2 = sdf.format(d2.getDate());
            pesquisar_datanasc(date1, date2);

        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(getInstance(), e);
        }
    }//GEN-LAST:event_btnPDataNascActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        imprimir_declaracao();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnPDataBatizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDataBatizadoActionPerformed
        try {
            tblBatizados.setModel(new DefaultTableModel(null, new Object[]{"IdBatizado", "Nome", "DataNasc", "Sexo", "Naturalidade", "UF", "Endereco", "NrResid", "Bairro", "Complemento", "CEP", "NomeMae", "NomePai", "NomeMadrinha", "NomePadrinho", "DataBatizado", "LoacalBatizado", "Celebrante", "Livro", "Folha", "Numero", "NomeLivro", "NomeMaeLivro", "NomePaiLivro", "NomeMadrinhaLivro", "NomePadrinhoLivro", "Observacoes", "",}));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date3 = sdf.format(d3.getDate());
            String date4 = sdf.format(d4.getDate());
            pesquisar_datanasc(date3, date4);

        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(getInstance(), e);
        }
    }//GEN-LAST:event_btnPDataBatizadoActionPerformed

    private void btnLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparCamposActionPerformed
        limpar();
    }//GEN-LAST:event_btnLimparCamposActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DataBatiz;
    private com.toedter.calendar.JDateChooser DataNasc;
    private javax.swing.JButton btnBuscarCep;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnLimparCampos;
    private javax.swing.JButton btnPDataBatizado;
    private javax.swing.JButton btnPDataNasc;
    private javax.swing.JComboBox<String> cboUF;
    private com.toedter.calendar.JDateChooser d1;
    private com.toedter.calendar.JDateChooser d2;
    private com.toedter.calendar.JDateChooser d3;
    private com.toedter.calendar.JDateChooser d4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpSexo;
    private javax.swing.JLabel lblDataHora;
    private javax.swing.JLabel lblStatusCep;
    private javax.swing.JRadioButton rbFeminino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JTable tblBatizados;
    private org.netbeans.examples.lib.timerbean.Timer timer1;
    private javax.swing.JTextArea txaObs;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtBatPesquisar;
    private javax.swing.JTextField txtCEP;
    private javax.swing.JTextField txtCelebrante;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtComplem;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtFolha;
    private javax.swing.JTextField txtIdBatizado;
    private javax.swing.JTextField txtLivro;
    private javax.swing.JTextField txtLocalCelebracao;
    private javax.swing.JTextField txtNomeBatizando;
    private javax.swing.JTextField txtNomeLivro;
    private javax.swing.JTextField txtNomeMadrinha;
    private javax.swing.JTextField txtNomeMadrinhaLivro;
    private javax.swing.JTextField txtNomeMae;
    private javax.swing.JTextField txtNomeMaeLivro;
    private javax.swing.JTextField txtNomePadrinho;
    private javax.swing.JTextField txtNomePadrinhoLivro;
    private javax.swing.JTextField txtNomePai;
    private javax.swing.JTextField txtNomePaiLivro;
    private javax.swing.JTextField txtNrResid;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables

    private String sexo;

}
