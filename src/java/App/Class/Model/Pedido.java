/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Class.Model;

//Classe bean Pedido

//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo

public class Pedido {

    private int id_pedido;
    private String data_pedido;
    private Usuario usuario;
    private Estabelecimentos estabelecimentos;

    public Pedido() {
    }

    public Pedido(int id_pedido, String data_pedido, Usuario usuario, Estabelecimentos estabelecimentos) {
        this.id_pedido = id_pedido;
        this.data_pedido = data_pedido;
        this.usuario = usuario;
        this.estabelecimentos = estabelecimentos;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(String data_pedido) {
        this.data_pedido = data_pedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estabelecimentos getEstabelecimentos() {
        return estabelecimentos;
    }

    public void setEstabelecimentos(Estabelecimentos estabelecimentos) {
        this.estabelecimentos = estabelecimentos;
    }
    
    



    
}
