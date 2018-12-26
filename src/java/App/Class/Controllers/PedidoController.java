package App.Class.Controllers;

//Classe Controller Pedido
import App.Class.DAO.PedidoDAO;
import App.Class.Model.Pedido;
import App.Class.Model.PedidoRequest;
import App.Class.Model.PedidoResponse;
import App.Class.Model.Pedido_Produto;
import App.Class.error.PiError;
import java.util.ArrayList;

//Responsável pela lógica de negócio da aplicação
//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo
public class PedidoController {

    public boolean inserirPedido(PedidoRequest dadosPedido) throws PiError {
        try {

            boolean retorno = new PedidoDAO().inserirPedido(dadosPedido);
            return retorno;

        } catch (PiError e) {
            throw e;
        }
    }

    public boolean inserirPedidoNovoTabelaPedido_Produto(Pedido_Produto dadosPedido) throws PiError {
        try {
            boolean retorno = new PedidoDAO().InserirUmNovoPedidoTabelaPedido_Produto(dadosPedido);
            return retorno;
        } catch (PiError e) {
            throw e;
        }
    }

    public ArrayList<Pedido> buscarPedidoPorEstabelecimento(int id) throws PiError {
        try {

            return new PedidoDAO().buscarPedidosPorEstabelecimento(id);

        } catch (PiError e) {
            throw e;
        }
    }
    public ArrayList<PedidoResponse> buscarComandaPorPedido(int id) throws PiError {
        try {

            return new PedidoDAO().buscarComandaPorPedido(id);

        } catch (PiError e) {
            throw e;
        }
    }
    
    public boolean mudarStatus(long dadosPedido) throws PiError {
        try {
            return new PedidoDAO().MudarStatus(dadosPedido);
        } catch (PiError e) {
            throw e;
        }
    }
    
    public ArrayList<String> historico(int id) throws PiError {
        try {
            return new PedidoDAO().produtosMaisVendidos(id);
        } catch (PiError e) {
            throw e;
        }
    }
    
    public String ValorTotal(int id) throws PiError {
        try {
            return new PedidoDAO().ValorTotal(id);
        } catch (PiError e) {
            throw e;
        }
    }

}
