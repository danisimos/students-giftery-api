package ru.itis.studentsgiftery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.models.Brand;
import ru.itis.studentsgiftery.validation.annotations.NotSameNames;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NotSameNames(names = {"firstName", "lastName"}, message = "{names} are same")
public class OrganizationDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
