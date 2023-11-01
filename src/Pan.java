import javax.swing.*;
import java.awt.*;

//画笔，负责绘制棋盘和棋子
public class Pan extends JPanel implements config{

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //划线
        for(int i=1;i<18;i++) {
            g.drawLine(0, i * 40, 720, i * 40);
            g.drawLine(i * 40, 0, i * 40, 720);
        }
        //在棋盘上添加标记，用于定位
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
            {
                int x=(3+4*i)*40-5;
                int y=(3+4*j)*40-5;
                g.setColor(new Color(0,0,0));
                g.fillOval(x,y,10,10);
            }
        //画棋子
        for(int i=1;i<=19;i++)
        {
            for(int j=1;j<=19;j++)
            {
                int x=i*40-15;
                int y=j*40-15;
                if(chess[i][j]==1)
                {
                    g.setColor(new Color(0,0,0));
                    g.fillOval(x,y,30,30);
                }
                else if(chess[i][j]==2)
                {
                    g.setColor(new Color(255,255,255));
                    g.fillOval(x,y,30,30);
                }
            }
        }
    }
}
