package ru.itis.studentsgiftery.dto.mapper;

import org.mapstruct.Mapper;
import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.models.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toAccount(AccountDto accountDto);

    AccountDto toAccountDto(Account account);
}
