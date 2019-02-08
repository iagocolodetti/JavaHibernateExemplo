
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author iagocolodetti
 */
public class main {

    private static ContatoDAO contatoDAO = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        contatoDAO = new ContatoDAO();

        int opcao;
        
        while (true) {
            System.out.println();
            System.out.println("..Hibernate Opções..");
            System.out.println("1 -> Adicionar");
            System.out.println("2 -> Buscar Por ID");
            System.out.println("3 -> Buscar Por Nome");
            System.out.println("4 -> Buscar Por Telefone");
            System.out.println("5 -> Buscar Por E-mail");
            System.out.println("6 -> Buscar Todos");
            System.out.println("7 -> Alterar");
            System.out.println("8 -> Deletar");
            System.out.println("0 -> Encerrar");
            System.out.print("Opção..: ");
            opcao = lerInt();

            switch (opcao) {
                case 0:
                    System.out.println();
                    encerrar();
                    break;
                case 1:
                    System.out.println();
                    adicionar();
                    break;
                case 2:
                    System.out.println();
                    buscar();
                    break;
                case 3:
                    System.out.println();
                    buscarPorNome();
                    break;
                case 4:
                    System.out.println();
                    buscarPorTelefone();
                    break;
                case 5:
                    System.out.println();
                    buscarPorEmail();
                    break;
                case 6:
                    System.out.println();
                    buscarTodos();
                    break;
                case 7:
                    System.out.println();
                    alterar();
                    break;
                case 8:
                    System.out.println();
                    deletar();
                    break;
                default:
                    System.out.println();
                    System.out.println("Opção Incorreta.");
                    break;
            }
        }
    }
    
    private static void encerrar() {
        contatoDAO.close();
        System.out.println("Encerrado.");
        System.exit(0);
    }

    private static void adicionar() {
        Contato c = new Contato();
        System.out.println("------------- [ADICIONAR] -------------");
        System.out.print("Nome: ");
        c.setNome(lerString());
        System.out.print("Telefone: ");
        c.setTelefone(lerString());
        System.out.print("E-mail: ");
        c.setEmail(lerString());
        try {
            contatoDAO.add(c);
            System.out.println("Contato \"" + c.getNome() + " | " + c.getTelefone() + " | " + c.getEmail() + "\"  adicionado.");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------");
    }

    private static void buscar() {
        System.out.println("------------ [BUSCAR (ID)] ------------");
        System.out.print("ID: ");
        try {
            Contato c = contatoDAO.getContato(lerInt());
            System.out.println("Contato: (ID: " + c.getId() + ") " + c.getNome() + " | " + c.getTelefone() + " | " + c.getEmail());
        } catch (ContatoNaoExisteException | HibernateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------");
    }

    private static void buscarPorNome() {
        System.out.println("----------- [BUSCAR (NOME)] -----------");
        System.out.print("Nome: ");
        try {
            List<Contato> contatos = contatoDAO.getContatosPorNome(lerString());
            for (Contato c : contatos) {
                System.out.println("Contato: (ID: " + c.getId() + ") " + c.getNome() + " | " + c.getTelefone() + " | " + c.getEmail());
            }
        } catch (ContatoNaoExisteException | HibernateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------");
    }

    private static void buscarPorTelefone() {
        System.out.println("---------- [BUSCAR (TELEFONE)] ----------");
        System.out.print("Telefone: ");
        try {
            List<Contato> contatos = contatoDAO.getContatosPorTelefone(lerString());
            for (Contato c : contatos) {
                System.out.println("Contato: (ID: " + c.getId() + ") " + c.getNome() + " | " + c.getTelefone() + " | " + c.getEmail());
            }
        } catch (ContatoNaoExisteException | HibernateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------");
    }

    private static void buscarPorEmail() {
        System.out.println("---------- [BUSCAR (E-EMAIL)] ----------");
        System.out.print("E-mail: ");
        try {
            List<Contato> contatos = contatoDAO.getContatosPorEmail(lerString());
            for (Contato c : contatos) {
                System.out.println("Contato: (ID: " + c.getId() + ") " + c.getNome() + " | " + c.getTelefone() + " | " + c.getEmail());
            }
        } catch (ContatoNaoExisteException | HibernateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------");
    }

    private static void buscarTodos() {
        System.out.println("----------- [BUSCAR (TODOS)] -----------");
        try {
            List<Contato> contatos = contatoDAO.getContatos();
            for (Contato c : contatos) {
                System.out.println("Contato: (ID: " + c.getId() + ") " + c.getNome() + " | " + c.getTelefone() + " | " + c.getEmail());
            }
        } catch (ContatoNaoExisteException | HibernateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------");
    }

    private static void alterar() {
        Contato c = new Contato();
        System.out.println("-------------- [ALTERAR] --------------");
        System.out.print("ID: ");
        c.setId(lerInt());
        System.out.print("Novo nome: ");
        c.setNome(lerString());
        System.out.print("Novo telefone: ");
        c.setTelefone(lerString());
        System.out.print("Novo e-mail: ");
        c.setEmail(lerString());
        try {
            contatoDAO.update(c);
            System.out.println("Contato alterado com sucesso.");
        } catch (ContatoNaoExisteException | HibernateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------");
    }

    private static void deletar() {
        System.out.println("-------------- [DELETAR] --------------");
        System.out.print("ID: ");
        try {
            contatoDAO.delete(lerInt());
            System.out.println("Contato deletado com sucesso.");
        } catch (ContatoNaoExisteException | HibernateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------");
    }

    private static String lerString() {
        java.util.Scanner s = new java.util.Scanner(System.in, "ISO-8859-1");
        return s.nextLine();
    }

    private static int lerInt() {
        try {
            java.util.Scanner s = new java.util.Scanner(System.in);
            return s.nextInt();
        } catch (java.util.InputMismatchException e) {
            return -1;
        }
    }
}
