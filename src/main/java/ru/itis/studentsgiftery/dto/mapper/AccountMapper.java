package ru.itis.studentsgiftery.dto.mapper;

import org.mapstruct.Mapper;
import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.models.Account;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toAccount(AccountDto accountDto);

    AccountDto toAccountDto(Account account);

    List<Account> toAccountList(List<AccountDto> accountsDto);

    List<AccountDto> toAccountDtoList(List<Account> accounts);
}
