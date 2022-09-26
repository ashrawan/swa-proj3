package com.swa.proj3commonmodule.dto;

import java.util.ArrayList;
import java.util.List;

public interface GenericMapper<D, E> {

    D toDto(E e);

    E toEntity(D d);

    default List<D> toDtoList(List<E> eList) {
        List<D> dtoList = new ArrayList<>();
        for (E e : eList) {
            dtoList.add(toDto(e));
        }
        return dtoList;
    }

    default List<E> toEntityList(List<D> dList) {
        List<E> entityList = new ArrayList<>();
        for (D d : dList) {
            entityList.add(toEntity(d));
        }
        return entityList;
    }

}
