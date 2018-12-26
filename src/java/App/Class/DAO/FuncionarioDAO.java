package App.Class.DAO;

import App.Class.Model.Estabelecimentos;
import App.Class.Model.Funcionario;
import App.Class.Model.FuncionarioSimplificado;
import App.Class.Model.TipoFuncionario;
import App.Class.error.LibError;
import App.Class.error.PiError;
import App.DataBase.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;

//Classe DAO Funcionario
//Responsável pela conexão com o banco de dados
//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo
public class FuncionarioDAO {

    public int inserirFuncionario(Funcionario dados) throws PiError {

        Estabelecimentos estabelecimentos = new Estabelecimentos();
        
        System.out.println(dados.getEmail());
        int id_funcionario = 0;
        try {
            Database base = new Database();

            String sql = "INSERT INTO `funcionario` (`nome`, `img`, `id_estabelecimento`, `id_tipoFuncionario`, `login`, `senha`) "
                    + "VALUES (?,?,?,?,?,MD5(?))";

            base.setQuerySql(sql);
            base.setQueryParameter().setString(1, dados.getNome());
            base.setQueryParameter().setString(2, dados.getImg());
            base.setQueryParameter().setInt(3, dados.getEstabelecimento().getId_estabelecimentos());
            base.setQueryParameter().setInt(4, dados.getTipoFuncionario().getId_tipoFuncionario());
            base.setQueryParameter().setString(5, dados.getEmail());
            base.setQueryParameter().setString(6, dados.getSenha());

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

    public boolean ExcluirFuncionario(long dado) throws PiError {

        boolean resposta = false;
        try {
            Database base = new Database();

            String sql = "UPDATE `funcionario` SET `status` = 0"
                    + " WHERE `id_funcionario` = ?";

            base.setQuerySql(sql);
            base.setQueryParameter().setLong(1, dado);
            

            base.preStatement.executeUpdate();
            ResultSet resultado = base.preStatement.getGeneratedKeys();

            if (resultado.first()) {
                resposta = true; 
            }
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

        return resposta;
    }

    public ArrayList<FuncionarioSimplificado> BuscarFuncionarioPorEstabelecimento(int dados) throws PiError {

        
        ArrayList<FuncionarioSimplificado> lista = new ArrayList<>();
        try {
            Database base = new Database();
            String sql = "";

            sql = "SELECT * FROM funcionario AS f "
                    + "INNER JOIN estabelecimento AS e ON e.id_estabelecimento = f.id_estabelecimento "
                    + "INNER JOIN tipo_funcionario AS tf ON tf.id_tipoFuncionario = f.id_tipoFuncionario "
                    + "WHERE (e.id_estabelecimento = ?) AND (status = 1) "
                    + "ORDER BY f.nome";

            base.setQuerySql(sql);
            base.setQueryParameter().setInt(1, dados);
            ResultSet resultado = base.preStatement.executeQuery();

            while (resultado.next()) {
                FuncionarioSimplificado funcionario = new FuncionarioSimplificado();
                funcionario.setId_funcionario(resultado.getInt("f.id_funcionario"));
                funcionario.setNome(resultado.getString("f.nome"));
                funcionario.setTipoFuncionario(resultado.getString("tf.descricao"));
                lista.add(funcionario);

            }
            base.close();
        } catch (PiError error) {
            System.out.println("erroooooo");

            throw error;

        } catch (SQLException e) {
            PiError error = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + e.getMessage(),
                    "FuncionarioDAO:buscarFuncionarios",
                    "lógica de negócio - busca de dados");

            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "FuncionarioDAO:buscarFuncionarios",
                    "lógica de negócio - busca de dados");
            throw error;
        }

        return lista;
    }

    public Funcionario LoginFuncionario(Funcionario dados) throws PiError {

        TipoFuncionario tipoFuncionario = new TipoFuncionario();
        Estabelecimentos estabelecimentos = new Estabelecimentos();
        Funcionario funcionario = new Funcionario();
        ArrayList<Funcionario> lista = new ArrayList<>();
        try {
            Database base = new Database();
            String sql = "";

            sql = "SELECT * FROM funcionario AS f "
                    + "INNER JOIN estabelecimento AS e ON e.id_estabelecimento = f.id_estabelecimento "
                    + "INNER JOIN tipo_funcionario AS t ON t.id_tipoFuncionario = f.id_tipoFuncionario "
                    + "WHERE (f.login = ? ) AND (f.senha = MD5(?)) AND (f.status != 0) ";

            base.setQuerySql(sql);
            base.setQueryParameter().setString(1, dados.getEmail());
            base.setQueryParameter().setString(2, dados.getSenha());

            ResultSet resultado = base.preStatement.executeQuery();

            if (resultado.first()) {
                funcionario.setId_funcionario(resultado.getInt("f.id_funcionario"));
                funcionario.setImg(resultado.getString("f.img"));
                funcionario.setEmail(resultado.getString("f.login"));
                funcionario.setSenha(resultado.getString("f.senha"));
                funcionario.setNome(resultado.getString("f.nome"));
                estabelecimentos.setId_estabelecimentos(resultado.getInt("e.id_estabelecimento"));
                estabelecimentos.setImg(resultado.getString("e.img"));
                estabelecimentos.setNome(resultado.getString("e.nome"));
                tipoFuncionario.setId_tipoFuncionario(resultado.getInt("t.id_tipoFuncionario"));
                tipoFuncionario.setTipo(resultado.getString("descricao"));
                funcionario.setEstabelecimento(estabelecimentos);
                funcionario.setTipoFuncionario(tipoFuncionario);
                
            }

            base.close();
            resultado.close();
        } catch (PiError error) {
            throw error;

        } catch (SQLException e) {
            PiError error = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + e.getMessage(),
                    "FuncionarioDAO:LoginFuncionario",
                    "lógica de negócio - login");
            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "FuncionarioDAO:LoginFuncionario",
                    "lógica de negócio - login");
            throw error;
        }

        return funcionario;
    }

}
