import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame{
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;
	public static final int ROW = 50;
	public static final int COLUMN = 60;
	public static final int BLOCK = 10;
	public static int speed = 200;
	private static boolean stop = false;
	
	Snake snake = new Snake();
	Egg egg = new Egg();
	Wall wall = new Wall();
	
	// ˫����
	
	
	private class Wall{
		
		Rectangle rectangleUp = new Rectangle(0, BLOCK*4, WIDTH, BLOCK);
		Rectangle rectangleDown = new Rectangle(0, HEIGHT-BLOCK, WIDTH, BLOCK);
		Rectangle rectangleLeft = new Rectangle(0, 0, BLOCK, HEIGHT);
		Rectangle rectangleRight = new Rectangle(WIDTH-BLOCK, 0, BLOCK, HEIGHT);
		
		public void drawWall(Graphics g){
			
			Color color = g.getColor();
			
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, BLOCK, HEIGHT);
			g.fillRect(WIDTH-BLOCK, 0, BLOCK, HEIGHT);
			g.fillRect(0, BLOCK*4, WIDTH, BLOCK);
			g.fillRect(0, HEIGHT-BLOCK, WIDTH, BLOCK);	
			
			g.setColor(color);
		}
		
		// ��ײ��� -- ��ͷ��ǽ
		public void isCollided(Snake snake){
			
			Rectangle r = snake.getRectangle();
			if(		
					wall.rectangleUp.intersects(r) ||
					wall.rectangleDown.intersects(r) ||
					wall.rectangleLeft.intersects(r) ||
					wall.rectangleRight.intersects(r)
					){
				snake.isAlive = false;
			}
		}
	}
	
 	public Yard() {
		
		setTitle("Snake v1.0");
		setLocation(350, 100);
		setSize(WIDTH, HEIGHT);
		setBackground(Color.BLACK);
		setResizable(false);
		setVisible(true);
	}
	
	public void start(){
		
		// ���������˳�
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				setVisible(false);
				System.exit(0);
			}
		});
		
		// ���̷�����ı���ͷ����
		addKeyListener(new KeyMonitor());
		
		new Thread(new PaintThread()).start();
	}
	
	@Override
	public void paint(Graphics g){
		
		Color color = g.getColor();
		
		// ������
//		g.setColor(Color.WHITE);
//		for(int i=0; i<ROW; i++)
//			g.drawLine(0, i*BLOCK, WIDTH, i*BLOCK);
//		for(int i=0; i<COLUMN; i++)
//			g.drawLine(i*BLOCK, 0, i*BLOCK, HEIGHT);		

		snake.move();
		// ��Ժǽ,����,����
		g.setColor(Color.WHITE);
		g.drawString("score:" + (snake.getSize()*5-5), BLOCK*2, 4*BLOCK);
		wall.drawWall(g);
		snake.getHead().drawNode(g);
		
		Snake.Node node = snake.getHead().next;
		while(node != null){
			node.drawNode(g);
			node = node.next;
		}		
		wall.isCollided(snake);
		snake.isBiteItself();
		egg.drawEgg(g);
		snake.isEatEgg(egg);	
		
		if(!snake.isAlive){
			g.setFont(new Font("����", Font.BOLD, 45));
			g.drawString("GAME OVER!", 190, 251);
			stop = true;
		}

		g.setColor(color);
	}
	
	@Override
	public void update(Graphics g) {
		
		Image offScreenImage = this.createImage(WIDTH, HEIGHT);

		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}



	private class PaintThread implements Runnable{

		@Override
		public void run() {
			
			while(true){
				
				repaint();				
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(stop == true)	break;
			}
		}		
	}
	
	private class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_UP:
				if(snake.getHead().direction != Direction.DOWN)
					snake.getHead().direction = Direction.UP;
				break;
			case KeyEvent.VK_DOWN:
				if(snake.getHead().direction != Direction.UP)
					snake.getHead().direction = Direction.DOWN;
				break;
			case KeyEvent.VK_LEFT:
				if(snake.getHead().direction != Direction.RIGHT)
					snake.getHead().direction = Direction.LEFT;
				break;
			case KeyEvent.VK_RIGHT:
				if(snake.getHead().direction != Direction.LEFT)
					snake.getHead().direction = Direction.RIGHT;
			}
		}		
	}
}
