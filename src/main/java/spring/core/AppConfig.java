package spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.RateDiscountPolicy;
import spring.core.member.MemberRepository;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepository;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

/**
 * AppConfig 란
 * 애플리케이션 전체를 설정하고 구성하는 역할을 한다.
 * 생성한 객체 인스턴스의 참조(구현체)를 생성자를 통해 주입(Injection)한다.
 * 구현체를 직접 선택하여 주입한다.
 *
 * 등장으로 크게 사용영역(Service)과 구성영역(AppConfig)으로 분리되고 있다.
 * 구현체가 변경되어도 구성영역에서만 변경하면 된다.
 * 사용영역에서는 어떤 코드도 변경할 필요가 없다.
 *
 * 구현체(ServiceImpl)에서는 인터페이스에만 의존하고 있다.
 * 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection) 우리말로 의존관계 주입 또는 의존성 주입이라 한다.
 *
 * AppConfig의 등장으로 OCP, DIP를 해결함
 *  -> 구현 객체를 대신 주입하고 있어 DIP 의존관계역전원칙을 지킴
 *  -> 사용영역과 구성영역을 분리하여 OCP 개방폐쇄원칙을 지킴 -> 구성영역에서만 변경하고 사용영역에서는 변경을 하지 않아도 됨
 */
@Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    //

    /**
     * Bean이란?
     * 애플리케이션 실행 시 스프링 컨테이너에 등록됨
     */
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        // 생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        // 생성자 주입
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("AppConfig.discountPolicy");
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

/**
 * 스프링 컨테이너 생성과정
 *
 * 	스프링 컨테이너 생성(AppConfig.class) -> 스프링 컨테이너	 -> 스프링 빈 저장소
 */