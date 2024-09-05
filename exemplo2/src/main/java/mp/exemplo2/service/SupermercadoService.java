package mp.exemplo2.service;

import mp.exemplo2.model.Supermercado;
import mp.exemplo2.repositories.SupermercadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupermercadoService {
    @Autowired
    private SupermercadoRepository supermercadoRepository;

    public List<Supermercado> listarSupermercados(){
        return supermercadoRepository.findAll();
    }

    public Optional<Supermercado> listarSupermercadoPorID(long id){
        return supermercadoRepository.findById(id);
    }

    public Supermercado criarSupermercado(Supermercado supermercado){
        return supermercadoRepository.save(supermercado);
    }

    public void deletarSupermercado(long id){
        supermercadoRepository.deleteById(id);
    }

    public void atualizarSupermercado(Supermercado supermercado){
        supermercadoRepository.save(supermercado);
    }
}
