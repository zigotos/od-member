package fr.miage.odoru.msclient.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
    public enum Roles {MEMBRE, SECRETAIRE, ENSEIGNANT, PRESIDENT};
    public enum States {EN_ATTENTE, RETARD, VERIFICATION, TRAITE};
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String mail;
    private String username;
    private String password;
    private String adress;
    private List<Roles> roles;
    private int level;
    private States medicalCertificateState;
    private States duesState;

}
