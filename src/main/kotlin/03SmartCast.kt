/**
 * Created
 * 创 建 人: @author zhouxiaolong
 * 创建日期: 2020/4/6
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述:
 */

val result = run {
    var str = currentValue()
    if (str == null) {
        str = "test"
    }
    str // the Kotlin compiler knows that str is not null here
}

fun currentValue(): String? {
    return null
}
// The type of 'result' is String? in Kotlin 1.3 and String in Kotlin 1.4

fun main() {
    //1.4之前版本执行会判断出result是String?类型
    println(result::class)
}