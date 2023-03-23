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
import DAO.ConexaoDAO;
import DTO.UsuariosTableModel;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.sql.Statement;
import javax.swing.JTable;
import static javax.swing.LayoutStyle.getInstance;

/**
 *
 * @author rg088
 */
public class FrmTelaUsuario extends javax.swing.JInternalFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st;

    /**
     * Creates new form FrmTelaUsuario
     */
    public FrmTelaUsuario() {
        initComponents();
        conn = ConexaoDAO.conectaDB();
        timer1.start();
        carregaTabela();
        
        RestrictedTextField validar = new RestrictedTextField(txtUsuCPF);
        validar.setOnlyNums(true);
        validar.setLimit(11);
        
        
        //desabilita botoes ao iniciar tela
        btnUsuCreate.setEnabled(false);
        btnUsuUpdate.setEnabled(false);
        btnUsuDelete.setEnabled(false);
        btnCancelar.setEnabled(false);

        //METODO PARA NAVEGAR PELOS BOTOES
        try {
            st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("select * from usuarios");

            if (rs.next()) {
                txtUsuId.setText(rs.getString(1));
                txtUsuNome.setText(rs.getString(2));
                txtUsuCPF.setText(rs.getString(3));
                txtUsuLogin.setText(rs.getString(4));                
                txtUsuSenha.setText(rs.getString(5));
                cboUsuPerfil.setSelectedItem(rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void carregaTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tblUsuarios.getModel();
        modelo.setNumRows(0);
        tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(20);
        tblUsuarios.getColumnModel().getColumn(2).setPreferredWidth(20);
        tblUsuarios.getColumnModel().getColumn(3).setPreferredWidth(20);
        tblUsuarios.getColumnModel().getColumn(4).setPreferredWidth(20);
        tblUsuarios.getColumnModel().getColumn(5).setPreferredWidth(20);

        try {
            pst = conn.prepareStatement("SELECT * FROM usuarios order by id_usuario;");
            rs = pst.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
                });
            }
            //conexao.close();
        } catch (Exception erro) {
            JOptionPane.showInternalMessageDialog(getInstance(), "Erro ao carregar a tabela de dados: " + erro, "ERRO", JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(null, "Erro ao carregar a tabela de dados: " + erro, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    //metodo para limpar os campos do formulario e da table
    private void limpar() {
        txtUsuId.setText(null);
        txtUsuNome.setText(null);
        txtUsuLogin.setText(null);
        txtUsuCPF.setText(null);
        txtUsuSenha.setText(null);
        cboUsuPerfil.setSelectedItem(null);
        // ((DefaultTableModel) tblUsuarios.getModel()).setRowCount(0);
    }
    
    public void limparPesquisar() {
        txtPesqUsuario.setText(null);
    }

    public void setar_campos() {
        //PREENCHE OS CAMPOS DO FORMULARIO        
        int setar = tblUsuarios.getSelectedRow();
        txtUsuId.setText(tblUsuarios.getModel().getValueAt(setar, 0).toString());
        txtUsuNome.setText(tblUsuarios.getModel().getValueAt(setar, 1).toString());
        txtUsuCPF.setText(tblUsuarios.getModel().getValueAt(setar, 2).toString());
        txtUsuLogin.setText(tblUsuarios.getModel().getValueAt(setar, 3).toString());
        txtUsuSenha.setText(tblUsuarios.getModel().getValueAt(setar, 4).toString());
        cboUsuPerfil.setSelectedItem(tblUsuarios.getModel().getValueAt(setar, 5).toString());
    }

    private Component getInstance() {
        return this;
    }

    private void AddUsuario() {
        int p = JOptionPane.showInternalConfirmDialog(getInstance(), "Are you sure you want to add record?", "Add Record", JOptionPane.YES_NO_OPTION);
        if (p == 0) {

            String sql = "INSERT INTO usuarios(usuario,cpf,login,senha,perfil_usuario_id_perfil) VALUES (?,?,?,?,?)";
            try {
                pst = conn.prepareStatement(sql);
                //pst.setString(1, txtUsuId.getText());
                pst.setString(1, txtUsuNome.getText());
                pst.setString(2, txtUsuCPF.getText());
                pst.setString(3, txtUsuLogin.getText());
                pst.setString(4, txtUsuSenha.getText());
                pst.setString(5, cboUsuPerfil.getSelectedItem().toString());

                //VALIDAÇÃO DOS CAMPOS OBRIGATÓRIOS
                if (//(txtUsuId.getText().isEmpty())||
                        (txtUsuNome.getText().isEmpty())
                        || (txtUsuCPF.getText().isEmpty())
                        || (txtUsuLogin.getText().isEmpty())
                        || (txtUsuSenha.getText().isEmpty())
                        || (cboUsuPerfil.getSelectedItem().toString().isEmpty())) {
                    JOptionPane.showInternalMessageDialog(getInstance(), "Preencha os Campos Obrigatórios!");

                } else {

                    //a linha abaixo atualiza a tbl usuarios com os dados do formulario
                    //a estrutura abaixo é usada para confirmar a inserção dos dados na tbl
                    int adicionado = pst.executeUpdate();

                    //a linha abaixo de apoio de entendimento da logica
                    //System.out.println(adicionado);
                    if (adicionado > 0) {
                        JOptionPane.showInternalMessageDialog(getInstance(), "Registro Adicionado com Sucesso!");
                        //txtUsuId.setText(null);
//                    txtUsuNome.setText(null);
//                    txtUsuCPF.setText(null);
//                    txtUsuLogin.setText(null);
//                    txtUsuSenha.setText(null);
//                    cboUsuPerfil.setSelectedItem(null);

                        carregaTabela();
                        btnUsuCreate.setEnabled(false);
                        limpar();
                    }
                }
            } catch (Exception erro) {
                JOptionPane.showInternalMessageDialog(getInstance(),"InsertUsuario:" + erro);
            }
        }
    }

    private void UpdateUsuario() {
        int confirma = JOptionPane.showInternalConfirmDialog(getInstance(), "Tem certeza que deseja alterar este registro?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "UPDATE usuarios SET usuario=?, "
                    + "cpf=?, "
                    + "login=?, "
                    + "senha=?, "
                    + "perfil_usuario_id_perfil=? "
                    + "WHERE id_usuario=?";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, txtUsuNome.getText());
                pst.setString(2, txtUsuCPF.getText());
                pst.setString(3, txtUsuLogin.getText());
                pst.setString(4, txtUsuSenha.getText());
                pst.setString(5, cboUsuPerfil.getSelectedItem().toString());
                pst.setString(6, txtUsuId.getText());

                if ((txtUsuId.getText().isEmpty())
                        || (txtUsuNome.getText().isEmpty())
                        || (txtUsuCPF.getText().isEmpty())
                        || (txtUsuLogin.getText().isEmpty())
                        || (txtUsuSenha.getText().isEmpty())
                        || (cboUsuPerfil.getSelectedItem().toString().isEmpty())) {
                    JOptionPane.showInternalMessageDialog(getInstance(), "Preencha os Campos Obrigatórios!");
                    //JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!");
                } else {

                    //a linha abaixo atualiza a tbusuarios com os dados do formulario
                    //a estrutura abaixo é usada para confirmar a alteração dos dados na tb
                    int adicionado = pst.executeUpdate();
                    //a linha abaixo de apoio de entendimento da logica
                    //System.out.println(adicionado);
                    if (adicionado > 0) {
                        JOptionPane.showInternalMessageDialog(getInstance(), "Registro Alterado com Sucesso!");
                        //JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso!");
                        txtUsuId.setText(null);
                        txtUsuNome.setText(null);
                        txtUsuCPF.setText(null);
                        txtUsuLogin.setText(null);
                        txtUsuSenha.setText(null);
                        cboUsuPerfil.setSelectedItem(null);

//                        carregaTabela();
//                        btnUsuNovo.setEnabled(true);
                    }
                }
            } catch (Exception erro) {
                JOptionPane.showInternalMessageDialog(getInstance(),"UpdateUsuário: " + erro);
                //JOptionPane.showMessageDialog(null, e);
            }
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {

            }
            //Update_table();
            carregaTabela();
            btnUsuNovo.setEnabled(true);
            btnUsuCreate.setEnabled(false);
            btnUsuUpdate.setEnabled(false);
            btnUsuDelete.setEnabled(false);
            //desabilitaCampos();
            //limparPesquisar();
        }
    }
    
    private void DeleteUsuario(){
        int confirma = JOptionPane.showInternalConfirmDialog(getInstance(), "Tem certeza que deseja remover este registro?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM usuarios WHERE id_usuario=? ";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, txtUsuId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showInternalMessageDialog(getInstance(), "Registro Removido com Sucesso!");
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuCPF.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                    cboUsuPerfil.setSelectedItem(null);

                    carregaTabela();
                    btnUsuNovo.setEnabled(true);
                }
            } catch (Exception erro) {
                JOptionPane.showInternalMessageDialog(getInstance(),"DeleteUsuário: " + erro);
            }
        }
    }
    
    private void pesquisarUsuario() {
        String sql = "SELECT * FROM usuarios WHERE usuario like ?";

        try {
            pst = conn.prepareStatement(sql);
            //PASSANDO O CONTRUDO DA CX DE PESQUISA PARA O ?
            //ATENÇÃO AO "%" QUE É A CONTINUAÇÃO DA STRING SQL
            pst.setString(1, txtPesqUsuario.getText() + "%");
            rs = pst.executeQuery();

            //A LINHA ABX USA BIBLIOTECA rs2xml.jar PARA PREENCHER A TABELA
            tblUsuarios.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception erro) {
            JOptionPane.showInternalMessageDialog(getInstance(),"PesquisarUsuario:" + erro);
            //JOptionPane.showMessageDialog(null, e);
        }
    }

    public void desabilitaCampos() {
        txtUsuNome.setEnabled(false);
        txtUsuCPF.setEnabled(false);
        txtUsuLogin.setEnabled(false);
        txtUsuSenha.setEnabled(false);
        cboUsuPerfil.setEnabled(false);
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
        jPanel4 = new javax.swing.JPanel();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsuNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuSenha = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        txtUsuCPF = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuNovo = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtPesqUsuario = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        lblDataHora = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnUltimo = new javax.swing.JButton();
        btnAvancar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnPrimeiro = new javax.swing.JButton();

        timer1.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                timer1OnTime(evt);
            }
        });

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Usuários");
        setPreferredSize(new java.awt.Dimension(800, 480));

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2" }));

        jLabel6.setText("*USUÁRIO");

        txtUsuId.setEnabled(false);

        jLabel7.setText("*PERFIL");

        jLabel3.setText("ID");

        jLabel5.setText("*LOGIN");

        jLabel4.setText("*SENHA");

        jLabel8.setText("*CPF:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUsuNome)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboUsuPerfil, 0, 130, Short.MAX_VALUE)
                    .addComponent(txtUsuCPF))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(txtUsuCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/save16x16.png"))); // NOI18N
        btnUsuCreate.setToolTipText("Adicionar Usuário");
        btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });

        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/update16x16.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("Alterar Usuário");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });

        btnUsuNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/add16x16.png"))); // NOI18N
        btnUsuNovo.setToolTipText("Novo Usuário");
        btnUsuNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuNovo.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuNovoActionPerformed(evt);
            }
        });

        btnUsuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/delete16x16.png"))); // NOI18N
        btnUsuDelete.setToolTipText("Remover Usuário");
        btnUsuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUsuNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUsuNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bookman Old Style", 1, 14), new java.awt.Color(0, 153, 153))); // NOI18N

        txtPesqUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesqUsuarioActionPerformed(evt);
            }
        });
        txtPesqUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesqUsuarioKeyReleased(evt);
            }
        });

        jLabel11.setText("Nome:");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPesqUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesqUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "USUÁRIO", "CPF", "LOGIN", "SENHA", "PERFIL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        lblDataHora.setText("DataHora");

        jLabel2.setText("*Campos Obrigatórios");

        btnUltimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/icons8-rodada-de-avanço-rápido-48.png"))); // NOI18N
        btnUltimo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });

        btnAvancar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/icons8-à-direita-dentro-de-um-círculo-48.png"))); // NOI18N
        btnAvancar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAvancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvancarActionPerformed(evt);
            }
        });

        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/icons8-à-esquerda-dentro-de-um-círculo-48.png"))); // NOI18N
        btnVoltar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnPrimeiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/icons8-botão-de-retrocesso-redondo-48.png"))); // NOI18N
        btnPrimeiro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeiroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(btnPrimeiro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAvancar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrimeiro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAvancar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDataHora)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        AddUsuario();
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        UpdateUsuario();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuNovoActionPerformed
        // TODO add your handling code here:
        txtUsuId.setText("");
        txtUsuNome.setText("");
        txtUsuCPF.setText("");
        txtUsuLogin.setText("");
        txtUsuSenha.setText("");
        cboUsuPerfil.setSelectedItem("");

        //btnUsuNovo.setEnabled(true);
        btnUsuCreate.setEnabled(true);
        btnUsuUpdate.setEnabled(false);
        btnUsuDelete.setEnabled(false);
        btnCancelar.setEnabled(true);
    }//GEN-LAST:event_btnUsuNovoActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        DeleteUsuario();        
    }//GEN-LAST:event_btnUsuDeleteActionPerformed


    private void txtPesqUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesqUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesqUsuarioActionPerformed

    private void txtPesqUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesqUsuarioKeyReleased
        pesquisarUsuario();
    }//GEN-LAST:event_txtPesqUsuarioKeyReleased

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        setar_campos();

        //A linha abx desabilita o btnCreate(add)
        btnUsuCreate.setEnabled(false);
        //btnUsuCreate.setEnabled(false);
        btnUsuUpdate.setEnabled(true);
        btnUsuDelete.setEnabled(true);
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void btnPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeiroActionPerformed
        // TODO add your handling code here:
        try {
            rs.first();
            txtUsuId.setText(rs.getString(1));
            txtUsuNome.setText(rs.getString(2));
            txtUsuCPF.setText(rs.getString(3));
            txtUsuLogin.setText(rs.getString(4));
            txtUsuSenha.setText(rs.getString(5));
            cboUsuPerfil.setSelectedItem(rs.getString(6));

        } catch (SQLException ex) {
            Logger.getLogger(FrmTelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrimeiroActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
        try {
            if (!rs.isFirst()) {
                rs.previous();
                txtUsuId.setText(rs.getString(1));
                txtUsuNome.setText(rs.getString(2));
                txtUsuCPF.setText(rs.getString(3));
                txtUsuLogin.setText(rs.getString(4));
                txtUsuSenha.setText(rs.getString(5));
                cboUsuPerfil.setSelectedItem(rs.getString(6));

            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmTelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnAvancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvancarActionPerformed
        // TODO add your handling code here:
        try {
            if (!rs.isLast()) {
                rs.next();
                txtUsuId.setText(rs.getString(1));
                txtUsuNome.setText(rs.getString(2));
                txtUsuCPF.setText(rs.getString(3));
                txtUsuLogin.setText(rs.getString(4));
                txtUsuSenha.setText(rs.getString(5));
                cboUsuPerfil.setSelectedItem(rs.getString(6));

            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmTelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAvancarActionPerformed

    private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed
        // TODO add your handling code here:
        try {
            rs.last();
            txtUsuId.setText(rs.getString(1));
            txtUsuNome.setText(rs.getString(2));
            txtUsuCPF.setText(rs.getString(3));
            txtUsuLogin.setText(rs.getString(4));
            txtUsuSenha.setText(rs.getString(5));
            cboUsuPerfil.setSelectedItem(rs.getString(6));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_btnUltimoActionPerformed

    private void timer1OnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timer1OnTime
        MinhaData minha = new MinhaData(new java.util.Date());
        lblDataHora.setText(minha.getDiaSemana() + ", " + minha.getDataHora());
    }//GEN-LAST:event_timer1OnTime

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        carregaTabela();
        btnUsuNovo.setEnabled(true);
        btnUsuCreate.setEnabled(false);
        btnUsuUpdate.setEnabled(false);
        btnUsuDelete.setEnabled(false);
        //desabilitaCampos();
        limparPesquisar();
        btnCancelar.setEnabled(false);
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvancar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnPrimeiro;
    private javax.swing.JButton btnUltimo;
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuNovo;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDataHora;
    private javax.swing.JTable tblUsuarios;
    private org.netbeans.examples.lib.timerbean.Timer timer1;
    private javax.swing.JTextField txtPesqUsuario;
    private javax.swing.JTextField txtUsuCPF;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JPasswordField txtUsuSenha;
    // End of variables declaration//GEN-END:variables
}
