import kotlin.properties.Delegates

/**
 * Created
 * 创 建 人: @author zhouxiaolong
 * 创建日期: 2020/4/16
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述:
 */

class MyClass {
    var myProp: String by Delegates.observable("<no name>") {
            _, oldValue, newValue ->
        println("My prop: $oldValue -> $newValue")
    }
}

class MyOtherClass {
    val lazyProp by lazy { 42 }
    var myProp: String by Delegates.observable("<no name>") {
            kProperty, oldValue, newValue ->
        println("${kProperty.name}: $oldValue -> $newValue")
    }
}

fun main() {
    val user = MyClass()
    user.myProp = "first"
    user.myProp = "second"
}