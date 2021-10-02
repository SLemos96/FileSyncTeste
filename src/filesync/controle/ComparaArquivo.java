/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.controle;


import filesync.persistencia.ArvoreDeArquivos;
import java.io.File;
import filesync.comunicao.*;


/**
 *
 * @author janioxavier
 */
public class ComparaArquivo{
    public ComparaArquivo() {
        
    }
    
    /**
     * @return true se os diretórios são iguais, false caso contrario.
     */
    public boolean compararDiretorios(ArvoreDeArquivos arvoreDir1, ArvoreDeArquivos arvoreDir2) {
        return true;
    }
    
    /**
     * @return 1 se os arquivo1 possui data mais recente que arquivo2
     *        -1 se os arquivo2 possui data mais recente que arquivo1
     *         0 se ambos arquivos possuem a mesma data. 
     */
    public int compararArquivoPorData(File arquivo1, File arquivo2) {
        return 0;
    }
    
    /**
     * @return true se os arquivos possuem o mesmo nome, false caso contrario.
     */
    public boolean compararArquivoPorNome(File arquivo1, File arquivo2) {
        return true;
    }

    
}
