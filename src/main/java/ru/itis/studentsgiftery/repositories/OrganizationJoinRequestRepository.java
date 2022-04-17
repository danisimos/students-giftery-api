package ru.itis.studentsgiftery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.studentsgiftery.models.OrganizationJoinRequest;

import java.util.Optional;

public interface OrganizationJoinRequestRepository extends JpaRepository<OrganizationJoinRequest, Long> {
    Optional<OrganizationJoinRequest> findById(Long id);
}
