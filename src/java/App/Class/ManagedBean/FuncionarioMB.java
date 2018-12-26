/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Class.ManagedBean;


import App.Class.Controllers.FuncionarioController;
import App.Class.Model.Funcionario;
import App.Class.Session.MessageMB;
import App.Class.Session.RedirectMB;
import App.Class.Session.SessionMB;
import java.lang.ProcessBuilder.Redirect;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

//Classe Managed bean Funcionario
//WEB JAVASERVERFACES
//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo

@ManagedBean(name = "funcionarioMB")
@RequestScoped
public class FuncionarioMB {

    public FuncionarioMB() {
    }
    
    private Funcionario funcionario = new Funcionario();
    private SessionMB session= new SessionMB();
    private Funcionario dadosSession = (Funcionario) session.getAttribute("funcionario");

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public Funcionario getFuncionarioSession(String funcionario){
    
    
    return (Funcionario) session.getAttribute("funcionario");
    }
    
    public void loginFuncionario(){
        
        try{
        
            Funcionario logado = new FuncionarioController().loginFuncionarios(this.funcionario);
            
            if(logado.getId_funcionario() != 0){
            
                boolean auth = true;
                session.setAttribute("auth", auth);
                session.setAttribute("funcionario", logado);
                
                String url = "/home.xhtml";
                System.out.println("logado");
                new RedirectMB(url);   
                
            }
            else {
                new MessageMB("msgInfo", "Usuário ou Senha inválidos!", "", 3);
                //return null;
            }
            
        
        }catch(Exception e){
            System.out.println("erro login web");
            new MessageMB("msgInfo", e.getMessage(), "", 4);
        }
    
    
}
}