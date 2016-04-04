/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.persistencia;

/**
 *
 * @author Francisco
 */
public class DadosLogin {
    private String nomeDeUsuario;
    private String senha;
    private String IP_remoto;
    
    public DadosLogin(String nomeDeUsuario, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getIP_remoto() {
        return IP_remoto;
    }

    public void setIP_remoto(String IP_remoto) {
        this.IP_remoto = IP_remoto;
    }
    
    
}
