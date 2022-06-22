package com.sxt;

import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class RankWindow extends JDialog{
	//�������а�·��
	public static String rankpath = System.getProperty("user.dir") + "/src/rank.txt";
	//�������а�����
    private String[] ranks = new String[5];

	
	public RankWindow(MyFrame jf) {
		try {
			//�����ļ�
			FileInputStream fis = new FileInputStream(new File(rankpath));
			InputStreamReader reader = new InputStreamReader(fis);
			BufferedReader buffReader = new BufferedReader(reader);
			//����rank.txt�ļ�
	        String strTmp = "";
	        int i = 0;
	        while((strTmp = buffReader.readLine())!=null){
	            if(i <= 6)
	            {
	            	ranks[i] = strTmp;
	            }
	        }
	        
	        buffReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		JDialog jd = new JDialog(jf,"Rank");
		jd.setLayout(new FlowLayout(FlowLayout.LEFT));
		jd.setSize(300,400);
		jd.setLocation(jf.getX() + jf.getWidth() / 2 - jd.getWidth() / 2,
				jf.getY() + jf.getHeight() / 2 - jd.getHeight() / 2);
		jd.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		//��ӱ�ǩ
		JLabel label = new JLabel("RANK");
		label.setFont(new Font("Kemco Pixel Bold",Font.PLAIN, 20));
		label.setBounds(jd.getX() + jd.WIDTH / 2,jd.getY() + jd.HEIGHT / 2, 300, 80);
		
		//������а��ǩ
		JLabel label2 = new JLabel("where");
		label2.setBounds(jd.WIDTH / 2, jd.HEIGHT / 2, 300, 80);
		
		//�������,�Թ�������
		JPanel jp = new JPanel();
		jp.add(label);
		jp.add(label2);
		
		jd.add(jp);
		jd.setVisible(true);
	}
}
