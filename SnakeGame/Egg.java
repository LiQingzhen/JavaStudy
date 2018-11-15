import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	
	private int egg_x;
	private int egg_y;
	private Random random = new Random();

	public void resetEgg() {
		// ���õ���λ��
		this.egg_x = random.nextInt(Yard.COLUMN-4) * Yard.BLOCK + 2*Yard.BLOCK;
		this.egg_y = random.nextInt(Yard.ROW-7) * Yard.BLOCK + 5*Yard.BLOCK;
	}

	public Egg() {
		// �������
		this.egg_x = random.nextInt(Yard.COLUMN-4) * Yard.BLOCK + 2*Yard.BLOCK;
		this.egg_y = random.nextInt(Yard.ROW-7) * Yard.BLOCK + 5*Yard.BLOCK;
	}
	
	public Rectangle getRectangle(){	
		// ������ײ���
		return new Rectangle(egg_x, egg_y, Yard.BLOCK, Yard.BLOCK);	
	}
	
	public void drawEgg(Graphics g){

		Color color = g.getColor();
		
		g.setColor(Color.WHITE);
		g.fillOval(egg_x, egg_y, Yard.BLOCK, Yard.BLOCK);
		
		g.setColor(color);
	}
}
