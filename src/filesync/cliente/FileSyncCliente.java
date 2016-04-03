/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.cliente;

import filesync.screens.MainScreen;

/**
 * Comente as classes e seus métodos... Podem ser comentários simplistas
 * @author Reinaldo
 */
public class FileSyncCliente {

    /**
     * @param args the command line arguments
     */
    Reply resposta;
    Request requisicao;

    public static void main(String[] args) {
        // TODO code application logic here
        MainScreen tela = new MainScreen();
        tela.setVisible(true);
        
        run_application(tela);
        
    }
    
    public static void run_application(MainScreen aplic){
    
        
    
    }

}
