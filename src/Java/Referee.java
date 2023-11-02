package Java;

import Interface.referee;
import Interface.config;

import javax.swing.*;

public class Referee implements referee,config {
    public boolean judgment(int qx,int qy){
        //起始位置和终止位置，不能够越界
        int begin_x=Math.max(qx-4,1);
        int begin_y=Math.max(qy-4,1);
        int end_x=Math.min(qx+4,17);
        int end_y=Math.min(qy+4,17);
        boolean bo;
        for(int x=begin_x;x<=qx;x++)//横向
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
        for(int y=begin_y;y<=qy;y++)//竖向
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
        for(int i=0;i<5;i++)//左上倒右下
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
        for(int i=0;i<5;i++)//坐下到右上
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
    public boolean isfullchess() {
        for(int i=1;i<=17;i++)
            for(int j=1;j<=17;j++)
                if(chess[i][j]==0)return false;
        return true;
    }
    public void judge_and_output()
    {
        if (judgment(location[0], location[1])) {
            flag[1] = 0;
            if(flag[2]==2) {
                JOptionPane.showMessageDialog(null, (flag[0] == 0 ? "玩家" : "机器") + "获胜");
                return;
            }
            JOptionPane.showMessageDialog(null, (flag[0] == 0 ? "黑" : "白") + "棋获胜");
        }
        if (isfullchess()) {
            flag[1] = 0;
            JOptionPane.showMessageDialog(null, "棋盘已满，平局");
        }
    }
}
