package priv.yihaochan.datastructure.stackbylinkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 中缀表达式 -> 后缀表达式 --- 栈 & 列表
 * 一、用一个栈临时存放操作符，用一个列表存放输出表达式的结果；
 * 二、遍历字符数组，如果是数字，则直接添加到输出表达式列表；
 * 三、如果遍历到操作符，则根据优先级判断：
 * 1. * 和 / 的优先级最高，+ 和 - 的优先级次之，( ) 的优先级最低，但后续处理有特殊情况；
 * 2. 如果遇到 ( ，则直接入栈；
 * 3. 如果遇到 ) ，则将栈顶元素弹出并添加到输出表达式列表中，直到栈顶元素为 ( 时停下，再将 ( 弹出但不添加到列表；
 * 4. 如果栈外操作符优先级大于栈内操作符，则直接入栈；
 * 5. 如果栈外操作符优先级小于等于栈内操作符，则将栈内元素弹出并添加到输出表达式列表中，直到栈外优先级大于栈内优先级
 * 时，将栈外操作符压栈；
 * 四、最后再将栈内元素全部弹出并添加到输出表达式列表中。
 */
public class InfixToPostfixTest {
    public static void main(String[] args) {
        // 给定中缀表达式
        String infixExpreesion = "1 + ((2 + 3) * 4) - 5";

        String infixExpreesionRemoveSpace = infixExpreesion.replace(" ", "");

        // 输出后缀表达式列表
        List<String> postfixExpression;

        postfixExpression = infixToPostfix(infixExpreesionRemoveSpace);

        for (String item : postfixExpression) {
            System.out.print(item + "\t");
        }
    }

    /**
     * @Description: 中缀表达式 -> 后缀表达式
     * 用一个栈临时存放操作符；
     * 用另一个列表存放操作数和弹出来的操作符
     */
    public static List<String> infixToPostfix(String infix) {
        // 存放操作符的栈
        StackByLinkedList<String> operators = new StackByLinkedList<>();

        // 存放输出表达式的list
        List<String> postfix = new ArrayList<>();

        // 字符串 -> 字符数组
        char[] nifixCharArray = infix.toCharArray();

        // 遍历字符数组
        for (char temp : nifixCharArray) {
            // 转为String，便于匹配
            String item = temp + "";

            if (item.matches("[0-9]+")) {
                // 如果是数字，直接放入输出表达式数组里
                postfix.add(item);
            } else if (item.equals("(")) {
                // 如果是左括号，直接压入操作符栈里
                operators.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号，把操作符栈里的元素弹出并加入到表达式数组，直到遇到左括号
                while (!operators.peek().getItem().equals("(")) {
                    // 弹出的元素，并转为字符串类型
                    String popItem = operators.pop().getItem() + "";

                    // 加入到输出表达式数组
                    postfix.add(popItem);
                }

                // 弹出左括号上方所有操作符后，把左括号也弹出，但是不加入输出表达式数组
                operators.pop();
            } else {
                // 如果是普通操作符，要根据优先级进行判断

                // 如果栈外优先级小于等于栈内优先级，则将栈内操作符弹出并加入输出表达式数组中
                while (!operators.isEmpty() &&
                        getPriority(item) <= getPriority(operators.peek().getItem() + "")) {
                    // 弹出的元素，并转为字符串类型
                    String popItem = operators.pop().getItem() + "";

                    // 加入到输出表达式数组
                    postfix.add(popItem);
                }

                // 直到栈外优先级大于栈内优先级时，把当前操作符压入到栈中
                operators.push(item);
            }
        }

        while (!operators.isEmpty()) { // 最后将栈内元素全部弹出并输出到表达式列表中
            // 弹出的元素，并转为字符串类型
            String popItem = operators.pop().getItem() + "";

            // 加入到输出表达式数组
            postfix.add(popItem);
        }

        return postfix;
    }


    /**
     * @Description: 获取操作符优先级
     */
    public static int getPriority(String operator) {
        if (operator.equals("*") || operator.equals("/")) {
            return 2;
        } else if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else {
            return 0; // 左括号在栈中的优先级最低，因为后续加入的操作符都是直接加入，直至遇到 )
        }
    }
}
