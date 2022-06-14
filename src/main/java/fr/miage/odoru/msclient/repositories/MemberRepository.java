package fr.miage.odoru.msclient.repositories;

import fr.miage.odoru.msclient.entities.Member;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, ObjectId> {
    Optional<Member> findByUsername(String username);

    List<Member> findAllByLevel(int level);
}
