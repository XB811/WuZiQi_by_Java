package Java;

import Interface.config;

import java.util.Random;

public class robot implements config {
    private int[] getloc(int x,int y) {
        int count = 1;
//		定义计数器，用于计算棋子数
        int[] wz1 = null;
        int[] wz2 = null;
//		定义数组，用来存储危险位置
        for(int i =x-1;i>0;i--){
            if(chess[i][y] == chess[x][y])
            {
                count++;
            }
            else
            {
                if(chess[i][y] == 0){
                    wz1 = new int[]{i,y};
//					获取左边的危险位置坐标
                }
                break;
            }
        }
//		左
        for(int i =x+1;i<18;i++){
            if(chess[i][y] == chess[x][y])
            {
                count++;
            }else{
                if(chess[i][y] == 0){
                    wz2 = new int[]{i,y};
//					获取有变位置危险坐标
                }
                break;
            }
        }
//		右
        if(count>=3)
        {
            //				判断返回右边危险位置
            if(wz1 != null){
                return wz1;
//				判断返回左边危险位置
            }else return wz2; //如果wz2也未空，返回wz2 即null
        }
//		左右
        count = 1;
        wz1 = null;
        wz2 = null;
//		初始化所有参数
        for(int j =y-1;j>0;j--){
            if(chess[x][j] == chess[x][y])
            {
                count++;
            }
            else
            {
                if(chess[x][j] == 0){
                    wz1 = new int[]{x,j};
                }
                break;
            }
        }
//		上
        for(int j =y+1;j<18;j++){
            if(chess[x][j] == chess[x][y])
            {
                count++;
            }else{
                if(chess[x][j] == 0){
                    wz2 = new int[]{x,j};
                }
                break;
            }
        }
//		下
        if(count>=3)
        {
            if(wz1 != null){
                return wz1;
            }else if(wz2 != null){
                return wz2;
            }else{
                return null;
            }
        }
//		上下
        count = 1;
        wz1 = null;
        wz2 = null;
        for(int i =x-1,j =y-1;i>0&&j>0;i--,j--){
            if(chess[i][j] == chess[x][y])
            {
                count++;
            }
            else
            {
                if(chess[i][j] == 0){
                    wz1 = new int[]{i,j};
                }
                break;
            }
        }
//		左上
        for(int i =x+1,j =y+1;i<18&&j<18;i++,j++){
            if(chess[i][j] == chess[x][y])
            {
                count++;
            }else{
                if(chess[i][j] == 0){
                    wz2 = new int[]{i,j};
                }
                break;
            }
        }
//		右下
        if(count>=3)
        {
            if(wz1 != null){
                return wz1;
            }else return wz2;
        }
//		左上右下
        count = 1;
        wz1 = null;
        wz2 = null;
        for(int i =x-1,j =y+1;i>0&&j<18;i--,j++){
            if(chess[i][j] == chess[x][y])
            {
                count++;
            }
            else
            {
                if(chess[i][j] == 0){
                    wz1 = new int[]{i,j};
                }
                break;
            }
        }
//		左下
        for(int i =x+1,j =y-1;i<18&&j>0;i++,j--){
            if(chess[i][j] == chess[x][y])
            {
                count++;
            }else{
                if(chess[i][j] == 0){
                    wz2 = new int[]{i,j};
                }
                break;
            }
        }
//		右上
        if(count>=3)
        {
            if(wz1 != null){
                return wz1;
            }else if(wz2 != null){
                return wz2;
            }else{
                return null;
            }
        }
//		左下右上
        return null;
    }
    public void run(Pan p)
    {

        int i=location[0], j=location[1];
        Random r=new Random();
        do{
            int[] wz =getloc(i, j);
            if(wz == null){
                do {
                    i = r.nextInt(18);
                    j = r.nextInt(18);
                }while(i==0||j==0);
            }else{
                i=wz[0];
                j=wz[1];
            }
//			设置随机的坐标
        }while(chess[i][j] != 0);
        //System.out.println(i+"  "+j);
        chess[i][j]=flag[0]+1;
        flag[0] = flag[0]==0?1:0;
        p.repaint();

    }
}
