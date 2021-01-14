package datastructure.stack;

import java.util.Scanner;

/**
 * @Description: 括号匹配问题 - 栈
 * 1. 将字符串分割为字符数组，遍历数组，仅判断左右括号；
 * 2. 创建栈，存放左括号，即栈中元素只有 ( 或 null 两种情况；
 * 3. 每遇到一个左括号，就将左括号压栈；每遇到一个右括号，就弹栈；
 * 4. 如果弹出来的元素为null，说明栈中没有左括号，即字符串中右括号数量大于左括号，直接返回false；
 * 5. 在字符数组遍历完成后，如果栈中还有元素，即有残留的左括号，说明字符串中左括号数量大于右括号，也返回false。
 */
public class BracketMatchTest {
    public static void main(String[] args) {
        // 程序是否继续运行的标志
        boolean flag = true;

        while (flag) {
            Scanner sc = new Scanner(System.in);

            System.out.println("输入要检查括号的字符串：(输入q退出)");

            String str = sc.next();

            if ("q".equals(str)) {
                flag = false;
            } else {
                boolean match = isMatch(str);

                System.out.println("字符串括号是否匹配：" + match);
            }
        }
    }

    public static boolean isMatch(String str) {
        // 存放左括号的栈，只有 ( 或 null 两种值
        Stack<Character> leftBracketStack = new Stack<>();

        // 字符串转为字符数组
        char[] chars = str.toCharArray();

        /*
         * 遍历字符数组，如果遇到左括号就push，如果遇到右括号就pop
         * 如果pop出的元素为左括号，则正确；如果pop出的元素为null，则直接返回false
         * 遍历完之后，如果栈中还有元素，证明原始字符串中左括号数量大于右括号，也返回false
         */
        for (char item : chars) {
            // 如果遍历到左括号，就压栈
            if (item == '(') {
                leftBracketStack.push(item);
                continue;
            }

            // 如果遍历到右括号，就弹栈
            if (item == ')') {
                // 如果弹出来的元素为null，则直接返回false
                if (leftBracketStack.pop() == null) {
                    return false;
                }
            }
        }

        // 如果在遍历结束后，栈里还有元素，则说明原始字符串中左括号数量大于右括号
        return leftBracketStack.size() == 0;
    }
}


