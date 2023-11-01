

//接口，用于存储全局变量

public interface   config{
    int [][]chess=new int[20][20];  //存储棋子，1为黑棋，2为白棋，0为空
    int []flag=new int[2];//flag[0]表示该哪位选手下棋，默认为0（黑棋），flag[1]表示是否能够开始游戏 默认为0，不能开始游戏
    int []location=new int[2];//记录上一次棋子位置，悔棋操作撤销棋子,撤销或者初始化后重置为0，表示未下棋
    String []ButtonName={"开始游戏","悔棋","认输","规则&关于","退出"};//按键名字

}