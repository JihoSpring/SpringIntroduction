package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberServiceTest {

    MemberService service;
    MemoryMemberRepository repository;

    @BeforeEach
    void beforeEach() {
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("홍길동");
        // when
        Long id = service.join(member);
        // then
        service.findOne(id).ifPresentOrElse(
            m -> assertThat(m.getName()).isEqualTo("홍길동"),
            () -> fail("Repository에 member가 존재하지 않음")
        );
    }

    @Test
    void 중복회원_예외() {
        // given
        Member alpha = new Member();
        alpha.setName("홍길동");
        Member beta = new Member();
        beta.setName("홍길동");

        // when
        service.join(alpha);
        // try {
        //     service.join(beta);
        //     fail("중복 회원");
        // } catch (IllegalStateException e) {
        //     assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // }

        // then
        Exception e = assertThrows( IllegalStateException.class, () -> { service.join(beta); });
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void testFindOne() {

    }

    @Test
    void testJoin() {

    }

}
