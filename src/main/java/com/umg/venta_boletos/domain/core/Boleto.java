package com.umg.venta_boletos.domain.core;

import com.umg.venta_boletos.domain.catalogo.CatEstadoBoleto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="BOLETOS",
        uniqueConstraints=@UniqueConstraint(name="UK_BOLETO_VUELO_ASIENTO", columnNames={"VUELO_ID","ASIENTO_ID"}),
        indexes={
                @jakarta.persistence.Index(name="IX_BOLETOS_ESTADO", columnList="ESTADO_ID"),
                @jakarta.persistence.Index(name="IX_BOLETOS_PASAJERO", columnList="PASAJERO_ID")
        })
@Getter @Setter @NoArgsConstructor
public class Boleto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name="VUELO_ID")
    private Vuelo vuelo;

    @ManyToOne(optional=false) @JoinColumn(name="PASAJERO_ID")
    private Pasajero pasajero;

    @ManyToOne(optional=false) @JoinColumn(name="ASIENTO_ID")
    private Asiento asiento;

    @Column(name="PRECIO", nullable=false, precision=10, scale=2)
    private BigDecimal precio;

    @Column(name="FECHA_EMISION", nullable=false)
    private LocalDate fechaEmision = LocalDate.now();

    @ManyToOne(optional=false) @JoinColumn(name="ESTADO_ID")
    private CatEstadoBoleto estado;
}
