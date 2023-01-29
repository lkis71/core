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

    // new 생성자가 없음, 인터페이스에만 의존적임
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
