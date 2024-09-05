package mp.exemplo2.service;

import mp.exemplo2.model.Produto;
import mp.exemplo2.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> listarProdutoPorID(long id){
        return produtoRepository.findById(id);
    }

    public Produto criarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public void deletarProduto(long id){
        produtoRepository.deleteById(id);
    }

    public void atualizarProduto(Produto produto){
        produtoRepository.save(produto);
    }
}
