package com.umg.venta_boletos.domain.core;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="CHECKIN",
        uniqueConstraints=@UniqueConstraint(name="UK_CHECKIN_BOLETO", columnNames="BOLETO_ID"),
        indexes = @jakarta.persistence.Index(name="IX_CHECKIN_BOLETO", columnList="BOLETO_ID"))
@Getter @Setter @NoArgsConstructor
public class Checkin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name="BOLETO_ID")
    private Boleto boleto;

    @Column(name="FECHA_CHECKIN", nullable=false)
    private LocalDate fechaCheckin = LocalDate.now();

    @Column(name="EQUIPAJE_FLAG", nullable=false, length=1)
    private String equipajeFlag = "N";  // 'S' o 'N'

    @Column(name="PESO_TOTAL_KG", nullable = false)
    private Double pesoTotalKg = 0.0;
}
