package App.Class.Controllers;

//Classe Controller Produtos por Estabelecimento
import App.Class.DAO.ProdutoDAO;
import App.Class.Model.Produto;
import App.Class.Model.ProdutoResponse;
import App.Class.error.PiError;
import java.util.ArrayList;

//Responsável pela lógica de negócio da aplicação
//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo
public class ProdutoController {

    public ArrayList<Produto> buscarProdutosPorEstabelecimento(int iEstabelecimento) throws PiError {
        try {
            return new ProdutoDAO().BuscarProdutosPorEstabelecimento(iEstabelecimento);

        } catch (PiError error) {
            throw error;
        }
    }
    public ArrayList<ProdutoResponse> buscarListaDeProdutosSimplificada(int iEstabelecimento) throws PiError {
        try {
            return new ProdutoDAO().BuscarListaDeProdutosSimplificada(iEstabelecimento);

        } catch (PiError error) {
            throw error;
        }
    }
    
    public ArrayList<ProdutoResponse> buscarProdutoId(long id) throws PiError {
        try {
            return new ProdutoDAO().BuscarProdutoId(id);

        } catch (PiError error) {
            throw error;
        }
    }

    public boolean inserirProdutos(Produto produtos) throws PiError {
        try {
            return new ProdutoDAO().inserirProdutos(produtos);
        } catch (PiError error) {
            throw error;
        }
    }
    
    public boolean excluirProdutos(long produto) throws PiError {
        try {

            return new ProdutoDAO().ExcluirProduto(produto);

        } catch (PiError e) {
            throw e;
        }
    }

}
