package hello.hellospring_component_scan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring_component_scan.domain.Member;
import hello.hellospring_component_scan.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository repository;

    @Autowired
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
        return repository.finalAll();
    }

    public Optional<Member> findOne(Long id) {
        return repository.findById(id);
    }

}
