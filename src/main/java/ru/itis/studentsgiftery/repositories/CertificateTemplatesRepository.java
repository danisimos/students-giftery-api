package ru.itis.studentsgiftery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.studentsgiftery.models.CertificateTemplate;

import java.util.List;

public interface CertificateTemplatesRepository extends JpaRepository<CertificateTemplate, Long> {
    List<CertificateTemplate> findAllByBrandId(Long brandId);
}
