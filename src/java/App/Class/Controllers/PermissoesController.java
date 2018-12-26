/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Class.Controllers;

import App.Class.DAO.PermissoesDAO;
import App.Class.Model.Permissoes_TipoFuncionario;
import App.Class.error.PiError;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class PermissoesController {
    
    public ArrayList<Permissoes_TipoFuncionario> obterPermissoesFuncionario(int id_funcionario) throws PiError {
        
        return new PermissoesDAO().BuscarPermissoesDoFuncionario(id_funcionario);
    }
    
}
