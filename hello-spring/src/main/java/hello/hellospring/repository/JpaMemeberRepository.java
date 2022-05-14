package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import hello.hellospring.domain.Member;

public class JpaMemeberRepository implements MemberRepository {

    private final EntityManager entityManager;

    public JpaMemeberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Member.class, id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return entityManager
            .createQuery("SELECT m FROM Member as m WHERE m.name = :name", Member.class)
            .setParameter("name", name)
            .getResultList()
            .stream()
            .findAny();
    }

    @Override
    public List<Member> findAll() {
        return entityManager
            .createQuery("SELECT m FROM Member as m", Member.class)
            .getResultList();
    }
    
}
