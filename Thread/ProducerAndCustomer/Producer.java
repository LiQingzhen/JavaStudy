
public class Producer implements Runnable {
	
	Basket basket = null;
	
	public Producer(Basket basket) {
		this.basket = basket;
	}
	
	@Override
	public void run() {
		
		while(basket.put()){		
			// 生产面包放入篮子
			// 任何一个生产者只要放满篮子就可以下班
		}		
	}	
}
