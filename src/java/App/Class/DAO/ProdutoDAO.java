package App.Class.DAO;

//Classe DAO Produtos existentes nos Estabelecimentos
import App.Class.Model.Estabelecimentos;
import App.Class.Model.Produto;
import App.Class.Model.Preco;
import App.Class.Model.ProdutoResponse;
import App.Class.Model.TipoProduto;
import App.Class.error.LibError;
import App.Class.error.PiError;
import App.DataBase.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Responsável pela conexão com o banco de dados
//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo
public class ProdutoDAO {

    public boolean inserirProdutos(Produto dados) throws PiError {

        Estabelecimentos estabelecimentos = new Estabelecimentos();
        Preco preco = new Preco();
        TipoProduto tipoProduto = new TipoProduto();
        boolean retorno = false;
        int count = 0;
        try {
            int idProduto = 0;
            int idPreco = 0;
            
            Database base2 = new Database();

            String sql_preco = "INSERT INTO `preco` (`valor`) VALUES (?)";

            base2.setQuerySql(sql_preco);
            base2.setQueryParameter().setDouble(1, dados.getPreco().getValor());
            base2.preStatement.executeUpdate();
            ResultSet resultado2 = base2.preStatement.getGeneratedKeys();
            
            if (resultado2.first()) {
                count++;
                idPreco = resultado2.getInt(1);
            }
            
            
            
            Database base = new Database();

            String sql_produto = "INSERT INTO `produto` (`descricao`, `id_preco`, `id_tipo`, `id_estabelecimento`, `nome`) VALUES ( ?, ?, ?, ?,?)";

            base.setQuerySql(sql_produto);
            base.setQueryParameter().setString(1, dados.getDescricao());
            base.setQueryParameter().setInt(2, idPreco);
            base.setQueryParameter().setInt(3, dados.getTipoProduto().getId_tipoProduto());
            base.setQueryParameter().setInt(4, dados.getEstabelecimentos().getId_estabelecimentos());
            base.setQueryParameter().setString(5, dados.getNome());


            base.preStatement.executeUpdate();
            ResultSet resultado = base.preStatement.getGeneratedKeys();
            if (resultado.first()) {
                count++;
                idProduto = resultado.getInt(1);
            }
            

            if (count == 2) {
                retorno = true;
            }

            base.close();
            base2.close();
            resultado.close();
            
        } catch (PiError error) {
            throw error;

        } catch (SQLException e) {
            PiError error = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + e.getMessage(),
                    "ProdutoDAO:inserirProdutos",
                    "lógica de negócio - inserção de dados");
            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "ProdutoDAO:inserir Produtos",
                    "lógica de negócio - inserção de dados");
            throw error;
        }

        return retorno;
    }

    public ArrayList<Produto> BuscarProdutosPorEstabelecimento(int idEstabelecimento) throws PiError {

       
        
        Preco preco = new Preco();
        ArrayList<Produto> lista = new ArrayList<>();
        try {
            Database base = new Database();
            String sql = "";

            sql = "SELECT * FROM produto AS p "
                    + "INNER JOIN estabelecimento AS e ON e.id_estabelecimento = p.id_estabelecimento "
                    + "INNER JOIN preco AS pc ON pc.id_preco = p.id_preco "
                    + "INNER JOIN tipo_produto AS tp ON tp.id_tipo = p.id_tipo "
                    + "WHERE (e.id_estabelecimento = ?) AND (p.status = 1)"
                    + "ORDER BY pc.valor";

            base.setQuerySql(sql);
            base.setQueryParameter().setInt(1, idEstabelecimento);

            ResultSet resultado = base.preStatement.executeQuery();

            while (resultado.next()) {
                Produto produto = new Produto();
                TipoProduto tipoProduto = new TipoProduto();
                Estabelecimentos estabelecimentos = new Estabelecimentos();
                produto.setId_produto(resultado.getInt("p.id_produto"));
                produto.setDescricao(resultado.getString("p.descricao"));
                produto.setNome(resultado.getString("p.nome"));
                estabelecimentos.setId_estabelecimentos(resultado.getInt("e.id_estabelecimento"));
                estabelecimentos.setImg(resultado.getString("e.img"));
                estabelecimentos.setNome(resultado.getString("e.nome"));
                preco.setId_preco(resultado.getInt("pc.id_preco"));
                preco.setValor(resultado.getDouble("pc.id_preco"));
                tipoProduto.setId_tipoProduto(resultado.getInt("tp.id_tipo"));
                tipoProduto.setDescricao(resultado.getString("tp.descricao"));

                produto.setEstabelecimentos(estabelecimentos);
                produto.setPreco(preco);
                produto.setTipoProduto(tipoProduto);

                lista.add(produto);

            }
            base.close();
            resultado.close();
        } catch (PiError error) {
            throw error;

        } catch (SQLException e) {
            PiError error = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + e.getMessage(),
                    "ProdutoDAO:buscarProdutos",
                    "lógica de negócio - busca de dados");
            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "ProdutoDAO:buscarProdutos",
                    "lógica de negócio - busca de dados");
            throw error;
        }

        return lista;
    }
    public ArrayList<ProdutoResponse> BuscarListaDeProdutosSimplificada(int idEstabelecimento) throws PiError {

       
        
        ArrayList<ProdutoResponse> lista = new ArrayList<>();
        try {
            Database base = new Database();
            String sql = "";

            sql = "SELECT * FROM produto AS p "
                    + "INNER JOIN estabelecimento AS e ON e.id_estabelecimento = p.id_estabelecimento "
                    + "INNER JOIN preco AS pc ON pc.id_preco = p.id_preco "
                    + "INNER JOIN tipo_produto AS tp ON tp.id_tipo = p.id_tipo "
                    + "WHERE (e.id_estabelecimento = ?) AND (p.status = 1) "
                    + "ORDER BY p.id_tipo DESC";

            base.setQuerySql(sql);
            base.setQueryParameter().setInt(1, idEstabelecimento);
            ResultSet resultado = base.preStatement.executeQuery();

            while (resultado.next()) {
                ProdutoResponse produto = new ProdutoResponse();
                produto.setId_produto(resultado.getInt("p.id_produto"));
                produto.setDescricao(resultado.getString("p.descricao"));
                produto.setNome(resultado.getString("p.nome"));
                produto.setEstabelecimentos(resultado.getInt("e.id_estabelecimento"));
                produto.setPreco(resultado.getDouble("pc.valor"));
                produto.setTipoProduto(resultado.getInt("tp.id_tipo"));

                lista.add(produto);

            }
            base.close();
            resultado.close();
        } catch (PiError error) {
            throw error;

        } catch (SQLException e) {
            PiError error = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + e.getMessage(),
                    "ProdutoDAO:buscarProdutos",
                    "lógica de negócio - busca de dados");
            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "ProdutoDAO:buscarProdutos",
                    "lógica de negócio - busca de dados");
            throw error;
        }

        return lista;
    }
    
    
    public ArrayList<ProdutoResponse> BuscarProdutoId(long id) throws PiError {

       
        
        ArrayList<ProdutoResponse> lista = new ArrayList<>();
        try {
            Database base = new Database();
            String sql = "";

            sql = "SELECT * FROM produto AS p "
                    + "INNER JOIN estabelecimento AS e ON e.id_estabelecimento = p.id_estabelecimento "
                    + "INNER JOIN preco AS pc ON pc.id_preco = p.id_preco "
                    + "INNER JOIN tipo_produto AS tp ON tp.id_tipo = p.id_tipo "
                    + "WHERE (p.id_produto = ?) AND (p.status = 1) ";

            base.setQuerySql(sql);
            base.setQueryParameter().setLong(1, id);
            ResultSet resultado = base.preStatement.executeQuery();

            while (resultado.next()) {
                ProdutoResponse produto = new ProdutoResponse();
                produto.setId_produto(resultado.getInt("p.id_produto"));
                produto.setDescricao(resultado.getString("p.descricao"));
                produto.setNome(resultado.getString("p.nome"));
                produto.setEstabelecimentos(resultado.getInt("e.id_estabelecimento"));
                produto.setPreco(resultado.getDouble("pc.valor"));
                produto.setTipoProduto(resultado.getInt("tp.id_tipo"));

                lista.add(produto);

            }
            base.close();
            resultado.close();
        } catch (PiError error) {
            throw error;

        } catch (SQLException e) {
            PiError error = new PiError(LibError.codeEP04,
                    LibError.msgEP04 + e.getMessage(),
                    "ProdutoDAO:buscarProdutos",
                    "lógica de negócio - busca de dados");
            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "ProdutoDAO:buscarProdutos",
                    "lógica de negócio - busca de dados");
            throw error;
        }

        return lista;
    }

    public boolean ExcluirProduto(long dado) throws PiError {

        boolean resposta = false;
        try {
            Database base = new Database();

            String sql = "UPDATE `produto` SET `status` = 0"
                    + " WHERE `id_produto` = ?";

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
                    "ProdutoDAO:inserirFuncionario",
                    "lógica de negócio - inserção de dados");
            throw error;

        } catch (Exception e) {
            PiError error = new PiError(LibError.codeEP999,
                    LibError.msgEP999 + e.getMessage(),
                    "ProdutoDAO:inserirFuncionario",
                    "lógica de negócio - inserção de dados");
            throw error;
        }

        return resposta;
    }
    
}
