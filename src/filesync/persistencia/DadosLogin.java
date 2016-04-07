/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.persistencia;

import java.io.Serializable;

/**
 *
 * @author Francisco
 */
public class DadosLogin implements Serializable {
    
    private final String nomeDeUsuario;
    private final String senha;
    
    public DadosLogin(String nomeDeUsuario, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;        
    }

    public String getSenha() {
        return senha;
    }
    
    
    
}
