/**
 * Created
 * 创 建 人: @author zhouxiaolong
 * 创建日期: 2020/4/6
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述: SAM conversion for kotlin functions and interfaces
 */

/**
 * SAM：Single Abstract Method （单抽象方法的接口）
 * SAM interface
 */
fun interface Action {
    fun run()

//  fun abstractFun()

    //不能有第二个抽象方法
    fun otherFun() {

    }
}

fun interface Action1 {
    fun run()
}

fun interface Action2 {
    fun run()
}

// Kotlin 函数，参数为 Kotlin 单一方法接口
fun runAction(a: Action) = a.run()

// Kotlin 函数，参数为 Java 单一方法接口
fun runRunnable(r: Runnable) = r.run()

fun runFunction(run: () -> Unit) {
    run()
}

fun runActionAll(action1: Action1, action2: Action2) {
    action1.run()
    action2.run()
}

fun main() {
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

    runFunction {
        println("I can run a function")
    }

    //1.4之前的版本两个参数需要都为 lambda 或常规对象，不可以混合
    JavaClass().runAction(
        { println("111") },
        object : JavaClass.Action2 {
            override fun run() {
                println("222")
            }
        })

    runActionAll({ println("111") },
        object : Action2 {
            override fun run() {
                println("222")
            }
        })
}
