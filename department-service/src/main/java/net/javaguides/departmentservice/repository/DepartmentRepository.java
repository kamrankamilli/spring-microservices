package net.javaguides.departmentservice.repository;

import net.javaguides.departmentservice.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    Optional<Department> findByCode(String code);
}
