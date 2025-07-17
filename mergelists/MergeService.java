package mergelists;


public class MergeService {

    public ListNode mergeSingleLinkedLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode current = dummyHead;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        } else if (list2 != null) {
            current.next = list2;
        }

        return dummyHead.next;
    }


    public static void main(String[] args) {

        MergeService mergeService = new MergeService();

        ListNode list1_ex1 = ListNode.fromArray(new int[]{3, 6, 9});
        ListNode list2_ex1 = ListNode.fromArray(new int[]{4, 7, 10});
        System.out.println("Wejście: list1=" + list1_ex1 + ", list2=" + list2_ex1);
        ListNode mergedList_ex1 = mergeService.mergeSingleLinkedLists(list1_ex1, list2_ex1);
        System.out.println("Wyjście: " + mergedList_ex1);
        System.out.println("---");
        //-------------------
        ListNode list1_empty = null;
        ListNode list2_empty = null;
        System.out.println("Wejście: list1=" + list1_empty + ", list2=" + list2_empty);
        ListNode mergedList_empty = mergeService.mergeSingleLinkedLists(list1_empty, list2_empty);
        System.out.println("Wyjście: " + mergedList_empty);
        System.out.println("---");
        //-------------------
        ListNode list1_single = ListNode.fromArray(new int[]{5});
        ListNode list2_empty_2 = null;
        System.out.println("Wejście: list1=" + list1_single + ", list2=" + list2_empty_2);
        ListNode mergedList_single = mergeService.mergeSingleLinkedLists(list1_single, list2_empty_2);
        System.out.println("Wyjście: " + mergedList_single);
        System.out.println("---");
        //-------------------
        ListNode list1_neg = ListNode.fromArray(new int[]{-5, -2, 0, 3});
        ListNode list2_neg = ListNode.fromArray(new int[]{-10, -2, 1, 3, 7});
        System.out.println("Wejście: list1=" + list1_neg + ", list2=" + list2_neg);
        ListNode mergedList_neg = mergeService.mergeSingleLinkedLists(list1_neg, list2_neg);
        System.out.println("Wyjście: " + mergedList_neg);
        System.out.println("---");
        //-------------------
        ListNode list1_long = ListNode.fromArray(new int[]{1, 5, 8, 12, 15});
        ListNode list2_short = ListNode.fromArray(new int[]{2, 6});
        System.out.println("Wejście: list1=" + list1_long + ", list2=" + list2_short);
        ListNode mergedList_diff_len = mergeService.mergeSingleLinkedLists(list1_long, list2_short);
        System.out.println("Wyjście: " + mergedList_diff_len); // Oczekiwane:
    }
}