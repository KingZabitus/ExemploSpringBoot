package mp.islancrud;

import jakarta.annotation.PostConstruct;
import mp.islancrud.model.Pessoa;
import mp.islancrud.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;


@RestController
@SpringBootApplication
public class IslanCrudApplication {

    @Autowired
    private PessoaService pessoaService;

    @RequestMapping("/")
    String home(){
        return "hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(IslanCrudApplication.class, args);
    }

    @PostConstruct
    public void run() {
        Scanner scanner  = new Scanner(System.in);

        while(true){
            System.out.println("Escolha uma operação");
            System.out.println("1. listar");
            System.out.println("2. criar pessoa");
            System.out.println("3. buscar por cpf");
            System.out.println("4. deleter por cpf");
            System.out.println("5. editar pessoa");
            System.out.println("6. sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha){
                case 1: listarPessoas();
                break;
                case 2: criarPessoa(scanner);
                break;
                case 3: buscarPessoaPorCPF(scanner);
                break;
                case 4: deleterPessoaPorCPF(scanner);
                break;
                case 5: editarPessoa(scanner);
                    break;
                case 6:
                    System.out.println("Adeus");
                    return;

                default:
                    System.out.println("Escolha inválida");
            }
        }

    }

    private void listarPessoas(){
        System.out.println("Lista de Pessoas:");
        pessoaService.listarPessoas().forEach(pessoa -> System.out.println("Nome: " + pessoa.getNome() + "CPF: " + pessoa.getCpf()));
    }

    private void criarPessoa(Scanner scanner) {
        System.out.println("Digite o nome da pessoa:");
        String nome = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Digite o cpf da pessoa:");
        long cpf = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Digite o email da pessoa:");
        String email = scanner.nextLine();
        scanner.nextLine();

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setCpf(cpf);
        pessoa.setEmail(email);


        pessoaService.criarPessoa(pessoa);
        System.out.println("Pessoa criada!");
    }
    private void buscarPessoaPorCPF(Scanner scanner){
        System.out.println("Digite o cpf da pessoa que deseja buscar");
        long cpf = scanner.nextLong();
        scanner.nextLine();

        pessoaService.obterPessoaPorCPF(cpf).ifPresentOrElse(
                pessoa -> System.out.println("Pessoa encontrada: " + pessoa.getNome()), () -> System.out.println("Pessoa não encontrada!")
        );
    }

    public void editarPessoa(Scanner scanner){
        System.out.println("Digite o cpf da pessoa que deseja editar:");
        long cpf = scanner.nextLong();
        scanner.nextLine();

        Pessoa pessoa = pessoaService.obterPessoaPorCPF(cpf).orElse(null);

        if(pessoa != null){
            System.out.println("Pessoa encontrada: " + pessoa.getNome());
            System.out.println("Digite o novo nome(ou pressione enter para manter o atual:");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()){
                pessoa.setNome(nome);
            }

            scanner.nextLine();

            System.out.println("Digite o novo email ou aperte enter:");
            String email = scanner.nextLine();
            if(!email.isEmpty()){
                pessoa.setEmail(email);
            }

            pessoaService.atualizarPessoa(pessoa);
        } else {
            System.out.println("Pessoa não encontrada!");
        }
    }

    private void deleterPessoaPorCPF(Scanner scanner){
        System.out.println("Digite o cpf da pessoa que deseja deletar");
        long cpf = scanner.nextLong();
        scanner.nextLine();

        pessoaService.deletarPessoa(cpf);
        System.out.println("Pessoa deletada com sucesso");
    }

}
