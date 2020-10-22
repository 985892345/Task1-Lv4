import java.util.*;

/*
 *cqupt
 *2020.10.20
 *2767465918@qq.com
 *花了一天写完，再写个注释，留个纪念，现在你看到的是我优化了几次后的版本
 *由于用object太麻烦，功能太少，用string来写他不香吗？
 *记录我最多的报错："java.lang.ArrayIndexOutOfBoundsException: "每回都是数组越界问题
 *突然发现原来idea还能改进代码！把添加数组的for循环全部改了
 *
 */

public class Lv4 {
	static MutableArrayList list = new MutableArrayList();
	public static void main(String[] args){
		start();
	}

	//开始输入的数组
	public static void start(){
		System.out.println("请输入你想加入的数组（空格用来分割，支持文字）：");
		String s = input();
		if(s.length() <= 0){
			System.out.println("别捣乱！不要输入空数组");
			start();
		}else{
			list.add(s);
			System.out.println("该数组内有：" + list.size() + "个元素" + "\n");
			print(1);
			judge(input());
		}
	}
	
	//选择功能的判断
	public static void judge(String s){
		if(s.length() <= 0){
			finish(1);
		}else if(s.equals(Integer.toString(1))){
			//增加新的元素
			System.out.println("请输入要添加的新元素" + "\n" + 
					           "（新元素将会添加到数组后面，支持添加多个元素）");
			list.add(input());
			print(2);
			finish(2);
		}else if(s.equals(Integer.toString(2))){
			//读取数组长短
			System.out.println("该数组内有：" + list.size() + "个元素");
			finish(2);
		}else if(s.equals(Integer.toString(3))){
			//读取某位元素
            System.out.println("请输入想要读取的元素的位数：");
			String a = input();
			if(a.length() <= 0){
				System.out.println("输入了回车，请重新输入");
				judge(s);
			}else if(a.length() >= 2){
				System.out.println("输入多位数据，请重新输入");
				judge(s);
			}else if(Integer.parseInt(a) <= list.size() && Integer.parseInt(a) >= 1){
				list.get(Integer.parseInt(a));
			}else{
				OutOfBounds(Integer.parseInt(a), s);
			}
			finish(2);
		}else if(s.equals(Integer.toString(4))){
			//指定位加元素
            System.out.println("请输入想要添加元素的位数和想添加的新元素：" + "\n" +
					           "（格式为：位数 新元素）");
			String[] in = input().split(" ");
			if(in.length != 2){//判断输入的数据是否为两个
				System.out.println("格式错误，请重新输入！");
				judge(s);
			}else if(Integer.parseInt(in[0]) <= list.size() && Integer.parseInt(in[0]) >= 1){//判断位数是否超过，下面else为超过
				list.add(Integer.parseInt(in[0]), in[1]);
				print(2);
			}else{
				OutOfBounds(Integer.parseInt(in[0]), s);
			}
			finish(2);
		}else if(s.equals(Integer.toString(5))){
			//修改某位元素
            System.out.println("请输入想要修改元素的位数和想改的新元素：" + "\n" +
					           "（格式为：位数 新元素）");
			String[] in = input().split(" ");
			if(in.length != 2){//判断输入的数据是否为两个
				System.out.println("格式错误，请重新输入！");
				judge(s);
			}else if(Integer.parseInt(in[0]) <= list.size() && Integer.parseInt(in[0]) >= 1){//判断位数是否超过，下面else为超过
				list.change(Integer.parseInt(in[0]), in[1]);
				print(2);
			}else{
				OutOfBounds(Integer.parseInt(in[0]), s);
			}
			finish(2);
		}else if(s.equals(Integer.toString(6))){
			//删除多位元素
            System.out.println("请输入想要删除的元素的位数（多个位数用空格隔开）：");
			int j = 0;  //这个j很关键，用来计算运行了几次正确的判断
			int length = list.size();
			String[] in = input().split(" ");
			Arrays.sort(in);//防止用户先删后面几位
			for (String value : in) {
				if (0 < Integer.parseInt(value) && Integer.parseInt(value) <= length) {
					list.remove(Integer.parseInt(value) - j);//运行了一次，则会生成一个新的数组，则后面的位置就会前移
					j++;
				} else {
					System.out.println(Integer.parseInt(value) > length ?
							("没有第" + value + "位元素，最多只有" + list.size() + "位元素") :
							("输入" + value + "位元素错误，位数是从1开始"));
				}
			}
			print(2);
			finish(2);
		}else if(s.equals(Integer.toString(7))){
			//删除多个元素
            System.out.println("请输入想要删除的元素（多个元素用空格隔开）：");
			String[] in = input().split(" ");
			for(String value: in){
				list.remove(value);
			}
			print(2);
			finish(2);
		}else{
			finish(1);
		}
	}

	//功能执行完后调用
	public static void finish(int a){
		System.out.println(a == 1 ? //三目运算
						   ("\n" + 
					       "请输入1~7的数字" + "\n" + 
					       "若需重新选择请按回车" + "\n" + 
					       "输入其他退出本程序"):
						   ("\n" + 
						   "若使用其他功能请按回车" + "\n" + 
						   "输入其他退出本程序"));
		String s = input();
		if(s.length() <= 0){
			print(1);
			judge(input());
		}else{
			System.out.println("程序结束，欢迎使用！");
			System.exit(0);
		}
	}

	public static void print(int b){
		if(b == 1){
			System.out.println("本程序有以下功能：" + "\n" + 
							   "1、增加新的元素" + "\n" + 
							   "2、读取数组长短" + "\n" + 
							   "3、读取某位元素" + "\n" + 
							   "4、指定位加元素" + "\n" + 
							   "5、修改某位元素" + "\n" +
							   "6、删除多位元素" + "\n" + 
							   "7、删除多个元素" + "\n" + 
							   "请输入想要实现的功能：");
		}else if(b == 2){//改了元素后的打印新数组
			System.out.println("新数组为：");
			for(Object a: MutableArrayList.arr){
				System.out.print(a + " ");
			}
		}
	}

	//从键盘输入值
	public static String input(){
		Scanner input = new Scanner(System.in);
		return input.nextLine();
	}
	
	//判断输入的位数是否越界
	public static void OutOfBounds(int p, String b){
		if(p > list.size()){
			System.out.println("超出数组长度，最多只有" + list.size() + "位元素，请重新输入！");
			judge(b);
		}else if(p <= 0){
			System.out.println("位数是从1开始，请重新输入");
			judge(b);
		}
	}
}
//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                      .   ' \\| |// `.
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .............................................
//                  佛祖保佑             永无BUG
//             佛曰:
//                    写字楼里写字间，写字间里程序员；
//                    程序人员写程序，又拿程序换酒钱。
//                    酒醒只在网上坐，酒醉还来网下眠；
//                    酒醉酒醒日复日，网上网下年复年。
//                    但愿老死电脑间，不愿鞠躬老板前；
//                    奔驰宝马贵者趣，公交自行程序员。
//                    别人笑我忒疯癫，我笑自己命太贱；
//                    不见满街漂亮妹，哪个归得程序员？

