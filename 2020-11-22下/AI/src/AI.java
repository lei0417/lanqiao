import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;
public class AI extends MIDlet
{
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
class MainCanvas extends Canvas implements Runnable
{
    Thread thread;

	int heroX,heroY,bossX,bossY;
	int leftFlag,rightFlag,upFlag,downFlag;
	Image currentImg;
	Image heroImg[][] = new Image[4][3]; //4������3��ͼƬ;0��ʾ����1��ʾ�ҷ���2��ʾ�Ϸ���3��ʾ�·���
	Image bossImg;
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
    
			leftFlag = rightFlag = upFlag = downFlag = 1;

			thread=new Thread(this);
			thread.start();

		}
        catch(IOException e)
		{
            e.printStackTrace();
		}
	}
	/*
	�ڶ��߳���дһ����ѭ������ͣ�رȽ�boss��hero������
	*/
    public void run(){
        while (true){
            try{
                Thread.sleep(200);
			}
			catch(InterruptedException e){
			     e.printStackTrace();
			}
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
			if(leftFlag == 1){
				currentImg = heroImg[0][0];
				leftFlag = 2;
			}
			else if(leftFlag == 2){
				currentImg = heroImg[0][2];
				leftFlag = 1;
			}
			System.out.println("����ת");
			heroX = heroX - 2;   
		}
		if(action == RIGHT){
			if(rightFlag == 1){
				currentImg = heroImg[1][0];
				rightFlag = 2;
			}
			else if(rightFlag == 2){
				currentImg = heroImg[1][2];
				rightFlag = 1;
			}
			System.out.println("����ת");
			heroX = heroX + 2;
		}
		if(action == UP){
			if(upFlag == 1){
				currentImg = heroImg[2][0];
				upFlag = 2;
			}
			else if(upFlag == 2){
				currentImg = heroImg[2][2];
				upFlag = 1;
			}
			System.out.println("����ת");
			heroY = heroY - 2;
		}
		if(action == DOWN){
			if(downFlag == 1){
				currentImg = heroImg[3][0];
				downFlag = 2;
			}
			else if(downFlag == 2){
				currentImg = heroImg[3][2];
				downFlag = 1;
			}
			System.out.println("����ת");
			heroY = heroY + 2;
		}
	}
}