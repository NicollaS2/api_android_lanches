
package App.Class.Model;

public class ProdutoResponse {
    
    
    private int id_produto;
    private String descricao;
    private String nome;
    private double preco;
    private int tipoProduto;
    private int estabelecimentos;

    public ProdutoResponse() {
    }

    public ProdutoResponse(int id_produto, String descricao, String nome, double preco, int tipoProduto, int estabelecimentos) {
        this.id_produto = id_produto;
        this.descricao = descricao;
        this.nome = nome;
        this.preco = preco;
        this.tipoProduto = tipoProduto;
        this.estabelecimentos = estabelecimentos;
    }
    
    
    

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(int tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public int getEstabelecimentos() {
        return estabelecimentos;
    }

    public void setEstabelecimentos(int estabelecimentos) {
        this.estabelecimentos = estabelecimentos;
    }
    
    
    
}
