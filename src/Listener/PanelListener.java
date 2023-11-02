package Listener;

import Java.Pan;
import Interface.config;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

//棋盘监听
public class PanelListener implements MouseListener, config {

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        if(flag[2]==3)return;//联网模式不执行
        Pan p=(Pan)e.getSource();
        if(flag[1]==1&&e.getX()>=20&&e.getX()<700&&e.getY()>=20&&e.getY()<700)
        {
            int lx=(e.getX()+20)/40;
            int ly=(e.getY()+20)/40;
            int cx=lx*40;
            int cy=ly*40;
            if(Math.sqrt((e.getX()-cx)*(e.getX()-cx)+(e.getY()-cy)*(e.getY()-cy))>15)return;//判定范围以点圆心，半径15的圆
            if(lx==location[0]&&ly==location[1]&&chess[lx][ly]==flag[0]+1){//同一个位置，未确认前，撤销棋子
                chess[lx][ly]=0;
                flag[3]=0;
                p.repaint();
                return;
            }
            if(flag[3]==1){
                flag[1]=0;
                JOptionPane.showMessageDialog(null,"你已经放过棋子了，请先确认，确认前可再次点击棋子撤销，重新放棋");
                flag[1]=2;
                return;//已经放棋，不能再放棋子
            }
            if(chess[lx][ly]!=0)return;//同一个位置判断是否放过棋子
            location[0] = lx;
            location[1] = ly;
            chess[location[0]][location[1]] = flag[0] + 1;
            p.repaint();
            if (huiqi[0] != 0) huiqi[1]++;
            flag[3]=1;//已放棋子，未确认
            //flag[0] = (flag[0] == 1) ? 0 : 1;  //自动切换选手功能已报废，选手切换功能放在确认按钮上
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
