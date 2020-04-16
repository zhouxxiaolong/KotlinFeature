import kotlin.properties.Delegates

/**
 * Created
 * 创 建 人: @author zhouxiaolong
 * 创建日期: 2020/4/13
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述:
 */
fun main() {
    var prop: String? by Delegates.observable(null) { p, old, new ->
        println("$old → $new")
    }
    prop = "abc"
    prop = "xyz"
}