package com.example.frota.caminhao;

import com.example.frota.marca.Marca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "caminhao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Caminhao {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "caminhao_id")
    private Long id;
    private String modelo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id", referencedColumnName = "marca_id")
    private Marca marca;
    private String placa;
    private double cargaMaxima;
    private int ano;
    
    // Dimensões do caminhão em metros
    private double comprimento;
    private double largura;
    private double altura;
    
    // Fator de cubagem para transporte rodoviário (300 kg/m³)
    private static final double FATOR_CUBAGEM = 300.0;
    
    // Quilometragem atual
    private double quilometragemAtual = 0.0;
    
    // Última quilometragem de manutenção
    private double ultimaManutencaoOleo = 0.0;
    private double ultimaTrocaPneus = 0.0;

    public Caminhao(CadastroCaminhao dados, Marca marca) {
        this.modelo = dados.modelo();
        this.placa = dados.placa();
        this.cargaMaxima = dados.cargaMaxima();
        this.marca = marca;
        this.ano= dados.ano();
        this.comprimento = dados.comprimento();
        this.largura = dados.largura();
        this.altura = dados.altura();
    }
    
    public Caminhao(AtualizacaoCaminhao dados, Marca marca) {
        this.modelo = dados.modelo();
        this.placa = dados.placa();
        this.cargaMaxima = dados.cargaMaxima();
        this.marca = marca;
        this.ano= dados.ano();
        this.comprimento = dados.comprimento();
        this.largura = dados.largura();
        this.altura = dados.altura();
    }

    public void atualizarInformacoes(AtualizacaoCaminhao dados, Marca marca) {
        if (dados.modelo() != null )
            this.modelo = dados.modelo();
        if (dados.placa() != null)
            this.placa =dados.placa();
        if (dados.cargaMaxima() != 0)
            this.cargaMaxima = dados.cargaMaxima();
        if (marca != null)
            this.marca = marca;
        if (dados.ano() != 0)
            this.ano = dados.ano();
        if (dados.comprimento() != null && dados.comprimento() > 0)
            this.comprimento = dados.comprimento();
        if (dados.largura() != null && dados.largura() > 0)
            this.largura = dados.largura();
        if (dados.altura() != null && dados.altura() > 0)
            this.altura = dados.altura();
    }
    
    /**
     * Calcula a metragem cúbica (volume) do caminhão em m³
     * @return volume em metros cúbicos
     */
    public double calcularMetragemCubica() {
        return comprimento * largura * altura;
    }
    
    /**
     * Calcula a capacidade em peso usando o fator de cubagem
     * Peso cubado = Volume (m³) x Fator de Cubagem
     * @return capacidade em kg
     */
    public double calcularCapacidadePesoCubado() {
        return calcularMetragemCubica() * FATOR_CUBAGEM;
    }
    
    /**
     * Verifica se o caminhão precisa de manutenção de óleo
     * @return true se necessita manutenção
     */
    public boolean necessitaManutencaoOleo() {
        return (quilometragemAtual - ultimaManutencaoOleo) >= 10000;
    }
    
    /**
     * Verifica se o caminhão precisa de troca de pneus
     * @return true se necessita troca
     */
    public boolean necessitaTrocaPneus() {
        return (quilometragemAtual - ultimaTrocaPneus) >= 70000;
    }
    
    /**
     * Registra a manutenção de óleo realizada
     */
    public void registrarManutencaoOleo() {
        this.ultimaManutencaoOleo = this.quilometragemAtual;
    }
    
    /**
     * Registra a troca de pneus realizada
     */
    public void registrarTrocaPneus() {
        this.ultimaTrocaPneus = this.quilometragemAtual;
    }

}
