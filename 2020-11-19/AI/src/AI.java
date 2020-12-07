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
class MainCanvas extends Canvas
{
	int x,y;
	int leftFlag,rightFlag,upFlag,downFlag;

    Image heroleftImg[]=new Image[3];
	Image herorightImg[]=new Image[3];
	Image heroupImg[]=new Image[3];
	Image herodownImg[]=new Image[3];

	Image currentImg;

	public MainCanvas(){
		try
		{    
			for(int i=0;i<3;i++){
				heroleftImg[i]=	Image.createImage("/sayo"+i+"2.png");
			}
			/*
            img4 = Image.createImage("/sayo02.png");
            img1 = Image.createImage("/sayo12.png");
			img5 = Image.createImage("/sayo22.png");
            */

            for(int i=0;i<3;i++){
				herorightImg[i]=Image.createImage("/sayo"+i+"6.png");
			}
			/*
			img6 = Image.createImage("/sayo06.png");
            img2 = Image.createImage("/sayo16.png");
			img7 = Image.createImage("/sayo26.png");
			*/

            for(int i=0;i<3;i++){
				heroupImg[i]=	Image.createImage("/sayo"+i+"4.png");
			}
			/*
			img8 = Image.createImage("/sayo04.png"); 
			img3 = Image.createImage("/sayo14.png");
			img9 = Image.createImage("/sayo24.png");
			*/

            for(int i=0;i<3;i++){
				herodownImg[i]=	Image.createImage("/sayo"+i+"0.png");
			}
			/*
			img10 = Image.createImage("/sayo00.png");
			  img = Image.createImage("/sayo10.png");
			img11 = Image.createImage("/sayo20.png");
			*/
			
			currentImg = herodownImg[1];
			x = 120;
            y = 100;

			leftFlag = 1;
			rightFlag = 1;
	        upFlag = 1;
	        downFlag = 1;

		}
        catch(IOException e)
		{
            e.printStackTrace();
		}
	}
	public void paint(Graphics g){
		g.setColor(250,240,230);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,x,y,0);
	}
	public void keyPressed(int keyCode){
		int action = getGameAction(keyCode);
		
		if(action == LEFT){
			if(leftFlag == 1){
				currentImg = heroleftImg[0];
				leftFlag = 2;
			}
			else if(leftFlag == 2){
				currentImg = heroleftImg[2];
				leftFlag = 1;
			}
			System.out.println("向左转");
			x = x - 2;
			repaint();	   
		}
		if(action == RIGHT){
			if(rightFlag == 1){
				currentImg = herorightImg[0];
				rightFlag = 2;
			}
			else if(rightFlag == 2){
				currentImg = herorightImg[2];
				rightFlag = 1;
			}
			System.out.println("向右转");
			x = x + 2;
			repaint();
		}
		if(action == UP){
			if(upFlag == 1){
				currentImg = heroupImg[0];
				upFlag = 2;
			}
			else if(upFlag == 2){
				currentImg = heroupImg[2];
				upFlag = 1;
			}
			System.out.println("向上转");
			y = y - 2;
			repaint();
		}
		if(action == DOWN){
			if(downFlag == 1){
				currentImg = herodownImg[0];
				downFlag = 2;
			}
			else if(downFlag == 2){
				currentImg = herodownImg[2];
				downFlag = 1;
			}
			System.out.println("向下转");
			y = y + 2;
			repaint();
		}
	}
}