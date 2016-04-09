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
    private String caminhoDoArquivo;
    private String nomeDoDestino;
    private String nomeDoArquivo;
    private long tamanho;
    private long ultimaAlteracao;
    private byte[] data;
    
    public Arquivo(File arquivo) {
        this.arquivo = arquivo;
        this.nomeDoArquivo = arquivo.getName();
        this.caminhoDoArquivo = arquivo.getAbsolutePath();
        this.tamanho = arquivo.length();
        this.ultimaAlteracao = arquivo.lastModified();
    }
    
    public Arquivo(String nomeDoDestino) {
        this.nomeDoDestino = nomeDoDestino;
    }

    public String getNomeDoArquivo() {
        return nomeDoArquivo;
    }

    public void setNomeDoArquivo(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
    }
    

    public byte[] getData() {
        return data;
    }

    public String getNomeDoDestino() {
        return nomeDoDestino;
    }

    public void setNomeDoDestino(String nomeDoDestino) {
        this.nomeDoDestino = nomeDoDestino;
    }
            
    public File getArquivo() {
        return arquivo;
    }
    
    public void setData(byte[] data) {
        this.data = data;
    }

    public String getCaminhoDoArquivo() {
        return caminhoDoArquivo;
    }

    public long getTamanho() {
        return tamanho;
    }

    public long getUltimaAlteracao() {
        return ultimaAlteracao;
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
