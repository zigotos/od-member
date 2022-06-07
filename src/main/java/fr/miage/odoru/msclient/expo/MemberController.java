package fr.miage.odoru.msclient.expo;

import fr.miage.odoru.msclient.expo.dto.MemberDto;
import fr.miage.odoru.msclient.entities.Member;
import fr.miage.odoru.msclient.services.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Optional<MemberDto> getUser(@PathVariable String username) throws ResponseStatusException {
        Optional<MemberDto> member = memberService.getUser(username).map(user -> modelMapper.map(user, MemberDto.class));
        return member;
    }

    @RequestMapping(value="/members", method = RequestMethod.GET )
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

    @GetMapping("/")
    @ResponseBody
    public List<MemberDto> getUsersWithLevel(@RequestParam("level") int level) {
        return memberService.getUserWithLevel(level)
                .stream()
                .map(user -> modelMapper.map(user, MemberDto.class))
                .collect(Collectors.toList());
    }
}
