package Java;

import javax.swing.*;
import java.awt.*;

public class about extends JFrame {
    public about()
    {
        String s="";
        s+="规则：\n";
        s+="1、点击“开始游戏”/“重新开始”后，才可以进行游戏。\n";
        s+="2、黑棋先手，轮流落子。\n";
        s+="3、每一方将自己的棋子放在棋盘的任意交叉点上，一次只能下一个棋子。\n";
        s+="4、先在棋盘上形成五个相同颜色的棋子连成一条线（横、竖、斜线皆可）的一方获胜。\n";
        s+="5、人机模式玩家先手\n";
        s+="6、按下确认键前可以悔棋多次(悔棋键或者再次点击之前所下的棋子），确认后仅在双人模式下且对方未下棋前可以悔棋一次\n";
        s+="7、联机模式还没做\n";
        s+="\n\n";
        s+="               更新记录\n";
        s+="v1.1:修复bug，添加判断平局功能，时间显示，人机对战功能\n";
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