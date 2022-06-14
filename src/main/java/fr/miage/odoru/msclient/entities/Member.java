package fr.miage.odoru.msclient.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "member")
public class Member {
    public enum Roles {MEMBRE, SECRETAIRE, ENSEIGNANT, PRESIDENT}
    public enum States {EN_ATTENTE, RETARD, VERIFICATION, TRAITE}

    @MongoId
    @NotNull
    @Field
    private String id;

    @Field
    @NotNull
    private String firstName;

    @Field
    private String lastName;

    @Field
    private String mail;

    @NotNull
    @Field
    private String username;

    @Field
    private String password;

    @Field
    private String address;

    @Field
    private List<Roles> roles;

    @Field
    private int level;

    @Field
    private States medicalCertificateState;

    @Field
    private States duesState;

}
