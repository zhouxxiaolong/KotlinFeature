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
    //1.3 当中，就需要明确泛型类型<String?>
    var prop: String? by Delegates.observable<String?>(null) { prop, old, new ->
        println("$old → $new")
    }
    prop = "abc"
    prop = "xyz"
}