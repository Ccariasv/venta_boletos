package com.umg.venta_boletos.web.mapper;

import com.umg.venta_boletos.domain.catalogo.*;
import com.umg.venta_boletos.domain.core.*;
import com.umg.venta_boletos.domain.seguridad.*;
import com.umg.venta_boletos.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityRefResolver {
    private final AeropuertoRepo aeropuertoRepo;
    private final RutaRepo rutaRepo;
    private final AvionRepo avionRepo;
    private final AsientoRepo asientoRepo;
    private final ClaseAsientoRepo claseAsientoRepo;
    private final CatEstadoAvionRepo catEstadoAvionRepo;
    private final CatEstadoVueloRepo catEstadoVueloRepo;
    private final CatEstadoBoletoRepo catEstadoBoletoRepo;
    private final CatMetodoPagoRepo catMetodoPagoRepo;
    private final CatRolTripulacionRepo catRolTripulacionRepo;
    private final VueloRepo vueloRepo;
    private final PasajeroRepo pasajeroRepo;
    private final BoletoRepo boletoRepo;
    private final EmpleadoRepo empleadoRepo;
    private final CheckinRepo checkinRepo;
    private final RolSistemaRepo rolSistemaRepo;

    public Aeropuerto refAeropuerto(Long id){ return id==null?null:aeropuertoRepo.findById(id).orElseThrow(); }
    public Ruta refRuta(Long id){ return id==null?null:rutaRepo.findById(id).orElseThrow(); }
    public Avion refAvion(Long id){ return id==null?null:avionRepo.findById(id).orElseThrow(); }
    public Asiento refAsiento(Long id){ return id==null?null:asientoRepo.findById(id).orElseThrow(); }
    public ClaseAsiento refClaseAsiento(Long id){ return id==null?null:claseAsientoRepo.findById(id).orElseThrow(); }
    public CatEstadoAvion refCatEstadoAvion(Integer id){ return id==null?null:catEstadoAvionRepo.findById(id).orElseThrow(); }
    public CatEstadoVuelo refCatEstadoVuelo(Integer id){ return id==null?null:catEstadoVueloRepo.findById(id).orElseThrow(); }
    public CatEstadoBoleto refCatEstadoBoleto(Integer id){ return id==null?null:catEstadoBoletoRepo.findById(id).orElseThrow(); }
    public CatMetodoPago refCatMetodoPago(Integer id){ return id==null?null:catMetodoPagoRepo.findById(id).orElseThrow(); }
    public CatRolTripulacion refCatRolTrip(Integer id){ return id==null?null:catRolTripulacionRepo.findById(id).orElseThrow(); }
    public Vuelo refVuelo(Long id){ return id==null?null:vueloRepo.findById(id).orElseThrow(); }
    public Pasajero refPasajero(Long id){ return id==null?null:pasajeroRepo.findById(id).orElseThrow(); }
    public Boleto refBoleto(Long id){ return id==null?null:boletoRepo.findById(id).orElseThrow(); }
    public Empleado refEmpleado(Long id){ return id==null?null:empleadoRepo.findById(id).orElseThrow(); }
    public Checkin refCheckin(Long id){ return id==null?null:checkinRepo.findById(id).orElseThrow(); }
    public RolSistema refRolSistema(Integer id){ return id==null?null:rolSistemaRepo.findById(id).orElseThrow(); }

    public Aeropuerto aeropuerto(Long id){ return refAeropuerto(id); }
    public Ruta ruta(Long id){ return refRuta(id); }
    public Avion avion(Long id){ return refAvion(id); }
    public Asiento asiento(Long id){ return refAsiento(id); }
    public ClaseAsiento claseAsiento(Long id){ return refClaseAsiento(id); }

    public CatEstadoAvion catEstadoAvion(Integer id){ return refCatEstadoAvion(id); }
    public CatEstadoVuelo catEstadoVuelo(Integer id){ return refCatEstadoVuelo(id); }
    public CatEstadoBoleto catEstadoBoleto(Integer id){ return refCatEstadoBoleto(id); }
    public CatMetodoPago catMetodoPago(Integer id){ return refCatMetodoPago(id); }
    public CatRolTripulacion catRolTrip(Integer id){ return refCatRolTrip(id); }

    public Vuelo vuelo(Long id){ return refVuelo(id); }
    public Pasajero pasajero(Long id){ return refPasajero(id); }
    public Boleto boleto(Long id){ return refBoleto(id); }
    public Empleado empleado(Long id){ return refEmpleado(id); }
    public Checkin checkin(Long id){ return refCheckin(id); }
    public RolSistema rolSistema(Integer id){ return refRolSistema(id); }

}