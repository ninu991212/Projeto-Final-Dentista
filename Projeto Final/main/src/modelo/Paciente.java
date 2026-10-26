package modelo;

import java.util.ArrayList;
import java.util.List;

public class Paciente {

    private String nome;
    private String morada;
    private String telemovel;
    private String nif;
    private int idade;
    private List<Dente> dentes;

    // Construtor: guarda os dados e cria os 32 dentes ao cliente
    public Paciente(String nome, String morada, String telemovel, int idade, String nif) {
        this.nome = nome;
        this.morada = morada;
        this.telemovel = telemovel;
        this.idade = idade;
        this.nif = nif;
        this.dentes = new ArrayList<>();
        criarDentes();
    }


    //array com o nome dos dentes
    private static final String[] nomes_dentes = {
            "Incisivo central superior direito", "Incisivo lateral superior direito",
            "Canino superior direito", "1º pré-molar superior direito",
            "2º pré-molar superior direito", "1º molar superior direito",
            "2º molar superior direito", "3º molar superior direito",
            "Incisivo central superior esquerdo", "Incisivo lateral superior esquerdo",
            "Canino superior esquerdo", "1º pré-molar superior esquerdo",
            "2º pré-molar superior esquerdo", "1º molar superior esquerdo",
            "2º molar superior esquerdo", "3º molar superior esquerdo",
            "Incisivo central inferior esquerdo", "Incisivo lateral inferior esquerdo",
            "Canino inferior esquerdo", "1º pré-molar inferior esquerdo",
            "2º pré-molar inferior esquerdo", "1º molar inferior esquerdo",
            "2º molar inferior esquerdo", "3º molar inferior esquerdo",
            "Incisivo central inferior direito", "Incisivo lateral inferior direito",
            "Canino inferior direito", "1º pré-molar inferior direito",
            "2º pré-molar inferior direito", "1º molar inferior direito",
            "2º molar inferior direito", "3º molar inferior direito"
    };

    // Cria os 32 dentes, numerados de 1 a 32
    private void criarDentes() {
        for (int i = 0; i < nomes_dentes.length; i++) {
            Dente d = new Dente(i + 1, nomes_dentes[i]);
            dentes.add(d);
        }
    }

    //devolve o nome do paciente
    public String getNome() {
        return nome;
    }

    //devolve a morada do paciente
    public String getMorada() {
        return morada;
    }

    //devolve o nr telemovel do paciente
    public String getTelemovel() {
        return telemovel;
    }

    //devolve o NIF do paciente
    public String getNif() {
        return nif;
    }

    //devolve a idade do paciente
    public int getIdade() {
        return idade;
    }

    //devolve a lista de dentes do paciente
    public List<Dente> getDentes() {
        return dentes;
    }

    //setter pois estes dados podem querer ser alterados pelo paciente
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setMorada(String morada) {
        this.morada = morada;
    }
    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }
    public void setNif(String nif) {
        this.nif = nif;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    //obter o nome do dente pela posição dele
    public Dente getDente(int numero){
        if(numero <1 || numero > nomes_dentes.length){
            return null;
        }
        return dentes.get(numero-1);
    }

    //obter a lista de dentes que o paciente não tem
    public List<Dente> getDentesRetirados(){
        List<Dente> retirados = new ArrayList<>();
        for(int i = 0; i < dentes.size(); i++){
            if(!dentes.get(i).isExiste()){
                retirados.add(dentes.get(i));
            }
        }
        return retirados;
    }

}
