/**
 * Created
 * 创 建 人: zhouxiaolong
 * 创建日期: 2019/9/4
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述:
 */
public class JavaClass {
    interface Action1 {
        void run();
    }

    interface Action2 {
        void run();
    }

    void runAction(Action1 action1, Action2 action2) {
        action1.run();
        action2.run();
    }
}
