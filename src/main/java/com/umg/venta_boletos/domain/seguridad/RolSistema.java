package com.umg.venta_boletos.domain.seguridad;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="ROLES_SISTEMA",
        uniqueConstraints=@UniqueConstraint(name="UK_ROLES_SISTEMA_CODIGO", columnNames="CODIGO"))
@Getter @Setter @NoArgsConstructor
public class RolSistema {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="CODIGO", nullable=false, length=30)
    private String codigo;

    @Column(name="DESCRIPCION", length=200)
    private String descripcion;
}
