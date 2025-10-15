package com.umg.venta_boletos.domain.catalogo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CAT_ESTADO_VUELO",
        uniqueConstraints = @UniqueConstraint(name="UK_CAT_ESTADO_VUELO_CODIGO", columnNames="CODIGO"))
@Getter @Setter @NoArgsConstructor
public class CatEstadoVuelo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="CODIGO", nullable=false, length=30)
    private String codigo;

    @Column(name="DESCRIPCION", length=200)
    private String descripcion;
}
