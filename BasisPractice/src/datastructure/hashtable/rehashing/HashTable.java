package datastructure.hashtable.rehashing;

/**
 * @Description: 除留余数法构造哈希函数，线性探测法处理哈希冲突，再使用再哈希法处理哈希冲突
 */
public class HashTable {
    // 哈希表
    private Integer[] hashTable; // 让默认元素为null而非0，所以用Integer

    // 哈希表长
    private int hashTableLength;

    // 哈希表中元素个数
    private int size;

    // 删除元素标记
    private final int DELETED = -777;

    // 装载因子的忍耐值，即如果装载因子大于该数，就要使用再哈希法
    private final double PATIENCE = 0.7;

    /**
     * @Description: 返回哈希表中存放元素个数
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @Description: 返回哈希表表长
     */
    public int getHashTableLength() {
        return this.hashTableLength;
    }

    /**
     * @Description: 判断一个数是否为素数
     */
    private boolean isPrime(int num) {
        double sqrt = Math.sqrt(num);

        if (num < 2) {
            return false;
        }

        if (num == 2 || num == 3) {
            return true;
        }

        if (num % 2 == 0) { // 先判断是否为偶数，若偶数就直接结束程序
            return false;
        }

        for (int i = 3; i <= sqrt; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * @Description: 如果哈希表长恰好为素数，则直接返回该表长；否则，计算大于它的最小素数
     */
    private int getPrime(int num) {
        if (isPrime(num)) {
            return num;
        }

        int answer = num + 1;

        while (!isPrime(answer)) {
            answer++;
        }

        return answer;
    }

    /**
     * @Description: 哈希表构造方法 - 带表长
     */
    public HashTable(int hashTableLength) {
        // 让哈希表长为一个素数
        if (!isPrime(hashTableLength)) {
            hashTableLength = getPrime(hashTableLength);
        }

        // 创建哈希表
        this.hashTableLength = hashTableLength;
        hashTable = new Integer[this.hashTableLength];
    }

    /**
     * @Description: 哈希表构造方法 - 不带表长，默认传入10
     */
    public HashTable() {
        this(10);
    }

    /**
     * @param item 需要计算哈希值的元素
     * @param n    冲突次数
     * @Description: 哈希函数，除留余数法，除数使用表长；处理哈希冲突，使用线性探测再散列
     */
    private int hash(int item, int n) {
        // 线性探测：di = i
        return (item + n) % this.hashTableLength;
    }

    /**
     * @Description: 根据原哈希表长度，创建一个原表长度两倍的新哈希表，并将元素通过新哈希函数放到新哈希表中
     */
    private Integer[] rehashing(int tableLength) {
        this.hashTableLength = getPrime(2 * tableLength);

        // 原来的哈希表
        Integer[] oldTable = this.hashTable;

        // 新哈希表，覆盖原来的表
        this.hashTable = new Integer[this.hashTableLength];

        // 将旧表中的元素根据新的哈希函数放入新哈希表中
        for (Integer item : oldTable) {
            // 如果取到null，就查找下一个元素
            if (item == null) {
                continue;
            }

            // 冲突次数
            int conflictCount = 0;

            // 新哈希表中每个元素的新的哈希地址
            int hashAddress = hash(item, conflictCount);

            // 哈希表中对应位置元素不为空，说明有元素占用，即发生哈希冲突
            while (this.hashTable[hashAddress] != null) {
                // 如果哈希地址对应元素为删除元素的标记，说明该位置删除过元素，直接跳出，在该位置处插入元素
                if (this.hashTable[hashAddress] == DELETED) {
                    break;
                }

                // 冲突次数自增
                conflictCount++;

                // 更新哈希地址
                hashAddress = hash(item, conflictCount);
            }

            // 将元素放入哈希表
            this.hashTable[hashAddress] = item;
        }

        return this.hashTable;
    }

    /**
     * @Description: 计算装填因子
     */
    public float getLoadFactor() {
        return (float) this.size / this.hashTableLength;
    }

    /**
     * @Description: 插入元素
     */
    public void insert(int item) {
        // 计算元素哈希地址，第一次计算时还没有发现是否会产生哈希冲突，所以线性探测系数为0
        int hashAddress = hash(item, 0);

        // 冲突次数
        int conflictCount = 0;

        // 哈希表中对应位置元素不为空，说明有元素占用，即发生哈希冲突
        while (this.hashTable[hashAddress] != null) {
            // 如果哈希地址对应元素为删除元素的标记，说明该位置删除过元素，直接跳出，在该位置处插入元素
            if (this.hashTable[hashAddress] == DELETED) {
                break;
            }

            // 冲突次数自增
            conflictCount++;

            // 更新哈希地址
            hashAddress = hash(item, conflictCount);
        }

        // 将元素放入哈希表
        this.hashTable[hashAddress] = item;

        this.size++;

        // 如果装载因子大于忍耐值，就使用再哈希法
        if (getLoadFactor() > PATIENCE) {
            this.hashTable = rehashing(this.hashTableLength);
        }
    }

    /**
     * @Description: 查找元素并返回哈希表中对应位置的下标
     */
    public int find(Integer item) {
        // 计算元素哈希地址，第一次计算时还没有发现是否会产生哈希冲突，所以线性探测系数为0
        int hashAddress = hash(item, 0);

        // 冲突次数
        int conflictCount = 0;

        // 哈希表中对应位置元素不等于待查找元素，说明有元素占用，即发生哈希冲突
        while (this.hashTable[hashAddress] != item) {
            // 如果根据处理冲突的方法，找到哈希表中某个为null的位置，说明待查找元素不在哈希表中
            if (hashTable[hashAddress] == null) {
                return -1;
            }

            // 冲突次数自增
            conflictCount++;

            // 更新哈希地址
            hashAddress = hash(item, conflictCount);
        }

        // 直到哈希表中对应元素等于待查找元素时，即找到待查找元素，返回下标
        return hashAddress;
    }

    /**
     * @Description: 删除元素
     */
    public void remove(int item) {
        // 找到待删除元素的哈希地址
        int position = find(item);

        // 待查找元素没有在哈希表中
        if (position == -1) {
            return;
        }

        // 将哈希表中对应待删除元素的位置作一个特殊标记，不能置为null
        this.hashTable[position] = DELETED;

        this.size--;
    }

    /**
     * @Description: 查看哈希表中元素
     */
    public void printHashTable() {
        for (int i = 0; i < hashTableLength; i++) {
            System.out.println(i + "\t" + hashTable[i]);
        }

        System.out.println();
    }
}