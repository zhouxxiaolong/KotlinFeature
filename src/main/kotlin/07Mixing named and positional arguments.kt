/**
 * Created
 * 创 建 人: @author zhouxiaolong
 * 创建日期: 2020/4/14
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述:
 */
fun f(a: Int = 0, b: Int = 0, c: Int = 0) {
    println("a=$a,b=$b,c=$c")
}

fun main() {
    f(a = 2, 3, 4)
    f(a = 2, 3)
    f(b = 2, c = 3)//c 必须具名
    f(c = 2, a = 1, b = 2, 4)
    //1.4之前这样是可以的
    f(b = 3)
    f(2, b = 3)
    f(2, b = 3, c = 4)
}