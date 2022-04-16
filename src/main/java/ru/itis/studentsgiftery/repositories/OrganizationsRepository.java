package ru.itis.studentsgiftery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.studentsgiftery.models.Organization;

public interface OrganizationsRepository extends JpaRepository<Organization, Long> {
}
