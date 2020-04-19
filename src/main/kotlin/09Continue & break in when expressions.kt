/**
 * Created
 * 创 建 人: @author zhouxiaolong
 * 创建日期: 2020/4/16
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述:
 */

fun main() {
    val list = listOf(0, 1, 2, "3", 4.0, 5F)
    for (i in list) {
        when (i) {
            0, 1 -> {
                println("0,1")
            }
            2 -> {
                continue
            }
            4.0 -> {
                break
            }
            else -> {
                println(i)
            }
        }
    }
}