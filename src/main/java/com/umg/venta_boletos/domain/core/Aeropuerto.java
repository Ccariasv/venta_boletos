package com.umg.venta_boletos.domain.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AEROPUERTOS",
        uniqueConstraints = @UniqueConstraint(name="UK_AEROPUERTO_IATA", columnNames="CODIGO_IATA"))
@Getter @Setter @NoArgsConstructor
public class Aeropuerto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NOMBRE", nullable=false, length=100)
    private String nombre;

    @Column(name="CIUDAD", length=100)
    private String ciudad;

    @Column(name="PAIS", length=100)
    private String pais;

    @Column(name="CODIGO_IATA", nullable=false, length=3)
    private String codigoIata;
}
