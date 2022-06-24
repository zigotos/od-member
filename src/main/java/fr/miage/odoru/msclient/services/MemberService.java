package fr.miage.odoru.msclient.services;
import fr.miage.odoru.msclient.clients.InvolvementClient;
import fr.miage.odoru.msclient.entities.Member;
import fr.miage.odoru.msclient.clients.dto.CourseDto;
import fr.miage.odoru.msclient.expo.dto.RoleDto;
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
        Member memberInDB;
        memberModify = memberRepository.findByUsername(username);

        if (memberModify.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (username != member.getUsername() && !memberRepository.findByUsername(member.getUsername()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        memberInDB = memberModify.get();
        member.setId(memberInDB.getId());
        member = putCheck(member, memberInDB);
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

    public void securityCheckRole(RoleDto role) {
        Optional<Member> member;
        boolean presence;

        member = memberRepository.findByUsername(role.getUsername());
        if (member.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        for (RoleDto.Roles aRole : role.getRoles()) {
            if (member.get().getRoles().contains(aRole)) {
                
            }
        }
    }

    private List<Member> securityCheck(List<Member> members) {
        members.forEach(this::securityCheck);
        return members;
    }

    private Member securityCheck(Member member) {
        member.setPassword(null);
        return member;
    }

    private Member putCheck(Member member, Member memberInDB) {
        member = putRoleCheck(member, memberInDB);
        member = putAddressCheck(member, memberInDB);
        member = putUsernameCheck(member, memberInDB);
        member = putDuesStateCheck(member, memberInDB);
        member = putLevelCheck(member, memberInDB);
        member = putFirstNameCheck(member, memberInDB);
        member = putLastNameCheck(member, memberInDB);
        member = putMailCheck(member, memberInDB);
        member = putMedicalCertificatesStateCheck(member, memberInDB);
        member = putPasswordCheck(member, memberInDB);
        return member;
    }

    private Member putRoleCheck(Member member, Member memberInDB) {
        if (member.getRoles() == null) {
            member.setRoles(memberInDB.getRoles());
        }
        return member;
    }

    private Member putAddressCheck(Member member, Member memberInDB) {
        if (member.getAddress() == null) {
            member.setAddress(memberInDB.getAddress());
        }
        return member;
    }

    private Member putUsernameCheck(Member member, Member memberInDB) {
        if (member.getUsername() == null) {
            member.setUsername(memberInDB.getUsername());
        }
        return member;
    }

    private Member putDuesStateCheck(Member member, Member memberInDB) {
        if (member.getDuesState() == null) {
            member.setDuesState(memberInDB.getDuesState());
        }
        return member;
    }

    private Member putFirstNameCheck(Member member, Member memberInDB) {
        if (member.getFirstName() == null) {
            member.setFirstName(memberInDB.getFirstName());
        }
        return member;
    }

    private Member putLastNameCheck(Member member, Member memberInDB) {
        if (member.getLastName() == null) {
            member.setLastName(memberInDB.getLastName());
        }
        return member;
    }

    private Member putLevelCheck(Member member, Member memberInDB) {
        if (member.getLevel() == null) {
            member.setLevel(memberInDB.getLevel());
        }
        return member;
    }

    private Member putMailCheck(Member member, Member memberInDB) {
        if (member.getMail() == null) {
            member.setMail(memberInDB.getMail());
        }
        return member;
    }

    private Member putMedicalCertificatesStateCheck(Member member, Member memberInDB) {
        if (member.getMedicalCertificateState() == null) {
            member.setMedicalCertificateState(memberInDB.getMedicalCertificateState());
        }
        return member;
    }

    private Member putPasswordCheck(Member member, Member memberInDB) {
        if (member.getPassword() == null) {
            member.setPassword(memberInDB.getPassword());
        }
        return member;
    }
}
