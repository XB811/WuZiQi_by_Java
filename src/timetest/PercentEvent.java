package timetest;
import java.util.EventObject;
/**
 * @Author: zhangjun
 * @Description: 比例变化的事件实体
 * 每次我监听的对象（testListenerExecuter ）改变的时候，都会把他的改变传递给我（PercentEvent ），我在把改变传递给需要这个改变的地方（run类）。
 * @Date: Create in 17:39 2020/4/30
 */
public class PercentEvent extends EventObject {

    private Object source;

    private int  percent;

    /**
     * 构造方法
     * @param source 监听的对象
     * @param percent 监听的变量
     */
    public PercentEvent(Object source, int  percent) {
        super(source);
        this.source=source;
        this.percent=percent;
    }
    public int getPercent(){
        return percent;
    }
}