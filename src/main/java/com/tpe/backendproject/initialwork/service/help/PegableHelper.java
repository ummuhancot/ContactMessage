package com.tpe.backendproject.initialwork.service.help;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PegableHelper {


    public Pageable getPageable(
            int page,
            int size,
            String sort,
            String type) {
        return PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(type), sort));
    }




}
