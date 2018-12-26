
package App.Class.Model;

//Classe bean historico de Pedidos

//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo

public class Historico {
    
    private int id_historico;
    private Usuario usuario;
    private Pedido pedido;

    public Historico() {
    }

    public Historico(int id_historico, Usuario usuario, Pedido pedido) {
        this.id_historico = id_historico;
        this.usuario = usuario;
        this.pedido = pedido;
    }

    public int getId_historico() {
        return id_historico;
    }

    public void setId_historico(int id_historico) {
        this.id_historico = id_historico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    
    
    
}
