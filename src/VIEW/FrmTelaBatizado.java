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
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.LayoutStyle.getInstance;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author rg088
 */
public class FrmTelaBatizado extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    Statement st;

    /**
     * Creates new form FrmTelaBatizado
     */
    public FrmTelaBatizado() {
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

        btnCreate.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnRemover.setEnabled(false);
        btnCancelar.setEnabled(false);
        desabilitaCampos();
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

        //A linha abx desabilita o btnCreate(add)
        btnCreate.setEnabled(false);
        btnRemover.setEnabled(true);
        btnUpdate.setEnabled(true);
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
            JOptionPane.showInternalMessageDialog(getInstance(),"PesquisarBatizado:" + erro);
            //JOptionPane.showMessageDialog(null, erro);
        } finally {

            try {
                rs.close();
                pst.close();
            } catch (Exception e) {

            }
            desabilitaCampos();
            //limpar();
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
            JOptionPane.showInternalMessageDialog(getInstance(),"PesquisarDataNasc:" + erro);
            //JOptionPane.showMessageDialog(null, e);
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
                        JOptionPane.showInternalMessageDialog(getInstance(), "CEP não encontrado!");
                    }
                }
            }
            //SETAR O CAMPO ENDEREÇO
            txtEndereco.setText(tipoLogradouro + " " + logradouro);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void AddBatizado() {
        int p = JOptionPane.showInternalConfirmDialog(getInstance(), "Are you sure you want to add record?", "Add Record", JOptionPane.YES_NO_OPTION);
        if (p == 0) {

            try {
                java.util.Date javaDate = new java.util.Date();
                java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
                java.sql.Timestamp datereg = new java.sql.Timestamp(new java.util.Date().getTime());

                String sql = "INSERT INTO batizados (DataReg,Nome,DataNasc,Sexo,Naturalidade,UF,Endereco,NrResid,Bairro,Complemento,CEP,"
                        + "NomeMae,NomePai,NomeMadrinha,NomePadrinho,DataBatizado,LocalBatizado,Celebrante,Livro,Folha,Numero,NomeLivro,NomeMaeLivro,NomePaiLivro,NomeMadrinhaLivro,NomePadrinhoLivro,Observacoes) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                pst = conn.prepareStatement(sql);
                pst.setTimestamp(1, datereg);
                //pst.setDate(1, mySQLDate);
                pst.setString(2, txtNomeBatizando.getText());
                //pst.setString(3, txtDataNasc.getText());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(DataNasc.getDate());
                pst.setString(3, date);
                pst.setString(4, sexo);
                pst.setString(5, txtCidade.getText());
                pst.setString(6, cboUF.getSelectedItem().toString());
                pst.setString(7, txtEndereco.getText());
                pst.setString(8, txtNrResid.getText());
                pst.setString(9, txtBairro.getText());
                pst.setString(10, txtComplem.getText());
                pst.setString(11, txtCEP.getText());
                //pst.setString(11, jTCep.getText());
                //pst.setString(5, cboBatsexo.getSelectedItem().toString());
                pst.setString(12, txtNomeMae.getText());
                pst.setString(13, txtNomePai.getText());
                pst.setString(14, txtNomeMadrinha.getText());
                pst.setString(15, txtNomePadrinho.getText());
                //pst.setDate(16, mySQLDate);
                SimpleDateFormat sdfb = new SimpleDateFormat("yyyy-MM-dd");
                String dateb = sdfb.format(DataBatiz.getDate());
                pst.setString(16, dateb);
                //pst.setString(16, txtDataCelebra.getText());
                pst.setString(17, txtLocalCelebracao.getText());
                pst.setString(18, txtCelebrante.getText());
                pst.setString(19, txtLivro.getText());
                pst.setString(20, txtFolha.getText());
                pst.setString(21, txtNumero.getText());
                pst.setString(22, txtNomeLivro.getText());
                pst.setString(23, txtNomeMaeLivro.getText());
                pst.setString(24, txtNomePaiLivro.getText());
                pst.setString(25, txtNomeMadrinhaLivro.getText());
                pst.setString(26, txtNomePadrinhoLivro.getText());
                pst.setString(27, txaObs.getText());
                // pst.setString(16, txtIdBatizando.getText());

                //validação dos campos obrigatorios
                if ((txtNomeBatizando.getText().equals(""))
                        //|| (txtDataNasc.getText().equals(""))
                        //|| (dob.getDate().toString().equals(""))
                        //|| (txtNaturalidade.getText().equals(""))
                        //|| (cboBatUF.getSelectedItem().toString().equals(""))
                        //|| (cboBatsexo.getSelectedItem().toString().equals(""))
                        || (txtNomeMae.getText().equals(""))) {
                    JOptionPane.showInternalMessageDialog(getInstance(), "Preencha os Campos Obrigatórios!");
                    txtNomeBatizando.requestFocus();
                    txtNomeMae.requestFocus();

                } else {

                    //a linha abaixo atualiza a tbusuarios com os dados do formulario
                    //a estrutura abaixo é usada para confirmar a inserção dos dados na tb
                    int adicionado = pst.executeUpdate();
                    //a linha abaixo de apoio de entendimento da logica
                    //System.out.println(adicionado);
                    if (adicionado > 0) {
                        JOptionPane.showInternalMessageDialog(getInstance(), "Registro adicionado com sucesso!");
                        carregaTabela();
                        btnCreate.setEnabled(false);
                        limpar();
                        desabilitaCampos();
                    }
                }
            } catch (Exception erro) {
                JOptionPane.showInternalMessageDialog(getInstance(),"AddBatizado:" + erro);
            }
        }
    }

    private void UpdateBatizado() {

        int p = JOptionPane.showInternalConfirmDialog(getInstance(), "Are you sure you want to update?", "Update Record", JOptionPane.YES_NO_OPTION);
        if (p == 0) {

            try {
                String sql = "UPDATE batizados SET Nome=?, DataNasc=?,Naturalidade=?,UF=?,Endereco=?,NrResid=?,Bairro=?,Complemento=?,CEP=?,"
                        + "NomeMae=?,NomePai=?,NomeMadrinha=?,NomePadrinho=?,DataBatizado=?,LocalBatizado=?,Celebrante=?,Livro=?,Folha=?,Numero=?,NomeLivro=?,NomeMaeLivro=?,NomePaiLivro=?,NomeMadrinhaLivro=?,NomePadrinhoLivro=?,Observacoes=? "
                        + "WHERE IdBatizado=?";
//                //+"date_format(data_batizado,'%d/%m/%Y' ),"

//                java.util.Date javaDate = new java.util.Date();
//                java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
                pst = conn.prepareStatement(sql);
                pst.setString(1, txtNomeBatizando.getText());
                //pst.setString(2, sexo.toString());
                //pst.setString(5,cboBatsexo.getSelectedItem().toString());
                //pst.setDate(2, mySQLDate);
                //pst.setString(2, txtDataNasc.getText());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(DataNasc.getDate());
                pst.setString(2, date);
                //pst.setString(3, sexo.toString());
                pst.setString(3, txtCidade.getText());
                pst.setString(4, cboUF.getSelectedItem().toString());
                pst.setString(5, txtEndereco.getText());
                pst.setString(6, txtNrResid.getText());
                pst.setString(7, txtBairro.getText());
                pst.setString(8, txtComplem.getText());
                pst.setString(9, txtCEP.getText());
                //pst.setString(9, jTCep.getText());
                pst.setString(10, txtNomeMae.getText());
                pst.setString(11, txtNomePai.getText());
                pst.setString(12, txtNomeMadrinha.getText());
                pst.setString(13, txtNomePadrinho.getText());
                //pst.setDate(14, mySQLDate);
                //pst.setString(14, txtDataCelebra.getText());
                SimpleDateFormat sdfb = new SimpleDateFormat("yyyy-MM-dd");
                String dateb = sdfb.format(DataBatiz.getDate());
                pst.setString(14, dateb);
                pst.setString(15, txtLocalCelebracao.getText());
                pst.setString(16, txtCelebrante.getText());
                pst.setString(17, txtLivro.getText());
                pst.setString(18, txtFolha.getText());
                pst.setString(19, txtNumero.getText());
                pst.setString(20, txtNomeLivro.getText());
                pst.setString(21, txtNomeMaeLivro.getText());
                pst.setString(22, txtNomePaiLivro.getText());
                pst.setString(23, txtNomeMadrinhaLivro.getText());
                pst.setString(24, txtNomePadrinhoLivro.getText());
                pst.setString(25, txaObs.getText());
                pst.setString(26, txtIdBatizado.getText());

                if ((txtNomeBatizando.getText().equals(""))
                        || (txtNomeMae.getText().equals(""))) {
                    JOptionPane.showInternalMessageDialog(getInstance(), "Preencha os Campos Obrigatórios!");
                    txtNomeBatizando.requestFocus();
                    txtNomeMae.requestFocus();
                } else {

                    //a linha abaixo atualiza a tbbatizado com os dados do formulario
                    //a estrutura abaixo é usada para confirmar a alteração dos dados na tb
                    int adicionado = pst.executeUpdate();

                    //a linha abaixo de apoio de entendimento da logica
                    //System.out.println(adicionado);
                    if (adicionado > 0) {
                        JOptionPane.showInternalMessageDialog(getInstance(), "Registro Alterado com Sucesso!");
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

//                        carregaTabela();
//                        btnCreate.setEnabled(true);
//                        limpar();
                    }
                }
            } catch (Exception erro) {
                JOptionPane.showInternalMessageDialog(getInstance(),"UpdateBatizado:" + erro);
            }
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {

            }
            //Update_table();
            carregaTabela();
            btnNovo.setEnabled(true);
            btnCreate.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnRemover.setEnabled(false);
            desabilitaCampos();
            limparPesquisar();
            //btnCreate.setEnabled(true);
            //limpar();
        }
    }

    private void DeletBatizado() {
        int p = JOptionPane.showInternalConfirmDialog(getInstance(), "Are you sure you want to delete record?", "Delete", JOptionPane.YES_NO_OPTION);
        if (p == 0) {

            String sql = "DELETE FROM batizados WHERE IdBatizado=? ";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, txtIdBatizado.getText());
                pst.execute();

                JOptionPane.showInternalMessageDialog(getInstance(), "Record Deleted");

                limpar();

            } catch (Exception erro) {
                JOptionPane.showInternalMessageDialog(getInstance(),"DeleteBatizado:" + erro);
            } finally {

                try {
                    rs.close();
                    pst.close();

                } catch (Exception e) {

                }
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
        jPanel2 = new javax.swing.JPanel();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtBatPesquisar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        d1 = new com.toedter.calendar.JDateChooser();
        d2 = new com.toedter.calendar.JDateChooser();
        btnPDataNasc = new javax.swing.JButton();
        lblDataHora = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBatizados = new javax.swing.JTable();

        timer1.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                timer1OnTime(evt);
            }
        });

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Batizados");
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

        jLabel17.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Data Nascimento");

        jLabel18.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Cidade");

        jLabel21.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("UF");

        cboUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "    ", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", " " }));

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

        DataNasc.setDateFormatString("dd-MM-yyyy");

        btnBuscarCep.setText("Buscar CEP");
        btnBuscarCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCepActionPerformed(evt);
            }
        });

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
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNrResid, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtComplem, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdBatizado, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNomeBatizando, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(DataNasc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jpSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblStatusCep, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarCep))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
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
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(jLabel9))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIdBatizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNomeBatizando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jpSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStatusCep, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel18))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNrResid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtComplem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel4))))))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCep)))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Filiação / Padrinhos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bookman Old Style", 1, 14), new java.awt.Color(0, 153, 153))); // NOI18N

        jLabel26.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("* Nome Mãe");

        jLabel27.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Nome Madrinha");

        jLabel28.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Nome Padrinho");

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
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Registro Livro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bookman Old Style", 1, 14), new java.awt.Color(0, 153, 153))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Data Celebração");

        jLabel23.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Local Celebração");

        jLabel24.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Nr");

        jLabel25.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Folha");

        jLabel30.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Celebrante");

        jLabel31.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Livro");

        DataBatiz.setDateFormatString("dd-MM-yyyy");

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
                                .addGap(66, 66, 66)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(144, 144, 144)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnCreate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/save16x16.png"))); // NOI18N
        btnCreate.setToolTipText("Salvar");
        btnCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/update16x16.png"))); // NOI18N
        btnUpdate.setToolTipText("Alterar");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnNovo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/add16x16.png"))); // NOI18N
        btnNovo.setToolTipText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnRemover.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/delete16x16.png"))); // NOI18N
        btnRemover.setToolTipText("Deletar");
        btnRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/cancel16x16.gif"))); // NOI18N
        btnCancelar.setToolTipText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(397, 397, 397))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bookman Old Style", 1, 14), new java.awt.Color(0, 153, 153))); // NOI18N

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBatPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(d2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPDataNasc)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtBatPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel14)
                .addComponent(jLabel11))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPDataNasc)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(d1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(d2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 1038, Short.MAX_VALUE)
                        .addComponent(lblDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
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

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        AddBatizado();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        UpdateBatizado();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        habilitaCampos();

        txtIdBatizado.setText("");
        txtNomeBatizando.setText("");
        //jpSexo.setToolTipText("");
        //txtDataNasc.setText("");
        DataNasc.setDateFormatString("dd-MM-yyyy");
        txtEndereco.setText("");
        txtNrResid.setText("");
        txtBairro.setText("");
        txtComplem.setText("");
        txtCEP.setText("");
        //jTCep.setText("");
        txtCidade.setText("");
        cboUF.setSelectedItem("");
        txtNomeMae.setText("");
        txtNomePai.setText("");
        txtNomeMadrinha.setText("");
        txtNomePadrinho.setText("");
        //txtDataCelebra.setText("");
        DataBatiz.setDateFormatString("dd-MM-yyyy");
        txtLocalCelebracao.setText("");
        txtCelebrante.setText("");
        txtLivro.setText("");
        txtFolha.setText("");
        txtNumero.setText("");
        txtNomeLivro.setText("");
        txtNomeMaeLivro.setText("");
        txtNomePaiLivro.setText("");
        txtNomeMadrinhaLivro.setText("");
        txtNomePadrinhoLivro.setText("");
        txaObs.setText("");

        txtBatPesquisar.setText("");

        btnCreate.setEnabled(true);        
        btnUpdate.setEnabled(false);
        btnRemover.setEnabled(false);
        btnCancelar.setEnabled(true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        DeletBatizado();
    }//GEN-LAST:event_btnRemoverActionPerformed

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

        } catch (Exception erro) {
            JOptionPane.showInternalMessageDialog(getInstance(),"BotãoPesqDataNasc:" + erro);
            //JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnPDataNascActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        carregaTabela();
        btnNovo.setEnabled(true);
        btnCreate.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnRemover.setEnabled(false);
        desabilitaCampos();
        limparPesquisar();
        btnCancelar.setEnabled(false);
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DataBatiz;
    private com.toedter.calendar.JDateChooser DataNasc;
    private javax.swing.JButton btnBuscarCep;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPDataNasc;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboUF;
    private com.toedter.calendar.JDateChooser d1;
    private com.toedter.calendar.JDateChooser d2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel2;
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
