package datastructure.stackbylinkedlist;

/**
 * @Description: 根据后缀(逆波兰)表达式求值 - 栈
 * 1. 创建存放操作数的栈；
 * 2. 遍历字符数组，如果遇到操作数，则入栈；如果遇到操作符，则弹出栈顶两个元素，进行运算，再将结果压栈。
 * PS.弹栈顺序和运算顺序不一致，后弹出的元素的原计算位置为前面；
 * 3. 最后把栈顶元素弹出，即得到运算结果
 */
public class PostfixToResultTest {
    public static void main(String[] args) {
        // 给定后缀表达式
        String[] postfixNotation = {"1", "2", "3", "+", "4", "*", "+", "5", "-"};

        System.out.println("求值结果为：" + postfixToResult(postfixNotation));
    }

    /**
     * @Description: 后缀表达式(逆波兰表达式) -> 计算值
     */
    public static int postfixToResult(String[] postfixNotation) {
        // 创建存放操作数的栈
        StackByLinkedList<Integer> operands = new StackByLinkedList<>();

        // 初始时，弹出的两个操作数都为null
        Node operandUpper; // 在栈中占上方的元素
        Node operandLower; // 在栈中占下方的元素

        // 表达式计算结果
        int result;

        for (String item : postfixNotation) {
            // 弹出元素后做运算，顺序需要注意：后弹出的在前，先弹出的在后
            switch (item) {
                case "+":
                    operandUpper = operands.pop();
                    operandLower = operands.pop();
                    result = (int) operandLower.getItem() + (int) operandUpper.getItem();
                    operands.push(result);
                    break;
                case "-":
                    operandUpper = operands.pop();
                    operandLower = operands.pop();
                    result = (int) operandLower.getItem() - (int) operandUpper.getItem();
                    operands.push(result);
                    break;
                case "*":
                    operandUpper = operands.pop();
                    operandLower = operands.pop();
                    result = (int) operandLower.getItem() * (int) operandUpper.getItem();
                    operands.push(result);
                    break;
                case "/":
                    operandUpper = operands.pop();
                    operandLower = operands.pop();
                    result = (int) operandLower.getItem() / (int) operandUpper.getItem();
                    operands.push(result);
                    break;
                default:
                    // 如果都不是操作符，则把操作数放入栈中
                    operands.push(Integer.parseInt(item));
                    break;
            }
        }

        // 弹出运算结果
        return (int) operands.pop().getItem();
    }
}
