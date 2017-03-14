package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int count = 0;
        for (List<V> list : map.values()) {
            count += list.size();
        }
        return count;
    }

    @Override
    public V put(K key, V value) {
        List<V> list;
        V e = null;
        if (!containsKey(key)) {
            list = new ArrayList<V>();
            list.add(value);

        } else {
            list = map.get(key);
            e = list.size() > 0 ? list.get(list.size() - 1) : null;
            if (list.size() == repeatCount)
                list.remove(0);
            list.add(value);
        }

        map.put(key, list);
        return e;
    }

    @Override
    public V remove(Object key) {
        List<V> list;
        V e = null;
        if (!containsKey(key)) return null;

        list = map.get(key);


        e = list.get(0);
        list.remove(0);
        if (list.size() == 0)
            map.remove(key);

        return e;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        Collection<V> collection = new ArrayList<V>();
        for (List<V> list : map.values()) {
            collection.addAll(list);
        }
        return collection;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (List<V> list : map.values()) {
            if (list.contains(value)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}