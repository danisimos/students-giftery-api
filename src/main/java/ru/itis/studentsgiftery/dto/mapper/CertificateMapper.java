package ru.itis.studentsgiftery.dto.mapper;

import org.mapstruct.Mapper;
import ru.itis.studentsgiftery.dto.CertificateDto;
import ru.itis.studentsgiftery.models.Certificate;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    Certificate toCertificate(CertificateDto certificateDto);

    CertificateDto toCertificateDto(Certificate certificate);
}
