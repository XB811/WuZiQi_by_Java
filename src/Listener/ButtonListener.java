package Listener;

import Java.Pan;
import Java.config;

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
        int n;
        //触发的按钮是哪一个
        switch (ButtonString) {
            case "开始游戏":
                JButton bu = (JButton) e.getSource();
                bu.setText("重新开始");
                init();
                break;
            case "重新开始":
                flag[1]=0;//暂停游戏
                n=JOptionPane.showConfirmDialog(null,"确认要重新开始游戏吗？","重新开始游戏",JOptionPane.YES_NO_OPTION);
                if(n==JOptionPane.YES_OPTION) {
                    init();
                }
                else{
                    flag[1]=2;//表示是未重新开始游戏，而是暂停后开始的游戏，用于计时器
                }
                break;
            case"悔棋":
                //前提必须是已经开始游戏
                if(flag[1]==1) {
                    if ( location[0] == 0 && location[1] == 0) {
                        flag[1]=0;
                        JOptionPane.showMessageDialog(null, "你还没有下棋", "警告", JOptionPane.WARNING_MESSAGE);
                        flag[1]=2;
                    } else  if(location[0]==18&&location[1]==18) {
                        flag[1]=0;
                        JOptionPane.showMessageDialog(null,"只能撤销一步操作","警告",JOptionPane.WARNING_MESSAGE);
                        flag[1]=2;
                    }
                    else if(huiqi[0]==(flag[0]==0?2:1)&&huiqi[1]==1) {
                        flag[1]=0;
                        JOptionPane.showMessageDialog(null,"只有一次悔棋机会");
                        flag[1]=2;
                    }
                    else{
                        flag[0]=(flag[0]==1)?0:1;//切换选手
                        chess[location[0]][location[1]] = 0;
                        location[0]=18;
                        location[1]=18;
                        huiqi[0]=flag[0]+1;
                        huiqi[1]=0;
                        p.repaint();
                    }
                }

                break;
            case "认输":
                if(flag[1]==1) {
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
                if(flag[1]==0) {
                    n = JOptionPane.showConfirmDialog(null, "要退出游戏吗？", "退出游戏", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
                else{
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
class about extends JFrame{
    public about()
    {
        String s="";
        s+="规则：\n";
        s+="1、点击“开始游戏”/“重新开始”后，才可以进行游戏。\n";
        s+="2、黑方先下，轮流落子。\n";
        s+="3、每一方将自己的棋子放在棋盘的任意交叉点上，一次只能下一个棋子。\n";
        s+="4、先在棋盘上形成五个相同颜色的棋子连成一条线（横、竖、斜线皆可）的一方获胜。\n";
        s+="5、悔棋只能够悔一次.\n";
        s+="\n\n";
        s+="               更新记录\n";
        s+="v1.1:修复bug，添加判断平局功能\n";
        s+="v1.0：实现单机版五子棋基本功能\n";
        JTextArea jt=new JTextArea(s);
        jt.setLineWrap(true);
        jt.setEditable(false);
        jt.setLayout(new FlowLayout());
        jt.setPreferredSize(new Dimension(400,500));
        jt.setFont(new Font("宋体", Font.BOLD, 18));
        add(jt);
        init();
    }
    private void init()
    {
        setResizable(false);      //禁止窗口拉伸
        setTitle("规则&关于");     //窗口名字
        Image icon=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icon.png"));
        setIconImage(icon);     //图标
        setSize(400,500);      //窗口尺寸
        setVisible(true);           //窗口可见
        setLocationRelativeTo(null);        //窗口居中
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //按下关闭按钮后退出程序
    }
}
