package spring.core.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import spring.core.annotation.MainDiscountPolicy;
import spring.core.discount.DiscountPolicy;
import spring.core.member.Member;
import spring.core.member.MemberRepository;

@Component
//@RequiredArgsConstructor // final이 붙은 생성자를 자동 생성
public class OrderServiceImpl implements OrderService {

    /**
     * 제어의 역전(IOC), 의존관계 주입(DI)
     * 1. new 생성자가 존재하지 않음, 오직 인터페이스에만 의존하여 외부에서 어떤 구현 객체가 들어오는지 알 수 없음. (DIP 의존관계 역전 원칙을 지킴)
     * 2. 외부에서 구현클래스를 대신 생성해주고 주입함. (스프링부트에서 @Configuration이 붙은 클래스에서 자동 빈 주입)
     * 3. 관심사 분리, 역할은 역할에만 구현은 구현만 하도록 책임을 확실히 분리함.
     * 4. 런타임 시점에 외부에서 실제 구현 객체를 생성하고, 클라이언트에 전달해서 서버와 실제 의존관계가 연결.
     * 5. 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있음.
     *      -> 정적인 클래스 의존관계란? 인터페이스에만 의존하고, 동적인 객체 인스턴스 의존관계를 외부에서 쉽게 변경하면서 주입할 수 있다.
     * 6. 의존관계를 애플리케이션 실행하지 않고도 확인 가능.
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // 단일책임원칙 (SRP)
        // 파라미터만 넘겨주면 할인된 금액을 출력해줌
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}