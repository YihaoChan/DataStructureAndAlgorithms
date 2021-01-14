package datastructure.symboltable.withorder;

/**
 * @Description: 有序符号表 - 测试类
 */
public class SymbolTableWithOrderTest {
    public static void main(String[] args) {
        SymbolTableWithOrder<Integer, String> symbolTableWithOrder = new SymbolTableWithOrder<>();

        /* 插入 */
        symbolTableWithOrder.put(1000, "Jay");
        symbolTableWithOrder.put(100, "Leehom");
        symbolTableWithOrder.put(10, "David");
        symbolTableWithOrder.put(1, "JJ");

        System.out.println("当前表中键值对个数为：" + symbolTableWithOrder.size());
        symbolTableWithOrder.show();

        /* 修改 */
        symbolTableWithOrder.put(1, "Khalil");
        System.out.println("替换键值对后的表：");
        symbolTableWithOrder.show();

        /* 查看 */
        String value = symbolTableWithOrder.get(100);
        System.out.println("当前键对应的值为：" + value);

        /* 删除 */
        SymbolTableWithOrder.Node deleteNode = symbolTableWithOrder.delete(10);
        System.out.println("删除的键值对为：" + deleteNode.getKey() + "---" + deleteNode.getValue());
        System.out.println("删除键值对后表中元素个数：" + symbolTableWithOrder.size());
    }
}
