/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Class.DAO;

import App.Class.Model.Estabelecimentos;
import App.Class.Model.Funcionario;
import App.Class.Model.Permissoes;
import App.Class.Model.Permissoes_TipoFuncionario;
import App.Class.Model.TipoFuncionario;
import App.Class.error.LibError;
import App.Class.error.PiError;
import App.DataBase.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class PermissoesDAO {

    public ArrayList<Permissoes_TipoFuncionario> BuscarPermissoesDoFuncionario(int id_funcionario) throws PiError {

        ArrayList<Permissoes_TipoFuncionario> lista = new ArrayList<>();
        try {
            Database base = new Database();
            String sql = "";

            sql = "SELECT p.descricao FROM tipofuncionario_tem_permissoes AS ttp "
                    + "INNER JOIN  tipo_funcionario AS tp ON tp.id_tipoFuncionario = ttp.id_tipoFuncionario "
                    + "INNER JOIN funcionario as f ON f.id_tipoFuncionario = tp.id_tipoFuncionario "
                    + "INNER JOIN permissoes AS p ON p.id_permissoes = ttp.id_permissoes "
                    + "WHERE (f.id_funcionario = ?)  ";

            base.setQuerySql(sql);
            base.setQueryParameter().setInt(1, id_funcionario);

            ResultSet resultado = base.preStatement.executeQuery();

            while (resultado.next()) {
                Permissoes permissoes = new Permissoes();
                Permissoes_TipoFuncionario permissoes_TipoFuncionario = new Permissoes_TipoFuncionario();

                permissoes.setDescricao(resultado.getString("p.descricao"));
                permissoes_TipoFuncionario.setPermissoes(permissoes);

                lista.add(permissoes_TipoFuncionario);

            }
            base.close();
            
        } catch (PiError error) {
            throw error;

        } catch (SQLException e) {
            PiError error = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + e.getMessage(),
                    "PermissoesDAO:buscarPermissoes",
                    "lógica de negócio - busca de dados");
            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "PermissoesDAO:buscarPermissoes",
                    "lógica de negócio - busca de dados");
            throw error;
        } 

        return lista;
    }

}
