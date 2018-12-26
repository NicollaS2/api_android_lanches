
package App.Class.Model;

//Classe bean Produto

//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo

public class Produto {

    private int id_produto;
    private String descricao;
    private Preco preco;
    private TipoProduto tipoProduto;
    private Estabelecimentos estabelecimentos;
    private String nome;

    public Produto() {
    }

    
    public Produto(int id_produto, String descricao, Preco preco, TipoProduto tipoProduto, Estabelecimentos estabelecimentos, String nome) {
        this.id_produto = id_produto;
        this.descricao = descricao;
        this.preco = preco;
        this.tipoProduto = tipoProduto;
        this.estabelecimentos = estabelecimentos;
        this.nome = nome;
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

    public Preco getPreco() {
        return preco;
    }

    public void setPreco(Preco preco) {
        this.preco = preco;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Estabelecimentos getEstabelecimentos() {
        return estabelecimentos;
    }

    public void setEstabelecimentos(Estabelecimentos estabelecimentos) {
        this.estabelecimentos = estabelecimentos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
     
    
    
    
}
