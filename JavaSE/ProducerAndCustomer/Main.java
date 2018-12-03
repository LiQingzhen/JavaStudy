
/**
 * 
 * @author LIQINGZHEN
 * 多线程编程：生产者与消费者
 *	2018-11
 */

public class Main {

	public static Integer id = 1;

	public static void main(String[] args) {

		Basket basket = new Basket();
		Customer customer = new Customer(basket);
		Producer producer = new Producer(basket);
		
		// 两个生产者
		Thread threadP1 = new Thread(producer, "1");
		Thread threadP2 = new Thread(producer, "2");
		
		// 四个消费者
		Thread threadC1 = new Thread(customer, "A");
		Thread threadC2 = new Thread(customer, "B");
		Thread threadC3 = new Thread(customer, "C");
		Thread threadC4 = new Thread(customer, "D");
	
		threadP1.start();
		threadP2.start();
		try {
			Thread.sleep(100); 	// 提前生产
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		threadC1.start();
		threadC2.start();
		threadC3.start();
		threadC4.start();
		
	}

}
