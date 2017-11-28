package runtime.main;


public class List<K, V> {

    public ListNode head;

    public V add(K key, V value) {
        if (head == null) {
            head = new ListNode(key, value);
            return null;
        }

        ListNode currentNode = head;

        while (currentNode.next != null && !currentNode.key.equals(key)) {
            currentNode = currentNode.next;
        }

        // Key existed
        if (currentNode.key.equals(key)) {
            V returnValue = currentNode.value;
            currentNode.value = value;
            return returnValue;
        }

        // Should work, if key don't exists we definitely should be at end of list
        assert currentNode.next == null;
        currentNode.next = new ListNode(key, value);
        return null;
    }

    public V remove(K key) {
        if (head == null) {
            return null;
        }

        ListNode currentNode = head;

        if (head.next == null) {
            if (head.key.equals(key)) {
                V returnValue = head.value;
                head = null;
                return returnValue;
            } else {
                return null;
            }
        }

        while (currentNode.next.next != null && !currentNode.next.key.equals(key)) {
            currentNode = currentNode.next;
        }

        if (currentNode.next.key.equals(key)) {
            V returnValue = currentNode.next.value;
            if (currentNode.next.next == null) {
                currentNode.next = null;
            } else {
                currentNode.next = currentNode.next.next;
            }
            return returnValue;
        } else {
            return null;
        }
    }

    public V get(K key) {
        if (head == null) {
            return null;
        }

        ListNode currentNode = head;

        while (currentNode.next != null && !currentNode.key.equals(key)) {
            currentNode = currentNode.next;
        }

        if (currentNode.key.equals(key)) {
            return currentNode.value;
        } else {
            return null;
        }
    }

    public class ListNode {

        public ListNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        ListNode next;
        K key;
        V value;
    }
}

