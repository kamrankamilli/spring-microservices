package net.javaguides.employeeservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentDto {
    private Long id;
    private String name;
    private String description;
    private String code;
}
