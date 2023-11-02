import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

//棋盘监听
public class PanelListener implements MouseListener,config {
    boolean judgment(int qx,int qy){
        //起始位置和终止位置，不能够越界
        int begin_x=Math.max(qx-4,1);
        int begin_y=Math.max(qy-4,1);
        int end_x=Math.min(qx+4,17);
        int end_y=Math.min(qy+4,17);
        boolean bo;
        for(int x=begin_x;x<=qx;x++)
        {
            bo=true;
            for(int j=0;j<5;j++)
            {
                if(x+j>end_x){
                    bo=false;
                    break;
                }
                if(chess[x+j][qy]!=chess[qx][qy])
                    bo=false;
            }
            if(bo)return bo;
        }
        for(int y=begin_y;y<=qy;y++)
        {
            bo=true;
            for(int j=0;j<5;j++)
            {
                if(y+j>end_y){
                    bo=false;
                    break;
                }
                if(chess[qx][y+j]!=chess[qx][qy])
                    bo=false;
            }
            if(bo)return bo;
        }
        for(int i=0;i<5;i++)
        {
            bo=true;
            if(qx-i<1||qy-i<1)break;
            for(int j=0;j<5;j++)
            {
                if(qx-i+j>17||qy-i+j>17){
                    bo=false;
                    break;
                }
                if(chess[qx-i+j][qy-i+j]!=chess[qx][qy])bo=false;
            }
            if(bo)return bo;
        }
        for(int i=0;i<5;i++)
        {
            bo=true;
            if(qx-i<1||qy+i>17)break;
            for(int j=0;j<5;j++)
            {
                if(qx-i+j>17||qy+i-j<1)
                {
                    bo=false;
                    break;
                }
                if(chess[qx-i+j][qy+i-j]!=chess[qx][qy])bo=false;
            }
            if(bo)return bo;
        }
        return false;
    }
    boolean isfullchess()
    {
        for(int i=1;i<=17;i++)
            for(int j=1;j<=17;j++)
                if(chess[i][j]==0)return false;
        return true;
    }
    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        Pan p=(Pan)e.getSource();
        if(flag[1]==1&&e.getX()>=20&&e.getX()<700&&e.getY()>=20&&e.getY()<700)
        {
            int lx=(e.getX()+20)/40;
            int ly=(e.getY()+20)/40;
            int cx=lx*40;
            int cy=ly*40;
            if(Math.sqrt((e.getX()-cx)*(e.getX()-cx)+(e.getY()-cy)*(e.getY()-cy))>15)return;//判定范围以点圆心，半径15的圆
            if(chess[lx][ly]!=0)return;//同一个位置不能下棋
            location[0]=lx;
            location[1]=ly;
            chess[location[0]][location[1]]=flag[0]+1;
            p.repaint();
            if(judgment(location[0],location[1] ))
            {
                flag[1]=0;
                JOptionPane.showMessageDialog(null,(flag[0]==0?"黑":"白")+"棋获胜");
            }
            if(flag[1]==1&&isfullchess())
            {
                flag[1]=0;
                JOptionPane.showMessageDialog(null,"棋盘已满，平局");
            }
            if(huiqi[0]!=0)huiqi[1]++;
            flag[0]=(flag[0]==1)?0:1;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
