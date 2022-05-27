package ru.itis.studentsgiftery.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CertificateInstance {
    public enum State {
        ACTIVATED, NOT_ACTIVATED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "amount")
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "certificate_template")
    private CertificateTemplate certificateTemplate;

    @Enumerated(value = EnumType.STRING)
    private State state;
}
