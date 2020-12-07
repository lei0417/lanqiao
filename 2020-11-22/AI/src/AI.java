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
	Image currentImg;
	Image heroImg[][] = new Image[4][3]; //4个方向，3张图片;0表示左方向，1表示右方向，2表示上方向，3表示下方向
	public MainCanvas(){
		try
		{    
			for(int i=0;i<heroImg.length;i++){
				for(int j=0;j<heroImg[i].length;j++){
					heroImg[i][j]=Image.createImage("/sayo"+i+j+".png");
				}
			}
			currentImg = heroImg[3][1];
			x = 120;
            y = 100;

			leftFlag = rightFlag = upFlag = downFlag = 1;

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
				currentImg = heroImg[0][0];
				leftFlag = 2;
			}
			else if(leftFlag == 2){
				currentImg = heroImg[0][2];
				leftFlag = 1;
			}
			System.out.println("向左转");
			x = x - 2;
			repaint();	   
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
			System.out.println("向右转");
			x = x + 2;
			repaint();
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
			System.out.println("向上转");
			y = y - 2;
			repaint();
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
			System.out.println("向下转");
			y = y + 2;
			repaint();
		}
	}
}