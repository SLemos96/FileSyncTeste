/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.controle;

import filesync.persistencia.IBancoDados;
import filesync.persistencia.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Francisco
 */
public class AutenticadorUsuario {
    private IBancoDados bd;
    
    public AutenticadorUsuario(IBancoDados bd) {
        this.bd = bd;
    }
    
    public boolean autenticarUsuario(String user, String password) 
    {
        boolean sucesso = false;
        
        if (bd.encontrarUsuario(user)) {
            if (bd.getSenha(user).equals(password)) {
                System.out.println("Você logou com sucessssssssssooo!!!");
                sucesso = true;
            }
            else {
                System.out.println("Senha incorreta!");
            }
        }
        else {
            System.out.println("Usuario não cadastrado!");
            sucesso = false;
        }
        return sucesso;
    }
}
