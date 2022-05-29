package ru.itis.studentsgiftery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {
    public enum State {
        ACTIVE, DELETED
    };

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private State state;
}
