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
@Table(name = "users")
public class Account extends AbstractEntity {

    public enum State {
        NOT_CONFIRMED, CONFIRMED
    }

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    String email;

    //TODO: security...

    @OneToMany(mappedBy = "userAccount", fetch = FetchType.EAGER)
    private List<Certificate> certificateList;

    @Enumerated(value = EnumType.STRING)
    private State state;
}
