package App.Class.DAO;

//Classe DAO Usuario
import App.Class.Model.Estabelecimentos;
import App.Class.Model.Funcionario;
import App.Class.Model.Pedido;
import App.Class.Model.Pedido_Produto;
import App.Class.Model.Usuario;
import App.Class.Model.Produto;
import App.Class.Model.TipoFuncionario;
import App.Class.error.LibError;
import App.Class.error.PiError;
import App.DataBase.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;

//Responsável pela conexão com o banco de dados
//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo
public class UsuarioDAO {    
    public int inserirUsuario(Usuario dados) throws PiError {

        Usuario usuario = new Usuario();
        Pedido pedido = new Pedido();
        Produto produto = new Produto();
        int id_usuario = 0;
        try {
            Database base = new Database();

            String sql = "INSERT INTO usuario(nome,login,senha,telefone) VALUES(?,?,MD5(?),?)";
            
            //colocando parametros para usuario
            //cadastra telefone e nome do usuario
            //email = telefone do usuario senha inicial = 123
            usuario.setEmail(dados.getTelefone());
            usuario.setSenha("123");
            
            base.setQuerySql(sql);
            base.setQueryParameter().setString(1, dados.getNome());
            base.setQueryParameter().setString(2, dados.getEmail());
            base.setQueryParameter().setString(3, dados.getSenha());
            base.setQueryParameter().setString(4, dados.    getTelefone());
            
            base.preStatement.executeUpdate();
            ResultSet resultado = base.preStatement.getGeneratedKeys();
            
            boolean verifica = false;
            if (resultado.first()) {
                id_usuario = resultado.getInt(1);
                verifica = true;

            }
            base.close();
            
        } catch (PiError error) {
            throw error;

        } catch (SQLException e) {
            PiError error = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + e.getMessage(),
                    "UsuarioDAO:inserirUsuario",
                    "lógica de negócio - inserção de dados");
            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "UsuarioDAO:inserirUsuario",
                    "lógica de negócio - inserção de dados");
            throw error;
        }

        return id_usuario;
    }
    
    
    
    public Usuario LoginUsuario(Usuario dados) throws PiError {

        Estabelecimentos estabelecimentos = new Estabelecimentos();
        ArrayList<Usuario> lista = new ArrayList<>();
        Usuario usuario = new Usuario();
        try {
            Database base = new Database();
            String sql = "";

            sql = "SELECT * FROM usuario AS u "
                    + "WHERE (u.login = ?) AND (u.senha = MD5(?))  ";

            base.setQuerySql(sql);
            base.setQueryParameter().setString(1, dados.getEmail());
            base.setQueryParameter().setString(2, dados.getSenha());


            ResultSet resultado = base.preStatement.executeQuery();

            if (resultado.first()) {
                usuario.setId_usuario(resultado.getInt("u.id_usuario"));
                usuario.setEmail(resultado.getString("u.login"));
                usuario.setSenha(resultado.getString("u.senha"));
                usuario.setNome(resultado.getString("u.nome"));
            }
            
            base.close();
            resultado.close();
            
        } catch (PiError error) {
            throw error;

        } catch (SQLException e) {
            PiError error = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + e.getMessage(),
                    "UsuarioDAO:loginUsuario",
                    "lógica de negócio - login");
            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "UsuarioDAO:loginUsuario",
                    "lógica de negócio - login");
            throw error;
        }

        return usuario;
    }

    
}
