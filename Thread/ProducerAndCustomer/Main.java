package com.producerAndCustomer;

/**
 * 
 * @author LIQINGZHEN
 * ���̱߳�̣���������������
 *	2018-11
 */

public class Main {

	public static Integer id = 1;

	public static void main(String[] args) {

		Basket basket = new Basket();
		Customer customer = new Customer(basket);
		Producer producer = new Producer(basket);
		
		// ����������
		Thread threadP1 = new Thread(producer, "1");
		Thread threadP2 = new Thread(producer, "2");
		
		// �ĸ�������
		Thread threadC1 = new Thread(customer, "A");
		Thread threadC2 = new Thread(customer, "B");
		Thread threadC3 = new Thread(customer, "C");
		Thread threadC4 = new Thread(customer, "D");
	
		threadP1.start();
		threadP2.start();
		try {
			Thread.sleep(100); 	// ��ǰ����
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		threadC1.start();
		threadC2.start();
		threadC3.start();
		threadC4.start();
		
	}

}
