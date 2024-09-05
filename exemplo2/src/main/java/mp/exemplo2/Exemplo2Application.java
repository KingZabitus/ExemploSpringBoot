package mp.exemplo2;

import jakarta.annotation.PostConstruct;
import mp.exemplo2.model.Produto;
import mp.exemplo2.model.Supermercado;
import mp.exemplo2.service.ProdutoService;
import mp.exemplo2.service.SupermercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class Exemplo2Application {

	@Autowired
	ProdutoService produtoService;
	@Autowired
	SupermercadoService supermercadoService;

	public static void main(String[] args) {
		SpringApplication.run(Exemplo2Application.class, args);
	}

	@PostConstruct
	public void run(){
		Scanner scanner = new Scanner(System.in);

		while(true){
			System.out.println("Escolha uma operação");
			System.out.println("1. listar produtos");
			System.out.println("2. criar produto");
			System.out.println("3. atualizar produto");
			System.out.println("4. deletar produto");
			System.out.println("5. buscar produto por código");
			System.out.println("6. listar supermercados");
			System.out.println("7. criar supermercado");
			System.out.println("8. atualizar supermercado");
			System.out.println("9. deletar supermercado");
			System.out.println("10. buscar supermercado por código");
			System.out.println("11. sair");

			int escolha = scanner.nextInt();
			scanner.nextLine();

			switch (escolha){
				case 1: listarProdutos(); break;
				case 2: criarProduto(scanner); break;
				case 3: atualizarProduto(scanner); break;
				case 4: deletarProduto(scanner); break;
				case 5: buscarProduto(scanner); break;
				case 6: listarSupermercados(); break;
				case 7: criarSupermercado(scanner); break;
				case 8: atualizarSupermercado(scanner); break;
				case 9: deletarSupermercado(scanner); break;
				case 10: buscarSupermercado(scanner); break;
				case 11: System.exit(0); break;
			}


		}
	}

	private void listarProdutos(){
		System.out.println("Produtos disponíveis:");
		produtoService.listarProdutos().forEach(produto ->
				System.out.println("nome: " + produto.getNome() + " preço: " + produto.getPreco()));
	}


	private void criarProduto(Scanner scanner){
		System.out.println("Digite o nome do produto:");
		String nome = scanner.nextLine();
		System.out.println("Digite o preço do produto:");
		double preco = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Digite a quantidade do produto:");
		int quantidade = scanner.nextInt();
		scanner.nextLine();

		Produto produto = new Produto();

		produto.setNome(nome);
		produto.setPreco(preco);
		produto.setQuantidade(quantidade);

		produtoService.criarProduto(produto);
		System.out.println("Produto criado com sucesso");
	}

	private void atualizarProduto(Scanner scanner){
		System.out.println("Digite o ID do produto a ser atualizado:");
		long id = scanner.nextLong();
		scanner.nextLine();

		Produto produto = produtoService.listarProdutoPorID(id).orElse(null);
		if(produto != null){
			System.out.println("Produto encontrado: " + produto.getNome());
			System.out.println("Digite o novo nome:");

			String nome = scanner.nextLine();
			if (!nome.isEmpty()){
				produto.setNome(nome);
			}
			scanner.nextLine();

			System.out.println("Digite o novo preço:");
			String precoInput = scanner.nextLine();
			if (!precoInput.isEmpty()){
				double preco = Double.parseDouble(precoInput);
				produto.setPreco(preco);
			}
			scanner.nextLine();

			System.out.println("Digite a nova quantidade:");
			String quantInput = scanner.nextLine();
			if(!quantInput.isEmpty()){
				int quantidade = Integer.parseInt(quantInput);
				produto.setQuantidade(quantidade);
			}

			produtoService.atualizarProduto(produto);
			System.out.println("Produto atualizado com sucesso!");
		} else {
			System.out.println("Não foi encontrado nenhum produto com esse id");
		}
	}

	private void deletarProduto(Scanner scanner){
		System.out.println("Digite o id do produto:");
		long id = scanner.nextLong();
		scanner.nextLine();

		produtoService.deletarProduto(id);
		System.out.println("Produto deletado com sucesso");
	}

	private void buscarProduto(Scanner scanner){
		System.out.println("Digite o id do produto:");
		long id = scanner.nextLong();
		scanner.nextLine();

		produtoService.listarProdutoPorID(id).ifPresentOrElse(
				produto -> System.out.println("Produto encontrado: " + produto.getNome() + ", preço: " + produto.getPreco()),
				() -> System.out.println("Produto não encontrado!")
		);
	}

	private void listarSupermercados() {
		System.out.println("Supermercados disponíveis:");
		supermercadoService.listarSupermercados().forEach(supermercado ->
				System.out.println("Nome: " + supermercado.getNome() + ", Endereço: " + supermercado.getEndereco()));
	}

	private void criarSupermercado(Scanner scanner) {
		System.out.println("Digite o nome do supermercado:");
		String nome = scanner.nextLine();
		System.out.println("Digite o endereço do supermercado:");
		String endereco = scanner.nextLine();

		Supermercado supermercado = new Supermercado();
		supermercado.setNome(nome);
		supermercado.setEndereco(endereco);

		supermercadoService.criarSupermercado(supermercado);
		System.out.println("Supermercado criado com sucesso");
	}

	private void atualizarSupermercado(Scanner scanner) {
		System.out.println("Digite o ID do supermercado a ser atualizado:");
		long id = scanner.nextLong();
		scanner.nextLine();

		Supermercado supermercado = supermercadoService.listarSupermercadoPorID(id).orElse(null);
		if (supermercado != null) {
			System.out.println("Supermercado encontrado: " + supermercado.getNome());
			System.out.println("Digite o novo nome:");

			String nome = scanner.nextLine();
			if (!nome.isEmpty()) {
				supermercado.setNome(nome);
			}
			scanner.nextLine();

			System.out.println("Digite o novo endereço:");
			String endereco = scanner.nextLine();
			if (!endereco.isEmpty()) {
				supermercado.setEndereco(endereco);
			}

			supermercadoService.atualizarSupermercado(supermercado);
			System.out.println("Supermercado atualizado com sucesso!");
		} else {
			System.out.println("Não foi encontrado nenhum supermercado com esse id");
		}
	}

	private void deletarSupermercado(Scanner scanner) {
		System.out.println("Digite o id do supermercado:");
		long id = scanner.nextLong();
		scanner.nextLine();

		supermercadoService.deletarSupermercado(id);
		System.out.println("Supermercado deletado com sucesso");
	}

	private void buscarSupermercado(Scanner scanner) {
		System.out.println("Digite o id do supermercado:");
		long id = scanner.nextLong();
		scanner.nextLine();

		supermercadoService.listarSupermercadoPorID(id).ifPresentOrElse(
				supermercado -> System.out.println("Supermercado encontrado: " + supermercado.getNome() + ", Endereço: " + supermercado.getEndereco()),
				() -> System.out.println("Supermercado não encontrado!")
		);
	}


}
