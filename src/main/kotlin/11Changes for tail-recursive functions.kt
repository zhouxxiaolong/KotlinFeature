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

tailrec fun test(i: Int, x: Int = inc(), y: Int = inc()) {
    println("x: $x, y: $y")
    if (i > 0) test(i - 1)
}

fun main() {
    test(2)
}