package com.sxt;

import java.awt.image.BufferedImage;

import javax.swing.TransferHandler;


public class Mario implements Runnable {
	// ���ڱ�ʾ��������
	private int x;
	private int y;
	
	// ���ڱ�ʾ��ǰ��״̬
	private String status;
	// ������ʾ��ǰ״̬��Ӧ��ͼ��
	private BufferedImage show = null;
	// ����һ��BackGround����������ȡ�ϰ������Ϣ
	private BackGround backGround = new BackGround();
	// ����ʵ������µĶ���
	private Thread thread = null;
	
	// ����µ��ƶ��ٶ�
	private double xSpeed;
	// ����µ���Ծ�ٶ�
	private int ySpeed;
	// �����ˮƽ���ٶ�
	private double xAccSpeed;
	// �������ֱ���ٶ�
	private int yAccSpeed;
	// ����ˮƽ��������ٶ�;
	private double xMaxSpeed = 10.0;

	// ����ˮƽ�������ٶȳ���
	final private double ACC = 0.80;
	// ����ת����ٶȳ���
	final private double TURNACC = 0.9;
	// ֹͣ���ٶ�
	final private double FRICTION = -0.08;
	// �����������ٶȳ���
	final private int GRAVITY = 9;

	
	// ����һ������
	private int index;
	// �ж�������Ƿ��ߵ��˳Ǳ��ſ�
	private boolean isOK;
	// �ж�������Ƿ�����
	private boolean isDeath = false;
	// ��ʾ����
	private int score = 0;

	
	public Mario() {
	}

	public Mario(int x, int y) {
		this.x = x;
		this.y = y;
		show = StaticValue.stand_R;
		this.status = "stand--right";
		thread = new Thread(this);
		thread.start();
	}

	// ����������ķ���
	public void death() {
		isDeath = true;
	}

	// ����������ƶ�
	public void leftMove() {
		//�ж���Ҫת��������
		status = "move--left";
	}

	// ����������ƶ�
	public void rightMove() {
		//�ж���Ҫת��������
		status = "move--right";
		

	}
	// ���������ֹͣ
	public void leftStop() {
		status = "stop--left";
	}
	// ���������ֹͣ
	public void rightStop() {
		status = "stop--right";
	}
	// �������Ծ
	public void jump() {
		if(xSpeed >= 0)
			status = "jump--right";
		else if(xSpeed < 0)
			status = "jump--left";
		yAccSpeed = GRAVITY;//���ٶ�����
	}
	// ���������
	public void fall() {
		if(xSpeed >= 0)
			status = "jump--right";
		else if(xSpeed < 0)
			status = "jump--left";
		yAccSpeed = GRAVITY;
	}


	@Override
	public void run() {
		for (index = 0; index < 22; index++) {
			// �ж��Ƿ����ϰ�����
			boolean onObstacle = true;
			// �ж��Ƿ����������
			boolean canRight = true;
			// �ж��Ƿ����������
			boolean canLeft = true;
			// �ж�������Ƿ񵽴����λ��
			boolean isFlag = false;
			
			
			/************ ������ƶ���� *************/
			//TODO finish this
			if(status.indexOf("move") != -1)//������move
			{
				if(status.indexOf("right") != -1)//�����ƶ�
				{
					if(xSpeed < 0) {
						xAccSpeed = TURNACC; 
					}else if(xSpeed >= 0) {
						xAccSpeed = ACC;
					}
					
				}else if (status.indexOf("left") != -1) {
					
					if(xSpeed > 0) {
						xAccSpeed = -TURNACC; 
					}else if(xSpeed <= 0) {
						xAccSpeed = -ACC;
					}
				}
			}else if(status.indexOf("stop") != -1) {
				if(status.indexOf("right") != -1)//���Ҽ���
				{
					xAccSpeed = FRICTION;
					if(xSpeed > 0)
						xSpeed += xAccSpeed;
					else if(xSpeed <= 0)
					{
						xAccSpeed = 0;
						xSpeed = 0;
					}
					
				}else if (status.indexOf("left") != -1) {
					xAccSpeed = -FRICTION;
					if(xSpeed < 0)
						xSpeed += xAccSpeed;
					else if(xSpeed >= 0)
					{
						xAccSpeed = 0;
						xSpeed = 0;
					}
				}
			}
			//�ж��Ƿ�ﵽ����ٶ�
			if(Math.abs(xSpeed) < xMaxSpeed) {
				xSpeed += xAccSpeed;
			}else if(status.indexOf("move") != -1 && status.indexOf("right") != -1) {
				xAccSpeed = 0;
				xSpeed = xMaxSpeed;
			}else if(status.indexOf("move") != -1 && status.indexOf("left") != -1) {
				xAccSpeed = 0;
				xSpeed = -xMaxSpeed;
			}
			
//			xAccSpeed += xSpeed * FRICTION;
			xSpeed += xAccSpeed;
			x += xSpeed + 0.5 * xAccSpeed;
			System.out.println(status);
			/****************** ������ƶ����ENDING ****************/

			//�߳�����
			try {
				Thread.sleep(45);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//ˢ��index��д��ѭ��
			if(index == 21)
			{
				index = 0;
			}
		}
	}

	
	
// Setter and Getters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getShow() {
		return show;
	}

	public void setShow(BufferedImage show) {
		this.show = show;
	}

	public BackGround getBackGround() {
		return backGround;
	}

	public void setBackGround(BackGround backGround) {
		this.backGround = backGround;
	}

	public boolean isOK() {
		return isOK;
	}

	public boolean isDeath() {
		return isDeath;
	}

	public int getScore() {
		return score;
	}

	public double getxMaxSpeed() {
		return xMaxSpeed;
	}
	public void setxMaxSpeed(double xMaxSpeed) {
		this.xMaxSpeed = xMaxSpeed;
	}

	public String getStatus() {
		return status;
	}

	public Thread getThread() {
		return thread;
	}

	public double getxSpeed() {
		return xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public double getxAccSpeed() {
		return xAccSpeed;
	}

	public int getyAccSpeed() {
		return yAccSpeed;
	}

	public double getACC() {
		return ACC;
	}

	public double getTURNACC() {
		return TURNACC;
	}

	public double getFRICTION() {
		return FRICTION;
	}

	public int getGRAVITY() {
		return GRAVITY;
	}

	public int getIndex() {
		return index;
	}

}
