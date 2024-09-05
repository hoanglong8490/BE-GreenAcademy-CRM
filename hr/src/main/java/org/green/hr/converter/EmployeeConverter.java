package org.green.hr.converter;

import org.green.hr.dto.EmployeeDTO;
import org.green.hr.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {

    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDTO convertToDto(Employee employee) {


        return null;
    }
}
