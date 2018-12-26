package App.Class.Controllers;

//Classe Controller Estabelecimentos
import App.Class.DAO.EstabelecimentosDAO;
import App.Class.Model.Funcionario;
import App.Class.error.PiError;
import com.fasterxml.jackson.core.JsonProcessingException;

//Responsável pela lógica de negócio da aplicação
//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo
public class EstabelecimentosController {
    
    public Funcionario inserirEstabelecimento(Funcionario funcionanrio) throws PiError, JsonProcessingException {
        try {
            return new EstabelecimentosDAO().inserirEstabelecimento(funcionanrio);
        } catch (PiError error) {
            throw error;
        }
    }
}
