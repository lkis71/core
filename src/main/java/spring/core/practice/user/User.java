package spring.core.practice.user;

public class User {

    private String userNm;
    private String userTy;

    public User(String userNm, String userTy) {
        this.userNm = userNm;
        this.userTy = userTy;
    }

    public String getUserNm() {
        return userNm;
    }

    public String getUserTy() {
        return userTy;
    }
}
