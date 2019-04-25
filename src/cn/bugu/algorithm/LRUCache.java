package cn.bugu.algorithm;

import java.util.HashMap;

public class LRUCache {
    private int capacity;
    private HashMap<Integer, Node> map = new HashMap<>();
    private Node head = new Node(-1, -1), tail = new Node(-1, -1);

    private class Node {
        Node prev, next;
        int val, key;

        public Node(int key, int val) {
            this.val = val;
            this.key = key;
            prev = null;
            next = null;
        }

//         @Override
//         public String toString() {
//             return "(" + key + ", " + val + ") " + "last:"
//                     + (prev == null ? "null" : "node");
//         }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        tail.prev = head;
        head.next = tail;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // remove current
        Node currentNode = map.get(key);
        currentNode.prev.next = currentNode.next;
        currentNode.next.prev = currentNode.prev;

        // move current to tail;
        moveToTail(currentNode);

        return map.get(key).val;
    }

    public void set(int key, int value) {
        if (get(key) != -1) {
            map.get(key).val = value;
            return;
        }
        if (map.size() == capacity) {
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }
        Node insert = new Node(key, value);
        map.put(key, insert);
        moveToTail(insert);
    }

    private void moveToTail(Node current) {
        current.prev = tail.prev;
        tail.prev = current;
        current.prev.next = current;
        current.next = tail;
    }
}
