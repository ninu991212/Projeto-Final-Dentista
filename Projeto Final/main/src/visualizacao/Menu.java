package visualizacao;

import modelo.Paciente;
import servico.GestaoPacientes;
import modelo.TipoTratamento;
import modelo.Tratamento;
import modelo.Dente;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private GestaoPacientes gestao;
    private Scanner sc;

    // Construtor: cria a gestão e o scanner para leitura
    public Menu() {
        this.gestao = new GestaoPacientes();
        this.sc = new Scanner(System.in);
    }

    //Mostra as opções do menu
    private void mostrarMenu() {
        System.out.println("=== Gestao de Pacientes ===");
        System.out.println("1 - Inserir paciente");
        System.out.println("2 - Listar pacientes");
        System.out.println("3 - Alterar paciente");
        System.out.println("4 - Eliminar paciente");
        System.out.println("5 - Inserir tipo de tratamento");
        System.out.println("6 - Listar tipos de tratamento");
        System.out.println("7 - Gerir dente (retirar / adicionar tratamento)");
        System.out.println("8 - Ver tratamentos efetuados de um paciente");
        System.out.println("0 - Sair");
    }

    //Metodo para ler a opção que o user intruduz e acede ao metodo da opção
    public void iniciar() {
        int opcao;
        do {
            mostrarMenu();
            opcao = Integer.parseInt(sc.nextLine());
            switch (opcao) {
                case 1 -> inserirPaciente();
                case 2 -> listarPacientes();
                case 3 -> alterarPaciente();
                case 4 -> eliminarPaciente();
                case 5 -> inserirTipoTratamento();
                case 6 -> listarTiposTratamento();
                case 7 -> gerirDente();
                case 8 -> verTratamentosEfetuados();
                case 0 -> System.out.println("A sair...");
                default -> System.out.println("Opcão invalida.");
            }
            System.out.println();
        } while (opcao != 0);
        sc.close();
    }

    //caso 1: pede os dados e cria um novo paciente
    private void inserirPaciente() {

        System.out.print("Nome: ");
        String nome = sc.nextLine();
        while(nome.equals("")) {
            System.out.print("O Nome é obrigatorio preencher.");
            System.out.print("Nome: ");
            nome = sc.nextLine();
        }

        System.out.print("Idade: ");
        int idade = -1;
        boolean idadeValida = false;
        while (!idadeValida) {
            System.out.print("Idade: ");
            try {
                idade = Integer.parseInt(sc.nextLine());
                if (idade >= 0) {
                    idadeValida = true;          // número válido e não negativo
                } else {
                    System.out.println("A idade não pode ser negativa.");
                }
            } catch (NumberFormatException erro) {
                System.out.println("A idade tem de ser um número.");
            }
        }


        System.out.print("Telemovel: ");
        String telemovel = sc.nextLine();
        while(telemovel.equals("")) {
            System.out.print("O telemovel é obrigatorio preencher.");
            System.out.print("Telemovel: ");
            telemovel = sc.nextLine();
        }

        System.out.print("NIF: ");
        String nif = sc.nextLine();
        while (nif.equals("")) {
            System.out.println("O NIF é obrigatorio preencher.");
            System.out.print("NIF: ");
            nif = sc.nextLine();
        }

        System.out.print("Morada: ");
        String morada = sc.nextLine();
        while (morada.equals("")) {
            System.out.println("A morada é obrigatorio preencher.");
            System.out.print("Morada: ");
            morada = sc.nextLine();
        }


        Paciente p = new Paciente(nome, morada, telemovel, idade, nif);
        //verifica primeiro se o cliente ja existe se não cria o paciente
        if (gestao.inserirPaciente(p)) {
            System.out.println("Paciente inserido"); // adiciona sempre com os 32 dentes
        } else {
            System.out.println("Ja existe um paciente com esse NIF.");
        }
    }

    //Caso 2: lista todos os pacientes registados
    private void listarPacientes() {
        System.out.println("Lista de Pacientes: ");
        List<Paciente> pacientes = gestao.listarPacientes();
        if (pacientes.isEmpty()) {
            System.out.println("Sem pacientes registados.");
            return;
        }
        for (Paciente p : pacientes) {
            System.out.println(p.getNome() + " | " + p.getIdade() + " anos | NIF: " + p.getNif() + " | tel: " + p.getTelemovel() + " | Morada: " + p.getMorada());
        }
    }

    //Caso 3: altera um campo à escolha de um paciente existente
    private void alterarPaciente() {
        System.out.print("NIF do paciente a alterar: ");
        String nif = sc.nextLine();

        Paciente p = gestao.procurarPaciente(nif);
        if (p == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.println("O que pretende alterar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Morada");
        System.out.println("3 - Telemovel");
        System.out.println("4 - NIF");
        System.out.println("5 - Idade");
        int op = Integer.parseInt(sc.nextLine());
        switch (op) {
            case 1 -> {
                System.out.print("Novo nome: ");
                p.setNome(sc.nextLine());
            }
            case 2 -> {
                System.out.print("Nova morada: ");
                p.setMorada(sc.nextLine());
            }
            case 3 -> {
                System.out.print("Novo telemovel: ");
                p.setTelemovel(sc.nextLine());
            }
            case 4 -> {
                System.out.print("Novo NIF: ");
                p.setNif(sc.nextLine());
            }
            case 5 -> {
                System.out.print("Nova idade: ");
                p.setIdade(Integer.parseInt(sc.nextLine()));
            }
            default -> System.out.println("Opcão invalida.");
        }
        System.out.println("Alteração concluida.");
    }

    //Caso 4: elimina um paciente pelo NIF
    private void eliminarPaciente() {
        System.out.print("Qual o NIF do paciente? ");
        String nif = sc.nextLine();

        if (gestao.eliminarPaciente(nif)) {
            System.out.println("Paciente eliminado com sucesso.");
        }else{
            System.out.println("Não existe um paciente com esse NIF.");
        }
    }

    //Caso 5: cria um novo tipo de tratamento
    private void inserirTipoTratamento() {
        System.out.print("Qual o tratamento a adicionar? ");
        String descricao = sc.nextLine();

        TipoTratamento tt = gestao.inserirTipoTratamento(descricao);
        System.out.println("Tratamento adicionado com o id " + tt.getIdTratamento() + ": " + tt.getDescricao());
    }

    //Caso 6: lista os tipos de tratamento criados
    private void listarTiposTratamento() {
        System.out.println("Tipos de tratamento: ");
        List<TipoTratamento> tipos = gestao.listarTipoTratamentos();
        if (tipos.isEmpty()) {
            System.out.println("Sem tipos de tratamento registados.");
            return;
        }
        for (TipoTratamento tt : tipos) {
            System.out.println(tt.getIdTratamento() + " - " + tt.getDescricao());
        }
    }

    //Caso 7: localiza um dente e permite retirá-lo ou adicionar-lhe um tratamento
    private void gerirDente() {
        System.out.print("NIF do paciente: ");
        String nif = sc.nextLine();

        Paciente p = gestao.procurarPaciente(nif);
        if (p == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.print("Numero do dente (1-32): ");
        int numeroDente = Integer.parseInt(sc.nextLine());
        Dente d = p.getDente(numeroDente);
        if (d == null) {
            System.out.println("Numero de dente invalido.");
            return;
        }

        System.out.println("O que pretende fazer ao dente " + d.getNomeDente() + "?");
        System.out.println("1 - Retirar dente");
        System.out.println("2 - Adicionar tratamento");
        int op = Integer.parseInt(sc.nextLine());

        switch (op) {
            case 1 -> {
                d.retirar();
                System.out.println("Dente retirado.");
            }
            case 2 -> {
                if (!d.isExiste()) {
                    System.out.println("Esse dente ja foi retirado, não pode ser tratado.");
                } else {
                    listarTiposTratamento();
                    System.out.print("Id do tipo de tratamento: ");
                    int idTipo = Integer.parseInt(sc.nextLine());
                    if (gestao.adicionarTratamento(nif, numeroDente, idTipo)) {
                        System.out.println("Tratamento adicionado.");
                    } else {
                        System.out.println("Não foi possivel adicionar o tratamento.");
                    }
                }
            }
            default -> System.out.println("Opcão invalida.");
        }
    }

    //Caso 8: mostra os dentes retirados e os dentes com tratamentos de um paciente
    private void verTratamentosEfetuados() {
        System.out.print("NIF do paciente: ");
        String nif = sc.nextLine();

        Paciente p = gestao.procurarPaciente(nif);
        if (p == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.println("Tratamentos efetuados a " + p.getNome() + ":");
        for (Dente d : p.getDentes()) {
            // Vai mostrar apenas se houver dentes removidos ou tratamentos
            if (!d.isExiste() || !d.getTratamentos().isEmpty()) {
                String estado;
                if (d.isExiste()) {
                    estado = "Existe";
                } else {
                    estado = "Retirado";
                }
                System.out.println(d.getNumero() + " - " + d.getNomeDente() + " [" + estado + "]");

                for (Tratamento t : d.getTratamentos()) {
                    System.out.println("      tratamento: " + t.getTipo().getDescricao() + " (" + t.getDataTratamento() + ")");
                }
            }
        }
    }
}