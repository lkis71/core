package spring.core.singleton;

/**
 * 싱글톤패턴 단점
 * 의존관계상 클라이언트가 구체 클래스에 의존한다. -> DIP를 위반한다.
 * 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
 * 내부속성을 변경하거나 초기화 하기 어렵다.
 * private 생성자로 자식 클래스를 만들기 어렵다.
 * 유연성이 떨어진다.
 */
public class SingletonService {

    //1. static 영역에 객체를 딱 1개만 생성한다.
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    //3. 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
    private SingletonService() {
    }
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
