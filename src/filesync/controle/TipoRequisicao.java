/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.controle;

import java.io.Serializable;

/**
 *
 * @author Francisco
 */
public enum TipoRequisicao implements Serializable{
    
    Autenticacao("autenticando usuario"),
    Download("download"),
    Upload("upload"),
    ObterListaArquivo("obterDiretorio"),
    ExibirArquivos("listar"),
    ExibirDiretoriosRemotos("criando arvore de diretórios remotos"),
    ExibirArquivosRemotos("listando arquivos remotos"),
    ObterEscolhaRemotaDiretorio("Escolha de Diretorio remota");        
        
    private final String descricao;
    
    TipoRequisicao(String requisicao) {
        this.descricao = requisicao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}
