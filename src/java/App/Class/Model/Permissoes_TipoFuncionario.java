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
public class Permissoes_TipoFuncionario {
 
    private TipoFuncionario tipoFuncionario;
    private Permissoes permissoes;

    public Permissoes_TipoFuncionario() {
    }

    public Permissoes_TipoFuncionario(TipoFuncionario tipoFuncionario, Permissoes permissoes) {
        this.tipoFuncionario = tipoFuncionario;
        this.permissoes = permissoes;
    }

    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    public Permissoes getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Permissoes permissoes) {
        this.permissoes = permissoes;
    }
    
    
    
    
    
}
