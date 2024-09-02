package mp.islancrud.services;

import mp.islancrud.model.Pessoa;
import mp.islancrud.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> obterPessoaPorCPF(long cpf){
        return pessoaRepository.findById(cpf);
    }

    public Pessoa criarPessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public void deletarPessoa(long cpf){
        pessoaRepository.deleteById(cpf);
    }


    public void atualizarPessoa(Pessoa pessoa){
        pessoaRepository.save(pessoa);
    }


}
