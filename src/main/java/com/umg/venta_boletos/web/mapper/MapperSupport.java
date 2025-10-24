package com.umg.venta_boletos.web.mapper;

import org.springframework.data.domain.Page;
import com.umg.venta_boletos.web.dto.PageResponse;

public final class MapperSupport {
    private MapperSupport() {}

    public static <T> PageResponse<T> toPageResponse(Page<T> page){
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
