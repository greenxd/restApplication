package ru.open.restApplication.entity;

import liquibase.pro.packaged.S;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column()
    @Length(min = 2)
    @NotNull
    private String name;

    @Column()
    private String avatar;

    @Column()
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

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Status getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Status status) {
        isOnline = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getStatusTimestamp() {
        return statusTimestamp;
    }

    public void setStatusTimestamp(Timestamp statusTimestamp) {
        this.statusTimestamp = statusTimestamp;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isOnline=" + isOnline +
                ", email='" + email + '\'' +
                ", statusTimestamp=" + statusTimestamp +
                '}';
    }

    public String getUploadDir() {
        return "uploads/avatar";
    }
}
