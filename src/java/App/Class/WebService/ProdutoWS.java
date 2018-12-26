
package App.Class.WebService;

import App.Class.Controllers.ProdutoController;
import App.Class.DAO.ProdutoDAO;
import App.Class.Model.Produto;
import App.Class.error.PiError;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

//Classe WebService Produto

//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo

@WebService(serviceName = "ProdutoWS")
public class ProdutoWS {

    
    @WebMethod(operationName = "inserirProduto")
    public boolean inserirProduto(@WebParam(name = "dados") Produto produto) throws PiError 
    {
        return new ProdutoController().inserirProdutos(produto);
    }
    
    @WebMethod(operationName = "BuscarProdutos")
    public ArrayList<Produto> buscarProdutosPorEstabeleccimento(@WebParam(name = "dados") int idEstabelecimento) throws PiError 
    {
        return new ProdutoController().buscarProdutosPorEstabelecimento(idEstabelecimento);
    }
}
