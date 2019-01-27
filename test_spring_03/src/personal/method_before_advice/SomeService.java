package personal.method_before_advice;

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
