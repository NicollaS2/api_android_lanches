package App.Class.Model;

//Classe bean Status

//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo

public class Status {
    private int id_status;
    private String descricao;

    public Status() {
    }

    public Status(int id_status, String descricao) {
        this.id_status = id_status;
        this.descricao = descricao;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
