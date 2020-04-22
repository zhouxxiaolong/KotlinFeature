import kotlin.reflect.KFunction

/**
 * Created
 * 创 建 人: @author zhouxiaolong
 * 创建日期: 2020/4/12
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述:
 */
sealed class Animal

class Cat : Animal() {
    fun meow() {
        println("meow")
    }
}

class Dog : Animal() {
    fun woof() {
        println("woof")
    }
}

fun perform(animal: Animal) {
    val kFunction: KFunction<*> = when (animal) {
        is Cat -> animal::meow
        is Dog -> animal::woof
    }

    kFunction.call()
}

fun main(){
    perform(Cat())
    perform(Dog())
}