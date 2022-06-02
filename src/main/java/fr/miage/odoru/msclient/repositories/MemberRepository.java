package fr.miage.odoru.msclient.repositories;

import fr.miage.odoru.msclient.entities.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//TODO: Passer sur mongodb
public interface MemberRepository extends CrudRepository<Member, Long> {
    Member findByUsername(String username);
}
