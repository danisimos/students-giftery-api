package ru.itis.studentsgiftery.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class EmailUtil {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(String to, String subject, String templateName, Map<String, Object> templateData){
        try {
            String templateContent = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerConfigurer.getConfiguration().getTemplate(templateName), templateData);
            MimeMessagePreparator preparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setSubject(subject);
                messageHelper.setText(templateContent, true);
                messageHelper.setTo(to);
                messageHelper.setFrom(from);
            };

            new Thread(() -> mailSender.send(preparator)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
