
public class Customer implements Runnable {
	
	private Basket basket = null;
	public Customer(Basket basket) {
		this.basket = basket;
	}
	
	@Override
	public void run() {
		
		Integer n = 5;	// 每个消费者要消费五个面包
		Integer count = 0;
		while(true){
			if(n == 0)	break;
			if(basket.get()){
				n--;
			}else{
				count++;
				if(count > 2)	break;	// 等待次数超过2
			}
		}
		if(n == 0)
			System.out.println(Thread.currentThread().getName() + "吃饱了，【离开】");
		else
			System.out.println(Thread.currentThread().getName() + "生气了，【离开】");
	}
}
