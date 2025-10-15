package com.umg.venta_boletos.domain.catalogo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CAT_ESTADO_BOLETO",
        uniqueConstraints = @UniqueConstraint(name="UK_CAT_ESTADO_BOLETO_CODIGO", columnNames="CODIGO"))
@Getter @Setter @NoArgsConstructor
public class CatEstadoBoleto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="CODIGO", nullable=false, length=30)
    private String codigo;

    @Column(name="DESCRIPCION", length=200)
    private String descripcion;
}
