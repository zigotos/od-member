package fr.miage.odoru.msclient.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
    public enum Roles {MEMBRE, SECRETAIRE, ENSEIGNANT, PRESIDENT}
    public enum States {EN_ATTENTE, RETARD, VERIFICATION, TRAITE}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column
    @NotNull
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String mail;

    @NotNull
    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String adress;

    @Column
    @ElementCollection(targetClass = String.class)
    private List<Roles> roles;

    @Column
    private int level;

    @Column
    private States medicalCertificateState;

    @Column
    private States duesState;

}
