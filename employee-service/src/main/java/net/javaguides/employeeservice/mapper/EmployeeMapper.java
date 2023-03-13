package net.javaguides.employeeservice.mapper;

import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "departmentCode", target = "departmentCode")
    EmployeeDto mapToEmployeeDto(Employee employee);
    Employee mapToEmployee(EmployeeDto employeeDto);
}
