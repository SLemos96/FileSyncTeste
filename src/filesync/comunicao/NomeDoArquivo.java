/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.comunicao;

import java.io.File;

/**
 *
 * @author Francisco
 */
public class NomeDoArquivo implements Parametro{
    private File arquivo;
    
    public NomeDoArquivo(File arquivo) {
        this.arquivo = arquivo;
    }
    
    public File getArquivo() {
        return arquivo;
    }
    
    public String getNomeArquivo() {
        return arquivo.getAbsolutePath();
    }

    @Override
    public String tipoParametro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
