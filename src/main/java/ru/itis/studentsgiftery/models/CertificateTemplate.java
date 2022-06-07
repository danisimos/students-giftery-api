package ru.itis.studentsgiftery.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"brand"})
public class CertificateTemplate {
    public enum State {
        ACTIVE, DELETED
    }

    public enum Type {
        PRODUCT, DISCOUNT, BONUS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand")
    private Brand brand;

    @OneToMany(mappedBy = "certificateTemplate")
    @JsonManagedReference
    private List<CertificateInstance> certificateInstances;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Type type;
}
