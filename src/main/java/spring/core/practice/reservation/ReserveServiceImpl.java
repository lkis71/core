package spring.core.practice.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import spring.core.practice.user.User;

@Component
public class ReserveServiceImpl implements ReserveService{

    private final ReserveRepository reserveRepository;

    public ReserveServiceImpl(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    @Override
    public void reserve(User user) {
        reserveRepository.save(user);
        User user1 = reserveRepository.fineByNm(user.getUserNm());
        System.out.println("user1 = " + user1);
    }
}
