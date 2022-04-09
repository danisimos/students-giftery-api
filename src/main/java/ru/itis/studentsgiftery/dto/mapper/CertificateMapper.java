package ru.itis.studentsgiftery.dto.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.itis.studentsgiftery.dto.CertificateDto;
import ru.itis.studentsgiftery.models.Certificate;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CertificateMapper {

    public abstract Certificate toCertificate(CertificateDto certificateDto);

    public abstract CertificateDto toCertificateDto(Certificate certificate);

    public abstract List<CertificateDto> toCertificateDtoList(List<Certificate> certificates);

    @AfterMapping
    protected void setCertificateUserToCertificateDtoUser(Certificate certificate, @MappingTarget CertificateDto certificateDto){
        certificateDto.setUserId(certificate.getUserAccount().getId());
    }

}
