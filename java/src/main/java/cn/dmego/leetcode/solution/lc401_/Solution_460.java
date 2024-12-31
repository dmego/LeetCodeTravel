package cn.dmego.leetcode.solution.lc401_;


import java.util.HashMap;
import java.util.Map;

/**
 * [460] LFU 缓存
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * 与 LRU 区别：先淘汰最少使用次数的，如果使用次数相同，先淘汰最久未使用的
 */
public class Solution_460 {

    /**
     - LFU 与 LRU 不同在于，需要记录每个元素访问的频率，先根据频率来删除，频率相同则删除最久未访问的
     - 定义两个 Map
        - `Map<Integer, Node> keyMap`: key 是缓存的 key, value 是 Node 节点
        - `Map<Integer, DoubleList> freqMap`: key 是节点访问频次 freq, value 是该频次下的节点，以双向链表方式存储
     - 自定义双向链表 `DoubleList`, 节点 `Node` 除了需要保存 key, value 外，将节点访问频次 freq 也保存进去
     - 定义变量 minFreq, 用来记录当前缓存中访问最小的频次是多少，方便从 freqMap 中直接根据 key 查找
     - `get(key)` 操作
        - 1. 如果 key 在缓存中不存在，返回 -1
        - 2. 如果 key 在缓存(keyMap)中存在
            - 2.1 更新 key 对应 Node 节点的访问频次：`freq += 1`
            - 2.2 将 Node 节点移动到 `freqMap` 中对应 `key = freq + 1` 的链表中(头部)
            - 2.3 如果 `freqMap` 中对应 `key = freq` 的链表为空了，将该频次(freq)从 `freqMap` 中移除
     - `put(key, value)` 操作
        - 1. 如果 key 在缓存中存在
            - 1.1 更新 key 对应 Node 节点的 value 值，以及访问频次：`freq += 1`
            - 1.2 将 Node 节点移动到 `freqMap` 中对应 `key = freq + 1` 的链表中(头部)
            - 1.3 如果 `freqMap` 中对应 `key = freq` 的链表为空了，将该频次(freq)从 `freqMap` 中移除
            - 1.4 如果 `freqMap` 中 不包含 `minFreq`， 则需要将 `minFreq++`
        - 2. 如果 key 在缓存中不存在
            - 2.1 如果缓存满了(cap == keyMap.size())
                - 2.1.1 从 `freqMap` 中取出 `minFreq` 对应的链表 `list`
                - 2.1.2 删除 `list` 最后一个节点(最久未使用的)，删除 `keyMap` 中对应的值
                - 2.1.3 如果 `freqMap` 中对应 `key = freq` 的链表为空了，将该频次(freq)从 `freqMap` 中移除
            - 2.2 新建一个 Node(key, value, 1), freq = 1
            - 2.3 将 node 添加到 `keyMap` 和 `freqMap` 中
            - 2.4 更新 minFreq 值为 1
     */


    /** 链表节点类*/
    static class Node {
        public int key;
        public int value;
        public int freq; // 节点访问频次
        public Node prev;
        public Node next;
        public Node(){}
        public Node(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }

    /**双端链表*/
    static class DoubleList {
        // 头节点 和 尾结点
        private Node head, tail;
        // 链表长度
        private int size;

        // 在链表头添加节点
        public void addFirst(Node node) {
            // head = null 说明链表为空
            if (head == null) {
                head = tail = node;
            } else {
                // 在链表头添加节点
                Node old = head;
                old.prev = node;
                node.next = old;
                head = node;
            }
            // 链表长度+1
            size++;
        }

        // 删除链表尾部的节点, 并返回最后一个节点
        public Node removeLast() {
            Node node = tail;
            remove(tail);
            return node;
        }

        // 删除链表中的 node 节点，x 一定存在
        public void remove(Node node) {
            // 链表中只有一个元素，并且是 node
            if (head == node && tail == node) {
                head = tail = null;
            } else if (node == head) {
                head = head.next;
                head.prev = null;
            } else if (node == tail) {
                tail = tail.prev;
                tail.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            // 链表长度-1
            size--;
        }

        // 获取链表长度
        public int size() {
            return size;
        }

        // 判断链表是否为空
        public boolean isEmpty() {
            return size == 0;
        }

    }

