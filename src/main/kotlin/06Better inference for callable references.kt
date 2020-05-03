/**
 * Created
 * 创 建 人: @author zhouxiaolong
 * 创建日期: 2020/4/12
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述:
 */
fun foo(i: Int = 1): String = "$i!"

fun apply1(func: () -> String): String = func()
fun apply2(func: (Int) -> String): String = func(42)

fun main() {
    //在Kotlin 1.3中，foo函数解释为一个带Int参数的函数，因此，apply1 会报类型错误。
    println(apply1(::foo))
    println(apply2(::foo))
}