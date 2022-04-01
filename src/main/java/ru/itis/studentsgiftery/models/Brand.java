package ru.itis.studentsgiftery.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "brands")
public class Brand extends AbstractEntity {

    @Column(name = "brand_name")
    private String brandName;

    private String description;

    @Column(name = "avatar_id")
    private Integer avatarId;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    private List<Certificate> certificateList;

}
