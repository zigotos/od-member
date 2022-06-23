package fr.miage.odoru.msclient.services;
import fr.miage.odoru.msclient.clients.InvolvementClient;
import fr.miage.odoru.msclient.entities.Member;
import fr.miage.odoru.msclient.clients.dto.CourseDto;
import fr.miage.odoru.msclient.repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    InvolvementClient involvementClient;

    public List<Member> getListUsers(){
        List<Member> result = new ArrayList<>();
        memberRepository.findAll().iterator().forEachRemaining(result::add);
        return securityCheck(result);
    }

    public Member getUser(String username) throws ResponseStatusException {
        Optional<Member> member = memberRepository.findByUsername(username);
        if (member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return securityCheck(member.get());
    }

    public Member getUserWithId(String idMember) throws ResponseStatusException {
        Optional<Member> member = memberRepository.findById(new ObjectId(idMember));
        if (member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return securityCheck(member.get());
    }

    public List<Member> getUsersWithLevelGreater(int level){
        List<Member> result = new ArrayList<>();
        memberRepository.findByLevelGreaterThanEqualAndRolesContaining(level, Member.Roles.MEMBRE).iterator().forEachRemaining(result::add);
        return result;
    }

    public void addUser(Member member){
        Optional<Member> memberModify;
        memberModify = memberRepository.findByUsername(member.getUsername());
        if (!memberModify.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        memberRepository.save(member);
        log.info(member + "Ajouter");
    }

    public void updateUser(String username, Member member) throws ResponseStatusException {
        Optional<Member> memberModify;
        memberModify = memberRepository.findByUsername(username);
        if (memberModify.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (username != member.getUsername() && !memberRepository.findByUsername(member.getUsername()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        member.setId(memberModify.get().getId());
        memberRepository.save(member);
        log.info(member + "Modifier");
    }

    public List<CourseDto> getCourses(String username) throws ResponseStatusException {
        Optional<Member> member = memberRepository.findByUsername(username);
        if (member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return involvementClient.getCourses(member.get().getId().toString());
    }

    public List<CourseDto> getCompetitions(String username) throws ResponseStatusException {
        Optional<Member> member = memberRepository.findByUsername(username);
        if (member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return involvementClient.getCompetitions(member.get().getId().toString());
    }

    public List<CourseDto> getCoursesByPeriod(String username, String dateStart, String dateEnd) throws ResponseStatusException {
        Optional<Member> member = memberRepository.findByUsername(username);
        if (member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return involvementClient.getCoursesByPeriod(member.get().getId().toString(), dateStart, dateEnd);
    }

    public List<CourseDto> getCompetitionsByPeriod(String username, String dateStart, String dateEnd) throws ResponseStatusException {
        Optional<Member> member = memberRepository.findByUsername(username);
        if (member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return involvementClient.getCompetitionsByPeriod(member.get().getId().toString(), dateStart, dateEnd);
    }

    private List<Member> securityCheck(List<Member> members) {
        members.forEach(this::securityCheck);
        return members;
    }

    private Member securityCheck(Member member) {
        member.setPassword(null);
        return member;
    }
}
