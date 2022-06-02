package fr.miage.odoru.msclient.expo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.miage.odoru.msclient.entities.Member;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto implements Serializable {
    private final Long id;
    @NotNull
    private final String firstName;
    private final String lastName;
    private final String mail;
    @NotNull
    private final String username;
    private final String password;
    private final String adress;
    private final List<String> roles;
    private final int level;
    private final String medicalCertificateState;
    private final String duesState;
}
