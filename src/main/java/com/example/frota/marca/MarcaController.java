package com.example.frota.marca;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping
    public String carregaPaginaListagem(Model model){
        System.out.println("Acessando /marca/listagem");
        model.addAttribute("lista", marcaRepository.findAll());
        return "example/frota/marca/listagem";
    }
    @GetMapping ("/formulario")
    public String carregaPaginaFormulario(Long id, Model model){
        if(id != null){
           var marca = marcaRepository.getReferenceById(id);
           model.addAttribute("marca", marca);
        }
        return "example/frota/marca/formulario";
    }

    @PostMapping
    @Transactional
    public String cadastrar (@Valid DadosCadastroMarca dados) {
        marcaRepository.save(new Marca(dados));
        return "redirect:marca";
    }

    @PutMapping
    @Transactional
    public String atualizar (DadosAtualizacaoMarca dados) {
        var marca = marcaRepository.getReferenceById(dados.id());
        marca.atualizarInformacoes(dados);
        return "redirect:marca";
    }

    @DeleteMapping
    @Transactional
    public String removeMarca (Long id) {
        marcaRepository.deleteById(id);
        return "redirect:marca";
    }

}
