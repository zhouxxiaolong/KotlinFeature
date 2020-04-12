/**
 * Created
 * 创 建 人: @author zhouxiaolong
 * 创建日期: 2020/4/12
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述:
 */
public class JavaTest {

    interface Action {
        void run();

//        void run2();
    }

    public static void main(String[] args) {
        Action action = new Action() {
            @Override
            public void run() {
                System.out.println("匿名内部类实现方式");
            }
        };
        action.run();

        Action action2 = () -> System.out.println("lambda实现方式");
        action2.run();

    }
}
