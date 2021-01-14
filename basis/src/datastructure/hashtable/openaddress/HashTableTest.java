package datastructure.hashtable.openaddress;

/**
 * @Description: 散列表 - 测试类
 */
public class HashTableTest {
    public static void main(String[] args) {
        // 《清华数据结构》 P257
        HashTable hashTable = new HashTable(5);

        System.out.println(hashTable.getHashTableLength());

        hashTable.insert(17);
        hashTable.insert(60);
        hashTable.insert(29);
        hashTable.insert(38);

        System.out.println("原哈希表");
        hashTable.printHashTable();

        hashTable.remove(17);
        System.out.println("删除元素之后的哈希表");
        hashTable.printHashTable();

        System.out.println("待查找元素在哈希表中的下标：" + hashTable.find(380));

        System.out.println("装填因子为：" + hashTable.getLoadFactor());
    }
}