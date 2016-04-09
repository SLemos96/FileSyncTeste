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
public class Arquivo implements Parametro{
    private File arquivo;
    private byte[] data;
    
    public Arquivo(File arquivo) {
        this.arquivo = arquivo;
    }
    
    public Arquivo(String nome) {
        this.arquivo = new File(nome);
    }

    public byte[] getData() {
        return data;
    }
    
    
    
    public File getArquivo() {
        return arquivo;
    }
    
    public String getNomeArquivo() {
        return arquivo.getAbsolutePath();
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return arquivo.getName();
    }
    
    @Override
    public String tipoParametro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
