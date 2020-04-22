/**
 * Created
 * 创 建 人: @author zhouxiaolong
 * 创建日期: 2020/4/16
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述:
 */
data class Person(val name: String, val age: Int, val id: Int)

fun main() {
    val person = Person(
        "huahua",
        30,
        1,
    )
    val s = listOf(
        1,
        2,
        3,
        4,
    )
}