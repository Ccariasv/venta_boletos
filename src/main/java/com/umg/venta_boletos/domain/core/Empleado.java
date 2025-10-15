package com.umg.venta_boletos.domain.core;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="EMPLEADOS")
@Getter @Setter @NoArgsConstructor
public class Empleado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NOMBRES", nullable=false, length=100)
    private String nombres;

    @Column(name="APELLIDOS", nullable=false, length=100)
    private String apellidos;

    @Column(name="PUESTO", nullable=false, length=50)
    private String puesto;

    @Column(name="EMAIL", length=100)
    private String email;

    @Column(name="TELEFONO", length=20)
    private String telefono;
}
