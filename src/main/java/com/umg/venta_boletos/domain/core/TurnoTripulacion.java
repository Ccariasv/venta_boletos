package com.umg.venta_boletos.domain.core;

import com.umg.venta_boletos.domain.catalogo.CatRolTripulacion;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="TURNOS_TRIPULACION",
        uniqueConstraints=@UniqueConstraint(name="UK_TURNO", columnNames={"EMPLEADO_ID","VUELO_ID","ROL_ID"}),
        indexes = @jakarta.persistence.Index(name="IX_TURNO_VUELO", columnList="VUELO_ID"))
@Getter @Setter @NoArgsConstructor
public class TurnoTripulacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) @JoinColumn(name="EMPLEADO_ID")
    private Empleado empleado;

    @ManyToOne(optional=false) @JoinColumn(name="VUELO_ID")
    private Vuelo vuelo;

    @ManyToOne(optional=false) @JoinColumn(name="ROL_ID")
    private CatRolTripulacion rol;
}
