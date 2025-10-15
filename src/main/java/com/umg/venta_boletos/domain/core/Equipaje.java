package com.umg.venta_boletos.domain.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="EQUIPAJE",
        indexes=@jakarta.persistence.Index(name="IX_EQUIPAJE_CHECKIN", columnList="CHECKIN_ID"))
@Getter @Setter @NoArgsConstructor
public class Equipaje {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name="CHECKIN_ID")
    private Checkin checkin;

    @Column(name="DESCRIPCION", length=200)
    private String descripcion;

    @Column(name="PESO_KG", nullable=false)
    private Double pesoKg;
}
