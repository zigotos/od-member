package fr.miage.odoru.msclient.expo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.miage.odoru.msclient.entities.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String mail;
    private String username;
    private String password;
    private String address;
    private List<String> roles;
    private int level = -1;
    private String medicalCertificateState;
    private String duesState;
}
