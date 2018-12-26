package App.Class.Model;

/**
 *
 * @author Nicollas
 */
public class FuncionarioSimplificado {

    int id_funcionario;
    String nome;
    String tipoFuncionario;

    public FuncionarioSimplificado() {
    }

    public FuncionarioSimplificado(int id_funcionario, String nome, String tipoFuncionario) {
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.tipoFuncionario = tipoFuncionario;
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

    public String getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(String tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }
    
}
