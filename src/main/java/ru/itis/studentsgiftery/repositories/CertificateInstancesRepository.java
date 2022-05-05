package ru.itis.studentsgiftery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.studentsgiftery.models.CertificateInstance;

public interface CertificateInstancesRepository extends JpaRepository<CertificateInstance, Long> {
}
