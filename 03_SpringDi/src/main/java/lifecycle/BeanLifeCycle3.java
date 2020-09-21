package lifecycle;

public class BeanLifeCycle3 {
	public void init() {
		System.out.println("빈 초기화 실행");
	}
	
	public void destroy() {
		System.out.println("빈 소멸 실행");
	}
}
