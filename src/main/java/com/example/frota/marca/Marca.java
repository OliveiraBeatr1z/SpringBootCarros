package com.example.frota.marca;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "marca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Marca {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String pais;

    public Marca (DadosCadastroMarca dados){
        this.nome = dados.nome();
        this.pais = dados.pais();
    }

    public void atualizarInformacoes(DadosAtualizacaoMarca dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
            this.pais = dados.pais();
        }
    }
}