package com.lf.commons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * 图片验证码
 */
public class RandomNumUtil {

	//画汉字
	public static String drawRandomCh(Graphics2D g) {
		String s="";//随机生成的四个字的组合
		g.setColor(Color.RED);
		g.setFont(new Font("宋体",Font.BOLD,20));
		String base="\u660e\u745e\u6559\u80b2\u9ad8\u85aa\u5c31\u4e1a\u5e26\u78b0\u8001\u5e08\u4e16\u754c\u7b2c\u4e00";
		//\u4e00-\u9fa5
		for(int i=0;i<4;i++){
			int degree=new Random().nextInt()%30;
			String ch=base.charAt(new Random().nextInt(base.length()))+"";
			g.rotate(degree*Math.PI/180, 5+30*i, 20);
			g.drawString(ch, 5+30*i, 20);
			g.rotate(-degree*Math.PI/180, 5+30*i, 20);

			s=s+ch;
		}
		return s;

	}
		
	//画0-9数字
	public static int drawNum(Graphics2D g) {
		int num=0;
		String nu="";
		g.setColor(Color.RED);
		g.setFont(new Font("宋体",Font.BOLD,20));
		for(int i=0;i<4;i++){
			int degree=new Random().nextInt()%30;
			int n=new Random().nextInt(10);
			g.rotate(degree*Math.PI/180, 5+30*i, 20);
			g.drawString(n+"", 5+30*i, 20);
			g.rotate(-degree*Math.PI/180, 5+30*i, 20);
			nu=nu+""+n;
		}
		return Integer.parseInt(nu);
	}
		
	//画0-9数字和所有大小写字母
	public static String drawNums(Graphics2D g) {
		String num="";//要返回的字符串
		char[] c={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t',
	   'u','v','w','x','y','z','A','B','C','D','E','F','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		g.setColor(Color.RED);
		g.setFont(new Font("宋体",Font.BOLD,20));
		for(int i=0;i<4;i++){
			int degree=new Random().nextInt()%30;
			int n=new Random().nextInt(c.length);
			g.rotate(degree*Math.PI/180, 5+30*i, 20);
			g.drawString(c[n]+"", 5+30*i, 20);
			g.rotate(-degree*Math.PI/180, 5+30*i, 20);
			num=num+c[n];

		}
		return num;
	}



		//画背景，边框，干扰线 的方法
		public static void setBackground(Graphics g,Integer WIDTH,Integer HEIGHT) {
				// TODO Auto-generated method stub
				g.setColor(Color.BLUE);
				g.fillRect(0, 0, WIDTH, HEIGHT);
		}
		
		public static void setBorder(Graphics g,Integer WIDTH,Integer HEIGHT) {
				// TODO Auto-generated method stub
				g.setColor(Color.WHITE);
				g.fillRect(1,1,WIDTH-2,HEIGHT-2);
		}
		
		public static void drawRandomLine(Graphics g,Integer WIDTH,Integer HEIGHT) {
				// TODO Auto-generated method stub
				g.setColor(Color.BLUE);
				for(int i=0;i<5;i++){
					int x1=new Random().nextInt(WIDTH);
					int y1=new Random().nextInt(HEIGHT);
					int x2=new Random().nextInt(WIDTH);
					int y2=new Random().nextInt(HEIGHT);
					g.drawLine(x1, y1, x2, y2);
				}
		}

	//生成一个汉字 3个字符 的方法
	public static String getChAndNumStr(Graphics2D g){
		String s="";//随机生成的四个字的组合
		g.setColor(Color.RED);
		g.setFont(new Font("宋体",Font.BOLD,20));
		String base="\u660e\u745e\u6559\u80b2\u9ad8\u85aa\u5c31\u4e1a\u5e26\u78b0\u8001\u5e08\u4e16\u754c\u7b2c\u4e00";
		//\u4e00-\u9fa5
		for(int i=0;i<1;i++){
			int degree=new Random().nextInt()%30;
			String ch=base.charAt(new Random().nextInt(base.length()))+"";
			g.rotate(degree*Math.PI/180, 5+30*i, 20);
			g.drawString(ch, 5+30*i, 20);
			g.rotate(-degree*Math.PI/180, 5+30*i, 20);

			s=s+ch;
		}

		char[] c={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t',
				'u','v','w','x','y','z','A','B','C','D','E','F','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		for(int i=0;i<3;i++){
			int degree=new Random().nextInt()%30;
			int n=new Random().nextInt(c.length);
			g.rotate(degree*Math.PI/180, 5+40*i, 20);
			g.drawString(c[n]+"", 5+40*i, 20);
			g.rotate(-degree*Math.PI/180, 5+40*i, 20);
			s=s+c[n];

		}
		return s;
	}

}