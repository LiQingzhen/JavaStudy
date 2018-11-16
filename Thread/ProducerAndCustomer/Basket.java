
public class Basket {
	
	private Integer top = 0;
	private Bread[] breads = new Bread[6];
	
	public synchronized boolean put(){

		this.notifyAll();	// 生产者提醒消费者买面包
		if(top >= breads.length){
			System.out.println("篮子已满，" + Thread.currentThread().getName() + "生产结束");
			return false;
		}
		try {
			Thread.sleep(100);	// 生产一个面包需要一定的时间
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Bread bread = new Bread(Main.id);
		breads[top] = bread;
		System.out.println("第" + breads[top].getId() + "个面包放入篮子");
		top++;
		Main.id++;
		return true;
	}
	
	public synchronized boolean get(){
		
		this.notifyAll();;	// 消费者间互相提醒买面包
		if(top < 1){
			try {
				System.out.println("面包售空,"+Thread.currentThread().getName()+"等待……");
				this.wait(2000);	// 若其他线程结束，最后一个处于wait状态的线程将得不到其他线程的唤醒，永远wait；所以设置了自我唤醒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return false;
		}else{
			top--;
			System.out.println(Thread.currentThread().getName() + "取出第" + breads[top].getId() + "个面包");
			return true;
		}	
	}
}
