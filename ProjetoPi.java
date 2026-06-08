import java.util.Scanner;

public class ProjetoPi {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String[][] clientes = new String[0][8];
        String[][] contatos = new String[0][5];

        menuPrincipal(clientes, contatos);
    }

    public static void menuPrincipal(String[][] clientes, String[][] contatos) {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Clientes");
            System.out.println("2 - Contatos");
            System.out.println("3 - Relatórios");
            System.out.println("0 - Sair");

            System.out.print("Escolha: ");

            opcao = Integer.parseInt(sc.nextLine());

            if (opcao == 1) {

                clientes = menuClientes(clientes, contatos, sc);

            } else if (opcao == 2) {

                contatos = menuContatos(clientes, contatos);

            } else if (opcao == 3) {

                exibirRelatorios(clientes, contatos);

            } else if (opcao != 0) {

                System.out.println("Opção inválida");
            }
        }
    }

    private static String[][] menuClientes(String[][] clientes, String[][] contatos, Scanner scanner) {

        String opcao = "";

        while (!opcao.equals("0")) {

            System.out.println("\n--- CLIENTES ---");
            System.out.println("1 - Incluir");
            System.out.println("2 - Listar");
            System.out.println("3 - Consultar");
            System.out.println("4 - Alterar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Voltar");

            System.out.print("Opção: ");
            opcao = scanner.nextLine();

            if (opcao.equals("1")) {

                clientes = incluirCliente(clientes, scanner);

            } else if (opcao.equals("2")) {

                listarClientesTabela(clientes);

            } else if (opcao.equals("3")) {

                consultarClientePorCodigo(clientes, scanner);

            } else if (opcao.equals("4")) {

                clientes = alterarCliente(clientes, scanner);

            } else if (opcao.equals("5")) {

                clientes = apagarCliente(clientes, contatos, scanner);

            } else if (!opcao.equals("0")) {

                System.out.println("Opção inválida");
            }
        }

        return clientes;
    }

    private static String[][] menuContatos(String[][] clientes, String[][] contatos) {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n--- CONTATOS ---");
            System.out.println("1 - Incluir");
            System.out.println("2 - Listar");
            System.out.println("3 - Contatos de um cliente");
            System.out.println("4 - Alterar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Voltar");

            System.out.print("Opção: ");

            opcao = Integer.parseInt(sc.nextLine());

            if (opcao == 1) {

                contatos = incluirContato(clientes, contatos);

            } else if (opcao == 2) {

                listarContatos(clientes, contatos);

            } else if (opcao == 3) {

                listarContatosPorCliente(clientes, contatos);

            } else if (opcao == 4) {

                contatos = alterarContato(contatos);

            } else if (opcao == 5) {

                contatos = excluirContato(contatos);
            }
        }

        return contatos;
    }

    private static String[][] incluirCliente(String[][] clientes, Scanner scanner) {

        String[][] novaMatriz = new String[clientes.length + 1][8];

        for (int i = 0; i < clientes.length; i++) {

            novaMatriz[i] = clientes[i];
        }

        clientes = novaMatriz;

        int novo = clientes.length - 1;

        clientes[novo][0] = String.valueOf(clientes.length);

        System.out.print("Nome: ");
        clientes[novo][1] = scanner.nextLine();

        System.out.print("CPF: ");
        clientes[novo][2] = scanner.nextLine();

        System.out.print("Nascimento: ");
        clientes[novo][3] = scanner.nextLine();

        System.out.print("Sexo: ");
        clientes[novo][4] = scanner.nextLine();

        System.out.print("Cidade: ");
        clientes[novo][5] = scanner.nextLine();

        System.out.print("Estado: ");
        clientes[novo][6] = scanner.nextLine();

        System.out.print("Status: ");
        clientes[novo][7] = scanner.nextLine();

        System.out.println("Cliente cadastrado");

        return clientes;
    }

    private static void listarClientesTabela(String[][] clientes) {

        if (clientes.length == 0) {

            System.out.println("Nenhum cliente cadastrado");
            return;
        }

        System.out.println("\nLISTA DE CLIENTES");

        for (int i = 0; i < clientes.length; i++) {

            System.out.println(
                    "Código: " + clientes[i][0]
                            + " | Nome: " + clientes[i][1]
                            + " | CPF: " + clientes[i][2]
                            + " | Cidade: " + clientes[i][5]
            );
        }
    }

    private static void consultarClientePorCodigo(String[][] clientes, Scanner scanner) {

        System.out.print("Digite o código: ");
        String codigo = scanner.nextLine();

        int posicao = buscarCliente(clientes, codigo);

        if (posicao != -1) {

            System.out.println("Cliente encontrado");

            System.out.println("Nome: " + clientes[posicao][1]);
            System.out.println("CPF: " + clientes[posicao][2]);
            System.out.println("Cidade: " + clientes[posicao][5]);

        } else {

            System.out.println("Cliente não encontrado");
        }
    }

    public static String[][] alterarCliente(String[][] clientes, Scanner scanner) {

        listarClientesTabela(clientes);

        System.out.print("Digite o código: ");
        String codigo = scanner.nextLine();

        int posicao = buscarCliente(clientes, codigo);

        if (posicao != -1) {

            System.out.print("Novo nome: ");
            clientes[posicao][1] = scanner.nextLine();

            System.out.print("Novo CPF: ");
            clientes[posicao][2] = scanner.nextLine();

            System.out.print("Nova cidade: ");
            clientes[posicao][5] = scanner.nextLine();

            System.out.println("Cliente alterado");

        } else {

            System.out.println("Cliente não encontrado");
        }

        return clientes;
    }

    private static String[][] apagarCliente(String[][] clientes, String[][] contatos, Scanner scanner) {

        listarClientesTabela(clientes);

        System.out.print("Digite o código: ");
        String codigo = scanner.nextLine();

        int posicao = buscarCliente(clientes, codigo);

        if (posicao != -1) {

            String[][] nova = new String[clientes.length - 1][8];

            int j = 0;

            for (int i = 0; i < clientes.length; i++) {

                if (i != posicao) {

                    nova[j] = clientes[i];
                    j++;
                }
            }

            clientes = nova;

            System.out.println("Cliente apagado");

        } else {

            System.out.println("Cliente não encontrado");
        }

        return clientes;
    }

    private static String[][] incluirContato(String[][] clientes, String[][] contatos) {

        String[][] nova = new String[contatos.length + 1][5];

        for (int i = 0; i < contatos.length; i++) {

            nova[i] = contatos[i];
        }

        contatos = nova;

        int novo = contatos.length - 1;

        contatos[novo][0] = String.valueOf(contatos.length);

        System.out.print("Código do cliente: ");
        contatos[novo][1] = sc.nextLine();

        System.out.print("Tipo: ");
        contatos[novo][2] = sc.nextLine();

        System.out.print("Valor: ");
        contatos[novo][3] = sc.nextLine();

        contatos[novo][4] = "ATIVO";

        System.out.println("Contato cadastrado");

        return contatos;
    }

    private static void listarContatos(String[][] clientes, String[][] contatos) {

        for (int i = 0; i < contatos.length; i++) {

            System.out.println(
                    "Código: " + contatos[i][0]
                            + " | Cliente: " + contatos[i][1]
                            + " | Tipo: " + contatos[i][2]
                            + " | Valor: " + contatos[i][3]
            );
        }
    }

    private static void listarContatosPorCliente(String[][] clientes, String[][] contatos) {

        System.out.print("Código do cliente: ");
        String codigo = sc.nextLine();

        for (int i = 0; i < contatos.length; i++) {

            if (contatos[i][1].equals(codigo)) {

                System.out.println(
                        contatos[i][2] + " - " + contatos[i][3]
                );
            }
        }
    }

    private static String[][] alterarContato(String[][] contatos) {

        listarContatos(null, contatos);

        System.out.print("Código do contato: ");
        String codigo = sc.nextLine();

        int posicao = buscarContato(contatos, codigo);

        if (posicao != -1) {

            System.out.print("Novo tipo: ");
            contatos[posicao][2] = sc.nextLine();

            System.out.print("Novo valor: ");
            contatos[posicao][3] = sc.nextLine();

            System.out.println("Contato alterado");

        } else {

            System.out.println("Contato não encontrado");
        }

        return contatos;
    }

    private static String[][] excluirContato(String[][] contatos) {

        listarContatos(null, contatos);

        System.out.print("Código do contato: ");
        String codigo = sc.nextLine();

        int posicao = buscarContato(contatos, codigo);

        if (posicao != -1) {

            String[][] nova = new String[contatos.length - 1][5];

            int j = 0;

            for (int i = 0; i < contatos.length; i++) {

                if (i != posicao) {

                    nova[j] = contatos[i];
                    j++;
                }
            }

            contatos = nova;

            System.out.println("Contato removido");

        } else {

            System.out.println("Contato não encontrado");
        }

        return contatos;
    }

    private static void exibirRelatorios(String[][] clientes, String[][] contatos) {

        System.out.println("\n--- RELATÓRIOS ---");

        int totalClientes = clientes.length;
        int totalContatos = contatos.length;

        System.out.println("Total de clientes: " + totalClientes);
        System.out.println("Total de contatos: " + totalContatos);

        if (totalClientes > 0) {

            double media = (double) totalContatos / totalClientes;

            System.out.println("Média de contatos: " + media);
        }

        System.out.println("\nClientes sem contato:");

        boolean encontrou = false;

        for (int i = 0; i < clientes.length; i++) {

            String codigo = clientes[i][0];
            String nome = clientes[i][1];

            int qtd = contarContatosDoCliente(contatos, codigo);

            if (qtd == 0) {

                System.out.println(nome);

                encontrou = true;
            }
        }

        if (encontrou == false) {

            System.out.println("Nenhum cliente sem contato");
        }

        System.out.println("\nClientes com mais contatos:");

        for (int i = 0; i < clientes.length; i++) {

            String codigo = clientes[i][0];
            String nome = clientes[i][1];

            int qtd = contarContatosDoCliente(contatos, codigo);

            if (qtd >= 2) {

                System.out.println(nome + " - " + qtd + " contatos");
            }
        }
    }
    private static int buscarCliente(String[][] clientes, String codigo) {

        for (int i = 0; i < clientes.length; i++) {

            if (clientes[i][0].equals(codigo)) {

                return i;
            }
        }

        return -1;
    }

    private static int buscarContato(String[][] contatos, String codigo) {

        for (int i = 0; i < contatos.length; i++) {

            if (contatos[i][0].equals(codigo)) {

                return i;
            }
        }

        return -1;
    }

    private static int contarContatosDoCliente(String[][] contatos, String codigoCliente) {

        int contador = 0;

        for (int i = 0; i < contatos.length; i++) {

            if (contatos[i][1].equals(codigoCliente)) {

                contador++;
            }
        }

        return contador;
    }

}