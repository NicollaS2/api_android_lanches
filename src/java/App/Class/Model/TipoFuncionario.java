
package App.Class.Model;

//Classe bean Tipo de cargo do Funcionario

//Autores: 
//         Lucas Gabriel,
//         Nicollas Ramires
//         Braian Maidame
//         João Marcelo

public class TipoFuncionario {
    
    private int id_tipoFuncionario;
    private String descricao;

    public TipoFuncionario() {
    }

    public TipoFuncionario(int id_tipoFuncionario, String descricao) {
        this.id_tipoFuncionario = id_tipoFuncionario;
        this.descricao = descricao;
    }

    public int getId_tipoFuncionario() {
        return id_tipoFuncionario;
    }

    public void setId_tipoFuncionario(int id_tipoFuncionario) {
        this.id_tipoFuncionario = id_tipoFuncionario;
    }

    public String getTipo() {
        return descricao;
    }

    public void setTipo(String descricao) {
        this.descricao = descricao;
    }
    
    
    
    
}
