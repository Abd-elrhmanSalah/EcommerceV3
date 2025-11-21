package com.ecommerce.ecommerce.utils;

import com.ecommerce.ecommerce.dto.response.CategoryResponseDto;
import com.ecommerce.ecommerce.dto.response.ItemResponseDto;
import com.ecommerce.ecommerce.entity.Item;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;

public class ObjectMapperUtils {

    private static ModelMapper modelMapper;


    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);




        modelMapper.typeMap(Item.class, ItemResponseDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getCategory().getTitle(),
                    (dest, value) -> {
                        if(dest.getCategory() == null) dest.setCategory(new CategoryResponseDto());
                        dest.getCategory().setTitle((String) value);
                    });
        });


    }

    private ObjectMapperUtils() {
    }


    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }


    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .toList();
    }
}