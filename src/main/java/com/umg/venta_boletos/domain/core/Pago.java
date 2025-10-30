package com.umg.venta_boletos.domain.core;

import com.umg.venta_boletos.domain.catalogo.CatMetodoPago;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="PAGOS",
        indexes = {
                @jakarta.persistence.Index(name="IX_PAGOS_BOLETO", columnList="BOLETO_ID"),
                @jakarta.persistence.Index(name="IX_PAGOS_METODO", columnList="METODO_PAGO_ID")
        })
@Getter @Setter @NoArgsConstructor
public class Pago {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name="BOLETO_ID")
    private Boleto boleto;

    @ManyToOne(optional=false) @JoinColumn(name="METODO_PAGO_ID")
    private CatMetodoPago metodo;

    @Column(name="MONTO", nullable=false)
    private BigDecimal monto;

    @Column(name="FECHA_PAGO", nullable=false)
    private LocalDate fechaPago = LocalDate.now();

    @Column(name="REFERENCIA_EXT", length=100)
    private String referenciaExt;
}
