package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        // given
        Member member = new Member();
        member.setName("Spring");
        // when
        repository.save(member);
        // then
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    void findByName() {
        // given
        Member alpha = new Member();
        alpha.setName("alpha");
        Member beta = new Member();
        beta.setName("beta");
        // when
        repository.save(alpha);
        repository.save(beta);
        // then
        assertThat(repository.findByName("alpha").get()).isEqualTo(alpha);
        assertThat(repository.findByName("beta").get()).isEqualTo(beta);
        assertThat(repository.findByName("alpha").get()).isNotEqualTo(beta);
    }

    @Test
    void finalAll() {
        // given
        Member alpha = new Member();
        alpha.setName("alpha");
        Member beta = new Member();
        beta.setName("beta");
        // when
        repository.save(alpha);
        repository.save(beta);
        // then
        assertThat(repository.finalAll().size()).isEqualTo(2);
    }

}
