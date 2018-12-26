package App.DataBase;

import App.Class.error.LibError;
import App.Class.error.PiError;
import java.sql.*;

public class Database {

    private Connection conexao;
    private Statement statement;
    public PreparedStatement preStatement;
    private String mensagem;

    //--------------------------------------------------------------------------
    public void open() throws SQLException, PiError {
        try {

            String driver;
            String url;
            String usuario;
            String senha;

            driver = "com.mysql.fabric.jdbc.FabricMySQLDriver";
            url = "jdbc:mysql://localhost:3306/bancoappestabelecimentos";
            usuario = "root";
            senha = "";

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            this.conexao = DriverManager.getConnection(url, usuario, senha);
            this.statement = conexao.createStatement();

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP01,
                    LibError.msgEP01 + e.getMessage(),
                    "DB:Open", "gestão de dados");
            throw error;
        }
    }

    //--------------------------------------------------------------------------
    public void close() throws SQLException, PiError {
        try{
            if (this.statement != null) {
                this.statement.close();
            }
            if (this.conexao != null) {
                this.conexao.close();
            }
        }catch (SQLException error) {
            PiError piError = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + error.getMessage(),
                    "DB:Close", "Gestão de Dados");

            throw piError;
        }

    }

    //--------------------------------------------------------------------------
    public ResultSet query(String sql, int tipo) throws SQLException, PiError {
        ResultSet result;
        result = null;
        try{
            if (this.conexao == null || this.statement == null) {
                open();
            }

            if (tipo == 1) {
                result = this.statement.executeQuery(sql);
            } else {
                statement.executeUpdate(sql);
            }
        } catch (SQLException error) {
            PiError piError = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + error.getMessage(),
                    "DB:ResultSet", "Gestão de Dados");

            throw piError;
        }

        return result;
    }
    //--------------------------------------------------------------------------
    
    public PreparedStatement setQuerySql(String sql) throws SQLException, PiError {
        try{
            if (this.conexao == null || this.statement == null) {
                open();
            }

            this.preStatement = this.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        } catch (SQLException error) {
            PiError piError = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + error.getMessage(),
                    "DB:Close", "Gestão de Dados");

            throw piError;
        }
        return preStatement;

    }

    //--------------------------------------------------------------------------
    public PreparedStatement setQueryParameter() {

        return this.preStatement;

    }

}
