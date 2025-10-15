package com.umg.venta_boletos.domain.seguridad;

import com.umg.venta_boletos.domain.core.Empleado;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="USUARIOS",
        uniqueConstraints=@UniqueConstraint(name="UK_USUARIO_USERNAME", columnNames="USERNAME"))
@Getter @Setter @NoArgsConstructor
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="USERNAME", nullable=false, length=50)
    private String username;

    @Column(name="PASSWORD_HASH", nullable=false, length=256)
    private String passwordHash;

    @ManyToOne(optional=false) @JoinColumn(name="ROL_ID")
    private RolSistema rol;

    @ManyToOne @JoinColumn(name="EMPLEADO_ID")
    private Empleado empleado;

    @Column(name="ACTIVO_FLAG", nullable=false, length=1)
    private String activoFlag = "S"; // 'S' o 'N'
}
