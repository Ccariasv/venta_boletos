package com.umg.venta_boletos.web.dto;

import java.util.List;

public record PageResponse<T>(
        List<T> content, int page, int size, long totalElements, int totalPages
) {}