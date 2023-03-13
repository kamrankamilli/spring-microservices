package net.javaguides.departmentservice.service;

import net.javaguides.departmentservice.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> getDepartments();
    DepartmentDto getDepartmentById(Long departmentId);

    DepartmentDto getDepartmentByCode(String departmentCode);

    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto updateDepartment(Long departmentId,DepartmentDto departmentDto);

    void deleteDepartmentById(Long departmentId);

}
