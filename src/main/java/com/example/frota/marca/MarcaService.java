package com.example.frota.marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository repository;

    public Marca save(Marca marca) {
        return repository.save(marca);
    }
    public List<Marca> findAll() {
        return repository.findAll();
    }

    public Optional<Marca> findById(Long id) {
        return repository.findById(id);
    }

    public Marca update(Long id, Marca marcaAtualizada) {
        Marca marca = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));
        // Atualize os campos necessários
        marca.setNome(marcaAtualizada.getNome());
        return repository.save(marca);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
