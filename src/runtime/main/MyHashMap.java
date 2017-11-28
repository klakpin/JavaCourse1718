package runtime.main;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K, V> {

    public static int BUCKETS_NUMBER = 16;

    public List<K, V>[] buckets;

    public MyHashMap() {
        buckets = new List[16];
        for (int i = 0; i < BUCKETS_NUMBER - 1; i++) {
            buckets[i] = new List<>();
        }
    }

    @Override
    public V get(Object key) {
        K searchKey;
        try {
            searchKey = (K) key;
        } catch (Exception e) {
            throw new ClassCastException();
        }
        return buckets[key.hashCode() % BUCKETS_NUMBER].get(searchKey);
    }

    @Override
    public V put(K key, V value) {
        K searchKey;
        V putValue;
        try {
            searchKey = (K) key;
            putValue = value;
        } catch (Exception e) {
            throw new ClassCastException();
        }
            return buckets[key.hashCode() % BUCKETS_NUMBER].add(searchKey, putValue);

    }

    @Override
    public V remove(Object key) {
        K searchKey;
        try {
            searchKey = (K) key;
        } catch (Exception e) {
            throw new ClassCastException();
        }
        return buckets[key.hashCode() % BUCKETS_NUMBER].remove(searchKey);
    }

    @Override
    public int size() {
        int returnSize = 0;
        for (int i = 0; i < buckets.length - 1; i++) {
//            returnSize += buckets[i].
        }
        return returnSize;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
