import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;





abstract class HW5Panel extends JPanel implements KeyListener{
	HW5Frame frame;
	HW5Panel(HW5Frame _frame){
		super();
		frame=_frame;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		GradientPaint gp
		 = 	new GradientPaint(0, 0,Color.BLACK, 0, getHeight(),new Color(0,0,128));
		g2D.setPaint(gp);
		g2D.fillRect(0,0,getWidth(),getHeight());
	}
}
class HW5StartPanel extends HW5Panel {
	JLabel jPH;
	JLabel bG;
	FlickerLabel fLabel;
	HW5StartPanel(HW5Frame _frame){				//패널 생성자 실행 시에는 아직 프레임에 추가가 안되서 너비와 높이 0
		super(_frame);
		setLayout(null);
		setFocusable(true);
		requestFocus();
		
		jPH = new JLabel("<html>Java Programming<br>Homework #5</html>");		///자바 과제5 라벨
		add(jPH);
		jPH.setHorizontalAlignment(JLabel.CENTER);		//가운데 정렬
		jPH.setForeground(Color.WHITE);
		
		bG= new JLabel("Block Game");					//블럭 게임 라벨
		add(bG);
		bG.setHorizontalAlignment(JLabel.CENTER);
		bG.setForeground(Color.WHITE);
		
		addKeyListener(this);
		
		fLabel=new FlickerLabel("Press SpaceBar to Play",this);
		add(fLabel);
		Thread fT=new Thread(fLabel);
		fT.start();
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		jPH.setFont(getFont().deriveFont(50f));			//자바 과제5 라벨
		jPH.setSize(getWidth(),150);		
		jPH.setLocation(0,100);
		
		bG.setFont(getFont().deriveFont(80f));			//블럭 게임 라벨
		bG.setSize(getWidth(),100);		
		bG.setLocation(0,getHeight()*2/4-50);
		
		fLabel.setFont(getFont().deriveFont(40f));			//깜빡이 라벨
		fLabel.setSize(getWidth(),50);		
		fLabel.setLocation(0,getHeight()*2/4+100);
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			setVisible(false);
			frame.gamingPanel.setVisible(true);
			frame.gamingPanel.requestFocus();
			frame.gamingPanel.stageStart();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	
}


class HW5EndPanel extends HW5Panel{
	JLabel scoreLabel;
	JLabel highestScoreLabel;
	HW5EndPanel(HW5Frame _frame) {
		super(_frame);
		addKeyListener(this);
		setFocusable(true);
		scoreLabel=new JLabel();
		highestScoreLabel=new JLabel();
		
		setLayout(null);
		add(scoreLabel);
		add(highestScoreLabel);
		
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		highestScoreLabel.setHorizontalAlignment(JLabel.CENTER);
		
		scoreLabel.setSize(frame.getWidth(), 60);
		highestScoreLabel.setSize(frame.getWidth(), 60);
		scoreLabel.setLocation(0,frame.getHeight()/2-30);
		highestScoreLabel.setLocation(0,frame.getHeight()/2+scoreLabel.getHeight());
		scoreLabel.setForeground(Color.WHITE);
		highestScoreLabel.setForeground(Color.WHITE);
		
		scoreLabel.setFont(getFont().deriveFont(50f));
		highestScoreLabel.setFont(getFont().deriveFont(50f));
		
		JLabel gameoverPanel=new JLabel("Game Over");
		gameoverPanel.setSize(frame.getWidth(), 100);
		gameoverPanel.setLocation(0, 200);
		gameoverPanel.setFont(getFont().deriveFont(100f));
		gameoverPanel.setForeground(Color.WHITE);
		gameoverPanel.setHorizontalAlignment(JLabel.CENTER);
		add(gameoverPanel);
		
		
		var fLabel=new FlickerLabel("Press SpaceBar",this);	//깜빡이 패널
		add(fLabel);
		Thread fT=new Thread(fLabel);
		fT.start();
		fLabel.setFont(getFont().deriveFont(30f));			
		fLabel.setSize(frame.getWidth(),40);		
		fLabel.setLocation(0,frame.getHeight()*3/4-20);
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			setVisible(false);
			frame.startPanel.setVisible(true);
			frame.startPanel.requestFocus();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
}

abstract class Things{
	protected int x,y;
	protected int width, height;
	Things(int _x , int _y, int _width, int _height){
		x=_x;
		y=_y;
		width=_width;
		height=_height;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public void setX(int _x)
	{
		x=_x;
	}
	public void setY(int _y)
	{
		y=_y;
	}
	public void setWidth(int _width)
	{
		width=_width;
	}
	public void setHeight(int _height)
	{
		height=_height;
	}
}

class Ball extends Things{					//x,y는 중심 기준
	private int radius;
	private int vX,vY,v;
	private double angle;
	Ball(int _x,int _y, int _radius){
		super(_x,_y,0,0);
		radius=_radius;
		v=5;
		
		//초기 속도 세팅
		
	}
	public void setVY(int _vY) {
		vY=_vY;
	}
	public void setV(int _v) {
		v=_v;
	}
	public int getV() {
		return v;
	}
	public int getRadius()
	{
		return radius;
	}
	public void setRadius(int _radius)
	{
		radius=_radius;
	}
	public void setAngle(double _angle)
	{
		angle=_angle;
		vX=(int)(v*Math.cos(angle));
		vY=(int)(-v*Math.sin(angle));
	}
	public int getVX() {
		return vX;
	}
	public int getVY() {
		return vY;
	}
	public double getAngle() {
		return angle;
	}
}

class Bar extends Things{						//x,y 는 중심 기준

