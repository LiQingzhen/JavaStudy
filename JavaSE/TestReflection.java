
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflection {
	
	public static void main(String[] args) {
		try {
			
			Class class1 = Class.forName("personal.lab01.T");	// 加载类
			Object object = class1.newInstance();	// 实例化
			
			Method[] methods = class1.getMethods();
			for(Method method : methods){
				
				System.out.println("[" + method.getName() + "]");
				if("setA".equals(method.getName())){
					method.invoke(object, 11, 17);
					//	获得参数类型
					for(Class paramType : method.getParameterTypes()){
						System.out.println(paramType.getName());
					}
				}
				if("getStr".equals(method.getName())){
					System.out.println("str = " + method.invoke(object));
					//	获得返回值类型
					System.out.println(method.getReturnType().getName());
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}

class T {
	static{
		System.out.println("static block");
	}
	
	{
		System.out.println("dynamic block");
	}
	
	int a = 0;
	String str = "ABCD";
	public T(){
		System.out.println("constructor");
	}
	public void setA(int x ,int y){
		this.a = x + y;
		System.out.println("a = " + this.a);
	}
	public String getStr(){
		return this.str;
	}
}
