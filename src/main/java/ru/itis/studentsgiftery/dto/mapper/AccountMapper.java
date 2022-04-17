package ru.itis.studentsgiftery.dto.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.itis.studentsgiftery.dto.AccountDto;
import ru.itis.studentsgiftery.dto.BrandDto;
import ru.itis.studentsgiftery.models.Account;
import ru.itis.studentsgiftery.models.Brand;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {
    public abstract Account toAccount(AccountDto accountDto);

    public abstract AccountDto toAccountDto(Account account);

    public abstract List<Account> toAccountList(List<AccountDto> accountsDto);

    public abstract List<AccountDto> toAccountDtoList(List<Account> accounts);
}
