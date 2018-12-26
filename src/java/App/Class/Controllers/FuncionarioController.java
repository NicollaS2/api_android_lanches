package App.Class.Controllers;

//Classe Controller Funcionario
import App.Class.DAO.FuncionarioDAO;
import App.Class.DAO.UsuarioDAO;
import App.Class.Model.Funcionario;
import App.Class.Model.FuncionarioSimplificado;
import App.Class.error.PiError;
import java.util.ArrayList;

//Responsável pela lógica de negócio da aplicação
//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo
public class FuncionarioController {

    public int inserirFuncionario(Funcionario funcionario) throws PiError {
        try {

            return new FuncionarioDAO().inserirFuncionario(funcionario);

        } catch (PiError e) {
            throw e;
        }
    }

    public ArrayList<FuncionarioSimplificado> buscarFuncionariosPorEstabelecimento(int idestabelecimento) throws PiError {
        try {

            return new FuncionarioDAO().BuscarFuncionarioPorEstabelecimento(idestabelecimento);

        } catch (PiError e) {
            throw e;
        }
    }

    public Funcionario loginFuncionarios(Funcionario funcionario) throws PiError {
        try {

            return new FuncionarioDAO().LoginFuncionario(funcionario);

        } catch (PiError e) {
            throw e;
        }
    }
    
    public boolean excluirFuncionarios(long funcionario) throws PiError {
        try {

            return new FuncionarioDAO().ExcluirFuncionario(funcionario);

        } catch (PiError e) {
            throw e;
        }
    }

}
