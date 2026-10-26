package modelo;

public class TipoTratamento {

    private int idTratamento;
    private String descricao;

//Contrutor que recebe os valore idTratamento e descrição
    public TipoTratamento(int idTratamento, String descricao) {
        this.idTratamento = idTratamento;
        this.descricao = descricao;
    }

    // Devolve o id do tipo de tratamento
    public int getIdTratamento() {
        return idTratamento;
    }

    // Devolve a descrição do tipo de tratamento
    public String getDescricao() {
        return descricao;
    }

    // Altera a descrição
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
