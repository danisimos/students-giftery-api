package ru.itis.studentsgiftery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.studentsgiftery.models.OrganizationJoinRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationJoinRequestDto {
    public enum State {
        SENT, CONFIRMED, DENIED
    }

    private Long id;
    private Long accountId;
    private Long organizationId;
    private OrganizationJoinRequest.State state;
}
