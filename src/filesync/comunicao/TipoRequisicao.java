/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesync.comunicao;

import java.io.Serializable;

/**
 *
 * @author Francisco
 */
public enum TipoRequisicao implements Serializable{
    
    Download("download"),
    Upload("upload"),
    ObterListaArquivo("obterDiretorio"),
    ExibirArquivos("listar");
    
        
    private final String descricao;
    
    TipoRequisicao(String requisicao) {
        this.descricao = requisicao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}
