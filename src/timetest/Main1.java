package timetest;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        testListenerExecuter test= new testListenerExecuter();
        PercentListener listener = new PercentListener() {
            @Override
            public void updateEvent(PercentEvent dm) {
                // System.out.println("我是获取到事件改变之后的执行的方法");
                System.out.println("我知道percent改变为="+dm.getPercent());
            }
        };
        //将监听器添加给该变量
        test.addPercentListener(listener);
        test.innerExcuter();
    }
}
