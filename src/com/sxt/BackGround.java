package com.sxt;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
    //��ǰ����Ҫ��ʾ��ͼ��
    private BufferedImage bgImage = null;
    //��¼��ǰ�ǵڼ�������
    private int sort;
    //�ж��Ƿ������һ������
    private boolean flag;
    //���ڴ�����ǵ������ϰ���
    private List<Obstacle> obstacleList = new ArrayList<>();
	// ���ڴ���������еĵ���
	private List<Enemy> enemyList = new ArrayList<>();
    //������ʾ���
    private BufferedImage gan = null;
    //������ʾ�Ǳ�
    private BufferedImage tower = null;
    //�ж�������Ƿ񵽴����λ��
    private boolean isReach = false;
    //�ж������Ƿ����
    private boolean isBase = false;
    


    public BackGround() {

    }

    public BackGround(int sort,boolean flag) {
        this.sort = sort;
        this.flag = flag;

        if (flag) {
            bgImage = StaticValue.bg2;
        }else if(sort == 1){
            bgImage = StaticValue.bg;
        }else {
        	bgImage = StaticValue.bg1;
        }

        //�ж��Ƿ��ǵ�һ��
        if (sort == 1) {
            //���Ƶ�һ�صĵ���,�ϵ���type=1,�µ���type=2
            for (int i = 0;i < 27;i++) {
                obstacleList.add(new Obstacle(i*30,420,1,this));
            }

            for (int j = 0;j <= 120;j += 30) {
                for (int i = 0;i < 27;i++) {
                    obstacleList.add(new Obstacle(i*30,570-j,2,this));
                }
            }


            //����ש��A
            for (int i = 120;i <= 150;i += 30) {
                obstacleList.add(new Obstacle(i,300,7,this));
            }


            //����ש��B-F
            for (int i = 300;i <= 570;i += 30) {
                if (i == 360 || i == 390 || i == 480 || i == 510 || i == 540) {
                    obstacleList.add(new Obstacle(i,300,7,this));
                } else {
                    obstacleList.add(new Obstacle(i,300,0,this));
                }
            }


            //����ש��G
            for (int i = 420;i <= 450;i += 30) {
                obstacleList.add(new Obstacle(i,240,7,this));
            }


            //����ˮ��
            for (int i = 360;i <= 600;i += 25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(620,i,3,this));
                    obstacleList.add(new Obstacle(645,i,4,this));
                }else {
                    obstacleList.add(new Obstacle(620,i,5,this));
                    obstacleList.add(new Obstacle(645,i,6,this));
                }
            }

			// ���Ƶ�һ�ص�Ģ������
			enemyList.add(new Enemy(580, 385, 1, true, this));
			// ���Ƶ�һ�ص�ʳ�˻�����
			enemyList.add(new Enemy(635, 420, 2, true, this, 328, 428));
        }

        //�ж��Ƿ��ǵڶ���
        if (sort == 2) {
            //���Ƶڶ��صĵ���,�ϵ���type=1,�µ���type=2
            for (int i = 0;i < 27;i++) {
                obstacleList.add(new Obstacle(i*30,420,1,this));
            }

            for (int j = 0;j <= 120;j += 30) {
                for (int i = 0;i < 27;i++) {
                    obstacleList.add(new Obstacle(i*30,570-j,2,this));
                }
            }

            //���Ƶ�һ��ˮ��
            for (int i = 360;i <= 600;i += 25) {
                if (i == 360) {
                    obstacleList.add(new Obstacle(60,i,3,this));
                    obstacleList.add(new Obstacle(85,i,4,this));
                }else {
                    obstacleList.add(new Obstacle(60,i,5,this));
                    obstacleList.add(new Obstacle(85,i,6,this));
                }
            }

            //���Ƶڶ���ˮ��
            for (int i = 330;i <= 600;i += 25) {
                if (i == 330) {
                    obstacleList.add(new Obstacle(620,i,3,this));
                    obstacleList.add(new Obstacle(645,i,4,this));
                }else {
                    obstacleList.add(new Obstacle(620,i,5,this));
                    obstacleList.add(new Obstacle(645,i,6,this));
                }
            }

            //����ש��C
            obstacleList.add(new Obstacle(300,330,0,this));

            //����ש��B,E,G
            for (int i = 270;i <= 330;i += 30) {
                if (i == 270 || i == 330) {
                    obstacleList.add(new Obstacle(i,360,0,this));
                }else {
                    obstacleList.add(new Obstacle(i,360,7,this));
                }
            }

            //����ש��A,D,F,H,I
            for (int i = 240;i <= 360;i += 30) {
                if (i == 240 || i == 360) {
                    obstacleList.add(new Obstacle(i,390,0,this));
                }else {
                    obstacleList.add(new Obstacle(i,390,7,this));
                }
            }

            //���Ʒ���1ש��
            obstacleList.add(new Obstacle(240,300,0,this));

            //���ƿ�1-4ש��
            for (int i = 360;i <= 540;i += 60) {
                obstacleList.add(new Obstacle(i,270,7,this));
            }
            
            //���Ƶڶ��صĵ�һ��ʳ�˻�����
			enemyList.add(new Enemy(75, 420, 2, true, this, 328, 418));
            //���Ƶڶ��صĵڶ���ʳ�˻�����
			enemyList.add(new Enemy(635, 420, 2, true, this, 298, 388));
            
            //���Ƶڶ��صĵ�һ��Ģ������
            enemyList.add(new Enemy(200, 385, 1, true, this));
            //���Ƶڶ��صĵڶ���Ģ������
            enemyList.add(new Enemy(500, 385, 1, true, this));
        }

        //�ж��Ƿ��ǵ�����
        if (sort == 3) {
            //���Ƶ����صĵ���,�ϵ���type=1,�µ���type=2
            for (int i = 0;i < 27;i++) {
                obstacleList.add(new Obstacle(i*30,420,1,this));
            }

            for (int j = 0;j <= 120;j += 30) {
                for (int i = 0;i < 27;i++) {
                    obstacleList.add(new Obstacle(i*30,570-j,2,this));
                }
            }

            //���Ƶ�����������A-Oש��
            int temp = 290;
            for (int i = 390;i >= 270;i -= 30) {
                for (int j = temp;j <= 410;j += 30) {
                    obstacleList.add(new Obstacle(j,i,7,this));
                }
                temp += 30;
            }

            //���Ƶ�����������P-Rש��
            temp = 60;
            for (int i = 390;i >= 360;i -= 30) {
                for (int j = temp;j <= 90;j += 30) {
                    obstacleList.add(new Obstacle(j,i,7,this));
                }
                temp += 30;
            }

            //�������
            gan = StaticValue.gan;

            //���ƳǱ�
            tower = StaticValue.tower;

            //������ӵ������
            obstacleList.add(new Obstacle(515,220,8,this));
            
            //���Ƶ�����Ģ������
			enemyList.add(new Enemy(150, 385, 1, true, this));
        }
    }

    public BufferedImage getBgImage() {
        return bgImage;
    }

    public int getSort() {
        return sort;
    }

    public boolean isFlag() {
        return flag;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public BufferedImage getGan() {
        return gan;
    }

    public BufferedImage getTower() {
        return tower;
    }
    public boolean isReach() {
        return isReach;
    }
    public void setReach(boolean isReach) {
        this.isReach = isReach;
    }
    public boolean isBase() {
        return isBase;
    }
    public void setBase(boolean isBase) {
        this.isBase = isBase;
    }

	public List<Enemy> getEnemyList() {
		return enemyList;
	}
}
