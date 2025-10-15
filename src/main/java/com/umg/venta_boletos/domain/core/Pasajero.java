package com.umg.venta_boletos.domain.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="PASAJEROS",
        indexes = @jakarta.persistence.Index(name="IX_BOLETOS_PASAJERO", columnList="ID"))
@Getter @Setter @NoArgsConstructor
public class Pasajero {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NOMBRES", nullable=false, length=100)
    private String nombres;

    @Column(name="APELLIDOS", nullable=false, length=100)
    private String apellidos;

    @Column(name="EMAIL", length=100)
    private String email;

    @Column(name="TELEFONO", length=20)
    private String telefono;

    @Column(name="DOCUMENTO_IDENTIDAD", nullable=false, length=50, unique=true)
    private String documentoIdentidad;
}
