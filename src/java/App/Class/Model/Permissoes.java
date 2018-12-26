/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Class.Model;

/**
 *
 * @author pc
 */
public class Permissoes {
    
    private int id_permissoes;
    private String descricao;

    public Permissoes() {
    }

    public Permissoes(int id_permissoes, String descricao) {
        this.id_permissoes = id_permissoes;
        this.descricao = descricao;
    }

    public int getId_permissoes() {
        return id_permissoes;
    }

    public void setId_permissoes(int id_permissoes) {
        this.id_permissoes = id_permissoes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
    
    
}
