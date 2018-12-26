
package App.Class.Model;

//Classe bean Preço

//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo

public class Preco {
    
    private int id_preco;
    private double valor;

    public Preco() {
    }

    public Preco(int id_preco, double valor) {
        this.id_preco = id_preco;
        this.valor = valor;
    }

    public int getId_preco() {
        return id_preco;
    }

    public void setId_preco(int id_preco) {
        this.id_preco = id_preco;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
    
    
}
