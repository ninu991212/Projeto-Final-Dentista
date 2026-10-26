package modelo;

import java.util.ArrayList;
import java.util.List;

public class Dente {
    private int numero;
    private String nomeDente;
    private boolean existe;
    private List<Tratamento> tratamentos;


    // Construtor: cria o dente como presente e com a lista de tratamentos vazia
    public Dente(int numero, String nomeDente) {
        this.numero = numero;
        this.nomeDente = nomeDente;
        this.existe = true;
        this.tratamentos = new ArrayList<>();
    }

    // Devolve o número do dente
    public int getNumero() {
        return numero;
    }

    // Devolve a designação do dente
    public String getNomeDente() {
        return nomeDente;
    }

    // Devolve a lista de tratamentos do dente
    public List<Tratamento> getTratamentos() {
        return tratamentos;
    }

    //Verifica se o dente existe
    public boolean isExiste() {
        return existe;
    }

    //Metodo para retirar o dente
    public void retirar(){
        this.existe = false;
    }

    // Adiciona um tratamento à lista de um determinado dente
    public void adicionarTratamento(Tratamento t){
        tratamentos.add(t);
    }

}
