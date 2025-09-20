package com.example.frota.marca;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping
    public String carregaPaginaListagem(Model model){
        System.out.println("Acessando /marca/listagem");
        model.addAttribute("lista", marcaRepository.findAll());
        return "marca/listagem";
    }
    @GetMapping ("/formulario")
    public String carregaPaginaFormulario(Model model){
        return "marca/formulario";
    }
    @PostMapping
    @Transactional
    public String cadastrar (@Valid DadosCadastroMarca dados) {
        marcaRepository.save(new Marca(dados));
        return "redirect:marca";
    }

    @DeleteMapping
    @Transactional
    public String removeMarca (Long id) {
        marcaRepository.deleteById(id);
        return "redirect:marca";
    }

}
