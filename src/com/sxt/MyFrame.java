package com.sxt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.GlyphVector;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import javazoom.jl.decoder.JavaLayerException;

public class MyFrame extends JFrame implements KeyListener, Runnable{
	//���ڴ洢���еı���
	private List<BackGround> allBg = new ArrayList<>();
	//���ڴ洢��ǰ�ı���
	private BackGround nowBg = new BackGround();
	//����˫����
	private Image offScreenImage = null;
	//����¶���
	private Mario mario = new Mario();
	//ʵ������µ��˶�,�̶߳���
	private Thread thread = new Thread(this);
	
	public MyFrame() {
		//���ô��ڵĴ�СΪ800 * 600
		this.setSize(800, 600);
		//���ô��ھ�����ʾ
		this.setLocationRelativeTo(null);
		//���ô��ڵĿɼ���
		this.setVisible(true);
		//���õ�������ϵĹرռ�����������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô��ڴ�С���ɱ�
		this.setResizable(false);
		//�򴰿ڶ�����Ӽ��̼�����
		this.addKeyListener(this);
		//���ô�������
		this.setTitle("ɨ������");	
		
		StaticValue.init();
		// ��ʼ�������0
		mario = new Mario(10, 385);
		
		//����ȫ���ĳ���
		for (int i = 1; i <= 3; i++) {
			allBg.add(new BackGround(i, i == 3 ? true : false));
		}
		//����һ����������Ϊ��ǰ����
		nowBg = allBg.get(0);
		mario.setBackGround(nowBg);
		//����ͼ��
		repaint();
		thread.start();
		try {
			new Music();
		} catch (FileNotFoundException | JavaLayerException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();
	}


	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// 37 <- 38 �� 39 -> 40 ��
		//�����ƶ�

//		System.out.println("a:" + mario.getxAccSpeed() + ",v:" + mario.getxSpeed());
		if(e.getKeyCode() == 39){
			mario.rightMove();
		}
		//�����ƶ�
		if(e.getKeyCode() == 37) {
			mario.leftMove();
		}
		//��Ծ
		if(e.getKeyCode() == 38) {
			mario.jump();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		System.out.println(e.getKeyCode());
//		System.out.println("a:" + mario.getxAccSpeed() + ",v:" + mario.getxSpeed());
		if(e.getKeyCode() == 37) {
			mario.leftStop();
		}
		if (e.getKeyCode() == 39) {
			mario.rightStop();
		}
	}
	
	
	
	@Override
	public void paint(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = createImage(800,600);
		}
		
		
		Graphics graphics = offScreenImage.getGraphics();
		graphics.fillRect(0, 0, 800, 600);
		
		//���Ʊ���
		graphics.drawImage(nowBg.getBgImage(),0,0,this);
		
		//���Ƶ���
		for(Enemy e : nowBg.getEnemyList()) {
			graphics.drawImage(e.getShow(), e.getX(), e.getY(), this);
		}
		
		//�����ϰ���
		for(Obstacle ob : nowBg.getObstacleList()) {
			graphics.drawImage(ob.getShow(),ob.getX(),ob.getY(),this);
		}

		//���ƳǱ�
		graphics.drawImage(nowBg.getTower(), 620, 270, this);
		
		//�������
		graphics.drawImage(nowBg.getGan(), 500, 220, this);
		
		//���������
		graphics.drawImage(mario.getShow(), mario.getX(), mario.getY(), this);
		//���Ʒ���
		Color c = graphics.getColor();
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Kemco Pixel Bold",Font.PLAIN, 20));
		graphics.drawString("Score: " + mario.getScore(), this.getWidth() / 2 - 70, 100);
		graphics.setColor(c);// ��ԭgraphics������ɫ
		//��ͼ����Ƶ�������
		g.drawImage(offScreenImage,0,0,this);
	}

	@Override
	public void run() {
		//TODO Rank List Window.
		{
			RankWindow rw = new RankWindow(this);
		}
		while(true) {
			repaint();
			try {
				Thread.sleep(50);
				
				if (mario.getX() >= 775) {
					//�ж��ߵ���ͼ�ұ��ұ����������һ��
					nowBg = allBg.get(nowBg.getSort());
					mario.setBackGround(nowBg);
					mario.setX(10);
					mario.setY(385);
				}
				
				//�ж�������Ƿ�����
				if (mario.isDeath()) {
					JOptionPane.showMessageDialog(this, "GAME OVER!!");
					System.exit(0);
				}
				
				//�ж���Ϸ�Ƿ����
				if(mario.isOK()){
					break;
//					System.exit(0);
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//while end
		
	}
}

