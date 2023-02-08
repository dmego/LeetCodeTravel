package cn.dmego.leetcode.solution.lc101_lc150;

import java.util.HashMap;
import java.util.Map;

/**
 * [146] LRU 缓存
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 */
public class Solution_146 {

    /** 链表节点类*/
    static class Node {
        public int key;
        public int value;
        public Node prev;
        public Node next;
        public Node(){}
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
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

    }


    static class LRUCache {

        Map<Integer, Node> map;
        DoubleList deque;
        int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.deque = new DoubleList();
        }

        public int get(int key) {
            // 未找到记录，返回 -1
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            // 将记录移动至双向链表头部
            deque.remove(node);
            deque.addFirst(node);
            // 返回记录
            return node.value;
        }

        public void put(int key, int value) {
            // 如果 key 已经存在，直接更新map
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.value = value;
                // 将记录移动至双向链表头部
                deque.remove(node);
                deque.addFirst(node);
            } else {
                // 如果缓存满了，删除最近最少使用的记录，也就是队列尾部的记录
                if (deque.size() == capacity) {
                    // 删除队列最后一个记录
                    Node node = deque.removeLast();
                    // 删除map中对应的记录
                    map.remove(node.key);
                }
                Node node = new Node(key, value);
                // 新记录添加到缓存头部，链表头部
                deque.addFirst(node);
                // map 中添加记录
                map.put(key, node);
            }
        }
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2,1);
        cache.put(1,1);
        cache.put(2,3);
        cache.get(1);
        cache.get(3);
        cache.put(4,1);
        cache.get(4);
    }


}
