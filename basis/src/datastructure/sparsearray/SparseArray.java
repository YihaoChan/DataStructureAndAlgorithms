package datastructure.sparsearray;

import java.io.*;

/**
 * @Description 二维数组 <-> 稀疏数组
 */
public class SparseArray {
    /**
     * @Description: 创建原始数组 -> 转化为稀疏数组 -> 恢复为原始二维数组 -> 将稀疏数组写入磁盘中 -> 读取稀疏数组文件
     */
    public static void main(String[] args) throws IOException {
        /* 1. 指定创建原始数组 */

        // 原始二维数组大小为11 x 11
        int[][] rawArrayInitialize = new int[11][11];

        // 用0表示没有棋子，1表示黑子，2表示蓝子
        // 黑子位置，第2行第3列
        rawArrayInitialize[1][2] = 1;
        // 蓝子位置，第3行第4列
        rawArrayInitialize[2][3] = 2;

        // 打印原始数组
        System.out.println("创建原始数组");
        printArray(rawArrayInitialize);
        System.out.println();

        /* 2. 原始数组 -> 稀疏数组（转化） */
        int[][] sparseArray = rawArrayToSparseArray(rawArrayInitialize);

        // 打印稀疏数组
        System.out.println("根据原始数组获得稀疏数组");
        printArray(sparseArray);
        System.out.println();

        /* 3. 稀疏数组 -> 原始数组（恢复） */
        int[][] rawArrayRestore = sparseArrayRestoreToRawArray(sparseArray);

        // 打印恢复后的原始数组
        System.out.println("根据稀疏数组恢复得到原始数组");
        printArray(rawArrayRestore);
        System.out.println();

        /* 4. 将稀疏数组写入文件中 */
        System.out.println("将稀疏数组写入文件中...");
        String sparseArrayPath = "BasisPractice" +
                File.separator + "src" +
                File.separator + "priv" +
                File.separator + "yihaochan" +
                File.separator + "datastructure" +
                File.separator + "sparsearray" +
                File.separator + "sparseArray.data";
        writeSparseArrayToFile(sparseArray, sparseArrayPath);
        System.out.println("写入完毕！");
        System.out.println();

        /* 5. 从文件中读取稀疏数组，并恢复得到原始数组 */
        System.out.println("读取文件中的稀疏数组...");
        int[][] sparseArrayFromFile = readSparseArrayFromFile(sparseArrayPath);
        System.out.println("读取完毕！");
        System.out.println();

        // 调用恢复方法，根据从稀疏数组文件中“拷贝”得到的稀疏数组，还原成原始数组
        System.out.println("从文件中恢复后的原始数组");
        int[][] rawArrayFromSparseArrayInFile = sparseArrayRestoreToRawArray(sparseArrayFromFile);

        // 打印从文件中读取到的稀疏数组经过恢复后的原始数组
        printArray(rawArrayFromSparseArrayInFile);
        System.out.println();
    }

    /**
     * @Description: 原始数组 -> 稀疏数组
     * @param: rawArray 原始数组
     * @return: int[][] 由原始数组转化得到的稀疏数组
     */
    public static int[][] rawArrayToSparseArray(int[][] rawArray) {
        // 1.先遍历二维数组，得到非0数据的个数
        int countNotZero = 0;
        for (int i = 0; i < rawArray.length; i++) {
            for (int j = 0; j < rawArray[0].length; j++) {
                if (rawArray[i][j] != 0) countNotZero++;
            }
        }
        System.out.println("原始数组中非0个数为：" + countNotZero);
        System.out.println();

        // 2.创建对应稀疏数组，3列，行数为非0个数+1，因为首行记录原始数组大小还有非0个数
        int[][] sparseArray = new int[countNotZero + 1][3];

        // 3.给稀疏数组赋值
        sparseArray[0][0] = rawArray.length;
        sparseArray[0][1] = rawArray[0].length;
        sparseArray[0][2] = countNotZero;

        // 4.遍历原始二维数组，将非0数据存放到稀疏数组中
        // 记录存放非0数据位置的行数的变量
        int rowCount = 0;
        // 把非0数据及其位置放入稀疏数组中
        for (int k = 0; k < rawArray.length; k++) {
            for (int v = 0; v < rawArray[0].length; v++) {
                if (rawArray[k][v] != 0) {
                    rowCount++;
                    sparseArray[rowCount][0] = k;
                    sparseArray[rowCount][1] = v;
                    sparseArray[rowCount][2] = rawArray[k][v];
                }
            }
        }

        return sparseArray;
    }

