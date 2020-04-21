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

还有一种写法：

```
fun runFunction(run: () -> Unit) {
    run()
}

runFunction {
    println("I can run a function")
}
```

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
    //1.4版本之前可以使用以下三种方式，注意下面的lambda 参数it
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
```
Tips:
这个问题我在1.3版本的kotlin中编译会报错
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

## 带有默认参数的函数的类型支持
如果一个函数有默认参数，我们在调用它的时候就可以不传入这个参数了，例如：
`fun foo(i: Int = 0): String = "$i!"`
调用的时候既可以是 foo() 也可以是 foo(5)

在 1.4 以前，如果我们想要获取它的引用，就只能获取到 (Int) -> String 这样的类型，显得不是很方便，现在这个问题解决了：

```

fun apply1(func: () -> String): String = func()
fun apply2(func: (Int) -> String): String = func(42)

fun main() {
    println(apply1(::foo))
    println(apply2(::foo))
}
```
## 属性代理的类型推导
在推断代理表达式的类型时，以往不会考虑属性代理的类型，因此我们经常需要在代理表达式中显式的声明泛型参数，下面的例子就是这样：

```
    var prop: String? by Delegates.observable<String?>(null) { p, old, new ->
        println("$old → $new")
    }
    prop = "abc"
    prop = "xyz"
```
这个例子在 1.4 中可以运行，但如果是在 1.3 当中，就需要明确泛型类型：

```
    var prop: String? by Delegates.observable<String?>(null) { p, old, new ->
        println("$old → $new")
    }
```

##混合位置参数和具名参数
位置参数就是按位置传入的参数，java中只支持位置参数
具名参数就是按名字传入的参数，可以完全不按照位置传，kotlin支持，但是1.4之前，kotlin 不支持混合
比如以下的写法是不可以的

```
fun f(a: Int, b: Int, c: Int) {
    println("a=$a,b=$b,c=$c")
}

fun main() {
    f(a = 2, 3, 4)
    //1.4之前这样是可以的
    f(2, b = 3, c = 4)
}
```
假如混用呢，如下
` f(c = 1, a = 2, 3, 4)`
会发现c=1不认。

## 优化属性代理的编译（没懂）

## 参数列表最后的逗号
很简单，JavaScript之前就支持
```
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
```
Tips:
JSON 的最后一个字段后面是不允许加逗号的

## when 表达式中使用 continue 和 break
continue 和 break 的含义没有任何变化，1.4版本之前是不可以使用continue 和 break 的


```

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
```

## 尾递归函数的优化
两个变动：
尾递归函数的默认参数的初始化顺序改为从左向右
```
var counter = 0
fun inc() = counter++

tailrec fun test(i: Int, x: Int = inc(), y: Int = inc()) {
    println("x: $x, y: $y")
    if (i > 0) test(i - 1)
}

fun main() {
    test(1)
}
```

```
1.4：
x: 0, y: 1
x: 2, y: 3

1.3：
x: 0, y: 1
x: 3, y: 2
```

## 契约(Experimental)
从 1.3 开始，Kotlin 引入了一个实验特性契约（Contract），主要来应对一些“显而易见”情况下的类型推导或者智能类型转换。

在 1.4 当中，这个特性仍然继续保持实验状态，不过有两项改进：

* 支持使用内联特化的函数来实现契约
* 1.3当中不能为成员函数添加契约，从1.4开始支持为 final 的成员函数添加契约（当然任意成员函数可能存在被覆写的问题，因而不能添加）

```
@ExperimentalContracts
inline fun CharSequence?.checkTextIsValid(otherCheck: () -> Boolean): Boolean {
    contract {
//        returns(true) implies (s != null)
        returns(true) implies (this@checkTextIsValid is String)
    }
    return null != this && this.isNotEmpty() && otherCheck.invoke()
}
```

```
open class HelloKotlinFeature {
    @ExperimentalContracts
    /**open*/ fun checkTextIsValid(s: String?): Boolean {
        contract {
            returns(true) implies (s is String)
        }
        return null != s && s.isNotEmpty()
    }
}
```


# 参考资料

[Kotlin公众号](https://mp.weixin.qq.com/s?__biz=MzIzMTYzOTYzNA==&mid=2247484576&idx=1&sn=d61d2fd19ce4fbf380d31283687deeec&chksm=e8a05b9ddfd7d28b9bb1d1146afbe2ef70b3f53892edfc536536badb1ab2d6b6fc04b9a18ddb&mpshare=1&scene=1&srcid=&sharer_sharetime=1586662469474&sharer_shareid=0d0ed9221bc1223986748805236d1451#rd)

