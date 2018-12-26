
package App.Class.Model;

//Classe bean Tipo do Produto

//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo

public class TipoProduto {
    
    private int id_tipoProduto;
    private String descricao;

    public TipoProduto() {
    }

    public TipoProduto(int id_tipoProduto, String descricao) {
        this.id_tipoProduto = id_tipoProduto;
        this.descricao = descricao;
    }

    public int getId_tipoProduto() {
        return id_tipoProduto;
    }

    public void setId_tipoProduto(int id_tipoProduto) {
        this.id_tipoProduto = id_tipoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
