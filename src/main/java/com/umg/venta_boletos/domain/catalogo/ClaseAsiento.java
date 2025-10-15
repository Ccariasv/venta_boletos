package com.umg.venta_boletos.domain.catalogo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CLASES_ASIENTO",
        uniqueConstraints = @UniqueConstraint(name="UK_CLASES_ASIENTO_NOMBRE", columnNames="NOMBRE_CLASE"))
@Getter @Setter @NoArgsConstructor
public class ClaseAsiento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NOMBRE_CLASE", nullable=false, length=50)
    private String nombreClase;

    @Column(name="DESCRIPCION", length=200)
    private String descripcion;
}
