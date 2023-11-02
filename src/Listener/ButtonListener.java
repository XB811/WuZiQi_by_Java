package Listener;

import Java.Pan;
import Java.config;
import Java.robot;
import Java.about;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//按键监听
public class ButtonListener implements ActionListener, config {
    Pan p;
    private int fl=0;
    about About=null;
    public ButtonListener(Pan pan){
        p=pan;
    }
    //初始化
    private void init() {
        flag[0]=0;
        flag[1]=1;
        huiqi[0]=0;
        huiqi[1]=0;
        location[0]=0;
        location[1]=0;
        for(int i=1;i<=19;i++)
        {
            for(int j=1;j<=19;j++)
                chess[i][j]=0;
        }
        p.repaint();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String ButtonString = e.getActionCommand();
        JButton bu = (JButton) e.getSource();
        int n;
        //触发的按钮是哪一个
        //仅在模式1或者模式2时，这个监听器才会起作用
        switch (ButtonString) {

            case "开始游戏":
            case "重新开始":
                if(flag[2]==0)JOptionPane.showMessageDialog(null,"请先选择游戏模式","警告",JOptionPane.WARNING_MESSAGE);
                else if(flag[2]==1||flag[2]==2){
                    bu.setText("退出本局游戏");
                    init();//初始化，开始游戏
                }
                break;
            case "退出本局游戏":
                if(flag[2]==1||flag[2]==2) {
                    flag[1] = 0;//暂停游戏
                    n = JOptionPane.showConfirmDialog(null, "确认要退出本局游戏吗？", "退出本局游戏", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        flag[2]=0;  //恢复的选择模式
                        bu.setText("重新开始");
                    } else {
                        flag[1] = 2;//表示是未重新开始游戏，而是暂停后继续的游戏，用于计时器
                    }
                }
                break;
            case "选择游戏模式":
                if(flag[1]==1)JOptionPane.showMessageDialog(null,"正在下棋，请勿更改游戏模式","警告",JOptionPane.WARNING_MESSAGE);
                else {
                    flag[2] = 1;
                    bu.setText("双人模式");
                }
                break;
            case "双人模式":
                if(flag[1]==1)JOptionPane.showMessageDialog(null,"正在下棋，请勿更改游戏模式","警告",JOptionPane.WARNING_MESSAGE);
                else {
                    bu.setText("人机模式");
                    flag[2] = 2;
                }
                break;
            case "人机模式":
                if(flag[1]==1)JOptionPane.showMessageDialog(null,"正在下棋，请勿更改游戏模式","警告",JOptionPane.WARNING_MESSAGE);
                else {
                    bu.setText("联网模式");
                    flag[2] = 3;
                    //联机模式还没有做这里可能还会更改
                }
                break;
            case "联网模式"://联网模式还没做，可能后面这一部分会挪走
                if(flag[1]==1)JOptionPane.showMessageDialog(null,"正在下棋，请勿更改游戏模式","警告",JOptionPane.WARNING_MESSAGE);
                else {
                    bu.setText("选择游戏模式");
                    flag[2] = 0;
                }
            case"悔棋":
                //前提必须是已经开始游戏且为模式1，确认后模式2会电脑自动操作，无法悔棋、
                //必须无未确认棋子
                if(flag[1]==1&&flag[2]==1&&flag[3]==0) {
                    if ( location[0] == 0 && location[1] == 0) {
                        flag[1]=0;
                        JOptionPane.showMessageDialog(null, "你还没有下棋", "警告", JOptionPane.WARNING_MESSAGE);
                        flag[1]=2;
                    } else  if(location[0]==18&&location[1]==18) {
                        flag[1]=0;
                        JOptionPane.showMessageDialog(null,"只能撤销一步操作","警告",JOptionPane.WARNING_MESSAGE);
                        flag[1]=2;
                    }
                    else if(huiqi[0]==(flag[0]==0?2:1)&&huiqi[1]==1) {//如果本次悔棋者和上一次悔棋者是同一个人，且操作间隔只有一步，则禁止悔棋
                        flag[1]=0;
                        JOptionPane.showMessageDialog(null,"只有一次悔棋机会","警告",JOptionPane.WARNING_MESSAGE);
                        flag[1]=2;
                    }
                    else{
                        flag[0]=(flag[0]==1)?0:1;//切换选手
                        chess[location[0]][location[1]] = 0;
                        location[0]=18;//用于连续撤回几次操作
                        location[1]=18;
                        huiqi[0]=flag[0]+1;//悔棋成功后记录悔棋者
                        huiqi[1]=0;//初始化距离上次悔棋的步数
                        p.repaint();
                    }
                } else if (flag[1]==1&&(flag[2]==1||flag[2]==2)&&flag[3]==1) {//未确认前，也可以使用悔棋键撤销操作
                    chess[location[0]][location[1]]=0;
                    flag[3]=0;
                    p.repaint();
                    return;
                }
                break;
            case "确认棋位":
                if(flag[3]==0){
                    flag[1]=0;
                    JOptionPane.showMessageDialog(null,"你还未放棋");
                    flag[1]=2;
                }
                if(flag[1]==1&&(flag[2]==1||flag[2]==2)) {
                    flag[0] = (flag[0] == 0) ? 1 : 0;
                    flag[3]=0;//可以继续放棋子
                    if(flag[2]==2) {
                        robot ro = new robot();
                        ro.run();
                    }
                }
                break;
            case "认输":
                if(flag[1]==1&&(flag[2]==1||flag[2]==2)) {
                    flag[1]=0;
                    n = JOptionPane.showConfirmDialog(null, "当前轮到"+(flag[0]==0?"黑":"白")+"棋下棋，你确认放弃游戏吗？", "认输", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null,(flag[0]==0?"白":"黑")+"棋获胜");
                        flag[1]=0;
                    }
                    else
                        flag[1]=2;
                }
                break;
            case "规则&关于":
                if(fl==0) {
                   About= new about();
                   fl=1;
                }
                else {
                    About.dispose();
                    About = new about();
                }
                break;
            case "退出":
                if(flag[1]==0) {//不在游戏中
                    n = JOptionPane.showConfirmDialog(null, "要退出游戏吗？", "退出游戏", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
                else if(flag[2]==1||flag[2]==2){//在模式1或者模式2
                    flag[1]=0;
                    n = JOptionPane.showConfirmDialog(null, "要退出游戏吗？", "退出游戏", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                    else
                        flag[1]=2;
                }
                break;
        }
    }

}

