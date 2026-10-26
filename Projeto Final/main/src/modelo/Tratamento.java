package modelo;

import java.time.LocalDate;

public class Tratamento {

    private TipoTratamento tipo;
    private LocalDate dataTratamento;


    // Construtor: associa um tipo de tratamento a uma dataa
    public Tratamento(TipoTratamento tipo, LocalDate dataTratamento) {
        this.tipo = tipo;
        this.dataTratamento = dataTratamento;
    }

    // Devolve o tipo de tratamento
    public TipoTratamento getTipo() {
        return tipo;
    }

    // Devolve a data em que o tratamento foi feito
    public LocalDate getDataTratamento() {
        return dataTratamento;
    }
}
