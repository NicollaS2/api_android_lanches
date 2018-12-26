/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Class.WebService;

import App.Class.Controllers.UsuarioController;
import App.Class.Model.Pedido_Produto;
import App.Class.Model.Usuario;
import App.Class.error.PiError;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

//Classe WebService Usuario
//Responsável pelo envio e recebimento de dados entre aplicações

//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo

@WebService(serviceName = "UsuarioWS")
public class UsuarioWS {

    @WebMethod(operationName = "inserirusuario")
    public int InserirUsuario(@WebParam(name = "dados") Usuario usuario) throws PiError {
        return new UsuarioController().inserirUsuario(usuario);
    }
    
    @WebMethod(operationName = "loginUsuario")
    public Usuario loginUsuario(@WebParam(name = "dados2") Usuario usuario) throws PiError {
        return new UsuarioController().loginUsuario(usuario);
    }
}
