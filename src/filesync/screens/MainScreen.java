/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.screens;

import filesync.persistencia.ArvoreDeArquivos;
import filesync.parametro.ArvoreGraficaDeArquivos;
import filesync.comunicao.Cliente;
import filesync.persistencia.FileSystemModel;
import filesync.parametro.Parametro;
import java.io.File;
import javax.swing.JFileChooser;
import filesync.controle.AutenticadorUsuario;
import filesync.controle.FileSync;
import filesync.parametro.Arquivo;
import filesync.persistencia.ArquivoDestino;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTree;


/**
 *
 * @author Reinaldo
 */
public class MainScreen extends javax.swing.JFrame {

    /**
     * Creates new form MainScreen
     */    
    private FileSystemModel fsmRemoto;
    private ArvoreGraficaDeArquivos arvoreLocal;
    private ArvoreGraficaDeArquivos arvoreRemota;
    private ArvoreDeArquivos arvoreDeArquivosLocal; 
    private ArvoreDeArquivos arvoreDeArquivosRemota;
    private EscolhaDiretorio chooseDiretorio;
    private List<ArquivoDestino> arquivosDiferentes;
    private Cliente cliente;
    private JFileChooser escolherDiretorio;
    
    public MainScreen(Cliente cliente) {
        initComponents();
        this.cliente = cliente;
        arquivosDiferentes = new ArrayList<ArquivoDestino>();
        fsmRemoto = null;                
    }

    /**
     * @param diretorio
     * @param isAreaLocal 
     */
    public void mostrarPainelDiretorio(FileSystemModel fsm, boolean isAreaLocal) {
        if (isAreaLocal) {            
            jTreeLocal.setModel(fsm);
            
        } else {
            jTreeRemota.setModel(fsm);            
        }
    }
    
    public void setPastaLocalField(String texto) {
        pastaLocalField.setText(texto);
    }
    
    public void setPastaRemotaField(String texto) {
        pastaRemotaField.setText(texto);
    }
        
    public ArvoreDeArquivos getArvoreDeArquivosLocal() {
        return arvoreDeArquivosLocal;
    }
    
    public File getRaizLocal() {
        return arvoreDeArquivosLocal.getRaiz();
    }
    
