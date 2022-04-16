package ru.itis.studentsgiftery.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationForm {
    private String name;
    private String description;
}
