package net.javaguides.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.exception.ResourceNotFoundException;
import net.javaguides.departmentservice.mapper.DepartmentMapper;
import net.javaguides.departmentservice.model.Department;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import net.javaguides.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;


    @Override
    public List<DepartmentDto> getDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(DepartmentMapper.MAPPER::mapToDepartmentDto).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentId));
        return  DepartmentMapper.MAPPER.mapToDepartmentDto(department);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department =departmentRepository.findByCode(code).orElseThrow(() -> new ResourceNotFoundException("Department", "code", code));
        return DepartmentMapper.MAPPER.mapToDepartmentDto(department);
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.MAPPER.mapToDepartment(departmentDto);
        department = departmentRepository.saveAndFlush(department);
        return DepartmentMapper.MAPPER.mapToDepartmentDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto) {
        Department existingDepartment =  departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentId));
        existingDepartment.setName(departmentDto.getName());
        existingDepartment.setDescription(departmentDto.getDescription());
        existingDepartment.setCode(departmentDto.getCode());
        Department updatedDepartment = departmentRepository.saveAndFlush(existingDepartment);
        return DepartmentMapper.MAPPER.mapToDepartmentDto(updatedDepartment);
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentId));
        departmentRepository.deleteById(departmentId);
    }
}
