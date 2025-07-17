package mergelists;



public class ListNode {
    int val;
    ListNode next;



    ListNode(int val) {
        this.val = val;
    }



    static ListNode fromArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode current = this;
        sb.append("[");

        while (current != null) {
            sb.append(current.val);

            if (current.next != null) {
                sb.append(",");
            }
            current = current.next;
        }
        sb.append("]");

        return sb.toString();
    }
}