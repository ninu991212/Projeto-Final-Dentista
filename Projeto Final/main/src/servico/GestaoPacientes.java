package servico;

import modelo.Dente;
import modelo.Paciente;
import modelo.TipoTratamento;
import modelo.Tratamento;


import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class GestaoPacientes {

    private List<Paciente> pacientes;
    private List<TipoTratamento> tipoTratamentos;
    private int proximoId;



    //Contrutor: para criar lista vazia de pacientes e de tratamentos
    public GestaoPacientes() {
        this.pacientes = new ArrayList<>();
        this.tipoTratamentos = new ArrayList<>();
        this.proximoId = 1;
        // tipos de tratamento inseridos automaticamente
        inserirTipoTratamento("Destartarização");
        inserirTipoTratamento("Obturação");
        inserirTipoTratamento("Extração");
        inserirTipoTratamento("Tratamento de canal");
        inserirTipoTratamento("Branqueamento");
    }


    //metodo para verificar se o cliente já existe
    public Paciente procurarPaciente(String nif) {
        for (Paciente p : pacientes) {
            if (p.getNif().equals(nif)) {
                return p;
            }
        }
        return null;
    }


    //metodo para inserir cliente
    public boolean inserirPaciente(Paciente p) {
        if (procurarPaciente(p.getNif()) != null) {
            return false;
        }
        pacientes.add(p);
        return true;
    }


    //devolve a lista de pacientes toda
    public List<Paciente> listarPacientes(){
        return pacientes;
    }


    //metodo que procura e elimina o paciente caso exista
    public boolean eliminarPaciente(String nif) {
        Paciente p = procurarPaciente(nif);
        if (p == null) {
            return false;
        }
        pacientes.remove(p);
        return true;
    }


    //metodo para inserir tratametentos e dar um id ao mesmo
    public TipoTratamento inserirTipoTratamento(String descricao) {
        TipoTratamento tipo = new TipoTratamento(proximoId, descricao);
        proximoId++;
        tipoTratamentos.add(tipo);
        return tipo;
    }

    //Lista todos os tratamentos efetuados
    public List<TipoTratamento> listarTipoTratamentos(){
        return tipoTratamentos;
    }

    //metodo para procura o tipo de tratamento pelo id
    public TipoTratamento procurarTipoTratamento(int id) {
        for (TipoTratamento tipo : tipoTratamentos) {
            if (tipo.getIdTratamento() == id) {
                return tipo;
            }
        }
        return null;
    }


    //metodo que adiciona um tratamento a um dente, com a data atual
    public boolean adicionarTratamento(String nif, int numeroDente, int idTipo){
        Paciente p = procurarPaciente(nif);
        if (p == null) {
            return false;
        }
        Dente d = p.getDente(numeroDente);
        if (d == null) {
            return false;
        }
        TipoTratamento tipo = procurarTipoTratamento(idTipo);
        if (tipo == null) {
            return false;
        }
        Tratamento t = new Tratamento(tipo, LocalDate.now());
        d.adicionarTratamento(t);
        return true;
    }

}

