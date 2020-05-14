/**
 * Created
 * 创 建 人: @author zhouxiaolong
 * 创建日期: 2020/4/17
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述:
 */
var counter = 0
fun inc() = counter++

//通过kotlin decompile 查看
//1.4
//public static final void test(int i, int x, int y) {
//   while(true) {
//      String var3 = "x: " + x + ", y: " + y;
//      boolean var4 = false;
//      System.out.println(var3);
//      if (i <= 0) {
//         return;
//      }
//
//      --i;
//      x = inc();
//      y = inc();
//   }
//}
tailrec fun test(i: Int, x: Int = inc(), y: Int = inc()) {
    println("x: $x, y: $y")
    if (i > 0) test(i - 1)
}

fun main() {
    test(2)
}

//不会有优化
//   public static final int factorialRecursion(int number) {
//      return number == 1 ? number : number * factorialRecursion(number - 1);
//   }
fun factorialRecursion(number: Int): Int {
    return if (number == 1) number else number * factorialRecursion(number - 1)
}