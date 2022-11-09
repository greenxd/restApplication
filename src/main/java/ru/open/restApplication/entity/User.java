package ru.open.restApplication.entity;

import liquibase.pro.packaged.S;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "users")

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Length(min = 2)
    @NotNull
    private String name;

    @Column
    private String avatar;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Status isOnline;

    @Column(unique = true)
    @Email
    @NotNull
    private String email;

    @Column(name = "status_timestamp")
    private Timestamp statusTimestamp;

    /**
     * TODO так можно хранить Enum?
     */
    public enum Status {
        ONLINE("Online"),
        OFFLINE("Offline");

        final private String title;

        Status(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Status{" + "title='" + title + '\'' + '}';
        }
    }
}
