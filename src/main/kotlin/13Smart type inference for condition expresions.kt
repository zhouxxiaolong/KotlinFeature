/**
 * Created
 * 创 建 人: @author zhouxiaolong
 * 创建日期: 2020/4/20
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述:
 */
fun validation(): Boolean {
    return true
}

fun main() {
    val number= if (validation()) 1F else 2.0
    println("number 类型：${number::class.java}")
    if (number > 2) {
        println("$number > 2")
    } else {
        println("$number <= 2")
    }

}

operator fun Number.compareTo(other: Number): Int {
    return this.toDouble().compareTo(other.toDouble())
}