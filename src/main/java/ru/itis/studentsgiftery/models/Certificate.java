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
public class Certificate {
    public enum State {
        ACTIVE, DELETED
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer value;

    @ManyToOne()
    @JoinColumn(name = "brand_id")
    private Long brandId;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private Account userAccount;

    @Enumerated(value = EnumType.STRING)
    private State state;
}
