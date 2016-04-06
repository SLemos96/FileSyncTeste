/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.controle;
import java.awt.*;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 *
 * @author Francisco
 */
public class EscolhaDiretorio {
    private JFileChooser fc;
    private Component dialogBox;
    private File diretorioOrigem;
   
    
    public EscolhaDiretorio() {
        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.dialogBox = null;
    }
    
    public EscolhaDiretorio(String pathInicial) {
        fc = new JFileChooser(pathInicial);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.dialogBox = null;
    }
    
    public EscolhaDiretorio(Component dialogBox, String pathInicial) {
        fc = new JFileChooser(pathInicial);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.dialogBox = dialogBox;
    }
    
    public File getDiretorioOrigem() {
        return diretorioOrigem;
    }

    public Component getDialogBox() {
        return dialogBox;
    }
    
    public void setDialogBox(Component dialogBox) {
        this.dialogBox = dialogBox;
    }
    
    public File escolherDiretorio() {
        fc.showOpenDialog(dialogBox);        
        return fc.getSelectedFile();
    }
    
}
