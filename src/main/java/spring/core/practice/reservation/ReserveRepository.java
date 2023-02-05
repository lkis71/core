package spring.core.practice.reservation;

import spring.core.practice.user.User;

public interface ReserveRepository {

    void save(User user);

    User fineByNm(String userNm);
}
