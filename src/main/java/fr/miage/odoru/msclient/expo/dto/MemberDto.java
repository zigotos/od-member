package fr.miage.odoru.msclient.expo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto implements Serializable {
    @Schema(description = "Identifiant unique des membres", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
    @Schema(description = "Prenom")
    private String firstName;
    @Schema(description = "Nom de famille")
    private String lastName;
    @Schema(description = "Email")
    private String mail;
    @Schema(description = "Nom d'utilisateur", accessMode = Schema.AccessMode.READ_WRITE)
    private String username;
    @Schema(description = "Mot de passe")
    private String password;
    @Schema(description = "Adresse")
    private String address;
    @Schema(description = "Roles de l'utilisateur", allowableValues = {"MEMBRE", "SECRETAIRE", "ENSEIGNANT", "PRESIDENT"})
    private List<String> roles;
    @Schema(description = "Niveau du membre")
    private Integer level = -1;
    @Schema(description = "Etat du certificat médical", allowableValues = {"EN_ATTENTE", "RETARD", "VERIFICATION", "TRAITE"})
    private String medicalCertificateState;
    @Schema(description = "Etat du payement", allowableValues = {"EN_ATTENTE", "RETARD", "VERIFICATION", "TRAITE"})
    private String duesState;
}
