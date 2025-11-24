package com.example.frota.caminhao;

import com.example.frota.marca.Marca;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CadastroCaminhao(
        @NotBlank
        String modelo,
        String placa,
        Marca marca,
        double cargaMaxima,
        int ano,
        
        @Positive(message = "Comprimento deve ser positivo")
        double comprimento,
        
        @Positive(message = "Largura deve ser positiva")
        double largura,
        
        @Positive(message = "Altura deve ser positiva")
        double altura) {

}

