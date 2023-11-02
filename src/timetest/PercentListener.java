package timetest;
import java.util.EventListener;

/**
 * @Author: zhangjun
 * @Description: 比例变化执行的监听器
 * @Date: Create in 18:38 2020/4/30
 */
public interface PercentListener extends EventListener {
    /**
     *  事件变化后执行的方法,自己定义的
     * @param dm
     */
    public void updateEvent(PercentEvent dm);
}
