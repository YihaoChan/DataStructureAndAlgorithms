package datastructure.symboltable.noorder;

/**
 * @Description: 用链表实现无序符号表 - 测试类
 */
public class SymbolTableWithNoOrderTest {
    public static void main(String[] args) {
        SymbolTableWithNoOrder<Integer, String> symbolTableWithNoOrder = new SymbolTableWithNoOrder<>();

        /* 插入 */
        symbolTableWithNoOrder.put(1000, "Jay");
        symbolTableWithNoOrder.put(100, "Leehom");
        symbolTableWithNoOrder.put(10, "David");
        symbolTableWithNoOrder.put(1, "JJ");

        System.out.println("当前表中键值对个数为：" + symbolTableWithNoOrder.size());
        symbolTableWithNoOrder.show();

        /* 修改 */
        symbolTableWithNoOrder.put(1, "Khalil");
        System.out.println("替换键值对后的表：");
        symbolTableWithNoOrder.show();

        /* 查看 */
        String value = symbolTableWithNoOrder.get(100);
        System.out.println("当前键对应的值为：" + value);

        /* 删除 */
        SymbolTableWithNoOrder.Node deleteNode = symbolTableWithNoOrder.delete(10);
        System.out.println("删除的键值对为：" + deleteNode.getKey() + "---" + deleteNode.getValue());
        System.out.println("删除键值对后表中元素个数：" + symbolTableWithNoOrder.size());
    }
}
