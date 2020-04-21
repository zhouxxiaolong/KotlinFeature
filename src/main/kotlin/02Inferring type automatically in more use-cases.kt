/**
 * Created
 * 创 建 人: @author zhouxiaolong
 * 创建日期: 2020/4/6
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述: Inferring type automatically in more use-cases
 */
val isAbc = fun(string: String?): Boolean = string == "abc"

fun alwaysTrue(string: String?): Boolean {
    return true
}

val rulesMap: Map<String, (String?) -> Boolean> = mapOf(
    "weak" to { it != null },
    "medium" to { !it.isNullOrBlank() },
    "strong" to { it != null && "^[a-zA-Z0-9]+$".toRegex().matches(it) },
    //1.4版本之前可以使用以下三种方式，注意下面的lambda 参数 it
    "old" to { it -> it != null },
    "isAbc" to isAbc,
    "true" to ::alwaysTrue,
)


fun main() {
    println(rulesMap.getValue("weak")("abc!"))
    println(rulesMap.getValue("strong")("abc"))
    println(rulesMap.getValue("strong")("abc!"))
    println(rulesMap.getValue("old")("abc!"))
    println(rulesMap.getValue("isAbc")("abc"))
    println(rulesMap.getValue("true")(null))
}