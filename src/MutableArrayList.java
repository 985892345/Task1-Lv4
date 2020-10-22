public class MutableArrayList{
	public String[] arr = new String[0];
	private String[] arr1;

	//增加新的元素
	public void add(String add){
		String[] arr2 = add.split(" ");//将加进来的新元素以空格作为分割线组成新的数组
		arr1 = new String[arr.length + arr2.length];//用一个新的数组来搭桥
		//将arr的全部数组写进arr1
		System.arraycopy(arr, 0, arr1, 0, arr.length);
		//将加进来的新元素填充到arr1的后面
		System.arraycopy(arr2, 0, arr1, arr.length, arr2.length);
		arr = new String[arr1.length];//将arr数组初始化，增加长度
		//将arr1全部写进arr
		System.arraycopy(arr1, 0, arr, 0, arr1.length);
	}

	//读取数组长短
	public int size(){
		return arr.length;
	}

	//读取某位元素
	public void get(int position){
		System.out.println("该位为：" + arr[position - 1]);
	}

	//指定位加元素
	public void add(int position, String element){
		arr1 = new String[arr.length + 1];
		arr1[position - 1] = element;
		System.arraycopy(arr, 0, arr1, 0, position - 1);
		System.arraycopy(arr, position - 1, arr1, position, arr.length - position + 1);
		arr = new String[arr1.length];
		System.arraycopy(arr1, 0, arr, 0, arr1.length);
	}
	
	public void change(int position, String element){
		arr[position - 1] = element;
	}

	//删除1位元素，但前面用了个for循环，就是删除多位元素了
	public void remove(int position){
		arr1 = new String[arr.length - 1];
		//将arr中position之前的元素写进arr1
		System.arraycopy(arr, 0, arr1, 0, position - 1);
		//将arr中position之后的元素写进arr1，此时刚好可以省去position位置的元素
		System.arraycopy(arr, position, arr1, position - 1, arr.length - position);
		arr = new String[arr1.length];//将arr数组初始化，增加长度
		System.arraycopy(arr1, 0, arr, 0, arr1.length);//将arr1全部写进arr
	}

	//删除1个元素，但前面用了个for循环，就是删除多个元素了
	public void remove(String element){
		int[] a = new int[arr.length];//用来装需要删掉的元素的位置，
		int b = 0;                    //长度设为arr.length是为了先找到，后面用了c[]重新装
		for(int i = 0; i < arr.length; i++){
			if(element.equals(arr[i])){
				a[b] = i + 1;
				b++;                  //用b来记录有几个元素需要删掉
			}
		}
		if(b == 0){
			System.out.println("不存在" + element + "这个元素");
		}else{
			//System.out.println("b="+b);
			int[] c = new int[b];     //用c[]来去掉a[]后面多的空元素
			//System.out.println("c的位数为"+c[i]);
		    System.arraycopy(a, 0, c, 0, b);
			for(int i = 0; i < b; i++){
				remove(c[i] - i);//妈的，此处想了好久,为什么要减个i？
			}                          //因为我是分开删掉的每个元素的位置，但，删了一个元素后，后面的元素都会向前移一格，所以必须减i
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

