package personal.method_interceptor;

public class SomeService implements ISomeService{

	@Override
	public void doFirst() {
		System.out.println("执行doFirst方法！");
	}

	@Override
	public String doSecond() {
		System.out.println("执行doSecond方法！");
		return "abc";
	}

}
