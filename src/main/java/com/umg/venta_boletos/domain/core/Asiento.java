package com.umg.venta_boletos.domain.core;

import com.umg.venta_boletos.domain.catalogo.ClaseAsiento;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="ASIENTOS",
        uniqueConstraints=@UniqueConstraint(name="UK_ASIENTO_NUMERO", columnNames={"AVION_ID","NUMERO_ASIENTO"}),
        indexes = @jakarta.persistence.Index(name="IX_ASIENTOS_AVION", columnList="AVION_ID"))
@Getter @Setter @NoArgsConstructor
public class Asiento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name="AVION_ID")
    private Avion avion;

    @Column(name="NUMERO_ASIENTO", nullable=false, length=10)
    private String numeroAsiento;

    @ManyToOne(optional=false) @JoinColumn(name="CLASE_ID")
    private ClaseAsiento clase;
}
