package LeetCode;

/**
 * 有两个单链表，代表两个非负数，每一个节点代表一个数位，数字是反向存储的，即第一个结点表示最低位，
 * 最后一个结点表示最高位。求两个数的相加和，并且以链表形式返回。
 */
public class TwoLinkedAddSolution {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

        ListNode listNode1 = new TwoLinkedAddSolution().new ListNode(2);

        ListNode listNode2 = new TwoLinkedAddSolution().new ListNode(4);

        ListNode listNode3 = new TwoLinkedAddSolution().new ListNode(3);

        listNode1.next = listNode2;

        listNode2.next = listNode3;

        ListNode listNode4 = new TwoLinkedAddSolution().new ListNode(5);

        ListNode listNode5 = new TwoLinkedAddSolution().new ListNode(6);

        ListNode listNode6 = new TwoLinkedAddSolution().new ListNode(4);

        listNode4.next = listNode5;

        listNode5.next = listNode6;

        ListNode listNode = new TwoLinkedAddSolution().addTwoNumbers(listNode1, listNode4);

        System.out.println(listNode);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        //要返回的链表
        ListNode resultNode = new ListNode(0);

        ListNode beginNode = new ListNode(0);

        boolean flag = true;

        int carryBit = 0;

        while (l1!=null && l2!=null) {

            int sum = l1.val + l2.val + carryBit;

            carryBit = sum / 10;

            if (carryBit > 0) {
                sum = sum-10;
            }

            if (flag) {

                resultNode.val = sum;

                beginNode = resultNode;

                flag = false;

            } else {

                ListNode node = new ListNode(sum);

                beginNode.next = node;

                beginNode = node;

            }

            l1 = l1.next;

            l2 = l2.next;

            if (l1 == null && l2 == null && carryBit > 0) {

                ListNode node = new ListNode(carryBit);

                beginNode.next = node;

            }
        }

        while (l1!=null || l2!=null) {

            if (l1 == null) {

                int sum = l2.val + carryBit;

                carryBit = sum / 10;

                if (carryBit > 0) {
                    sum = sum-10;
                }

                ListNode node = new ListNode(sum);

                beginNode.next = node;

                beginNode = node;

                l2 = l2.next;
            } else {

                int sum = l1.val + carryBit;

                carryBit = sum / 10;

                if (carryBit > 0) {
                    sum = sum-10;
                }

                ListNode node = new ListNode(sum);

                beginNode.next = node;

                beginNode = node;

                l1 = l1.next;
            }
            if (l1 == null && l2 == null && carryBit > 0) {

                ListNode node = new ListNode(carryBit);

                beginNode.next = node;

            }
        }

        return resultNode;
    }

}
