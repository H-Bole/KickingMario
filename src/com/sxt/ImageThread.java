package com.sxt;

public class ImageThread extends Thread{
	private Mario mario;
	public ImageThread(Mario mario) {
		this.mario = mario;
	}
	
	@Override
	public void run() { 
		while(true) {
			// �жϵ�ǰ�Ƿ����ƶ�״̬
			if (status.contains("move")) {
				// �ж��Ƿ������ƶ�
				if ("move--left".equals(status)) {
					for (index = 0; index < 21; index++) {
						show = StaticValue.run_L.get(index);// չʾ����µ�ͼƬ��������
//						System.out.println("L:" + index);// ���ڼ�������Ƿ����
						if (index == 21) {
							index = 0;
						}
						try {
							Thread.sleep(45);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
				// �ж��Ƿ������ƶ�
				if ("move--right".equals(status)) {
					for (index = 0; index < 21; index++) {
						show = StaticValue.run_R.get(index);// չʾ����µ�ͼƬ��������
						System.out.println("R:" + index);// ���ڼ�������Ƿ����
						if (index == 21) {
							index = 0;
						}
						
						try {
							Thread.sleep(45);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
		}

		
	}

}
