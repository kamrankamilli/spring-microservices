package net.javaguidess.organizationservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrganizationDto {

    private Long id;
    @NotEmpty
    private String name;


    private String description;

    @NotEmpty
    private String code;

    private LocalDateTime createdAt;
}
