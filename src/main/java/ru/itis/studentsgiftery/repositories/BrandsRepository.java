package ru.itis.studentsgiftery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.studentsgiftery.models.Brand;

public interface BrandsRepository extends JpaRepository<Brand, Long> {
}
