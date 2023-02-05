package spring.core.practice.reservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.PracAppConfig;
import spring.core.practice.user.User;

import static org.assertj.core.api.Assertions.assertThat;

class ReserveServiceImplTest {

    @Test
    void reserve() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(PracAppConfig.class);
        ReserveServiceImpl bean = ac.getBean(ReserveServiceImpl.class);
        OnlineReserveRepository bean1 = ac.getBean(OnlineReserveRepository.class);

        User user = new User("홍길동", "학생");
        bean.reserve(user);
        User user1 = bean1.fineByNm(user.getUserNm());

        assertThat(user).isEqualTo(user1);
    }
}