/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;
import Atxy2k.CustomTextField.RestrictedTextField;
import DAO.MinhaData;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import DTO.BatizadosDTO;
import DAO.ConexaoDAO;

import DAO.CEP;
import DAO.ViaCEP;
import DAO.ViaCEPBase;
import DAO.ViaCEPException;

import java.awt.Panel;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author rg088
 */
public class FrmBatizado extends javax.swing.JInternalFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    DefaultTableModel model = new DefaultTableModel();
    /**
     * Creates new form FrmBatizado
     */
    public FrmBatizado() {
   
        initComponents();
        conn = ConexaoDAO.conectaDB();
        timer1.start();
        carregaTabela();
        Fill_Date();

        RestrictedTextField validar = new RestrictedTextField(txtCEP);
        validar.setOnlyNums(true);
        validar.setLimit(8);
        
    }

    private void carregaTabela() {
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
        //tblBatizados.getColumnModel().getColumn(12).setPreferredWidth(20);
        //tblBatizados.getColumnModel().getColumn(13).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(13).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(14).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(15).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(16).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(17).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(18).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(19).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(20).setPreferredWidth(20);
        tblBatizados.getColumnModel().getColumn(21).setPreferredWidth(20);

        try {
            pst = conn.prepareStatement("SELECT IdBatizado,DataReg,Nome,DATE_FORMAT(DataNasc,'%d-%m-%Y') AS DataNasc,Sexo,Naturalidade,UF,Endereco,NrResid,Bairro,Complemento,CEP,"
                    + "NomeMae,NomePai,NomeMadrinha,NomePadrinho,DATE_FORMAT(DataBatizado,'%d-%m-%Y') AS DataBatizado,LocalBatizado,Celebrante,Livro,Folha,Numero "
                    + "FROM batizados order by IdBatizado;");

            //+ "DataNasc,\n" + //"DataBatizado,\n" +
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
                    rs.getString(22),});

            }
            //conexao.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a tabela de dados: " + e, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void pesquisar_batizado() {
        String sql = "SELECT IdBatizado,"
                + "DataReg,"
                + "Nome, "
                //+"DataNasc,"        
                + "DATE_FORMAT(DataNasc,'%d-%m-%Y'),"
                + "Sexo,"
                + "Naturalidade,"
                + "UF,"
                + "Endereco,"
                + "NrResid,"
                + "Bairro,"
                + "Complemento,"
                + "CEP,"
                //+"estado_id_estado,"
                //+"cidade_id_cidade,"                
                + "NomeMae,"
                + "NomePai,"
                + "NomeMadrinha,"
                + "NomePadrinho,"
                //+"DataBatizado,"
                + "DATE_FORMAT(DataBatizado,'%d-%m-%Y' ),"
                + "LocalBatizado,"
                + "Celebrante,"
                + "Livro,"
                + "Folha,"
                + "Numero "
                + "FROM batizados WHERE Nome like ?";

