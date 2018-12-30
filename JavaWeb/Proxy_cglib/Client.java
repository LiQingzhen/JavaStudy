public class Client {

	public static void main(String[] args) {
		Subject subject = new SubjectImp();
		Subject subjectProxy = (Subject) new CGLibFactory(subject).createProxy();
		
		// 这是系统返回的代理类名
		System.out.println(subjectProxy.getClass().getName() + "\n");
		
		subjectProxy.rent();
		System.out.println();
		subjectProxy.hello("Tim");
	}

}
