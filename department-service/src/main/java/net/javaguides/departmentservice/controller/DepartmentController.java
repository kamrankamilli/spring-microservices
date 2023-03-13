package net.javaguides.departmentservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.response.ApiResponse;
import net.javaguides.departmentservice.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartmentDto>>> getDepartments() {
        List<DepartmentDto> departments = departmentService.getDepartments();
        return new ApiResponse<>(HttpStatus.OK, null, departments).sendResponse();
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<ApiResponse<DepartmentDto>> getDepartment(@PathVariable Long departmentId) {
        DepartmentDto department = departmentService.getDepartmentById(departmentId);
        return new ApiResponse<>(HttpStatus.OK, null, department).sendResponse();
    }

    @GetMapping("/code/{departmentCode}")
    public ResponseEntity<ApiResponse<DepartmentDto>> getDepartment(@PathVariable String departmentCode) {
        DepartmentDto department = departmentService.getDepartmentByCode(departmentCode);
        System.out.println(department);
        return new ApiResponse<>(HttpStatus.OK, null, department).sendResponse();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentDto>> postDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        DepartmentDto createdDepartment = departmentService.createDepartment(departmentDto);
        return new ApiResponse<>(HttpStatus.CREATED, "Department created successfully", createdDepartment).sendResponse();
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<ApiResponse<DepartmentDto>> putDepartment(@PathVariable Long departmentId, @Valid @RequestBody DepartmentDto departmentDto) {
        DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentId, departmentDto);
        return new ApiResponse<>(HttpStatus.OK, "Department updated successfully", updatedDepartment).sendResponse();
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<ApiResponse<DepartmentDto>> deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return new ApiResponse<DepartmentDto>(HttpStatus.OK,"Department deleted successfully",null).sendResponse();
    }

}
