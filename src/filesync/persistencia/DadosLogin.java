/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.persistencia;

import filesync.parametro.Parametro;
import java.io.Serializable;

/**
 *
 * @author Francisco
 */
public class DadosLogin implements Serializable, Parametro{
    
    private final String nomeDeUsuario;
    private final String senha;
    
    public DadosLogin(String nomeDeUsuario, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;        
    }

    public String getSenha() {
        return senha;
    }
    
    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }
    
    @Override
    public String tipoParametro() {
        return "dados de login";
    }
    
    
    
}
