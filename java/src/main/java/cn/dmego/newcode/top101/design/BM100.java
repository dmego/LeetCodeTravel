package cn.dmego.newcode.top101.design;

import java.util.HashMap;

/**
 * 设计 LRU 缓存
 * @author dmego
 * @date 2022/03/23 09:32
 */
public class BM100 {

    public class Solution {
        // 双端链表节点
        class Node {
            int key;
            int value;
            Node prev;
            Node next;
            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
        // 双端链表
        class DoubleList {
            // 头节点
            private Node head;
            // 尾节点
            private Node tail;
            // 链表长度
            private int size;

            // 添加 node 到链表头
            public void addFirst(Node node) {
                // head = null 说明链表为空
                if(head == null) {
                    head = tail = node;
                } else {
                    // 添加到链表头，并设置为头结点
                    head.prev = node;
                    node.next = head;
                    head = node;
                }
                // 链表长度 + 1
                size++;
            }

            // 删除链表中的一个节点
            public void remove(Node node) {
                // 如果链表中就 node 一个元素
                if (head == node && tail == node) {
                    head = tail = null;
                } else if (head == node) {
                    // 删除头结点
                    head.next.prev = null;
                    head = head.next;
                } else if (tail == node) {
                    // 删除尾结点
                    tail.prev.next = null;
                    tail = tail.prev;
                } else {
                    // 删除 node
                    node.next.prev = node.prev;
                    node.prev.next = node.next;
                }
                // 链表长度 - 1
                size--;
            }

            // 删除链表尾节点
            public Node removeLast() {
                Node last = tail;
                remove(tail);
                return last;
            }

            // 返回链表长度
            public int getSize() {
                return size;
            }
        }

        // LRU 缓存容量
        int capacity;
        // 双端链表
        DoubleList queue;
        // Map 缓存
        HashMap<Integer, Node> map;

        public Solution(int capacity) {
            // write code here
            this.capacity = capacity;
            queue = new DoubleList();
            map = new HashMap<>();
        }

        public int get(int key) {
            // cache 中存在 key
            if (map.containsKey(key)) {
                Node node = map.get(key);
                // 将 Node 移动至链表头部，表示最近访问
                queue.remove(node);
                queue.addFirst(node);
                // 返回 value
                return node.value;
            }
            // cache 不存在，返回 - 1
            return -1;
        }

        public void set(int key, int value) {
            // cache 中存在 key
            if (map.containsKey(key)) {
                // 更新 value 值，并且将 Node 移动至链表头部，表示最近访问
                Node node = map.get(key);
                queue.remove(node);
                node.value = value;
                queue.addFirst(node);
            } else {
                // cache 中不存在 key
                if (queue.getSize() == capacity) {
                    // 如果 cache 容量已满，删除最少使用的元素（链表尾元素）
                    Node last = queue.removeLast();
                    // 注意:  map 中的也要删除
                    map.remove(last.key);
                }
                // 根据 key-value 新建 node
                Node node = new Node(key, value);
                // 添加到链表头部
                queue.addFirst(node);
                // 添加到 map 缓存
                map.put(key, node);
            }
        }
    }

}
