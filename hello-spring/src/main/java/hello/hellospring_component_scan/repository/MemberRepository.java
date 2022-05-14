package hello.hellospring_component_scan.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring_component_scan.domain.Member;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> finalAll();
}
