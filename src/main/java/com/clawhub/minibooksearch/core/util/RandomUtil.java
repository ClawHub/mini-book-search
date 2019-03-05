package com.clawhub.minibooksearch.core.util;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * <Description> 随机获取一个集合（List, Set，Map）中的元素<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-05 22:12<br>
 */
public enum RandomUtil {
    /**
     * Instance random util.
     */
    INSTANCE;

    /**
     * The Random.
     */
    private Random random = new Random();


    /**
     * 获得一个[0,max)之间的整数。
     *
     * @param max the max
     * @return random int
     */
    public int getRandomInt(int max) {
        return Math.abs(random.nextInt()) % max;
    }

    /**
     * 获得一个[0,max)之间的整数。
     *
     * @param max the max
     * @return random long
     */
    public long getRandomLong(long max) {
        return Math.abs(random.nextInt()) % max;
    }

    /**
     * 从list中随机取得一个元素
     *
     * @param <E>  the type parameter
     * @param list the list
     * @return random element
     */
    public <E> E getRandomElement(List<E> list) {
        return list.get(getRandomInt(list.size()));
    }

    /**
     * 从set中随机取得一个元素
     *
     * @param <E> the type parameter
     * @param set the set
     * @return random element
     */
    public <E> E getRandomElement(Set<E> set) {
        int rn = getRandomInt(set.size());
        int i = 0;
        for (E e : set) {
            if (i == rn) {
                return e;
            }
            i++;
        }
        return null;
    }

    /**
     * 从map中随机取得一个key
     *
     * @param <K> the type parameter
     * @param <V> the type parameter
     * @param map the map
     * @return random key from map
     */
    public <K, V> K getRandomKeyFromMap(Map<K, V> map) {
        int rn = getRandomInt(map.size());
        int i = 0;
        for (K key : map.keySet()) {
            if (i == rn) {
                return key;
            }
            i++;
        }
        return null;
    }

    /**
     * 从map中随机取得一个value
     *
     * @param <K> the type parameter
     * @param <V> the type parameter
     * @param map the map
     * @return random value from map
     */
    public <K, V> V getRandomValueFromMap(Map<K, V> map) {
        int rn = getRandomInt(map.size());
        int i = 0;
        for (V value : map.values()) {
            if (i == rn) {
                return value;
            }
            i++;
        }
        return null;
    }

}
