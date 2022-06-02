package fr.miage.odoru.msclient.repositories;

import fr.miage.odoru.msclient.entities.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//TODO: Passer sur mongodb
public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    List<Member> findByLevelAfterAnAndRolesIs(int level, Member.Roles role);
}
