package com.producerAndCustomer;

public class Customer implements Runnable {
	
	private Basket basket = null;
	public Customer(Basket basket) {
		this.basket = basket;
	}
	
	@Override
	public void run() {
		
		Integer n = 5;	// ÿ��������Ҫ����������
		Integer count = 0;
		while(true){
			if(n == 0)	break;
			if(basket.get()){
				n--;
			}else{
				count++;
				if(count > 2)	break;	// �ȴ���������2
			}
		}
		if(n == 0)
			System.out.println(Thread.currentThread().getName() + "�Ա��ˣ����뿪��");
		else
			System.out.println(Thread.currentThread().getName() + "�����ˣ����뿪��");
	}
}
