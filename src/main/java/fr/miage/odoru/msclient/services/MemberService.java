package fr.miage.odoru.msclient.services;

import fr.miage.odoru.msclient.entities.Member;
import fr.miage.odoru.msclient.repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public List<Member> getListUsers(){
        List<Member> result = new ArrayList<>();
        memberRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    public Optional<Member> getUser(String username){
        return memberRepository.findByUsername(username);
    }

    public Optional<List<Member>> getUserWithLevel(int level){
        return memberRepository.findByLevelIsGreaterThanEqualAndRolesIs(level, Member.Roles.MEMBRE.name());
    }

    public void addOrUpdateUser(Member member){
        log.info(member + "Ajouter");
        memberRepository.save(member);
    }
}
