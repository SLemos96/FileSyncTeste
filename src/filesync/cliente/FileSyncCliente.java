/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.cliente;

import filesync.controle.AutenticadorUsuario;
import filesync.persistencia.BDArquivo;
import filesync.persistencia.IBancoDados;
import filesync.screens.MainScreen;

/**
 * Comente as classes e seus métodos... Podem ser comentários simplistas
 * @author Reinaldo
 */
public class FileSyncCliente {

    /**
     * @param args the command line arguments
     */
    private AutenticadorUsuario autenticador;
    private IBancoDados bd;
    private Reply resposta;
    private Request requisicao;
    
    public FileSyncCliente() {
        bd = new BDArquivo();
        autenticador = new AutenticadorUsuario(bd);
    }

    public AutenticadorUsuario getAutenticador() {
        return autenticador;
    }

    public IBancoDados getBd() {
        return bd;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        FileSyncCliente fsc = new FileSyncCliente();
        MainScreen tela = new MainScreen();
        tela.setVisible(true);
        
        run_application(tela);
        
    }
    
    public static void run_application(MainScreen aplic){
    
        
    
    }

}
