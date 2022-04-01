package ru.itis.studentsgiftery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.studentsgiftery.models.Certificate;

public interface CertificatesRepository extends JpaRepository<Certificate, Long> {
}
