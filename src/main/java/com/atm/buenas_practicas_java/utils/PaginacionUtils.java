package com.atm.buenas_practicas_java.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

public class PaginacionUtils {

    public static <T> Page<T> listToPage(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());

        if (start > end) {
            start = 0;
            end = 0;
        }

        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }
}
