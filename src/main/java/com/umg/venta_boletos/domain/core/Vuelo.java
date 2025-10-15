package com.umg.venta_boletos.domain.core;

import com.umg.venta_boletos.domain.catalogo.CatEstadoVuelo;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name="VUELOS",
        indexes = {
                @jakarta.persistence.Index(name="IX_VUELOS_RUTA",  columnList="RUTA_ID"),
                @jakarta.persistence.Index(name="IX_VUELOS_AVION", columnList="AVION_ID"),
                @jakarta.persistence.Index(name="IX_VUELOS_ESTADO", columnList="ESTADO_ID")
        })
@Getter @Setter @NoArgsConstructor
public class Vuelo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name="RUTA_ID")
    private Ruta ruta;

    @ManyToOne(optional=false) @JoinColumn(name="AVION_ID")
    private Avion avion;

    @Column(name="NUMERO_VUELO", nullable=false, length=10)
    private String numeroVuelo;

    @Column(name="SALIDA_TS", nullable=false)
    private OffsetDateTime salidaTs;

    @Column(name="LLEGADA_TS", nullable=false)
    private OffsetDateTime llegadaTs;

    @ManyToOne(optional=false) @JoinColumn(name="ESTADO_ID")
    private CatEstadoVuelo estado;
}