    static class LFUCache {

        // 缓存容量
        private int capacity;
        // 当前缓存中，访问最小的频次
        private int minFreq;
        // 节点缓存 Map
        private Map<Integer, Node> keyMap;
        // 节点访问频次 Map
        private Map<Integer, DoubleList> freqMap;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.minFreq = 0;
            this.keyMap = new HashMap<>();
            this.freqMap = new HashMap<>();
        }

        // 从缓存中 get 元素
        public int get(int key) {
            // 如果不在缓存中，直接返回 -1
            if (!keyMap.containsKey(key)) return -1;
            // 从缓存中取出 node 节点
            Node node = keyMap.get(key);
            // 将 node 从频次为 freq 的链表中移除
            removeNodeFromOldList(node);
            // 更新 node 的访问频次
            node.freq++;
            // 将 node 添加到新频次的链表头部
            addNodeToNewList(node);
            // 返回 key 对应的 value
            return node.value;
        }

        // 向缓存中 put 元素
        public void put(int key, int value) {
            // 如果 key 在缓存中存在
            if (keyMap.containsKey(key)) {
                // 从缓存中取出 node 节点
                Node node = keyMap.get(key);
                // 将 node 从频次为 freq 的链表中移除
                removeNodeFromOldList(node);
                // 更新 node 的访问频次
                node.freq++;
                // 更新 node 的 value 值
                node.value = value;
                // 将 node 添加到新频次的链表头部
                addNodeToNewList(node);
            } else {
                // 如果缓存的容量已满
                if (capacity == keyMap.size()) {
                    //将缓存中访问频次最小的节点移除
                    removeMinFreqNode();
                }
                // 添加一个新节点到缓存中
                addNewNodeToList(key, value);
            }
        }

        // 从 freqMap 中获取 freq 对应的 list, 如果不存在，就添加一个空 list 到 map 中
        public DoubleList getListFromFreqMap(int freq) {
            if (freqMap.containsKey(freq)) {
                return freqMap.get(freq);
            } else {
                DoubleList list = new DoubleList();
                freqMap.put(freq, list);
                return list;
            }
        }

        // 将 node 从 freq 对应的链表中删除
        public void removeNodeFromOldList(Node node) {
            DoubleList list = getListFromFreqMap(node.freq);
            list.remove(node);
        }

        // 将 node 添加到 freq 对应的链表的头部
        public void addNodeToNewList(Node node) {
            DoubleList list = getListFromFreqMap(node.freq);
            list.addFirst(node);
            // 如果 freqMap 中不存在 minFreq, 更新 minFreq 值为 minFreq + 1
            // 这种情况是 更新的 freq 原来值 = minFreq
            if (freqMap.get(minFreq).isEmpty()) {
                minFreq++;
            }
        }

        // 添加一个新节点到缓存中
        public void addNewNodeToList(int key, int value) {
            // 新建一个节点，访问次数 freq = 1
            Node node = new Node(key, value, 1);
            // 添加到 keyMap 中
            keyMap.put(key, node);
            // 重置当前的最小访问次数 minFreq = 1
            minFreq = 1;
            // 将节点添加到 freqMap 对应链表的头部
            DoubleList list = getListFromFreqMap(node.freq);
            list.addFirst(node);
        }

        // 删除缓存中访问频次最小，且最久未访问的节点
        public void removeMinFreqNode() {
            // 取出 minFreq 对应的 list
            DoubleList list = getListFromFreqMap(minFreq);
            // 删除 list 最后一个结点
            Node node = list.removeLast();
            // 删除 keyMap 中对应的节点
            keyMap.remove(node.key);
        }
    }

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);
        lfu.put(2, 2);
        System.out.print(lfu.get(1) + " ");
        lfu.put(3, 3);
        System.out.print(lfu.get(2) + " ");
        System.out.print(lfu.get(3) + " ");
        lfu.put(4, 4);
        System.out.print(lfu.get(1) + " ");
        System.out.print(lfu.get(3) + " ");
        System.out.print(lfu.get(4) + " ");
    }

}
