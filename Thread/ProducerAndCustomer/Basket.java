package com.producerAndCustomer;

public class Basket {
	
	private Integer top = 0;
	private Bread[] breads = new Bread[6];
	
	public synchronized boolean put(){

		this.notifyAll();	// ���������������������
		if(top >= breads.length){
			System.out.println("����������" + Thread.currentThread().getName() + "��������");
			return false;
		}
		try {
			Thread.sleep(100);	// ����һ�������Ҫһ����ʱ��
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Bread bread = new Bread(Main.id);
		breads[top] = bread;
		System.out.println("��" + breads[top].getId() + "�������������");
		top++;
		Main.id++;
		return true;
	}
	
	public synchronized boolean get(){
		
		this.notifyAll();;	// �����߼以�����������
		if(top < 1){
			try {
				System.out.println("����ۿ�,"+Thread.currentThread().getName()+"�ȴ�����");
				this.wait(2000);	// �������߳̽��������һ������wait״̬���߳̽��ò��������̵߳Ļ��ѣ���Զwait���������������һ���
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return false;
		}else{
			top--;
			System.out.println(Thread.currentThread().getName() + "ȡ����" + breads[top].getId() + "�����");
			return true;
		}	
	}
}
