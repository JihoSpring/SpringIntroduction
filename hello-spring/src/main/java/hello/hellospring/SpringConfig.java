package hello.hellospring;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemeberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {

    // private final DataSource dataSource;
    // private final EntityManager entityManager;
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // public SpringConfig(DataSource dataSource, EntityManager entityManager) {
    //     this.dataSource = dataSource;
    //     this.entityManager = entityManager;
    // }

    // @Bean
    // public MemberRepository memberRepository() {
    //     // return new MemoryMemberRepository();
    //     // return new JdbcMemberRepository(dataSource);
    //     // return new JdbcTemplateMemberRepository(dataSource);
    //     return new JpaMemeberRepository(entityManager);
    // }

    @Bean
    public MemberService memberService() {
        // return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

}
