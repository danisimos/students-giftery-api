package ru.itis.studentsgiftery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.studentsgiftery.models.Certificate;

import java.util.List;

public interface CertificatesRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findAllByBrandId(Long brandId);
}
