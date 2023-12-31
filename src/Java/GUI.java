package Java;

import java.awt.*;
import javax.swing.*;

import Interface.config;
import Listener.*;
import java.lang.*;
import java.text.DecimalFormat;

public class GUI extends JFrame implements config {
    public GUI()
    {
        //重置标签
        config.flag[0]=0;
        config.flag[1]=0;
        //设置窗口布局为空，直接使用坐标放置容器
        setLayout(null);
        //棋盘组件
        Pan p=new Pan();
        p.setBounds(40,40,721,721);
        p.setBackground(new Color(236,196,161));
        p.addMouseListener(new PanelListener());
        //按键
        JPanel panel=new JPanel();
        panel.setBackground(new Color(255,255,255));
        panel.setBounds(800,0,200,800);
        JButton []jb=new JButton[7];

        //计时器面板
        JTextArea []jta=new JTextArea[2];
        for(int i=0;i<2;i++) {
            jta[i] = new JTextArea("");
            jta[i].setBounds(800, 60*i, 200, 60);
            jta[i].setEditable(false);
            jta[i].setEnabled(false);
            jta[i].setFont(new Font("宋体", Font.BOLD, 18));
            jta[i].setDisabledTextColor(Color.red);
            add(jta[i]);
        }

        for(int i=0;i<7;i++){
            jb[i]=new JButton(config.ButtonName[i]);
            jb[i].addActionListener(new ButtonListener(p));
            jb[i].setBounds(800,120+i*80,200,80);
            jb[i].setFont(new Font("宋体", Font.BOLD, 24));
            add(jb[i]);
        }

        add(panel);
        add(p);
        init();
        new about();
        timerAdd(jta,jb[1]);
    }
    //计时器
    private void timerAdd(JTextArea []jta,JButton jb){
        int []last_flag=new int[2];//表示上次玩家和上次的是否开始游戏
        last_flag[0]=1;
        //last_flag[1]=0;
        long []last_time=new long[3];//记录上一次切换玩家,上一次开始游戏,上一次暂停游戏
        long totaltime=0;
        long compensate_time=0;//总游戏的时间补偿
        long player_compensate_time=0;//切换玩家时，不再需要加上之前的时间补偿
        long playertime=60000;
        while(true){   //计时器以及复原模式选择按钮
            long x=System.currentTimeMillis();
            //总时间计数
            if(last_flag[1]==flag[1])
            {
                if(flag[1]==1)//表示正在游戏
                {
                    totaltime=x-last_time[1];
                }
            }
            else{   //flag[1]发生变化的情况
                if(flag[1]==1)//表示开始游戏，初始化数组
                {
                    last_time[1]=x;
                    totaltime=System.currentTimeMillis()-last_time[1];
                    last_flag[1]=1;
                    compensate_time=0;//重新开始游戏，则补偿时间要置零
                    player_compensate_time=0;
                    last_flag[0]=1;//把上一次玩家置为白棋，让计时器初始化
                }
                else if(flag[1]==2) {//暂停游戏后开始，避免初始化
                    flag[1]=1;
                    totaltime=x-last_time[1];//避免重置上一次开始游戏的时间点
                    compensate_time+=x-last_time[2];//补偿时间
                    last_flag[1]=1;
                }
                else{   //暂停游戏
                    last_flag[1]=0;
                    last_time[2]=System.currentTimeMillis();
                }
            }
            //倒计时
            if(flag[1]==1) {
                if (last_flag[0] == flag[0])
                    playertime = 60000 - (System.currentTimeMillis() - last_time[0]);
                else {
                    last_time[0] = System.currentTimeMillis();
                    playertime = 60000 - (System.currentTimeMillis() - last_time[0]);
                    last_flag[0] = flag[0];
                    player_compensate_time=compensate_time;//如果切换玩家，则不需要在加上之前的时间补偿
                }
                //时间补偿,游戏运行时，totaltime和playertime是根据系统时间实时计算的，多算了暂停的时间，但是系统暂停时，显示的是暂停前存储的数据
                totaltime-=compensate_time;
                playertime+=compensate_time-player_compensate_time;//有bug
            }


            if(playertime<=0)
            {
                JOptionPane.showMessageDialog(null,"已超时"+(flag[0]==1?"黑":"白")+"棋获胜");
                flag[1]=0;
            }
            DecimalFormat d1=new DecimalFormat("00");
            DecimalFormat d2=new DecimalFormat("000");
            time_string[1]="游戏时长："+ d1.format(totaltime/60000)+':'+d1.format((totaltime%60000)/1000)+':'+d2.format(totaltime%1000);
            time_string[0]="剩余时长："+d1.format(playertime/60000)+':'+d1.format((playertime%60000)/1000)+':'+d2.format(playertime%1000);
            jta[1].setText(time_string[0]);
            jta[0].setText(time_string[1]);
            if(flag[2]==0&&!jb.getText().equals("选择游戏模式"))//重新开始游戏后，需要再次选择模式
                jb.setText("选择游戏模式");

        }

    }
    private void init()
    {
        setResizable(false);        //禁止窗口拉伸
        setTitle("单机版五子棋");     //窗口名字
        Image icon=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icon.png"));
        setIconImage(icon);     //图标
        setSize(1016,840);      //窗口尺寸
        setVisible(true);           //窗口可见
        setLocationRelativeTo(null);        //窗口居中
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //按下关闭按钮后退出程序

    }
}


