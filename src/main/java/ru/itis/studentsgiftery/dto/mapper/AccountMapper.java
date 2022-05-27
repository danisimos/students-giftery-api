package ru.itis.studentsgiftery.dto.mapper;

import org.mapstruct.Mapper;
import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.models.Account;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {
    public abstract Account toAccount(AccountDto accountDto);

    public abstract AccountDto toAccountDto(Account account);

    public abstract List<Account> toAccountList(List<AccountDto> accountsDto);

    public abstract List<AccountDto> toAccountDtoList(List<Account> accounts);
}
