# 第811题 子域名访问计数

一个网站域名，如"discuss.leetcode.com"，包含了多个子域名。作为顶级域名，常用的有"com"，下一级则有"leetcode.com"，最低的一级为"discuss.leetcode.com"。当我们访问域名"discuss.leetcode.com"时，也同时访问了其父域名"leetcode.com"以及顶级域名 "com"。

给定一个带访问次数和域名的组合，要求分别计算每个域名被访问的次数。其格式为访问次数+空格+地址，例如："9001 discuss.leetcode.com"。

接下来会给出一组访问次数和域名组合的列表cpdomains 。要求解析出所有域名的访问次数，输出格式和输入格式相同，不限定先后顺序。

示例 1:

```
输入: 
["9001 discuss.leetcode.com"]
输出: 
["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
说明: 
例子中仅包含一个网站域名："discuss.leetcode.com"。按照前文假设，子域名"leetcode.com"和"com"都会被访问，所以它们都被访问了9001次。
```

示例 2

```
输入: 
["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
输出: 
["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
说明: 
按照假设，会访问"google.mail.com" 900次，"yahoo.com" 50次，"intel.mail.com" 1次，"wiki.org" 5次。
而对于父域名，会访问"mail.com" 900+1 = 901次，"com" 900 + 50 + 1 = 951次，和 "org" 5 次。
```

### 2 解法

注意：split函数遇到特殊符号要加转义符。

```
class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        int domainNum = cpdomains.length;
        
        List<String> res = new ArrayList<>();
        Map<String, Integer> domainVisitTimes = new HashMap<>();

        if (domainNum == 0) {
            return res;
        }

        for (int i = 0; i < domainNum; i++) {
            String[] timesWithDomain = cpdomains[i].split(" ");
            Integer times = Integer.parseInt(timesWithDomain[0]);
            String domain = timesWithDomain[1];
            
            String[] components = domain.split("\\.");
            int numOfComponents = components.length;

            StringBuilder sb = new StringBuilder();

            for (int j = numOfComponents - 1; j >= 0; j--) {
                sb.insert(0, 
                    new StringBuilder(components[j]).append("."));
                if (j == numOfComponents - 1) {
                    sb.delete(sb.length() - 1, sb.length());
                }
                String str = sb.toString();

                Integer preTimes = domainVisitTimes.get(str);
                if (preTimes != null) {
                    domainVisitTimes.put(str, preTimes + times);
                } else {
                    domainVisitTimes.put(str, times);
                }
            }
        }

        for (Map.Entry<String, Integer> entry :
            domainVisitTimes.entrySet()) {
            StringBuilder sb = new StringBuilder();

            String domain = entry.getKey();
            Integer times = entry.getValue();

            sb.append(times).append(" ").append(domain);

            res.add(sb.toString());
        }

        return res;
    }
}
```

复杂度分析：

设访问次数、域名及其各个组成部分的总数量为n。

1. 时间复杂度：对每个域名及其次数需要进行分割并一一访问，共花费O(n)时间，遍历哈希表并填入res共花费O(n)时间，故总时间复杂度为**O(n)**；
2. 空间复杂度：创建额外哈希表用于存储每个子域名及其访问次数，故空间复杂度为**O(n)**。

