public class MutableArrayList{
	public String[] arr = {};//此为原数组
	private static int length = 0;//用length来计算数组长度

	//增加新的元素
	//思路为原数组多缓存位置，如果每次都重新修改原数组长度，会降低效率
	public void add(String add){
		String[] arr1 = add.split(" ");//将加进来的新元素以空格作为分割线组成新的数组
		//将新加进来的全部元素直接写到原数组后面
		if(arr1.length + length > arr.length){//判断新加进来的数组是否会超过原数组总长度，超过则多缓存位置
			String[] arr2 = arr;//后面初始化原数组会清空原数组的元素，所以要复制一份，后面会把它写入原数组前面
			arr = new String[length + 2 * arr1.length];//初始化数组，并缓存多的2倍新加进来数组的长度
			System.arraycopy(arr2, 0, arr, 0, length);//将之前的原数组元素写到前面
		}//当新加进来的数组不超过原数组长度时
		System.arraycopy(arr1, 0, arr, length, arr1.length);//将新加进来的全部元素写到原数组后面
		length += arr1.length;//增加length
	}

	//读取数组长短
	public int size(){
		return length;
	}

	//读取某位元素
	public void get(int position){
		System.out.println("该位为：" + arr[position - 1]);
	}

	//指定位加元素
	public void add(int position, String element){
		System.arraycopy(arr, position - 1, arr, position, length - position + 1);
		arr[position - 1] = element;
	}

	//改变某位数组
	public void change(int position, String element){
		arr[position - 1] = element;
	}

	//删除1位元素，但前面用了个for循环，就是删除多位元素了
	public void remove(int position){
		length--;//去一个元素减1长度
		//将arr中position之后的元素写进原数组，此时刚好可以覆盖position位置的元素
		//如：数组0 1 2 3，要删掉1，则position = 2
		System.arraycopy(arr, position, arr, position - 1, length - position + 1);
	}

	//删除1个元素，但前面用了个for循环，就是删除多个元素了
	public void remove(String element){
		int[] a = new int[length];//用来装需要删掉的元素的位置，
		int b = 0;
		for(int i = 0; i < length; i++){
			if(element.equals(arr[i])){
				a[b] = i + 1;         //a[b]中的b表示有几个元素需要删掉
				b++;                  //用i+1来记录要删除元素的位置
			}
		}
		if(b == 0){
			System.out.println("不存在" + element + "这个元素");
		}else{
			for(int i = 0; i < b; i++){
				remove(a[i] - i);//妈的，此处想了好久,为什么要减个i？
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

