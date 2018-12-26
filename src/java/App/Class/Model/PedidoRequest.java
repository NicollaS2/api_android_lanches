
package App.Class.Model;

import java.util.ArrayList;


public class PedidoRequest {
    
    
    private ArrayList<Pedido_Produto> listaProdutos;
    private Pedido pedido;

    public PedidoRequest() {
    }

    public PedidoRequest(ArrayList<Pedido_Produto> listaProdutos, Pedido pedido) {
        this.listaProdutos = listaProdutos;
        this.pedido = pedido;
    }

    public ArrayList<Pedido_Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ArrayList<Pedido_Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    
    
}
