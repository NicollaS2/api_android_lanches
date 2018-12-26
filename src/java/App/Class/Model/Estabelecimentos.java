
package App.Class.Model;

//Classe bean Estabelecimentos

//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo

public class Estabelecimentos {
    
    private int id_estabelecimentos;
    private String nome;
    private String img;

    public Estabelecimentos() {
    }

    public Estabelecimentos(int id_estabelecimentos, String nome, String img) {
        this.id_estabelecimentos = id_estabelecimentos;
        this.nome = nome;
        this.img = img;
    }

    public int getId_estabelecimentos() {
        return id_estabelecimentos;
    }

    public void setId_estabelecimentos(int id_estabelecimentos) {
        this.id_estabelecimentos = id_estabelecimentos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
    
    
    
    
}
