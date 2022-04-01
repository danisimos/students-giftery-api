package ru.itis.studentsgiftery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.studentsgiftery.models.Account;

public interface AccountsRepository extends JpaRepository<Account, Long> {
}
