package fr.miage.odoru.msclient.expo;

import fr.miage.odoru.msclient.clients.dto.CourseDto;
import fr.miage.odoru.msclient.expo.dto.MemberDto;
import fr.miage.odoru.msclient.entities.Member;
import fr.miage.odoru.msclient.services.MemberService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@OpenAPIDefinition(info =
    @Info(title = "Membres", description = "Gestion des membres de Odoru", version = "1.0"))
@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    ModelMapper modelMapper = new ModelMapper();

    @Operation(summary = "Récuperer le membre grace a son nom d'utilisateur",
            responses = {@ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Membre inconnu", responseCode = "404", content = @Content())})
    @GetMapping("{username}")
    @ResponseBody
    public MemberDto getUser(@PathVariable String username) throws ResponseStatusException {
        return modelMapper.map(memberService.getUser(username), MemberDto.class);
    }

    @Operation(summary = "Récuperer le membre grace a son identifiant",
            responses = {@ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Membre inconnu", responseCode = "404", content = @Content())})
    @GetMapping("id/{idMember}")
    @ResponseBody
    public MemberDto getUserWithId(@PathVariable String idMember) throws ResponseStatusException {
        return modelMapper.map(memberService.getUserWithId(idMember), MemberDto.class);
    }

    @Operation(summary = "Récuperer l'ensemble des membres",
            responses = {@ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Aucuns membres existants", responseCode = "404", content = @Content())})
    @GetMapping("")
    @ResponseBody
    public List<MemberDto> getAllUsers() {
        return memberService.getListUsers()
                .stream()
                .map(user -> modelMapper.map(user, MemberDto.class))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Ajouter un membre",
            responses = {@ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Utilisateur deja présent", responseCode = "401", content = @Content())})
    @PostMapping
    public void postUser(@RequestBody MemberDto member){
        memberService.addUser(modelMapper.map(member, Member.class));
    }

    @Operation(summary = "Modifier un membre",
            responses = {@ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Nom d'utilisateur deja pris", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Membre inexistant", responseCode = "404", content = @Content())})
    @PutMapping("{username}")
    public void putUser(@PathVariable("username") String username, @RequestBody MemberDto member) {
        memberService.updateUser(username, modelMapper.map(member, Member.class));
    }

    @Operation(summary = "Recupérer les utilisateur d'un certain niveau",
            responses = {@ApiResponse(description = "OK", responseCode = "200")})
    @GetMapping("/levelGreater/{level}")
    @ResponseBody
    public List<MemberDto> getUsersWithLevelGreater(@PathVariable("level") int level) {
        return memberService.getUsersWithLevelGreater(level)
                .stream()
                .map(user -> modelMapper.map(user, MemberDto.class))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Récuperer les cours d'un membre avec son nom d'utilisateur",
            responses = {@ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Membre inconnu", responseCode = "404", content = @Content())})
    @GetMapping("/{username}/courses")
    public List<CourseDto> getCourses(@PathVariable("username") String username) {
        return memberService.getCourses(username);
    }

    @Operation(summary = "Récuperer les compétitions d'un membre avec son nom d'utilisateur",
            responses = {@ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Membre inconnu", responseCode = "404", content = @Content())})
    @GetMapping("/{username}/competitions")
    public List<CourseDto> getCompetitions(@PathVariable("username") String username) {
        return memberService.getCompetitions(username);
    }
}
