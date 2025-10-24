package com.umg.venta_boletos.repo;

import com.umg.venta_boletos.domain.core.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepo extends JpaRepository<Checkin,Long> {
}
