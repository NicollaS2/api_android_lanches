/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Class.ManagedBean;

import App.Class.Controllers.PermissoesController;
import App.Class.Model.Permissoes;
import App.Class.Model.Permissoes_TipoFuncionario;
import App.Class.error.PiError;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "permissoesMB")
@RequestScoped
public class PermissoesMB {
    
    
    Permissoes permissoes = new Permissoes();

    public PermissoesMB() {
    }

    public Permissoes getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Permissoes permissoes) {
        this.permissoes = permissoes;
    }
    
    
    
    public ArrayList<Permissoes_TipoFuncionario> permissoesFuncionario(int id) throws PiError{
    
        ArrayList<Permissoes_TipoFuncionario> lista = new PermissoesController().obterPermissoesFuncionario(id);
        
        return lista;
        
    }
    
    
    
    
}
