
package App.Class.Model;

//Classe bean Pedido Produto

//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo

public class Pedido_Produto {
    
    private int quantidade;
    private String observacoes;
    private Pedido pedido;
    private Produto produto;
    private Funcionario funcionario;
    private Status status;

    public Pedido_Produto() {
    }

    public Pedido_Produto(int quantidade, String observações, Pedido pedido, Produto produto, Funcionario funcionario, Status status) {
        this.quantidade = quantidade;
        this.observacoes = observações;
        this.pedido = pedido;
        this.produto = produto;
        this.funcionario = funcionario;
        this.status = status;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    
    
    
    
}
