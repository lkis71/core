package spring.core.order;

import spring.core.discount.DiscountPolicy;
import spring.core.member.Member;
import spring.core.member.MemberRepository;

/**
 * 의존관계의 주입으로
 * OrderServiceImpl의 입장에서는 생성자를 통해 어떤 구현 객체가 들어올지 알 수 없음
 *  -> 운전자는 운전을 해야하는데 어떤 차를 운전할지 알 수 없음
 * OrderServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부에서 결정됨
 * OrderServiceImpl은 실행에만 집중함
 */
public class OrderServiceImpl implements OrderService {

    /**
     * 제어의 역전(IOC), 의존관계 주입(DI)
     * 인터페이스에만 의존하고 의존관계 주입은 AppConfig에서 처리함
     * 외부에서 의존관계를 주입한다 하여 제어의 역전, 의존관계 주입이라고 함
     * 런타임 시점에 외부에서 실제 구현 객체를 생성하고, 클라이언트에 전달해서 서버와 실제 의존관계가 연결 되는 것
     * 의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있음
     *  -> 정적인 클래스 의존관계란? memberRepository와 같은 변수에 new 생성자를 통해서 의존관계를 주입할 필요 없이(변경할 필요 없이)
     *      오직 외부에서 의존관계를 주입한다.(동적인 객체 인스턴스 의존관계를 쉽게 변경한다.)
     */
    // new 생성자가 없음, 인터페이스에만 의존적임 (DIP 의존관계 역전 원칙을 지킴)
    private final MemberRepository memberRepository;

    // DIP 문제 해결방법: 인터페이스에만 의존하도록 의존관계를 변경
    // 누군가 구현클래스를 대신 생성해주고 주입해야함
    // 관심사를 분리하자, 역할은 역할에만 구현은 구현만 하도록 책임을 확실히 분리하자.
    private final DiscountPolicy discountPolicy;
    
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
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
}


// 정적인 클래스 의존관계
// 의존관계를 애플리케이션을 실행하지 않고도 확인할 수 있다.

// 의존관계 주입
// 런타임 시점에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결 되는 것
// 의존관계 주입을 사용하면 정적인 클래스 의존관계(Service와 ServiceImpl 관계 등)를 변경하지 않고, 동적인 객체 인스텉스 의존관계를 쉽게 변경할 수 있다.
