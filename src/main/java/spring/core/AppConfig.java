package spring.core;

import spring.core.discount.DiscountPolicy;
import spring.core.discount.RateDiscountPolicy;
import spring.core.member.MemberRepository;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepository;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

// 1.
// 애플리케이션 전체를 설정하고 구성한다.
// 구현 객체를 생성한다.
// 생성한 객체 인스턴스의 참조를 생성자를 통해 주입(Injection)한다.
public class AppConfig {

    // 2.
    // 역할과 구현 클래스가 한눈에 들어온다.
    // 애플리케이션 전체 구성이 어떻게 되어있는지 파악하기 쉬움
    public MemberService memberService() {
        // 생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        // 생성자 주입
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

// 중요!!
// 인터페이스의 구현체를 생성하여 생성자 주입함.
// 구현체에서는 인터페이스에만 의존하고, 인터페이스의 의존관계를 AppConfig에서 구현체에 생성자를 주입시킴
// 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection) 우리말로 의존관계 주입이라 한다.
// 구현체를 AppConfig가 직접 선택함

// AppConfig의 등장으로 크게 사용영역(Service)과 구성 영역(AppConfig)으로 분리됨
// 구현체가 변경되어도 구성 영역의 주입만 바꾸면 됨, 사용영역의 어떤 코드도 변경할 필요 없음
