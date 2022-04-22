package ru.itis.studentsgiftery.dto.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.itis.studentsgiftery.dto.CertificateTemplateDto;
import ru.itis.studentsgiftery.models.CertificateTemplate;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CertificateMapper {

    public abstract CertificateTemplate toCertificate(CertificateTemplateDto certificateTemplateDto);

    public abstract CertificateTemplateDto toCertificateTemplateDto(CertificateTemplate certificateTemplate);

    public abstract List<CertificateTemplateDto> toCertificateTemplateDtoList(List<CertificateTemplate> certificateTemplates);

    @AfterMapping
    protected void setBrandToCertificateTemplateDto(CertificateTemplate certificateTemplate, @MappingTarget CertificateTemplateDto certificateTemplateDto){
        certificateTemplateDto.setBrandId(certificateTemplate.getBrand().getId());
    }

}
