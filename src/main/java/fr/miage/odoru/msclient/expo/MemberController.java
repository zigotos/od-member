package fr.miage.odoru.msclient.expo;

import fr.miage.odoru.msclient.entities.Member;
import fr.miage.odoru.msclient.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class MemberController {
    @Autowired
    MemberService memberService;
    @GetMapping("{username}")
    public Member getUser(@PathVariable String username){
        return memberService.getUser(username);
    }

    @GetMapping
    public List<Member> getUsers() {
        return memberService.getListUsers();
    }

    @PostMapping
    public void postUser(Member member){
        memberService.addUser(member);
    }
}
