package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Long join(Member member) {
        checkDuplicatedName(member);
        repository.save(member);
        return member.getId();
    }

    private void checkDuplicatedName(Member member) {
        repository
            .findByName(member.getName())
            .ifPresent(m -> { throw new IllegalStateException("이미 존재하는 회원입니다."); });
    }

    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> findOne(Long id) {
        return repository.findById(id);
    }

}
