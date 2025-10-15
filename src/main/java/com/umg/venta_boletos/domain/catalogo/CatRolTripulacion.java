package com.umg.venta_boletos.domain.catalogo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CAT_ROL_TRIPULACION",
        uniqueConstraints = @UniqueConstraint(name="UK_CAT_ROL_TRIPULACION_CODIGO", columnNames="CODIGO"))
@Getter @Setter @NoArgsConstructor
public class CatRolTripulacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="CODIGO", nullable=false, length=30)
    private String codigo;

    @Column(name="DESCRIPCION", length=200)
    private String descripcion;
}
