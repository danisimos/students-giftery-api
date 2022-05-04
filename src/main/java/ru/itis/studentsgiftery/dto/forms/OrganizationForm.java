package ru.itis.studentsgiftery.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.studentsgiftery.validation.annotations.NotSameNames;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationForm {
    private String name;
    private String description;
}
