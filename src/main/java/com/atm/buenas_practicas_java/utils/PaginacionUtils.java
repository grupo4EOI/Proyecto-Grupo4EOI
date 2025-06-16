package com.atm.buenas_practicas_java.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

public class PaginacionUtils {

    public static <T> Page<T> listToPage(List<T> list, Pageable pageable) {
        if (pageable == null) {
            return new PageImpl<>(list);
        }

        int total = list.size();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<T> pagedList;

        if (startItem >= total) {
            pagedList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, total);
            pagedList = list.subList(startItem, toIndex);
        }
        return new PageImpl<>(pagedList, pageable, total);
    }
}
