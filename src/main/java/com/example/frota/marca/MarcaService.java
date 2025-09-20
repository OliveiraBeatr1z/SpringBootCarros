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

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
