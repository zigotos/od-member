package fr.miage.odoru.msclient.services;

import fr.miage.odoru.msclient.entities.Member;
import fr.miage.odoru.msclient.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public List<Member> getListUsers(){
        return null;
    }

    public Member getUser(String username){
        return null;
    }
    
    public void addUser(Member member){
    }
}