    /**
     * @Description: 稀疏数组 -> 原始数组
     * @param: sparseArray 稀疏数组
     * @return: int[][] 由稀疏数组复原得到的原始数组
     */
    public static int[][] sparseArrayRestoreToRawArray(int[][] sparseArray) {
        // 1.从稀疏数组中取原始数组的行数和列数
        int rowArrayRestore = sparseArray[0][0];
        int columnArrayRestore = sparseArray[0][1];
        int[][] rawArrayRestore = new int[rowArrayRestore][columnArrayRestore];

        // 2.恢复的原始数组需要在哪一行和哪一列添加非0数据
        int rowArrayRestoreFill = 0;
        int columnArrarRestoreFill = 0;
        for (int i = 1; i < sparseArray.length; i++) {
            // 应该填充非0数据的地方
            rowArrayRestoreFill = sparseArray[i][0];
            columnArrarRestoreFill = sparseArray[i][1];

            // 填充的非0数据的值
            rawArrayRestore[rowArrayRestoreFill][columnArrarRestoreFill] = sparseArray[i][2];
        }

        return rawArrayRestore;
    }

    /**
     * @Description: 把数组写进文件中
     * @param: sparseArray 需要被写入的稀疏数组
     * @param: sparseArrayWritePath 需要写入的稀疏数组文件的目的路径
     */
    public static void writeSparseArrayToFile(int[][] sparseArray, String sparseArrayWritePath) throws IOException {
        // 存放数组数据的文件的路径
        File file = new File(sparseArrayWritePath);

        // 文件写入流
        FileWriter fw = new FileWriter(file);

        // 将数组写入文件中
        for (int i = 0; i < sparseArray.length; i++) {
            for (int j = 0; j < sparseArray[0].length; j++) {
                fw.write(sparseArray[i][j] + "\t");
            }
            fw.write("\n");
        }

        fw.close();
    }

    /**
     * @Description: 从文件中读取稀疏数组，并返回原始数组
     * @param: sparseArrayReadPath 需要读取的稀疏数组文件的源路径
     * @return: int[][] 由从文件中读取到的稀疏数组
     */
    public static int[][] readSparseArrayFromFile(String sparseArrayReadPath) throws IOException {
        // 创建文件
        File file = new File(sparseArrayReadPath);

        /* 用FileReader和BufferedReader方式读取稀疏数组文件 */
        FileReader frOne = new FileReader(file);
        BufferedReader bfOne = new BufferedReader(frOne);

        // 计算稀疏数组文件中的行数，为了创建新的稀疏数组
        int rowSparseArrayFile = 0;
        while ((bfOne.readLine()) != null) rowSparseArrayFile++;

        // 关闭流
        bfOne.close();
        frOne.close();

        /*
         * 再次用FileReader和BufferedReader方式读取数组文件。
         * 不能用同一个流进行两次遍历，否则在第二次遍历的时候readLine()指示会发生冲突（读行的“指针”一直是null，导致数组越界），
         * 甚至会抛出"Stream Closed"的异常。
         * 暂时还不明白这是为什么...
         */
        FileReader frTwo = new FileReader(file);
        BufferedReader brTwo = new BufferedReader(frTwo);

        // 根据已获得的稀疏数组文件的行数创建新的稀疏数组
        int[][] sparseArrayFromFile = new int[rowSparseArrayFile][3];

        // line用于判断当前行是否为最后一行以及分割每一行的所有字符；rowSparseArrayNew用于自增，为稀疏数组赋值
        String line = null;
        int rowSparseArrayFileNew = 0;

        while ((line = brTwo.readLine()) != null) {
            String[] temp = line.split("\t");
            for (int j = 0; j < temp.length; j++) {
                sparseArrayFromFile[rowSparseArrayFileNew][j] = Integer.parseInt(temp[j]);
            }
            rowSparseArrayFileNew++;
        }

        // 关闭流
        brTwo.close();
        frTwo.close();

        return sparseArrayFromFile;
    }

    /**
     * @Description: 遍历数组并打印各元素，查看该数组
     * @param: array 任意二维数组
     */
    public static void printArray(int[][] array) {
        // 遍历行，行数共为array.length
        for (int row = 0; row < array.length; row++) {
            // 遍历列，列数共为array[0].length
            for (int column = 0; column < array[0].length; column++) {
                System.out.printf("%d\t", array[row][column]);
            }
            System.out.println(); // 换行
        }
    }
}
