package Java;

//接口，用于存储全局变量

public interface   config{
    int [][]chess=new int[20][20];  //存储棋子，1为黑棋，2为白棋，0为空
    int []flag=new int[2];//flag[0]表示该哪位选手下棋，默认为0（黑棋），flag[1]表示是否能够开始游戏 默认为0，不能开始游戏,1为新开游戏，2为继续游戏
    int []huiqi=new int [2];//记录上一次悔棋者，上一次悔棋和这次悔棋之间的距离
    //huiqi[0]为0表示没有悔棋者，为1表示是黑棋，为2表示是白棋
    int []location=new int[2];//记录上一次棋子位置，悔棋操作撤销棋子,撤销或者初始化后重置为0，表示未下棋
    String []ButtonName={"开始游戏","游戏模式","悔棋","认输","规则&关于","退出"};//按键名字

    String []time_string=new String[2];//计数，显示在面板上，time[0]为游戏经过的时间，time[1]为剩余时间

}