# 第599题 两个列表的最小索引和

## 1 题目

假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。

你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。

示例 1:

```
输入:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
输出: ["Shogun"]
解释: 他们唯一共同喜爱的餐厅是“Shogun”。
```

示例 2:

```
输入:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
输出: ["Shogun"]
解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
```

## 2 解法

```
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map1 = new HashMap<>();

        int rest1Num = list1.length;
        int rest2Num = list2.length;

        for (int i = 0; i < rest1Num; i++) {
            map1.put(list1[i], i);
        }
        
        List<String> res = new ArrayList<>();
        int minSumIndex = Integer.MAX_VALUE;
        
        for (int j = 0; j < rest2Num; j++) {
            String rest2 = list2[j];

            if (map1.containsKey(rest2)) {
                int rest2InMap1Index = map1.get(rest2);
                int sumIndex = j + rest2InMap1Index;

                if (sumIndex < minSumIndex) {
                    res.clear();
                    minSumIndex = sumIndex;
                    res.add(rest2);
                } else if (sumIndex == minSumIndex) {
                    res.add(rest2);
                }
            }
        }

        String[] ans = new String[res.size()];
        int ptr = 0;

        for (String rest : res) {
            ans[ptr] = rest;
            ptr++;
        }

        return ans;
    }
}
```

复杂度分析：

设list1有m个元素，list2有n个元素。

1. 时间复杂度：遍历list1并添加进哈希表花费O(m)时间，遍历list2并在第一个哈希表中查找共花费O(n)时间，故时间复杂度为**O(m + n)**；
2. 空间复杂度：创建哈希表共花费O(m)空间，其余空间小于O(m)，故空间复杂度为**O(m)**。



