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
public class Organization {
    public enum State {
        ACTIVE, DELETED
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "organization")
    private List<Brand> brands;

    @OneToMany(mappedBy = "organization")
    private List<Account> accounts;

    @OneToMany(mappedBy = "organization")
    private List<OrganizationJoinRequest> joinRequests;

    @Enumerated(value = EnumType.STRING)
    private State state;
}
