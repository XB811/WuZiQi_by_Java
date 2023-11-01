import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame implements config{
    public GUI()
    {
        //重置标签
        flag[0]=0;
        flag[1]=0;
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
        JButton []jb=new JButton[5];


        for(int i=0;i<5;i++){
            jb[i]=new JButton(ButtonName[i]);
            jb[i].addActionListener(new ButtonListener(p));
            jb[i].setBounds(800,120+i*120,200,80);
            jb[i].setFont(new Font("宋体", Font.BOLD, 30));
            add(jb[i]);
        }

        add(panel);
        add(p);
        init();
    }
    private void init()
    {
        setResizable(false);        //禁止窗口拉伸
        setTitle("单机版五子棋");     //窗口名字
        Image icon=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png"));
        setIconImage(icon);     //图标
        setSize(1016,840);      //窗口尺寸
        setVisible(true);           //窗口可见
        setLocationRelativeTo(null);        //窗口居中
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //按下关闭按钮后退出程序
    }
}


