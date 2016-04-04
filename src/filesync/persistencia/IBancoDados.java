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
public interface IBancoDados {
    boolean encontrarUsuario(String nomeDeUsuario);
    String getSenha(String nomeDeUsuario);    
}