//                "SELECT id_batizando as Id_registro, nomebatizando as Nome_Batizando, datanasc as Data_Nasc,\n"
//                + "naturalbatiz as Naturalidade, ufbatiz as UF, sexobatiz as Sexo, nomemae as NomeMae, nomepai as NomePai,\n"
//                + "nomemadrinha as NomeMadr, nomepadrinho as NomePadr, celebrante as Celebrante, localcelebracao as LocalCelebr,\n"
//                + "datacelebracao as DataCelebr, livro as Livro, folha as Folha, numero as Numero "
//                + "FROM tbbatizandos WHERE nomebatizando like ?";
        try {
            pst = conn.prepareStatement(sql);
            //passando o conteudo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da String sql
            pst.setString(1, txtBatPesquisar.getText() + "%");
            rs = pst.executeQuery();
            //a linha abx usa a biblioteca rs2xml.jar para preencher a tabela
            tblBatizados.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Fill_Date() {
        Date date = new Date();
//        DataNasc.setDateFormatString("dd-MM-yyyy");
//        DataNasc.setDate(date);
//        DataBatiz.setDateFormatString("dd-MM-yyyy");
//        DataBatiz.setDate(date);

    }

    //metodo para setar informações nos campos
    public void setar_campos() {
        int setar = tblBatizados.getSelectedRow();

        txtIdBatizando.setText(tblBatizados.getModel().getValueAt(setar, 0).toString());
        txtNomeBatizando.setText(tblBatizados.getModel().getValueAt(setar, 2).toString());
        //txtDataNasc.setText(tblBatizados.getModel().getValueAt(setar, 3).toString());      

        try {
            String date = (tblBatizados.getModel().getValueAt(setar, 3).toString());
            Date d = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            DataNasc.setDate(d);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        String sexo = (tblBatizados.getModel().getValueAt(setar, 4).toString());
        if (sexo.equals("M")) {
            rbMasculino.setSelected(true);
        } else {
            rbFeminino.setSelected(true);
        }

        txtCidade.setText(tblBatizados.getModel().getValueAt(setar, 5).toString());
        cboUF.setSelectedItem(tblBatizados.getModel().getValueAt(setar, 6).toString());
        txtEndereco.setText(tblBatizados.getModel().getValueAt(setar, 7).toString());
        txtNrResid.setText(tblBatizados.getModel().getValueAt(setar, 8).toString());
        txtBairro.setText(tblBatizados.getModel().getValueAt(setar, 9).toString());
        txtComplem.setText(tblBatizados.getModel().getValueAt(setar, 10).toString());
        txtCEP.setText(tblBatizados.getModel().getValueAt(setar, 11).toString());
        //jTCep.setText(tblBatizados.getModel().getValueAt(setar, 11).toString());
        //cboBatsexo.setSelectedItem(tblBatizandos.getModel().getValueAt(setar, 5).toString());
        txtNomeMae.setText(tblBatizados.getModel().getValueAt(setar, 12).toString());
        txtNomePai.setText(tblBatizados.getModel().getValueAt(setar, 13).toString());
        txtNomeMadrinha.setText(tblBatizados.getModel().getValueAt(setar, 14).toString());
        txtNomePadrinho.setText(tblBatizados.getModel().getValueAt(setar, 15).toString());

        //txtDataCelebra.setText(tblBatizados.getModel().getValueAt(setar, 16).toString());
        try {
            String date = (tblBatizados.getModel().getValueAt(setar, 16).toString());
            Date db = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            DataBatiz.setDate(db);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        txtLocalCelebracao.setText(tblBatizados.getModel().getValueAt(setar, 17).toString());
        txtCelebrante.setText(tblBatizados.getModel().getValueAt(setar, 18).toString());
        txtLivro.setText(tblBatizados.getModel().getValueAt(setar, 19).toString());
        txtFolha.setText(tblBatizados.getModel().getValueAt(setar, 20).toString());
        txtNumero.setText(tblBatizados.getModel().getValueAt(setar, 21).toString());

        //A linha abx desabilita o btnCreate(add)
        btnCreate.setEnabled(false);
        btnRemover.setEnabled(true);
        btnUpdate.setEnabled(true);
    }

    //metodo para limpar os campos do formulario e da table
    private void limpar() {
        txtBatPesquisar.setText(null);
        txtIdBatizando.setText(null);
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

        ((DefaultTableModel) tblBatizados.getModel()).setRowCount(0);
    }

    private String sexo;

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
        txtIdBatizando = new javax.swing.JTextField();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBatizados = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtBatPesquisar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        lblDataHora = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();

        setTitle("Batizados");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

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
        jLabel9.setText("* Nome Batizando");
        jLabel9.setToolTipText("");

        jLabel17.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("* Data Nascimento");

        jLabel18.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Cidade");

        jLabel21.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("UF");

        cboUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "    ", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", " " }));

        txtIdBatizando.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtIdBatizando.setEnabled(false);
        txtIdBatizando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBatizandoActionPerformed(evt);
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

        DataNasc.setDateFormatString("yyyy-MM-dd");

        btnBuscarCep.setText("Buscar CEP");
        btnBuscarCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jpSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblStatusCep, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)
                                        .addComponent(btnBuscarCep))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(353, 353, 353)))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNrResid, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboUF, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(90, 90, 90)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtComplem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39))))))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel17)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(txtIdBatizando, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(txtNomeBatizando, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(54, 54, 54))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarCep)
                            .addComponent(lblStatusCep, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNomeBatizando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdBatizando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jpSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel1))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel21)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComplem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNrResid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNomeMadrinha)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNomePadrinho)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNomePai)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomePai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel29)))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNomePadrinho, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtNomeMadrinha, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel24.setText("Numero");

        jLabel25.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Folha");

        jLabel30.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Celebrante");

        jLabel31.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Livro");

        DataBatiz.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DataBatiz, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtLocalCelebracao, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCelebrante, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLivro)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFolha, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addGap(1, 1, 1)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtLocalCelebracao)
                    .addComponent(DataBatiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCelebrante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFolha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tblBatizados = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return (false);
            }
        };
        tblBatizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Registro", "Data_Reg", "Nome", "Data Nasc", "Sexo", "Natural", "UF", "Endereco", "Nr Resid", "Bairro", "Complemento", "CEP", "Nome Mãe", "Nome Pai", "Madrinha", "Padrinho", "Data Batizado", "Local Batizado", "Celebrante", "Livro", "Folha", "Nr"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(txtBatPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBatPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnCreate.setText("Adicionar");
        btnCreate.setToolTipText("Adicionar");
        btnCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnUpdate.setText("Alterar");
        btnUpdate.setToolTipText("Alterar");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnRemover.setText("Deletar");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(311, 311, 311))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnRemover, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblDataHora.setText("DataHora");

        lblUsuario.setText("Usuario:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1304, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 26, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUsuario)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(233, 233, 233))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuario)
                .addGap(46, 46, 46)
                .addComponent(lblDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setBounds(0, 0, 1363, 713);
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

    private void txtIdBatizandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBatizandoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBatizandoActionPerformed

    private void btnBuscarCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCepActionPerformed

        buscarCep();

        //        ViaCEP viaCep = new ViaCEP();
        //
        //        try {
            //            //viaCep.buscar(jTCep.getText());
            //            viaCep.buscar(txtCEP.getText());
            //            txtBairro.setText(viaCep.getBairro());
            //            txtEndereco.setText(viaCep.getLogradouro());
            //            txtCidade.setText(viaCep.getLocalidade());
            //            cboUF.setSelectedItem(viaCep.getUf());
            //
            //        } catch (ViaCEPException ex) {
            //            Logger.getLogger(FrmCep.class.getName()).log(Level.SEVERE, null, ex);
            //        }
    }//GEN-LAST:event_btnBuscarCepActionPerformed

    private void tblBatizadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBatizadosMouseClicked
        // chamar metodo setar_campos
        setar_campos();
    }//GEN-LAST:event_tblBatizadosMouseClicked

    private void txtBatPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBatPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBatPesquisarActionPerformed

    private void txtBatPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBatPesquisarKeyReleased
        pesquisar_batizado();
    }//GEN-LAST:event_txtBatPesquisarKeyReleased

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // chamando o metodo add
        //adicionar();

        int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to add record?", "Add Record", JOptionPane.YES_NO_OPTION);
        if (p == 0) {

            try {
                java.util.Date javaDate = new java.util.Date();
                java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
                java.sql.Timestamp datereg = new java.sql.Timestamp(new java.util.Date().getTime());

                String sql = "INSERT INTO batizados (DataReg,Nome,DataNasc,Sexo,Naturalidade,UF,Endereco,NrResid,Bairro,Complemento,CEP,"
                + "NomeMae,NomePai,NomeMadrinha,NomePadrinho,DataBatizado,LocalBatizado,Celebrante,Livro,Folha,Numero) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                // +"estado_id_estado,"
                //+"cidade_id_cidade,"
                //+"DATE_FORMAT(DataNasc,'%d-%m-%Y')," //+ "DATE_FORMAT(DataBatizado,'%d-%m-%Y'),"

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
                // pst.setString(16, txtIdBatizando.getText());

                //validação dos campos obrigatorios
                if ((txtNomeBatizando.getText().equals(""))
                    //|| (txtDataNasc.getText().equals(""))
                    //|| (dob.getDate().toString().equals(""))
                    //|| (txtNaturalidade.getText().equals(""))
                    //|| (cboBatUF.getSelectedItem().toString().equals(""))
                    //|| (cboBatsexo.getSelectedItem().toString().equals(""))
                    || (txtNomeMae.getText().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                    txtNomeBatizando.requestFocus();
                    txtNomeMae.requestFocus();

                } else {

                    //a linha abaixo atualiza a tbusuarios com os dados do formulario
                    //a estrutura abaixo é usada para confirmar a inserção dos dados na tb
                    int adicionado = pst.executeUpdate();
                    //a linha abaixo de apoio de entendimento da logica
                    //System.out.println(adicionado);
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Registro adicionado com sucesso!");
                        //                    txtDataNasc.setText(null);
                        //                    txtNaturalidade.setText(null);
                        //                    cboBatUF.setSelectedItem(null);
                        //                    //cboBatsexo.setSelectedItem(null);
                        //                    txtNomeMae.setText(null);
                        //                    txtNomePai.setText(null);
                        //                    txtNomePadrinho.setText(null);
                        //                    txtNomeMadrinha.setText(null);
                        //                    txtCelebrante.setText(null);
                        //                    txtLocalCelebracao.setText(null);
                        //                    txtDataCelebra.setText(null);
                        //                    txtLivro.setText(null);
                        //                    txtFolha.setText(null);
                        //                    txtNumero.setText(null);

                        carregaTabela();
                        btnCreate.setEnabled(false);
                        limpar();
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // chamando o metodo alterar
        //alterar();
        int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to update?", "Update Record", JOptionPane.YES_NO_OPTION);
        if (p == 0) {

            String sql = "UPDATE batizados SET Nome=?, DataNasc=?,Naturalidade=?,UF=?,Endereco=?,NrResid=?,Bairro=?,Complemento=?,CEP=?,"
            + "NomeMae=?,NomePai=?,NomeMadrinha=?,NomePadrinho=?,DataBatizado=?,LocalBatizado=?,Celebrante=?,Livro=?,Folha=?,Numero=? "
            + "WHERE IdBatizado=?";
            //+"date_format(data_batizado,'%d/%m/%Y' ),"

            try {
                java.util.Date javaDate = new java.util.Date();
                java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());

                pst = conn.prepareStatement(sql);
                pst.setString(1, txtNomeBatizando.getText());
                //pst.setString(2, sexo.toString());
                //pst.setString(5,cboBatsexo.getSelectedItem().toString());
                //pst.setDate(2, mySQLDate);
                //pst.setString(2, txtDataNasc.getText());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(DataNasc.getDate());
                pst.setString(2, date);
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
                pst.setString(20, txtIdBatizando.getText());

                if ((txtNomeBatizando.getText().equals(""))
                    //|| (txtDataNasc.getText().equals(""))
                    //|| (txtNaturalidade.getText().equals(""))
                    //|| (cboBatUF.getSelectedItem().toString().equals(""))
                    //||(cboBatsexo.getSelectedItem().toString().equals(""))
                    || (txtNomeMae.getText().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                    txtNomeBatizando.requestFocus();
                    txtNomeMae.requestFocus();
                } else {

                    //a linha abaixo atualiza a tbbatizando com os dados do formulario
                    //a estrutura abaixo é usada para confirmar a alteração dos dados na tb
                    int adicionado = pst.executeUpdate();

                    //a linha abaixo de apoio de entendimento da logica
                    //System.out.println(adicionado);
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso!");
                        txtIdBatizando.setText(null);
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

                        carregaTabela();
                        btnCreate.setEnabled(true);
                        //limpar();
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        txtIdBatizando.setText("");
        txtNomeBatizando.setText("");
        //jpSexo.setToolTipText("");
        //txtDataNasc.setText("");
        //DataNasc.setDateFormatString("");
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
        //DataBatiz.setDateFormatString("");
        txtLocalCelebracao.setText("");
        txtCelebrante.setText("");
        txtLivro.setText("");
        txtFolha.setText("");
        txtNumero.setText("");

        txtBatPesquisar.setText("");

        btnCreate.setEnabled(true);
        btnRemover.setEnabled(false);
        btnUpdate.setEnabled(false);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // TODO add your handling code here:
        int p = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete record?", "Delete", JOptionPane.YES_NO_OPTION);
        if (p == 0) {

            String sql = "DELETE FROM batizados WHERE IdBatizado=? ";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, txtIdBatizando.getText());
                pst.execute();

                JOptionPane.showMessageDialog(null, "Record Deleted");

                limpar();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {

                try {
                    rs.close();
                    pst.close();

                } catch (Exception e) {

                }
            }
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSairActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        rbMasculino.setSelected(true);
        sexo = "Masculino";
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DataBatiz;
    private com.toedter.calendar.JDateChooser DataNasc;
    private javax.swing.JButton btnBuscarCep;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboUF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpSexo;
    private javax.swing.JLabel lblDataHora;
    private javax.swing.JLabel lblStatusCep;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JRadioButton rbFeminino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JTable tblBatizados;
    private org.netbeans.examples.lib.timerbean.Timer timer1;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtBatPesquisar;
    private javax.swing.JTextField txtCEP;
    private javax.swing.JTextField txtCelebrante;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtComplem;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtFolha;
    private javax.swing.JTextField txtIdBatizando;
    private javax.swing.JTextField txtLivro;
    private javax.swing.JTextField txtLocalCelebracao;
    private javax.swing.JTextField txtNomeBatizando;
    private javax.swing.JTextField txtNomeMadrinha;
    private javax.swing.JTextField txtNomeMae;
    private javax.swing.JTextField txtNomePadrinho;
    private javax.swing.JTextField txtNomePai;
    private javax.swing.JTextField txtNrResid;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables
}
