package spring.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


/**
 * 싱글톤 방식의 주의점
 * 특정 클라이언트에 의존적인 필드가 있으면 안된다.
 * 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
 * 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, 쓰레드 로컬 등을 사용해야 한다.
 * 스프링 빈은 항상 무상태로 설계해야 한다.
 */
public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB: B사용자가 20000원 주문
        int userBPrice = statefulService1.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