	Bar(int _x, int _y, int _width, int _height) {
		super(_x, _y, _width, _height);
	}
}

class Block extends Things{
	Boolean activated;
	Boolean golden;
	Block(int _x, int _y, int _width, int _height) {
		super(_x, _y, _width, _height);
		activated=true;
		golden=false;
		double rand=Math.random();
		if(rand<0.3) golden=true;
	}
}

class FlickerLabel extends JLabel implements Runnable{

	JPanel panel;
	FlickerLabel(String str,JPanel _panel){
		super(str);
		panel=_panel;
		setForeground(Color.red);
		setHorizontalAlignment(JLabel.CENTER);
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(150);
			}
			catch(InterruptedException e) {	}
			setVisible(true);
			try {
				Thread.sleep(150);
			}
			catch(InterruptedException e) {	}
			setVisible(false);
		}
	}

}


class HW5GamingPanel extends HW5Panel implements Runnable{
	Bar bar;
	LinkedList<Ball> balls;
	ArrayList<ArrayList<Block>> blocks;
	LinkedList<Ball> tmpBalls;
	int level;
	boolean gameover;
	private final static int OW=600, OH=800;
	HW5GamingPanel(HW5Frame _frame) {
		super(_frame);
		addKeyListener(this);
		setFocusable(true);
		bar=new Bar(OW*1/2, OH*17/20,OW/4,OH/25);
		gameover=true;
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(bar.getX()-bar.getWidth()/2>0)
				bar.setX(bar.getX()-30);
			if(bar.getX()-bar.getWidth()/2<0)
				bar.setX(bar.getWidth()/2);
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			
			if(bar.getX()+bar.getWidth()/2<OW)
				bar.setX(bar.getX()+30);
			if(bar.getX()+bar.getWidth()/2>OW)
				bar.setX(OW-bar.getWidth()/2);
		}
	}
	public void keyReleased(KeyEvent e) {}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(118,12,12));
		g.fillRect((bar.getX()-bar.getWidth()/2),(bar.getY()-bar.getHeight()/2)
					,bar.getWidth(),bar.getHeight());
		
		g.setColor(Color.white);
		for(int i=0;i<balls.size();i++) {
			Ball ball=balls.get(i);
			g.fillOval(ball.getX()-ball.getRadius()/2,ball.getY()-ball.getRadius()/2,ball.getRadius(),ball.getRadius());
		}
		for(ArrayList<Block> blockArr : blocks)
		{
			for(Block b : blockArr) {
				if(b.golden) g.setColor(Color.YELLOW);
				else g.setColor(Color.gray);
				if(b.activated)
					g.fillRect(b.x-b.width/2,b.y-b.height/2,b.width,b.height);
			}
		}
		repaint();
	}
	
	public void stageStart() {
		Thread t = new Thread(this);
		
		balls=new LinkedList<Ball>();
		Ball ball = new Ball(OW*1/2,bar.getY()-50,15);
		balls.add(ball);
		
		if(gameover) {
			level=0;
			frame.score=0;
			gameover=false;
			ball.setV(3);
		}
		ball.setV(ball.getV()+3);
		level++;
		int nOfB=level*3;		//줄당 블럭 수
		int width=(getWidth()-5*(nOfB+2))/nOfB;		//5가 블럭 간 간격
		int height=(getHeight()/2-5*(nOfB+2))/nOfB;
		blocks = new ArrayList<ArrayList<Block>>();
		for(int i=0;i<nOfB;i++)
		{
			ArrayList<Block> blockArr=new ArrayList<>();
			for(int j=0;j<nOfB;j++) {
				blockArr.add(new Block((j+1)*5+j*width+width/2,(i+1)*5+i*height+height/2,width,height ));
			}
			blocks.add(blockArr);
		}
		
		
		ball.setX(OW*1/2);
		ball.setY(bar.getY()-50);
		ball.setAngle(Math.random()*(Math.PI-0.3)+0.15);	//볼 출발 방향 설정
		
		bar.setX(OW*1/2);
		bar.setY(OH*17/20);
		
		t.start();
	}
	
	public void ObjectCollision(Things thi) {
		Iterator<Ball> bI=balls.iterator();
		tmpBalls=new LinkedList<>();
		while(bI.hasNext()) {
			Ball ball=bI.next();
			if(thi.getX()+thi.getWidth()/2>ball.getX() 
					&& ball.getX()>thi.getX()-thi.getWidth()/2){//수직 충돌
				if(thi.getY()+thi.getHeight()/2+ball.getRadius()>ball.getY() 
						&& ball.getY()>thi.getY()-thi.getHeight()/2-ball.getRadius()) {
					if (thi instanceof Block) {
						Block block = (Block) thi;
						block.activated=false;
						frame.score+=10;
						ball.setAngle(-ball.getAngle());
						if(block.golden) {
							for(int i=1;i<=2;i++) {
								Ball newBall = new Ball(ball.getX(),ball.getY(),15);
								newBall.setV(ball.getV());
								newBall.setAngle(ball.getAngle()+i*0.2);
								tmpBalls.add(newBall);
							}
						}
					}
					else if (thi instanceof Bar) {					//바 튕김 각 조절
						double arctan;
						if(ball.y-bar.y!=0) {
							arctan=Math.atan((ball.x-bar.x)/(double)(ball.y-bar.y));
							ball.setAngle(arctan + Math.PI/2);
						}
						else {
							ball.setAngle(-ball.getAngle());
						}
						
					}
				}
			}
			if(thi.getY()+thi.getHeight()/2>ball.getY() 
					&& ball.getY()>thi.getY()-thi.getHeight()/2){//옆 충돌
				if(thi.getX()+thi.getWidth()/2+ball.getRadius()>ball.getX() 
						&& ball.getX()>thi.getX()-thi.getWidth()/2-ball.getRadius()) {
					ball.setAngle(Math.PI-ball.getAngle());
					if (thi instanceof Block) {
						Block block = (Block) thi;
						block.activated=false;
						frame.score+=10;
						if(block.golden) {
							for(int i=1;i<=2;i++) {
								Ball newBall = new Ball(ball.getX(),ball.getY(),15);
								newBall.setV(ball.getV());
								newBall.setAngle(ball.getAngle()+i*0.2);
								tmpBalls.add(newBall);
								
							}
						}
					}
				}
			}
			if(ball.getVY()==0)		//공이 정수 연산으로 인해 수직 움직임 속도가 0이 됐을 때 게임이 안 끝남 방지
			{
				ball.setVY(1);
			}
		}
			//bar랑 충돌시 
		for(Ball b : tmpBalls) {
			balls.add(b);
		}
		
	}
	
	public void run() {				//game
		boolean breakFlag=false;
		
		while(!balls.isEmpty()) {
			try {
				Thread.sleep(16);
			}
			catch (InterruptedException e) {}
			for(int i=0;i<balls.size();i++) {
				Ball ball=balls.get(i);
				ball.setX(ball.getX()+ball.getVX());
				ball.setY(ball.getY()+ball.getVY());
			
				if(ball.getX()<=ball.getRadius()) {
					ball.setAngle(Math.PI-ball.getAngle());		//충돌시 방향 바뀜
					ball.setX(ball.getRadius());
				}
				if(ball.getX()>=600-ball.getRadius()) {
					ball.setAngle(Math.PI-ball.getAngle());
					ball.setX(600-ball.getRadius());
				}
				if(ball.getY()-ball.getRadius()<0) ball.setAngle(-ball.getAngle());
				if(ball.getY()-ball.getRadius()>800) {
					balls.remove(i);
					if(balls.isEmpty()) {
						gameover=true;
						break;
					}
				}
			
				ObjectCollision(bar);
				breakFlag=true;
				for(ArrayList<Block> blockArr : blocks) {
					for(Block b : blockArr) {
						if(b.activated) {
							ObjectCollision(b);
							breakFlag=false;
						}
					}
				}
				if(breakFlag) break;
			}
			if(breakFlag) break;
			if(gameover) break;
		}
		
		
		if(gameover) {
			setVisible(false);
			frame.endPanel.setVisible(true);
			frame.endPanel.requestFocus();
			frame.endPanel.scoreLabel.setText("Score : "+frame.score);
			frame.highestScore= frame.highestScore > frame.score ? frame.highestScore : frame.score;
			frame.endPanel.highestScoreLabel.setText("Highest Score : "+frame.highestScore);
		}
		else if(breakFlag) {
			stageStart();
		}
	}
}



class HW5Frame extends JFrame{
	HW5StartPanel startPanel;
	HW5GamingPanel gamingPanel;
	HW5EndPanel endPanel;
	int score;
	int highestScore;
	
	HW5Frame(){
		super();
		setTitle("Block Game");
		setSize(600,800);
		setLayout(new CardLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		startPanel = new HW5StartPanel(this);
		add(startPanel);
		gamingPanel = new HW5GamingPanel(this);
		add(gamingPanel);
		gamingPanel.setVisible(false);
		endPanel = new HW5EndPanel(this);
		add(endPanel);
		endPanel.setVisible(false);
		
		setVisible(true);
		
		highestScore=0;
		
		
	}
}

public class HW5 {
	static public void main(String[] args)
	{
		new HW5Frame();
	}
}
