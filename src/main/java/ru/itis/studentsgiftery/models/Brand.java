package ru.itis.studentsgiftery.models;

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

    private String description;

    @Column(name = "avatar_id")
    private String avatarLink;

    @OneToMany(mappedBy = "brand")
    private List<Certificate> certificateList;

    @Enumerated(value = EnumType.STRING)
    private State state;
}
