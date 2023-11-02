package Java;

import java.awt.*;
import javax.swing.*;
import Listener.*;
import java.lang.*;
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
        JButton []jb=new JButton[6];

        //计时器面板
        JTextArea []jta=new JTextArea[2];
        jta[0]=new JTextArea("");
        jta[1]=new JTextArea("");
        jta[0].setBounds(800,0,200,60);
        jta[1].setBounds(800,60,200,60);
        add(jta[0]);
        add(jta[1]);

        for(int i=0;i<6;i++){
            jb[i]=new JButton(config.ButtonName[i]);
            jb[i].addActionListener(new ButtonListener(p));
            jb[i].setBounds(800,120+i*80,200,80);
            jb[i].setFont(new Font("宋体", Font.BOLD, 30));
            add(jb[i]);
        }

        add(panel);
        add(p);
        init();
        jishi(jta);
    }
    private void jishi(JTextArea []jta){
        int []last_flag=new int[2];//表示上次玩家和上次的是否开始游戏
        last_flag[0]=0;
        last_flag[1]=0;
        long []last_time=new long[2];//记录上一次切换玩家和上一次开始游戏
        long totaltime=0;
        while(true){
            if(last_flag[1]==flag[1])
            {
                if(flag[1]==1)//表示正在游戏
                {
                    totaltime=System.currentTimeMillis()-last_time[1];
                }
            }
            else{
                if(flag[1]==1)//表示开始游戏
                {
                    last_time[1]=System.currentTimeMillis();
                    totaltime=System.currentTimeMillis()-last_time[1];
                    last_flag[1]=1;
                }
                else if(flag[1]==2) {
                    flag[1]=1;
                    totaltime=System.currentTimeMillis()-last_time[1];
                    last_flag[1]=1;
                }
                else{
                    last_flag[1]=0;
                }
            }
            time_string[1]="游戏时长："+ totaltime/60000+':'+totaltime/1000+':'+totaltime%1000;
            jta[0].setText(time_string[1]);
            //System.out.println(time_string[1]+"\n");

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


