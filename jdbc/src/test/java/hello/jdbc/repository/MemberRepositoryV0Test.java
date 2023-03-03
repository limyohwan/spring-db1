package hello.jdbc.repository;

import java.util.NoSuchElementException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberRepositoryV0Test {
    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws Exception{
        Member member = new Member("memberV100",10000);
        repository.save(member);

        Member findMember = repository.findById(member.getMemberId());
        log.info("findmember = {}", findMember);
        Assertions.assertThat(findMember).isEqualTo(member);

        repository.update(member.getMemberId(), 20000);
        Member updateMember = repository.findById(member.getMemberId());
        Assertions.assertThat(updateMember.getMoney()).isEqualTo(20000);

        repository.delete(member.getMemberId());
        Assertions.assertThatThrownBy(() -> repository.findById(member.getMemberId()))
            .isInstanceOf(NoSuchElementException.class);
    }
}
