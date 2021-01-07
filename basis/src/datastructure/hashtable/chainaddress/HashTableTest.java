package datastructure.hashtable.chainaddress;

/**
 * @Description: 测试类 - 样例来源：清华《数据结构》 P258
 */
public class HashTableTest {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(13);

        hashTable.insert(19);
        hashTable.insert(14);
        hashTable.insert(23);
        hashTable.insert(1);
        hashTable.insert(68);
        hashTable.insert(20);
        hashTable.insert(84);
        hashTable.insert(27);
        hashTable.insert(55);
        hashTable.insert(11);
        hashTable.insert(10);
        hashTable.insert(79);

        System.out.println("原哈希表");
        hashTable.printHashTable();

        System.out.println("待查找元素在哈希表中的下标：" + hashTable.find(+790));
        System.out.println();

        System.out.println("删除79后的哈希表");
        hashTable.remove(79);
        hashTable.printHashTable();
    }
}
