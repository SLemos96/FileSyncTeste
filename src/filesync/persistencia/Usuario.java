/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.persistencia;

import filesync.comunicao.Parametro;
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Francisco
 */
public class Usuario implements Serializable, Parametro{
    private DadosLogin dadosLogin;
    private File diretorioAssociado;
    
    public Usuario(DadosLogin dadosDeLogin) {
        this.dadosLogin = dadosDeLogin;        
    }

    public DadosLogin getDadosLogin() {
        return dadosLogin;
    }

    public void setDadosLogin(DadosLogin dadosLogin) {
        this.dadosLogin = dadosLogin;
    }

    public File getDiretorioAssociado() {
        return diretorioAssociado;
    }

    public void setDiretorioAssociado(File diretorioAssociado) {
        this.diretorioAssociado = diretorioAssociado;
    }

    @Override
    public String tipoParametro() {
        return "usuario";
    }            
}
