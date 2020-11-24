import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;
import java.util.*;
public class AI extends MIDlet{
	Display display;
	MainCanvas mc;
	public AI(){
		display=Display.getDisplay(this);
		mc=new MainCanvas();
		display.setCurrent(mc);
	}
	public void startApp(){
	}
	public void destroyApp(boolean unc){
	}
	public void pauseApp(){
	}
}

class MainCanvas extends Canvas implements Runnable{
    Thread thread;
	int heroX,heroY,bossX,bossY;
	int flag;
	Image currentImg;
	Image heroImg[][] = new Image[4][3]; //4个方向，3张图片;0表示左方向，1表示右方向，2表示上方向，3表示下方向
	Image bossImg;
	Random rd = new Random();

	public MainCanvas(){
		try
		{    
			for(int i=0;i<heroImg.length;i++){
				for(int j=0;j<heroImg[i].length;j++){
					heroImg[i][j]=Image.createImage("/sayo"+i+j+".png");
				}
			}
			currentImg = heroImg[3][1];
			bossImg = Image.createImage("/zuzu000.png");
			heroX = 120;
            heroY = 100;
			bossX = 100;
			bossY = 0;
			flag = 1;

			thread=new Thread(this);
			thread.start();

		}
        catch(IOException e)
		{
            e.printStackTrace();
		}
	}
	/*
	在多线程里写一个死循环，不停地比较boss和hero的坐标
	*/
    public void run(){
        while (true){
			int rdNumber = rd.nextInt(10);

            try{
                Thread.sleep(200);
			}
			catch(InterruptedException e){
			     e.printStackTrace();
			}
			if(rdNumber%3==0){
				if(bossX<heroX){
					bossX++;
				}
				else{
					bossX--;
				}
				if(bossY<heroY){
					bossY++;
				}
				else{
					bossY--;
				}
			}
		    repaint();
		}
	}

	public void paint(Graphics g){
		g.setColor(250,240,230);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,heroX,heroY,0);
		g.drawImage(bossImg,bossX,bossY,0);
	}

	public void keyPressed(int keyCode){
		int action = getGameAction(keyCode);	
		if(action == LEFT){
			changePicAndDirection(0);
			heroX = heroX - 2;   
		}
		if(action == RIGHT){
			changePicAndDirection(1);
			heroX = heroX + 2;
		}
		if(action == UP){
			changePicAndDirection(2);
			heroY = heroY - 2;
		}
		if(action == DOWN){
		    changePicAndDirection(3);
			heroY = heroY + 2;
		}
	}

	public void changePicAndDirection(int direction){
	    if(flag == 1){
				currentImg = heroImg[direction][0];
				flag = 2;
		}
		else if(flag == 2){
				currentImg = heroImg[direction][2];
				flag = 1;
		}
	}

}