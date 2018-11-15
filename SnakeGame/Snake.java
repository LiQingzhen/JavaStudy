import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Snake {
	
	private int size = 1;
	private Node head = new Node(520, 340, Direction.LEFT);	
	private Node tail = head;
	public static boolean isAlive = true;
	
	// Getter
	public int getSize() {
		return size;
	}

	public Node getHead() {
		return head;
	}

	public Node getTail() {
		return tail;
	}

	class Node{
		int node_x;	
		int node_y;
		Direction direction;
		Node last = null; 
		Node next = null;
		
		Node(int node_x, int node_y, Direction direction) {
			this.node_x = node_x;
			this.node_y = node_y;
			this.direction = direction;
		}
		
		void drawNode(Graphics g){
			
			Color color = g.getColor();
			
			g.setColor(Color.YELLOW);
			g.fillRect(node_x, node_y, Yard.BLOCK, Yard.BLOCK);
			
			g.setColor(color);
		}
	}
	
	
	public void move(){
		
		addHead();
		deleteTail();
	}
	
	public void isEatEgg(Egg egg){
		// 碰撞检测 -- 蛇和蛋
		if(this.getRectangle().intersects(egg.getRectangle())){
			// 长大一节
			addTail();
			size++;
			Yard.speed -= 5;
			
			// 蛋的位置重新出现
			egg.resetEgg();
		}	
	}
	
	public void isBiteItself(){
		
		for(Node node = head.next; node != null; node = node.next){
			if(head.node_x == node.node_x && head.node_y == node.node_y)
				isAlive = false;
		}
	}
	
	public Rectangle getRectangle(){
		// 用于碰撞检测
		 return new Rectangle(head.node_x, head.node_y, Yard.BLOCK, Yard.BLOCK);
	}
	
	private void addHead(){
		
		Node node = null;
		switch(head.direction){
		case UP:
			node = new Node(head.node_x, head.node_y-Yard.BLOCK, head.direction);
			break;
		case DOWN:
			node = new Node(head.node_x, head.node_y+Yard.BLOCK, head.direction);
			break;
		case LEFT:
			node = new Node(head.node_x-Yard.BLOCK, head.node_y, head.direction);
			break;
		case RIGHT:
			node = new Node(head.node_x+Yard.BLOCK, head.node_y, head.direction);
		}
		node.next = head;
		head.last = node;
		head = node;
	}
	
	private void addTail(){
		
		Node node = null;
		switch(tail.direction){
		case UP:
			node = new Node(tail.node_x, tail.node_y+Yard.BLOCK, tail.direction);
			break;
		case DOWN:
			node = new Node(tail.node_x, tail.node_y-Yard.BLOCK, tail.direction);
			break;
		case LEFT:
			node = new Node(tail.node_x+Yard.BLOCK, tail.node_y, tail.direction);
			break;
		case RIGHT:
			node = new Node(tail.node_x-Yard.BLOCK, tail.node_y, tail.direction);
		}
		tail.next = node;
		node.last = tail;
		tail = node;
	}
	
	private void deleteTail(){
		
		tail = tail.last;
		tail.next = null;
	}
}
