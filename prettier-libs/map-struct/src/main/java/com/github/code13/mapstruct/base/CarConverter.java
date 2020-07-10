package com.github.code13.mapstruct.base;

import com.github.code13.mapstruct.bean.Car;
import com.github.code13.mapstruct.bean.CarDto;
import org.mapstruct.Mapper;

/**
 * @author Code13
 * @date 2020-07-10 11:52
 */
@Mapper(componentModel = "spring")
public interface CarConverter extends BaseConverter<Car, CarDto> {

}
