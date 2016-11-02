import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;



public class Snake {
	Node head = null;
	Node tail = null;
	int size = 0;
	
	
	Node n = new Node(10,20,Dir.L);
	
	public Snake() {
		this.head = n;
		this.tail = n;
		this.size = 1;
		new Thread(new SnakeThread()).start();
	}
	

	private void addToTail() {
		Node node = null;
		switch(tail.dir) {
			case L:
				node = new Node(tail.row,tail.col+1,tail.dir);
				break;
			
			case U:
				node = new Node(tail.row+1,tail.col,tail.dir);
				break;
			
			case R:
				node = new Node(tail.row,tail.col-1,tail.dir);
				break;
			
			case D:
				node = new Node(tail.row+1,tail.col,tail.dir);
				break;
		
		}
		
		tail.next = node;
		node.prev = tail;
		tail = node;
		
		size++;
	}
	
	public void addToHead() {
		Node node = null;
		switch(head.dir) {
		case L :
			node = new Node(head.row, head.col - 1, head.dir);
			break;
		case U :
			node = new Node(head.row - 1, head.col, head.dir);
			break;
		case R :
			node = new Node(head.row, head.col + 1, head.dir);
			break;
		case D :
			node = new Node(head.row + 1, head.col, head.dir);
			break;
		}
		node.next = head;
		head.prev = node;
		head = node;
		size ++;
	}
	
	private void move() {
		this.addToHead();
		this.deleteFromTail();
	}
	
	private void deleteFromTail() {
		if(size == 0) return;
		tail = tail.prev;
		tail.next = null;
		
	}


	public void draw(Graphics g) {
		if(size<=0) return;
		for(Node n = head; n != null; n = n.next) {
			n.draw(g);
		}
		
		
	}
	
	private class Node {
		
		int w = Yard.BLOCK_SIZE, h = Yard.BLOCK_SIZE;
		int row, col;
		Node next = null;
		Dir dir = Dir.L;
		Node prev = null;
		
		private Node(int row, int col, Dir dir) {
			this.dir = dir;
			this.row = row;
			this.col = col;
		}
		
		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillRect(col*Yard.BLOCK_SIZE, row*Yard.BLOCK_SIZE, w, h);
			g.setColor(c);
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_LEFT :
			head.dir = Dir.L;
			break;
		case KeyEvent.VK_UP :
			head.dir = Dir.U;
			break;
		case KeyEvent.VK_RIGHT :
			head.dir = Dir.R;
			break;
		case KeyEvent.VK_DOWN :
			head.dir = Dir.D;
			break;
		
		}
		
	}
	
	public Rectangle getRect() {
		return new Rectangle(head.col*Yard.BLOCK_SIZE, head.row*Yard.BLOCK_SIZE, head.w, head.h);
	}
	
	public boolean eat(Egg e) {
		if(this.getRect().intersects(e.getRect())) {
			e.reAppear();
			this.addToHead();
			return true;
		}
		return false;
	}
	
	private class SnakeThread implements Runnable {
		public void run() {
			while(true) {
				try {
					Thread.sleep(200);
					move();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
			}
		}
	}
	
	
}
