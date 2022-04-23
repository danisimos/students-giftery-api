package ru.itis.studentsgiftery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.studentsgiftery.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByEmail(String email);
    Optional<Account> findAccountByConfirmCode(String confirmCode);

    List<Account> findAllByOrganizationId(Long organizationId);
}
