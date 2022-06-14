package fr.miage.odoru.msclient.expo;

import fr.miage.odoru.msclient.clients.dto.CourseDto;
import fr.miage.odoru.msclient.expo.dto.MemberDto;
import fr.miage.odoru.msclient.entities.Member;
import fr.miage.odoru.msclient.services.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("{username}")
    @ResponseBody
    public MemberDto getUser(@PathVariable String username) throws ResponseStatusException {
        return modelMapper.map(memberService.getUser(username), MemberDto.class);
    }

    @GetMapping("id/{idMember}")
    @ResponseBody
    public MemberDto getUserWithId(@PathVariable String idMember) throws ResponseStatusException {
        return modelMapper.map(memberService.getUserWithId(idMember), MemberDto.class);
    }

    @GetMapping("")
    @ResponseBody
    public List<MemberDto> getAllUsers() {
        return memberService.getListUsers()
                .stream()
                .map(user -> modelMapper.map(user, MemberDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void postUser(@RequestBody MemberDto member){
        memberService.addUser(modelMapper.map(member, Member.class));
    }

    @PutMapping("{username}")
    public void putUser(@PathVariable("username") String username, @RequestBody MemberDto member) {
        memberService.updateUser(username, modelMapper.map(member, Member.class));
    }

    // TODO Patch GET de base et rectifier gestion level
    @GetMapping("/levelGreater/{level}")
    @ResponseBody
    public List<MemberDto> getUsersWithLevelGreater(@PathVariable("level") int level) {
        return memberService.getUsersWithLevelGreater(level)
                .stream()
                .map(user -> modelMapper.map(user, MemberDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{username}/courses")
    public List<CourseDto> getCourses(@PathVariable("username") String username) {
        return memberService.getCourses(username);
    }

    @GetMapping("/{username}/competitions")
    public List<CourseDto> getCompetitions(@PathVariable("username") String username) {
        return memberService.getCompetitions(username);
    }
}
