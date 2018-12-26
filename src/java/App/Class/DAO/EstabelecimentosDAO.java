package App.Class.DAO;

//Classe DAO Estabelecimentos
import App.Class.Model.Estabelecimentos;
import App.Class.Model.Funcionario;
import App.Class.Model.TipoFuncionario;
import App.Class.RestAPI.Config.Json;
import App.Class.error.LibError;
import App.Class.error.PiError;
import App.DataBase.Database;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

//Responsável pela conexão com o banco de dados
//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo
public class EstabelecimentosDAO {

    public Funcionario inserirEstabelecimento(Funcionario dados) throws PiError, JsonProcessingException {
        boolean resposta = false;
        int id_estabelecimento = 0;
        int id_funcionario = 0;
        Funcionario loginDados = new Funcionario();
                        System.out.println(Json.toJson(dados) + " primeiro json rever");

        try {
            Database base = new Database();

            String sql = "INSERT INTO `estabelecimento` (`nome`, `img`) "
                    + "VALUES (?,?)";

            base.setQuerySql(sql);
            base.setQueryParameter().setString(1, dados.getEstabelecimento().getNome());
            base.setQueryParameter().setString(2, dados.getEstabelecimento().getImg());

            base.preStatement.executeUpdate();
            ResultSet resultado = base.preStatement.getGeneratedKeys();

            if (resultado.first()) {
                                System.out.println("entrou no if de inserir estabelecimento" + dados.getEmail());

                id_estabelecimento = resultado.getInt(1);
                TipoFuncionario tp = new TipoFuncionario(2, "dados");
                Estabelecimentos estabelecimento = new Estabelecimentos();
                estabelecimento.setId_estabelecimentos(id_estabelecimento);
                dados.setEstabelecimento(estabelecimento);
                dados.setTipoFuncionario(tp);
                System.out.println("revendo  if de inserir estabelecimento / variavel tipo funcionario " + dados.getTipoFuncionario().getId_tipoFuncionario());
                id_funcionario = inserirFuncionario(dados);
                System.out.println("saiu no if de cadastrar funcionario da classe estabelecimento + " + dados.getId_funcionario());
                if (id_funcionario != 0) {
                    System.out.println("entrou no if de login da classe estabelecimento");
                    loginDados = new FuncionarioDAO().LoginFuncionario(dados);
                }
                
                System.out.println("classe estabelecimento retornou id funcionario + " + dados.getId_funcionario());
            }
            base.close();
            return loginDados;
            
        } catch (PiError error) {
            throw error;

        } catch (SQLException e) {
            PiError error = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + e.getMessage(),
                    "EstabelecimentoDAO:inserirEstabelecimento",
                    "lógica de negócio - inserção de dados");
            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "EstabelecimentoDAO:inserirEstabelecimento",
                    "lógica de negócio - inserção de dados");
            throw error;
        }
    }
    
    
    public int inserirFuncionario(Funcionario dados) throws PiError {

        Estabelecimentos estabelecimentos = new Estabelecimentos();
        
        System.out.println(dados.getEmail());
        int id_funcionario = 0;
        try {
            Database base = new Database();

            String sql = "INSERT INTO `funcionario` (`nome`, `img`, `id_estabelecimento`, `id_tipoFuncionario`, `login`, `senha`, `status`) "
                    + "VALUES (?,?,?,?,?,MD5(?),?)";

            base.setQuerySql(sql);
            base.setQueryParameter().setString(1, dados.getNome());
            base.setQueryParameter().setString(2, dados.getImg());
            base.setQueryParameter().setInt(3, dados.getEstabelecimento().getId_estabelecimentos());
            base.setQueryParameter().setInt(4, dados.getTipoFuncionario().getId_tipoFuncionario());
            base.setQueryParameter().setString(5, dados.getEmail());
            base.setQueryParameter().setString(6, dados.getSenha());
            base.setQueryParameter().setInt(7,3);

            base.preStatement.executeUpdate();
            ResultSet resultado = base.preStatement.getGeneratedKeys();

            if (resultado.first()) {
                System.out.println(dados.getEmail() + "entrou no if de inserir funcionario");
                id_funcionario = resultado.getInt(1);

            }
                            System.out.println(dados.getEmail() + "sai no if de inserir funcionario");

            base.close();
        } catch (PiError error) {
            throw error;

        } catch (SQLException e) {
            PiError error = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + e.getMessage(),
                    "FuncionarioDAO:inserirFuncionario",
                    "lógica de negócio - inserção de dados");
            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "FuncionarioDAO:inserirFuncionario",
                    "lógica de negócio - inserção de dados");
            throw error;
        }

        return id_funcionario;
    }
}
