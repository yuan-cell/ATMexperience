import kotlin.system.exitProcess

fun main(){
    //欢迎界面
    welcome()
    //密码登录
    login()
    //进入操作界面
    menu(operationMenu)
    //选择操作
    while (true){
        print("请选择操作：")
        when(readLine()){
            "1" -> {
                //取款
                withDrawMoney()
            }
            "2" -> {
                //查询余额
                checkAccount()
            }
            "3" -> {
                //修改密码
                changePassword()
            }
            //退出
            "4" -> exit()
            else -> {
                print("输入错误 ")
            }
        }
    }
}

//记录初始密码
var orgPassword=1234
//记录总金额
var totalMoney = 1000
//保存操作选项
var operationMenu = arrayOf("取款","查询余额","更改密码","退出")

fun welcome(){
    println("*******************")
    println("   欢迎使用ATM    ")
    println("*******************")
}

//登录界面
fun login(){
    var wrongTime = 0
    print("请输入登录密码：")
    while(wrongTime <= 3){
        val inputPassword: String? = readLine()
        if (inputPassword?.toInt() == orgPassword) {
            break
        } else {
            wrongTime++
            if (wrongTime == 3){
                println("三次密码错误 即将退出！！")
                exitProcess(wrongTime)
            }
            print("密码错误 请重新输入：")
        }
    }
}

//菜单界面
fun menu(option:Array<String>){
    println("*******************")
    for ((i,op) in option.withIndex()){
        println("  ${i+1}. $op ")
    }
    println("*******************")
}

//取款
fun withDrawMoney(){
    var outMoney = 0
    var moneyMenu = arrayOf("100","200","500","1000","2000")
    loop@ while (true){
        menu(moneyMenu)
        print("请选择取款金额:")
        val c: String? = readLine()
        if (c.toString() in "1".."5") {
            when (c?.toInt()) {
                1, 2, 3, 4, 5 -> {
                    if (totalMoney >= moneyMenu[c?.toInt()-1].toInt()) {
                        outMoney = moneyMenu[c?.toInt()- 1].toInt()

                        totalMoney -= outMoney
                        println("取款成功 余额为$totalMoney 元！")
                        isContinue()
                        break@loop
                    } else {
                        println("余额不足！！")
                        isContinue()
                    }
                }
                else -> print("输入错误 ")
            }
        } else print("输入错误 ")
    }
}

//查询余额
fun checkAccount(){
    println("余额为：$totalMoney 元！")
    isContinue()
}

//修改密码
fun changePassword(){
    while (true){
        print("请输入原始密码：")
        if (readLine()?.toInt()  == orgPassword){
            print("请输入新的密码：")
            var inputPassword: String? = readLine()
            print("请再次确认密码：")
            if (readLine() == inputPassword){
                println("密码修改成功！！")
                orgPassword = inputPassword?.toInt()!!
                isContinue()
                break
            } else{
                print("两次密码不一致 ")
            }
        } else{
            print("密码错误 ")
        }
    }
}

//退出
fun exit(){
    exitProcess(4)
}

//是否继续
fun isContinue(){
    loop@ while (true){
        print("是否继续（y/n):")
        when(readLine()){
            "y","Y" -> break@loop
            "n","N" -> exit()
            else -> println("输入错误！")
        }
    }
    menu(operationMenu)
}
