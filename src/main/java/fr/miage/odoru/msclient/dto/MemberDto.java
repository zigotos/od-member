package fr.miage.odoru.msclient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto {
    private String username;
    private String firstName;
    private String lastName;
    private String mail;
    private String adress;
    private String roles;
    private int level;
    private String medicalCertificateState;
    private String duesState;

}
