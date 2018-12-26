package App.Class.WebService;

import App.Class.Controllers.PedidoController;
import App.Class.Model.Pedido_Produto;
import App.Class.Model.Usuario;
import App.Class.error.PiError;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "PedidoWS")
public class PedidoWS {

////    @WebMethod(operationName = "inserirPedido")
////    public boolean inserirPedido(@WebParam(name = "dadosPedido") Pedido_Produto pedido) throws PiError {
////        return new PedidoController().inserirPedido(Ped);
////    }
    
    @WebMethod(operationName = "inserirUmNovoProdutoNoPedido")
    public boolean inserirProdutoNovoNoPedido(@WebParam(name = "dados") Pedido_Produto pedido) throws PiError {
        return new PedidoController().inserirPedidoNovoTabelaPedido_Produto(pedido);
    }
    
    
    
}
