package spring.core.practice.reservation;

import org.springframework.stereotype.Component;
import spring.core.practice.user.User;

import java.util.HashMap;
import java.util.Map;

@Component
public class OnlineReserveRepository implements ReserveRepository{

    private static Map<String, User> store = new HashMap<>();

    @Override
    public void save(User user) {
        store.put(user.getUserNm(), user);
    }

    @Override
    public User fineByNm(String userNm) {
        User user = store.get(userNm);
        return user;
    }
}
