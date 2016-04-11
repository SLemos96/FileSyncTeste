/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.screens;

import filesync.controle.FileSync;
import filesync.persistencia.DadosLogin;
import filesync.persistencia.Usuario;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author samue
 */
public class LoginScreen extends javax.swing.JFrame {
    
    private FileSync fileSync;
    /**
     * Creates new form LoginButton
     */
    public LoginScreen(FileSync fileSync, int porta) {
        initComponents();
        jFieldUsuario.setText("admin");
        jFieldSenha.setText("admin");
        jFieldPorta.setText(Integer.toString(porta));
        this.fileSync = fileSync;        
        try {
            jFieldIP_Servidor.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jFieldDiretorioServidor.setText(System.getProperty("user.home")+"\\FileSyncRemote");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButtonClienteLogin = new javax.swing.JButton();
        jFieldSenha = new javax.swing.JPasswordField();
        jFieldIP_Cliente = new javax.swing.JTextField();
        jFieldPorta = new javax.swing.JTextField();
        IPDestLabel = new javax.swing.JLabel();
        jCheckBoxIPLocal = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        portaLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jFieldUsuario = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jFieldIP_Servidor = new javax.swing.JTextField();
        iniciarServidorButton = new javax.swing.JButton();
        avisoDeLogin1 = new javax.swing.JLabel();
        jFieldDiretorioServidor = new javax.swing.JTextField();
        butaoProcurar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jButtonClienteLogin.setText("Login");
        jButtonClienteLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClienteLoginActionPerformed(evt);
            }
        });

        jFieldSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldSenhaActionPerformed(evt);
            }
        });

        jFieldIP_Cliente.setText("localhost");
        jFieldIP_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldIP_ClienteActionPerformed(evt);
            }
        });

        jFieldPorta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldPortaActionPerformed(evt);
            }
        });

        IPDestLabel.setText("IP Servidor:");

        jCheckBoxIPLocal.setSelected(true);
        jCheckBoxIPLocal.setText("IP Local");
        jCheckBoxIPLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxIPLocalActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuário:");

        portaLabel.setText("Porta:");

        jLabel2.setText("Senha:");

        jFieldUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(portaLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(IPDestLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonClienteLogin)
                            .addComponent(jFieldPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jFieldIP_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFieldSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxIPLocal))))
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(281, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IPDestLabel)
                    .addComponent(jFieldIP_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxIPLocal))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(portaLabel))
                .addGap(23, 23, 23)
                .addComponent(jButtonClienteLogin)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cliente", jPanel1);

        jLabel6.setText("IP_Servidor:");

        jFieldIP_Servidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldIP_ServidorActionPerformed(evt);
            }
        });

        iniciarServidorButton.setText("Iniciar");
        iniciarServidorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarServidorButtonActionPerformed(evt);
            }
        });

        avisoDeLogin1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jFieldDiretorioServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldDiretorioServidorActionPerformed(evt);
            }
        });

        butaoProcurar.setText("Procurar");
        butaoProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butaoProcurarActionPerformed(evt);
            }
        });

        jLabel4.setText("Diretório_raiz:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFieldIP_Servidor, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jFieldDiretorioServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(butaoProcurar))
                            .addComponent(iniciarServidorButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(avisoDeLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butaoProcurar)
                    .addComponent(jFieldDiretorioServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(avisoDeLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jFieldIP_Servidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(iniciarServidorButton)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Servidor", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 558, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFieldUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFieldUsuarioActionPerformed

    private void jCheckBoxIPLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxIPLocalActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxIPLocal.isSelected())
            jFieldIP_Cliente.setText("localhost");
        else
            jFieldIP_Cliente.setText("");
    }//GEN-LAST:event_jCheckBoxIPLocalActionPerformed

    private void jFieldPortaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldPortaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFieldPortaActionPerformed

    private void jFieldIP_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldIP_ClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFieldIP_ClienteActionPerformed

    private void jFieldSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFieldSenhaActionPerformed

    private void jButtonClienteLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClienteLoginActionPerformed
        int porta = 0;   
        Usuario usuario;
        
        try {
            porta = Integer.parseInt(jFieldPorta.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Porta invalida");
            return;
        
        }
        fileSync.iniciarCliente();
        usuario = new Usuario(new DadosLogin(jFieldUsuario.getText(), obterSenha(jFieldSenha)));
        
        if (fileSync.autenticarServidor(usuario, jFieldIP_Cliente.getText(), porta)) {                                    
            //avisoDeLogin1.setText("Conexão estabelecida");
            //avisoDeLogin1.setVisible(true);
            dispose();
            
            //    JOptionPane.showMessageDialog(null, "Sevidor na porta: " + porta + " não encontrado");            
        }
        else {
            JOptionPane.showMessageDialog(null, "Usuário ou Senha incorreta!");
        }
    }//GEN-LAST:event_jButtonClienteLoginActionPerformed

    private void iniciarServidorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarServidorButtonActionPerformed
        // TODO add your handling code here:
        //Login sem escolha de pasta
        String serverName;
        String pastaServidor;
        
        serverName = jFieldIP_Servidor.getText();
        pastaServidor = jFieldDiretorioServidor.getText();
        
        fileSync.iniciarServidor(serverName, pastaServidor);
        dispose();
        
        /* Login com escolha de pasta
        String raiz = diretorioField.getText();
        if (raiz.equals(""))
            JOptionPane.showMessageDialog(null, "Um diretório deve ser especificado");
        else {
            fileSync.iniciarServidor(IPServidorField.getText(), raiz);
            new ServerScreen(IPServidorField.getText(), raiz).setVisible(true);            
            dispose();
        }*/
    }//GEN-LAST:event_iniciarServidorButtonActionPerformed

    private void jFieldIP_ServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldIP_ServidorActionPerformed
        
    }//GEN-LAST:event_jFieldIP_ServidorActionPerformed

    private void jFieldDiretorioServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldDiretorioServidorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFieldDiretorioServidorActionPerformed

    private void butaoProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butaoProcurarActionPerformed
        // TODO add your handling code here:
        jFieldDiretorioServidor.setText(fileSync.escolherDiretorioServidor());
    }//GEN-LAST:event_butaoProcurarActionPerformed
    
    /*
    public boolean verificarLogin() {
        boolean sucesso;
        
        String senhaString = obterSenha(jFieldSenha);
        String login = jFieldUsuario.getText();
        
        if (fileSync.autenticarUsuario(login, senhaString)) {
            // Cria janela MainScreen
            //new MainScreen().setVisible(true);
            sucesso = true;
        } else {
            sucesso = false;
        }
        
        return sucesso;
    }*/

    private String obterSenha(JPasswordField campoSenha) {
        char[] senha = campoSenha.getPassword();
        return new String(senha);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IPDestLabel;
    private javax.swing.JLabel avisoDeLogin1;
    private javax.swing.JButton butaoProcurar;
    private javax.swing.JButton iniciarServidorButton;
    private javax.swing.JButton jButtonClienteLogin;
    private javax.swing.JCheckBox jCheckBoxIPLocal;
    private javax.swing.JTextField jFieldDiretorioServidor;
    private javax.swing.JTextField jFieldIP_Cliente;
    private javax.swing.JTextField jFieldIP_Servidor;
    private javax.swing.JTextField jFieldPorta;
    private javax.swing.JPasswordField jFieldSenha;
    private javax.swing.JTextField jFieldUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel portaLabel;
    // End of variables declaration//GEN-END:variables
}
