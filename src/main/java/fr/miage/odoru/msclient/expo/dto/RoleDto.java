package fr.miage.odoru.msclient.expo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.miage.odoru.msclient.entities.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto implements Serializable {
    private String username;
    private List<Member.Roles> roles;
}
