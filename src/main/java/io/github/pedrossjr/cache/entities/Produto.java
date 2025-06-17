package io.github.pedrossjr.cache.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_produtos")
public class Produto implements Serializable {

    @Id
    private String sku;
    private BigDecimal preco;
    private String descricao;
    private int quantidade;
    private Boolean ativo;

}
