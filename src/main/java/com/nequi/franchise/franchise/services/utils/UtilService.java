package com.nequi.franchise.franchise.services.utils;

import com.nequi.franchise.franchise.enums.exceptions.ExceptionEnum;
import com.nequi.franchise.franchise.exceptions.NotFoundException;
import com.nequi.franchise.franchise.objects.utils.PaginationObj;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilService {

    public static <T> T checkOptionalEmpty(Optional<T> optional, ExceptionEnum exceptionEnum) {
        if (optional.isEmpty()) {
            throw new NotFoundException(exceptionEnum);
        }
        return optional.get();
    }

    public static Pageable buildPageable(PaginationObj paginationObj) {
        Sort sort = (paginationObj.getOrder() != null && paginationObj.getOrder().equalsIgnoreCase("DESC"))
                ? Sort.by(paginationObj.getColumn()).descending()
                : Sort.by(paginationObj.getColumn()).ascending();

        return PageRequest.of(paginationObj.getPage(), paginationObj.getSize(), sort);
    }
}
