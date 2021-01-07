package datastructure.hashtable.rehashing;

/**
 * @Description: 测试类 - 样例来源：《数据结构与算法分析Java语言描述》 P130 - P131
 */
public class HashTableTest {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);

        hashTable.insert(13);
        hashTable.insert(15);
        hashTable.insert(24);
        hashTable.insert(6);

        System.out.println("原哈希表");
        hashTable.printHashTable();

        System.out.println("装填因子为：" + hashTable.getLoadFactor());

        // 插入23后，装载因子超过忍耐值，使用再哈希法
        hashTable.insert(23);

        System.out.println("再哈希之后的哈希表");
        hashTable.printHashTable();

        hashTable.remove(23);
        System.out.println("删除元素之后的哈希表");
        hashTable.printHashTable();

        System.out.println("待查找元素在哈希表中的下标：" + hashTable.find(380));

        System.out.println("装填因子为：" + hashTable.getLoadFactor());
    }
}