package com.umg.venta_boletos.domain.core;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Index;

@Entity
@Table(name="RUTAS",
        indexes={
                @jakarta.persistence.Index(name="IX_RUTAS_ORIGEN",  columnList="A_ORIGEN_ID"),
                @jakarta.persistence.Index(name="IX_RUTAS_DESTINO", columnList="A_DESTINO_ID")
        })
@Getter @Setter @NoArgsConstructor
public class Ruta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name="A_ORIGEN_ID")
    private Aeropuerto origen;

    @ManyToOne(optional=false) @JoinColumn(name="A_DESTINO_ID")
    private Aeropuerto destino;

    @Column(name="DURACION_MIN", nullable=false)
    private Integer duracionMin;

    @Column(name="DISTANCIA_KM")
    private Double distanciaKm;
}
