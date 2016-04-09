/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.screens;

import filesync.comunicao.FileSystemModel;
import java.awt.Component;
import java.io.File;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Francisco
 */
public class EscolhaDiretorio extends javax.swing.JFrame implements Serializable{
    private File arquivoSelecionado;
    private FileSystemModel arvoreDeArquivos;
    private String jTreeSelecao;
    private final String raiz = System.getProperty("file.separator");
    private MainScreen telaPrincipal;
    private boolean isLocal;
    
    /**
     * Creates new form EscolhaDiretorio
     */
    public EscolhaDiretorio(MainScreen telaPrincipal, FileSystemModel raiz, boolean exibir) {
        initComponents();        
        this.telaPrincipal = telaPrincipal;
        this.arvoreDeArquivos = raiz;
        jTree1.setModel(arvoreDeArquivos); 
        this.isLocal = exibir;
        setVisible(true);
    }
    
    public EscolhaDiretorio(FileSystemModel raiz, boolean exibir) {
        initComponents();        
        this.telaPrincipal = null;
        this.arvoreDeArquivos = raiz;
        jTree1.setModel(arvoreDeArquivos);
        this.isLocal = exibir;
        setVisible(true);
        
    }
    
    public void setTelaPrincipal(MainScreen telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jButtonSelecionar = new javax.swing.JButton();

        setTitle("Escolha Diretorio");
        setAlwaysOnTop(true);

        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jTree1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTree1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        jButtonSelecionar.setText("Selecionar");
        jButtonSelecionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSelecionarMouseClicked(evt);
            }
        });
        jButtonSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSelecionar))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSelecionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        // TODO add your handling code here:
        try {            
            jTreeSelecao = jTree1.getSelectionPath().toString().replaceAll("[\\[\\]]", "").replace(", ", "\\");            
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_jTree1MouseClicked

    private void jTree1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTree1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTree1KeyReleased

    private void jButtonSelecionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSelecionarMouseClicked
        // TODO add your handling code here:        
    }//GEN-LAST:event_jButtonSelecionarMouseClicked

    private void jButtonSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecionarActionPerformed
        // TODO add your handling code here:
        try {
            if (raiz.equals("\\")) {
                jTreeSelecao = jTreeSelecao.substring(1);
                arquivoSelecionado = new File(jTreeSelecao);
            }
            else
                arquivoSelecionado = new File(jTreeSelecao);
        } catch(Exception e) {
            arquivoSelecionado = null;
        }
        if (arquivoSelecionado != null && arquivoSelecionado.exists()) {
            if (arquivoSelecionado.isFile()) {
                arquivoSelecionado = null;
                JOptionPane.showMessageDialog(rootPane, "Selecione um diretorio");
            } else {
                exibirDiretorioSelecionado();                
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Nenhum diretorio foi selecionado");
        }
    }//GEN-LAST:event_jButtonSelecionarActionPerformed

    private void exibirDiretorioSelecionado() {
        if (isLocal) {
            telaPrincipal.setPastaLocalField(jTreeSelecao);
            telaPrincipal.mostrarPainelDiretorio(new FileSystemModel(new File(jTreeSelecao)), isLocal);       
        } else {
            telaPrincipal.setPastaRemotaField(jTreeSelecao);
            telaPrincipal.mostrarPainelDiretorio(new FileSystemModel(new File(jTreeSelecao)), isLocal);       
        }
    }
    
    public File getarquivoSelecionado() {
        return arquivoSelecionado;
    }
    
    public FileSystemModel getArvoreDeArquivos() {
        if (arquivoSelecionado != null)
            return new FileSystemModel(arquivoSelecionado);
        return null;
    }        
    
    public static void main(String[] args) {
        EscolhaDiretorio ed = new EscolhaDiretorio(null, null, true);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSelecionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
