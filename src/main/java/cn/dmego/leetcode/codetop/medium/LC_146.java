package cn.dmego.leetcode.codetop.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU 最近最少使用缓存(保证 get put 时间复杂度 O(1))
 * <p>
 * 特点：搜索快，插入快，删除快，插入有序
 * <p>
 * 哈希表：查找快，但是无需
 * 双向链表：插入有序，插入快，删除快
 *
 * @author dmego
 * @date 2021/12/31 10:59
 */
public class LC_146 {

    // 双向链表节点类
    static class Node {
        // 存储 map 的 key-value
        public int key;
        public int value;
        public Node prev;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // 双向链表
    static class DoubleList {
        private Node head; // 前驱
        private Node tail; // 后继
        private int size;

        // 在链表头部添加元素
        public void addFirst(Node node) {
            // 如果 cache 中没有元素，head tail 都指向第一个元素
            if (head == null) {
                head = tail = node;
            } else {
                // head 原先指向的是第一个元素 old
                Node old = head;
                // old 的前驱执行新元素
                old.prev = node;
                // 新元素的后继执行 old
                node.next = old;
                // 把 head 执行新插入的元素 node
                head = node;
            }
            size++;
        }

        // 删除链表中的 x 节点，x 一定存在
        // 双向链表 删除节点时间复杂度 O(1)
        public void remove(Node node) {
            // 如果链表中只有一个元素，直接删除
            if (head == node && tail == node) {
                head = tail = null;
            }
            // 如果删除的是头节点
            else if (head == node) {
                head = head.next;
                head.prev = null;
            }
            // 如果删除的是尾节点
            else if (tail == node) {
                tail = tail.prev;
                tail.next = null;
            }
            // 删除连接中间元素
            else {
                node.next.prev = node.prev;
                node.prev.next = node.next;
            }
            size--;
        }

        // 删除链表尾部元素
        public Node removeLast() {
            Node node = tail;
            remove(tail);
            return node;
        }

        // 返回链表的大小
        public int getSize() {
            return size;
        }
    }


    static class LRUCache {
        // cache 容量
        int capacity;
        // cache 存储(key: key，Node: key-value)
        Map<Integer, Node> cache;
        // 双向链表 维持缓存的顺序
        DoubleList deque;

        //LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
        public LRUCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>();
            deque = new DoubleList();
        }

        /*
           int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
           每 get 一次，需要把 该值移动到 cache 头部
         */
        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            } else {
                Node node = cache.get(key);
                // 将节点从链表中删除，然后添加到链表头部
                deque.remove(node);
                deque.addFirst(node);
                return node.value;
            }
        }

        /*
           void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组
           key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。

           1. 如果 put 时存在该值，先改变该值，然后将该值移动到 cache 头部
           2. 如果 capacity 已满，将 cache 尾部元素删除，同时将map 中元素删除。
           3. 将 元素添加到 map, 添加到 cache 头部

        */
        public void put(int key, int value) {
            // 判断 key 是否已存在
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                deque.remove(node);
                // 更新 value 值
                node.value = value;
                deque.addFirst(node);
            } else {
                // 判断cache 是否已满
                if (deque.getSize() == capacity) {
                    Node last = deque.removeLast();
                    // 删除 cache 中的元素
                    cache.remove(last.key);
                }
                Node node = new Node(key, value);
                cache.put(key, node);
                deque.addFirst(node);
            }
        }
    }


    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }

}
