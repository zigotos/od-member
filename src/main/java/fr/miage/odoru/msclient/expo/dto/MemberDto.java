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
    private final Long id = null;
    @NotNull
    private final String firstName = null;
    private final String lastName = null;
    private final String mail = null;
    @NotNull
    private final String username = null;
    private final String password = null;
    private final String adress = null;
    private final List<String> roles = null;
    private final int level = -1;
    private final String medicalCertificateState = null;
    private final String duesState = null;
}
