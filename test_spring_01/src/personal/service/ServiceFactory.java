package personal.service;

public class ServiceFactory {
	public SomeService getSomeService(){
		return new SomeServiceImpl();
	}
}
