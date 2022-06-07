package ru.itis.studentsgiftery.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Brand {
    public enum State {
        ACTIVE, DELETED
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CertificateTemplate> certificateTemplates;

    @ManyToOne
    @JoinColumn(name = "organization")
    private Organization organization;

    @Enumerated(value = EnumType.STRING)
    private State state;
}