    /*
    public void formatarAreaDeTexto(ArvoreDeArquivos arvore, boolean isAreaRemota) {     
        try {
            arvoreDeArquivosRemota = arvore.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] lines = arvore.listaDeArquivos().split("\n");
        String text = "";
        int cont = 0;
        
        for (String line : lines) {
            if (cont == 0)
                cont++;
            else {
                text += line ;
            }
        }
        
        if (isAreaRemota) {
            pastaRemotaField.setText(lines[0]);
            remoteTextArea.setText(text);            
        } else {
            pastaLocalField.setText(lines[0]);
            localTextArea.setText(text);
        }
    }*/
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pastaLocalField = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pastaRemotaField = new javax.swing.JTextField();
        procurarLocalButton = new javax.swing.JButton();
        procurarRemotoButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTreeRemota = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTreeLocal = new javax.swing.JTree();
        jButtonSincronizar = new javax.swing.JButton();
        jButtonComparar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pastaLocalField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pastaLocalFieldActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/filesync/screens/setas.jpg"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Pasta Local");

        jLabel2.setText("Pasta Remota");

        pastaRemotaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pastaRemotaFieldActionPerformed(evt);
            }
        });

        procurarLocalButton.setText("procurar");
        procurarLocalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procurarLocalButtonActionPerformed(evt);
            }
        });

        procurarRemotoButton.setText("procurar");
        procurarRemotoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procurarRemotoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(pastaLocalField, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(procurarLocalButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pastaRemotaField, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(procurarRemotoButton)))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pastaLocalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(procurarLocalButton))
                        .addGap(41, 41, 41))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pastaRemotaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(procurarRemotoButton))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTreeRemota.setModel(null);
        jScrollPane1.setViewportView(jTreeRemota);

        jTreeLocal.setModel(null);
        jScrollPane2.setViewportView(jTreeLocal);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8))))
                .addContainerGap())
        );

        jButtonSincronizar.setText("Sincronizar");
        jButtonSincronizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSincronizarActionPerformed(evt);
            }
        });

        jButtonComparar.setText("Comparar");
        jButtonComparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCompararActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonSincronizar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonComparar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSincronizar)
                    .addComponent(jButtonComparar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pastaLocalFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pastaLocalFieldActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_pastaLocalFieldActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonCompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCompararActionPerformed
        // TODO add your handling code here:
        if (estaConectado()) {
            if (pastaLocalField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecione a pasta que será sincronizada");
            } else {
                //compararArvoresDeArquivos();
                JOptionPane.showMessageDialog(null, "Nao implementado");
            }
        }
            
    }//GEN-LAST:event_jButtonCompararActionPerformed

    private void pastaRemotaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pastaRemotaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pastaRemotaFieldActionPerformed

    private void jButtonSincronizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSincronizarActionPerformed
        // TODO add your handling code here:
        estaConectado();
        if (pastaLocalField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecione a pasta que será sincronizada");
        } else {            
            cliente.sincronizarDiretorios(pastaLocalField.getText(), pastaRemotaField.getText());            
        }
        
    }//GEN-LAST:event_jButtonSincronizarActionPerformed

    private void procurarLocalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procurarLocalButtonActionPerformed
        // TODO add your handling code here:        
        File dir = null;
        
        
        JFileChooser escolherDiretorio = new JFileChooser(System.getProperty("user.home"));
        escolherDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        escolherDiretorio.showOpenDialog(jPanel1);
        dir = escolherDiretorio.getSelectedFile();        
        
        if (dir != null) {
            setPastaLocalField(dir.getAbsolutePath());
            mostrarPainelDiretorio(new FileSystemModel(dir), true);
        }
    }//GEN-LAST:event_procurarLocalButtonActionPerformed

    private void procurarRemotoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procurarRemotoButtonActionPerformed
        // TODO add your handling code here:
        Arquivo diretorioRemoto = cliente.acessarPastaRemota();
        
        File pastaLocal = new File(diretorioRemoto.getCaminhoLocal());
        
        JFileChooser escolherDiretorio = new JFileChooser(pastaLocal);
        escolherDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int fc = escolherDiretorio.showOpenDialog(jPanel1);
        if (fc == JFileChooser.APPROVE_OPTION) {
            pastaLocal = escolherDiretorio.getSelectedFile();
            setPastaRemotaField(diretorioRemoto.getCaminhoLocal());
            mostrarPainelDiretorio(new FileSystemModel(pastaLocal), false);
        }
    }//GEN-LAST:event_procurarRemotoButtonActionPerformed
        
    
    /*
    public String listarArquivosDiferentes() {
        String text = "";
        
        for (ArquivoDestino arquivoDestino : arquivosDiferentes) {
            if (arquivoDestino.isToServer())
                text += arquivoDestino.getFile().getName() + " >>> " ;
            else
                text += " <<< " + arquivoDestino.getFile().getName();
            text += "\n";
        }
        return text;
    }*/
    
    public boolean estaConectado() {
        boolean sucesso;
        if (pastaRemotaField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Aguarde a conexão do outro cliente");
            sucesso = false;
        } else {
            sucesso = true;
        }
        return sucesso;
    }
    
    /**
     * 
     * @return uma lista com os arquivos destino. Arquivos destino são
     * os arquivos que serão sincronizados na pasta do servidor ou do cliente,
     * e eles possuem nomes diferentes, caso sejam iguais o mais antigo será
     * sincronizado.
     *
    private void compararArvoresDeArquivos() {
        
        File raizLocal = arvoreDeArquivosLocal.getRaiz();
        File raizRemota = arvoreDeArquivosRemota.getRaiz();
        
        //1- filhos da arvore local e remota       
        LinkedList<File> filhosLocal = arquivosFilhos(arvoreDeArquivosLocal);
        LinkedList<File> filhosRemoto = arquivosFilhos(arvoreDeArquivosRemota);        
        
        compararArquivos(filhosLocal, filhosRemoto, true);
        compararArquivos(filhosRemoto, filhosLocal, false);
        
        /*2- comparacação dos filhos
        while(!filhosLocal.isEmpty()) {
            File filhoLocal = filhosLocal.pollLast();
            filhosRemoto = arquivosFilhos(arvoreDeArquivosRemota);
            compararArquivos(filhoLocal, filhosRemoto, true);
        }
        
        filhosRemoto = arquivosFilhos(arvoreDeArquivosRemota);
        while(!filhosRemoto.isEmpty()) {
            File filhoRemoto = filhosRemoto.pollLast();
            filhosLocal = arquivosFilhos(arvoreDeArquivosLocal);
            compararArquivos(filhoRemoto, filhosLocal, false);
        } *
    }
    
    private void compararArquivos(LinkedList<File> arquivos1, LinkedList<File> arquivos2, boolean toServer) {
        while (!arquivos1.isEmpty()) {
                File arquivo1 = arquivos1.pollLast();
                File arquivo2 = buscarArquivoPorNome(arquivo1, arquivos2);
                if (arquivo2 == null) {                    
                    arquivosDiferentes.add(new ArquivoDestino(arquivo1, toServer));
                }
                else {
                    switch (arquivoMaisRecente(arquivo1, arquivo2)) {
                        case 1:
                            arquivosDiferentes.add(new ArquivoDestino(arquivo1, false));
                            break;
                        case -1:
                            arquivosDiferentes.add(new ArquivoDestino(arquivo2, true));
                            break;
                    }
                }
        }
    }

    private File buscarArquivoPorNome(File arquivo, LinkedList<File> arquivos) {
        File fileName = null;
        while (!arquivos.isEmpty()) {
            fileName = arquivos.pollLast();
            if (fileName.getName().equals(arquivo.getName()))
                return fileName;
        }
        return null;
    }
    
    private boolean mesmoArquivo(File filhoLocal, File filhoRemoto) {
        if (mesmoCaminho(filhoLocal, filhoRemoto))
            return true;
        else 
            return false;
    }
    
    private boolean mesmoCaminho(File filhoLocal, File filhoRemoto) {
        String raizLocal = pastaLocalField.getText();
        String raizRemota = pastaRemotaField.getText();
        
        String filhoLocalString = filhoLocal.getName();
        String filhoRemotoString = filhoRemoto.getName();
        
        
        return ((filhoLocalString.compareTo(filhoRemotoString)) == 0);
        
    }
    
    public LinkedList<File> arquivosFilhos(ArvoreDeArquivos arvore) {
        LinkedList<File> arquivosFilhos = new LinkedList<>();
        File raiz = arvore.getRaiz();
        ArrayList<File> filhos;
        LinkedList<File> pilha = new LinkedList<>();
        pilha.push(raiz);
        
        while(!pilha.isEmpty()) {
            raiz = pilha.pollLast();
            arquivosFilhos.push(raiz);
            filhos = arvore.getFilhos(raiz);
            
            for (File filho : filhos) {
                if (arvore.isDiretorio(filho))
                    pilha.push(filho);
                else
                    arquivosFilhos.push(raiz);
            }                                                
        }
        return arquivosFilhos;
    }
    
    private int arquivoMaisRecente(File file1, File file2) {
        if (file1.lastModified() > file2.lastModified())
            return 1;
        else if (file1.lastModified() < file2.lastModified())
            return -1;
        else
            return 0;
    }    */
    
    public static void main(String[] args) {
        
        Cliente cliente = new Cliente();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen(cliente).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonComparar;
    private javax.swing.JButton jButtonSincronizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTree jTreeLocal;
    private javax.swing.JTree jTreeRemota;
    private javax.swing.JTextField pastaLocalField;
    private javax.swing.JTextField pastaRemotaField;
    private javax.swing.JButton procurarLocalButton;
    private javax.swing.JButton procurarRemotoButton;
    // End of variables declaration//GEN-END:variables

    public void setFSMRemoto(FileSystemModel arvoreDeArquivosRemoto) {
        this.fsmRemoto = arvoreDeArquivosRemoto;
    }    
}
