package com.producerAndCustomer;

public class Producer implements Runnable {
	
	Basket basket = null;
	
	public Producer(Basket basket) {
		this.basket = basket;
	}
	
	@Override
	public void run() {
		
		while(basket.put()){		
			// ���������������
			// �κ�һ��������ֻҪ�������ӾͿ����°�
		}		
	}	
}
