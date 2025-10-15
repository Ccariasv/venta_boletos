package com.umg.venta_boletos.domain.core;

import com.umg.venta_boletos.domain.catalogo.CatEstadoAvion;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="AVIONES",
        uniqueConstraints = @UniqueConstraint(name="UK_AVION_MATRICULA", columnNames="MATRICULA"))
@Getter @Setter @NoArgsConstructor
public class Avion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="MODELO", nullable=false, length=100)
    private String modelo;

    @Column(name="FABRICANTE", length=100)
    private String fabricante;

    @Column(name="CAPACIDAD_TOTAL", nullable=false)
    private Integer capacidadTotal;

    @ManyToOne(optional=false) @JoinColumn(name="ESTADO_ID")
    private CatEstadoAvion estado;

    @Column(name="MATRICULA", nullable=false, length=20)
    private String matricula;
}
