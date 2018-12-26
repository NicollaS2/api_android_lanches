
package App.Class.WebService;

import App.Class.Controllers.FuncionarioController;
import App.Class.Controllers.PermissoesController;
import App.Class.Model.Funcionario;
import App.Class.Model.Permissoes_TipoFuncionario;
import App.Class.RestAPI.Config.Json;
import App.Class.Util.TrueFalseResponse;
import App.Class.error.ApiError;
import App.Class.error.LibError;
import App.Class.error.PiError;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ws.rs.core.Response;

//Classe WebService Funcionario

//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo

@WebService(serviceName = "FuncionarioWS")
public class FuncionarioWS 
{

    
    @WebMethod(operationName = "InserirFuncionario")
    public Response InserirFuncionario(@WebParam(name = "dados3") Funcionario funcionario) throws PiError 
    {   
        try{
        boolean resposta = false;
        int idFuncionario = new FuncionarioController().inserirFuncionario(funcionario);
        if(idFuncionario != 0){
            resposta = true;
            
        }
            return Response.status(200).entity(new TrueFalseResponse(resposta)).build();
        }catch(PiError error){
            ApiError apierror = new ApiError(error);
            return Response.status(500).entity(apierror).build();
            
        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP06,
                    LibError.msgEP06 + e.getMessage(),
                    "FuncionariosApi:inserrirFuncionario",
                    "api para inserção de dados");
            ApiError apierror = new ApiError(error);

            return Response.status(500).entity(apierror).build();
        }
    }
    
    /*@WebMethod(operationName = "buscarFuncionarioporEstabelecimento")
    public ArrayList<Funcionario> buscarFuncionarioPorEstabelecimento(@WebParam(name = "dados1") int estabelecimento) throws PiError 
    {
      return new FuncionarioController().buscarFuncionariosPorEstabelecimento(estabelecimento);
    }*/ 
    
    @WebMethod(operationName = "loginFuncionarios")
    public Funcionario loginFuncionarios(@WebParam(name = "dados2") Funcionario funcionario) throws PiError 
    {
        return new FuncionarioController().loginFuncionarios(funcionario);
    }
    
    @WebMethod(operationName = "obterPermissoes")
    public ArrayList<Permissoes_TipoFuncionario> obterPermissoesFuncionario(@WebParam(name = "id") int id_funcionario) throws PiError 
    {
        return new PermissoesController().obterPermissoesFuncionario(id_funcionario);
    }
}

