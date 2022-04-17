package ru.itis.studentsgiftery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.studentsgiftery.models.Organization;

import java.util.Optional;

public interface OrganizationsRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findById(Long id);
}
