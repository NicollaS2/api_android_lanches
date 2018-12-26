
package App.Class.Model;

//Classe bean Funcionario

//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo

public class Funcionario {
    
    private int id_funcionario;
    private String nome;
    private String img;
    private String email;
    private String senha;
    private Estabelecimentos estabelecimento;
    private TipoFuncionario tipoFuncionario;

    public Funcionario() {
    }

    public Funcionario(int id_funcionario, String nome, String img, Estabelecimentos estabelecimento, TipoFuncionario tipoFuncionario, String email, String senha) {
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.img = img;
        this.estabelecimento = estabelecimento;
        this.tipoFuncionario = tipoFuncionario;
        this.email = email;
        this.senha = senha;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Estabelecimentos getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimentos estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }
    
    
    
    
}
