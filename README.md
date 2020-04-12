# KotlinFeature
> Kotlin 1.4 NewFeature 

## Kotlin 接口和函数的 SAM（Single Abstract Method） 转换
首先需要创建一个SAM interface，需要在接口前加上`fun`关键字

```
fun interface Action {
    fun run()

//    fun abstractFun()

    //不能有第二个抽象方法
    fun otherFun() {

    }
}
```

声明函数
```
// Kotlin 函数，参数为 Kotlin 单一方法接口
fun runAction(a: Action) = a.run()

// Kotlin 函数，参数为 Java 单一方法接口
fun runRunnable(r: Runnable) = r.run(
```

调用

```
    //1.4 以前，只能这样调用
    runAction(object : Action {
        override fun run() {
            println("Not good...")
        }
    })

    //或者，只能这样调用
    runAction(Action {
        println("Not good...")
    })

    //还是传递的对象
    //-----------------------------------------------
    //1.4之后，传递lambda
    runAction {
        println("Hello, Kotlin 1.4!")
    }

    runRunnable {
        println("Hello, Kotlin 1.4!")
    }
```
Tips:
可能有同学会有疑问，咦，这个不是早就支持了吗？比如View点击事件啊?

```
view.setOnClickListener{
    println("click")
}
```
其实不是的，因为这里的setOnClickListener是一个java方法，OnClickListener也是一个java接口，要是其中的任何一个条件不满足，在1.4之前的版本都是不支持SAM的。例如setOnClickListener是一个kotlin方法又或者OnClickListener 是一个kolin接口。

## 类型推导支持了更多的场景
类型推导让 Kotlin 的语法获得了极大的简洁性。不过，大家在使用 Kotlin 开发时，一定会发现有些情况下明明类型是很确定的，编译器却一定要让我们显式的声明出来，这其实就是类型推导算法没有覆盖到的场景了。

```
val isAbc = fun(string: String?): Boolean = string == "abc"

fun alwaysTrue(string: String?): Boolean {
    return true
}

val rulesMap: Map<String, (String?) -> Boolean> = mapOf(
    "weak" to { it != null },
    "medium" to { !it.isNullOrBlank() },
    "strong" to { it != null && "^[a-zA-Z0-9]+$".toRegex().matches(it) },
    //1.4版本之前使用以下两种方式
    "isAbc" to isAbc,
    "true" to ::alwaysTrue,
)


fun main() {
    println(rulesMap.getValue("weak")("abc!"))
    println(rulesMap.getValue("strong")("abc"))
    println(rulesMap.getValue("strong")("abc!"))
    println(rulesMap.getValue("isAbc")("abc"))
    println(rulesMap.getValue("true")(null))
}
```
Tips:
这个问题我在1.3版本的kotlin中运行会报错
`Kotlin: Type inference failed. Expected type mismatch: inferred type is Map<String, Function<Boolean>> but Map<String, (String?) -> Boolean> was expected`

## Lambda 表达式最后一行的智能类型转换

```
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
```
# 参考资料

【1】[Kotlin公众号](https://mp.weixin.qq.com/s?__biz=MzIzMTYzOTYzNA==&mid=2247484576&idx=1&sn=d61d2fd19ce4fbf380d31283687deeec&chksm=e8a05b9ddfd7d28b9bb1d1146afbe2ef70b3f53892edfc536536badb1ab2d6b6fc04b9a18ddb&mpshare=1&scene=1&srcid=&sharer_sharetime=1586662469474&sharer_shareid=0d0ed9221bc1223986748805236d1451#rd)

