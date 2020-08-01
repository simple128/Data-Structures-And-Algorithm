package wy.algorithm.greedy;

import java.util.*;

/**
 * 贪心算法解决集合覆盖问题
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = buildBroadcosts();

        System.out.println(greedy(broadcasts));
    }

    public static List<String> greedy(HashMap<String, HashSet<String>> broadcasts) {
        HashSet<String> allAreas = pickupAllAreas(broadcasts);
        List<String> res = new ArrayList<>();
        HashSet<String> tempAreas;

        while (allAreas.size() != 0) {
            String maxKey = null;
            for (Map.Entry<String, HashSet<String>> entry : broadcasts.entrySet()) {
                tempAreas = entry.getValue();
                tempAreas.retainAll(allAreas);
                // 每次都取覆盖最多地区的广播站，提现了贪心算法思想
                if ((tempAreas.size() > 0) && (maxKey == null || broadcasts.get(maxKey).size() < tempAreas.size())) {
                    maxKey = entry.getKey();
                }
            }

            if (maxKey != null) {
                allAreas.removeAll(broadcasts.get(maxKey));
                res.add(maxKey);
            }
        }
        return res;
    }

    public static HashMap<String, HashSet<String>> buildBroadcosts() {
        //创建广播电台,放入到Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);
        return broadcasts;
    }

    /**
     * 取出所有区域
     * @param broadcasts
     * @return
     */
    public static HashSet<String> pickupAllAreas(HashMap<String, HashSet<String>> broadcasts) {
        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();
        for (HashSet<String> value : broadcasts.values()) {
            allAreas.addAll(value);
        }
        return allAreas;
    }
}
