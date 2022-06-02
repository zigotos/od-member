package fr.miage.odoru.msclient.expo;

import fr.miage.odoru.msclient.dto.MemberDto;
import fr.miage.odoru.msclient.entities.Member;
import fr.miage.odoru.msclient.services.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("{username}")
    @ResponseBody
    public Optional<Member> getUser(@PathVariable String username){
        return memberService.getUser(username);
    }

    @GetMapping
    @ResponseBody
    public List<MemberDto> getUsers() {
        return memberService.getListUsers()
                .stream()
                .map(user -> modelMapper.map(user, MemberDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void postUser(@RequestBody MemberDto member){
        memberService.addOrUpdateUser(modelMapper.map(member, Member.class));
    }

    @PutMapping
    public void putUser(@RequestBody MemberDto member) {
        memberService.addOrUpdateUser(modelMapper.map(member, Member.class));
    }

    @GetMapping
    @ResponseBody
    public List<MemberDto> getUsersWithLevel(@RequestParam("level") int level) {
        return memberService.getUserWithLevel(level)
                .stream()
                .map(user -> modelMapper.map(user, MemberDto.class))
                .collect(Collectors.toList());
    }
}
