import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Created
 * 创 建 人: @author zhouxiaolong
 * 创建日期: 2020/4/19
 * 邮   箱: 1016579848@qq.com
 * 参   考: @link
 * 描   述:
 */
fun textIsValid(s: String?): Boolean {
    return null != s && s.isNotEmpty()
}

//由于Contract契约API还是Experimental，所以需要使用ExperimentalContracts注解声明
@ExperimentalContracts
fun checkTextIsValid(s: String?): Boolean {
    contract {
//        returns(true) implies (s != null)
        returns(true) implies (s is String)
    }
    return null != s && s.isNotEmpty()
}

@ExperimentalContracts
fun main() {

    var str: String? = "ssss"

    if (textIsValid(str)) {
        println(str?.length)
    }

    if (null != str && str.isNotEmpty()) {
        println(str.length)
    }

    if (!str.isNullOrEmpty()) {
        println(str.length)
    }

    if (checkTextIsValid(str)) {
        println(str.length)
    }

}