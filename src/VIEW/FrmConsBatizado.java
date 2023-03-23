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
public class FrmConsBatizado extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    Statement st;

    /**
     * Creates new form FrmTelaBatizado
     */
    public FrmConsBatizado() {
        initComponents();
        conn = ConexaoDAO.conectaDB();
        timer1.start();
        carregaTabela();
        //Update_tableb();
        Fill_Date();

        

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
    }

    public void habilitaCampos() {
        txtNomeBatizando.setEnabled(true);
        DataNasc.setEnabled(true);        
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

        try {
            pst = conn.prepareStatement("SELECT IdBatizado AS Id,Nome,DATE_FORMAT(DataNasc,'%d-%m-%Y') AS DataNasc,NomeMae As Mãe,NomePai AS Pai,NomeMadrinha AS Madrinha,NomePadrinho AS Padrinho,DATE_FORMAT(DataBatizado,'%d-%m-%Y') AS DataBatizado,LocalBatizado AS Local,Celebrante,Livro,Folha,Numero FROM batizados order by IdBatizado;");

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
                    rs.getString(13),});

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
        
        try {
            String date = (tblBatizados.getModel().getValueAt(setar, 2).toString());
            Date d = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            DataNasc.setDate(d);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }        
        
        txtNomeMae.setText(tblBatizados.getModel().getValueAt(setar, 3).toString());
        txtNomePai.setText(tblBatizados.getModel().getValueAt(setar, 4).toString());
        txtNomeMadrinha.setText(tblBatizados.getModel().getValueAt(setar, 5).toString());
        txtNomePadrinho.setText(tblBatizados.getModel().getValueAt(setar, 6).toString());       
        
        try {
            String date = (tblBatizados.getModel().getValueAt(setar, 7).toString());
            Date db = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            DataBatiz.setDate(db);
        } catch (Exception erro) {
            JOptionPane.showInternalMessageDialog(getInstance(), erro);
            //JOptionPane.showMessageDialog(null, e);
        }

        txtLocalCelebracao.setText(tblBatizados.getModel().getValueAt(setar, 8).toString());
        txtCelebrante.setText(tblBatizados.getModel().getValueAt(setar, 9).toString());
        txtLivro.setText(tblBatizados.getModel().getValueAt(setar, 10).toString());
        txtFolha.setText(tblBatizados.getModel().getValueAt(setar, 11).toString());
        txtNumero.setText(tblBatizados.getModel().getValueAt(setar, 12).toString());
       
        btnImprimir.setEnabled(true);
        btnLimparCampos.setEnabled(true);
    }

    //metodo para pesquisar pelo nome com filtro
    private void pesquisar_batizado() {
        String sql = "SELECT IdBatizado,Nome,DATE_FORMAT(DataNasc,'%d-%m-%Y') AS DataNasc,NomeMae,NomePai,NomeMadrinha,NomePadrinho,DATE_FORMAT(DataBatizado,'%d-%m-%Y') AS DataBatizado,LocalBatizado,Celebrante,Livro,Folha,Numero FROM batizados WHERE Nome like ?";

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
                pst = conn.prepareStatement("SELECT IdBatizado,Nome,DataNasc,NomeMae,NomePai,NomeMadrinha,NomePadrinho,DataBatizado,LocalBatizado,Celebrante,Livro,Folha,Numero FROM batizados");

            } else {
                pst = conn.prepareStatement("SELECT IdBatizado,Nome,DataNasc,NomeMae,NomePai,NomeMadrinha,NomePadrinho,DataBatizado,LocalBatizado,Celebrante,Livro,Folha,Numero FROM batizados WHERE DataNasc BETWEEN ? And ?");
                pst.setString(1, d1);
                pst.setString(2, d2);
            }
            rs = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tblBatizados.getModel();

            Object[] row;

            while (rs.next()) {
                row = new Object[13];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getDate(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getDate(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getString(12);
                row[12] = rs.getString(13);               

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
                pst = conn.prepareStatement("SELECT IdBatizado,Nome,DataNasc,NomeMae,NomePai,NomeMadrinha,NomePadrinho,DataBatizado,LocalBatizado,Celebrante,Livro,Folha,Numero FROM batizados");

            } else {
                pst = conn.prepareStatement("SELECT IdBatizado,Nome,DataNasc,NomeMae,NomePai,NomeMadrinha,NomePadrinho,DataBatizado,LocalBatizado,Celebrante,Livro,Folha,Numero FROM batizados WHERE DataBatizado BETWEEN ? And ?");
                pst.setString(3, d3);
                pst.setString(4, d4);
            }
            rs = pst.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) tblBatizados.getModel();

            Object[] row;

            while (rs.next()) {
                                row = new Object[13];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getDate(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getDate(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getString(12);
                row[12] = rs.getString(13); 
                
                modelo.addRow(row);
            }
        } catch (Exception erro) {
            JOptionPane.showInternalMessageDialog(getInstance(), "PesquisaDataBatizado111:" + erro);
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
        DataNasc.setDate(null);       
        txtNomeMae.setText(null);
        txtNomePai.setText(null);
        txtNomeMadrinha.setText(null);
        txtNomePadrinho.setText(null);       
        DataBatiz.setDate(null);
        txtLocalCelebracao.setText(null);
        txtCelebrante.setText(null);
        txtLivro.setText(null);
        txtFolha.setText(null);
        txtNumero.setText(null);       
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


    private void imprimir_declaracao() {

        int confirma = JOptionPane.showInternalConfirmDialog(getInstance(), "Confirma a emissão desta declaração?", "Atenção", JOptionPane.YES_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {

            try {
                
                //usando a class HashMap para criar um filtro
                HashMap filtro = new HashMap();
                filtro.put("IdBatizado", Integer.parseInt(txtIdBatizado.getText()));

                JasperPrint print = JasperFillManager.fillReport("D:/ProjectsNetBeans/reports/DeclaracaoBatismo.jasper", filtro, conn);
                JasperViewer.viewReport(print, false);
//                JasperViewer jv = new JasperViewer(print,true);
//                jv.setVisible(true);
//                jv.toFront();

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
        jLabel16 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNomeBatizando = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtIdBatizado = new javax.swing.JTextField();
        DataNasc = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        txtNomeMae = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtNomeMadrinha = new javax.swing.JTextField();
        txtNomePadrinho = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtNomePai = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        DataBatiz = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        txtLocalCelebracao = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtCelebrante = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtLivro = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtFolha = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
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

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel16.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Código");

        jLabel9.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Nome Batizando(a)");
        jLabel9.setToolTipText("");

        txtNomeBatizando.setEditable(false);

        jLabel17.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Data Nascimento");

        txtIdBatizado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtIdBatizado.setEnabled(false);
        txtIdBatizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBatizadoActionPerformed(evt);
            }
        });

        DataNasc.setDateFormatString("dd-MM-yyyy");
        DataNasc.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Mãe");

        txtNomeMae.setEditable(false);

        jLabel27.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Madrinha");

        txtNomeMadrinha.setEditable(false);

        txtNomePadrinho.setEditable(false);

        jLabel28.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Padrinho");

        txtNomePai.setEditable(false);

        jLabel29.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Pai");

        jLabel22.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Data Celebração");

        DataBatiz.setDateFormatString("dd-MM-yyyy");
        DataBatiz.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Local Celebração");

        txtLocalCelebracao.setEditable(false);

        jLabel30.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Celebrante");

        txtCelebrante.setEditable(false);

        jLabel31.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Livro");

        txtLivro.setEditable(false);

        jLabel25.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Folha");

        txtFolha.setEditable(false);

        jLabel24.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Nr");

        txtNumero.setEditable(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNomeMadrinha, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(txtNomePadrinho, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DataBatiz, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtLocalCelebracao, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCelebrante, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel25))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdBatizado, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtNomeBatizando, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(txtNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 45, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdBatizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomeBatizando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel31))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(txtNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeMadrinha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomePadrinho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(1, 1, 1)
                                .addComponent(DataBatiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel30)
                                        .addComponent(jLabel25))
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtLocalCelebracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCelebrante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtFolha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel24)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                "Id", "Nome", "Data Nasc", "Mãe", "Pai", "Madrinha", "Padrinho", "Data Batizado", "Local", "Celebrante", "Livro", "Folha", "Nr"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
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

        btnImprimir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/print16x16.png"))); // NOI18N
        btnImprimir.setText("Imprimir Declaração");
        btnImprimir.setToolTipText("Imprimir Declaração");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnLimparCampos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(btnImprimir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimparCampos))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(19, Short.MAX_VALUE))
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
                .addGap(1, 1, 1)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimparCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(0, 0, 1330, 680);
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdBatizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBatizadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBatizadoActionPerformed

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

    private void timer1OnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timer1OnTime
        MinhaData minha = new MinhaData(new java.util.Date());
        lblDataHora.setText(minha.getDiaSemana() + ", " + minha.getDataHora());
    }//GEN-LAST:event_timer1OnTime

    private void btnPDataNascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDataNascActionPerformed

        try {
            tblBatizados.setModel(new DefaultTableModel(null, new Object[]{"IdBatizado", "Nome", "DataNasc", "NomeMae", "NomePai", "NomeMadrinha", "NomePadrinho", "DataBatizado", "LoacalBatizado", "Celebrante", "Livro", "Folha", "Numero",}));
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
            tblBatizados.setModel(new DefaultTableModel(null, new Object[]{"IdBatizado", "Nome", "DataNasc", "NomeMae", "NomePai", "NomeMadrinha", "NomePadrinho", "DataBatizado", "LoacalBatizado", "Celebrante", "Livro", "Folha", "Numero",}));
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
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnLimparCampos;
    private javax.swing.JButton btnPDataBatizado;
    private javax.swing.JButton btnPDataNasc;
    private com.toedter.calendar.JDateChooser d1;
    private com.toedter.calendar.JDateChooser d2;
    private com.toedter.calendar.JDateChooser d3;
    private com.toedter.calendar.JDateChooser d4;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDataHora;
    private javax.swing.JTable tblBatizados;
    private org.netbeans.examples.lib.timerbean.Timer timer1;
    private javax.swing.JTextField txtBatPesquisar;
    private javax.swing.JTextField txtCelebrante;
    private javax.swing.JTextField txtFolha;
    private javax.swing.JTextField txtIdBatizado;
    private javax.swing.JTextField txtLivro;
    private javax.swing.JTextField txtLocalCelebracao;
    private javax.swing.JTextField txtNomeBatizando;
    private javax.swing.JTextField txtNomeMadrinha;
    private javax.swing.JTextField txtNomeMae;
    private javax.swing.JTextField txtNomePadrinho;
    private javax.swing.JTextField txtNomePai;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables

    //private String sexo;

}
