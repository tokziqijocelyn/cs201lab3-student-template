public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3) {
          int[][] t = { parse(traversal1), parse(traversal2), parse(traversal3) };
          for (int i = 0; i < 3; i++) { // inorder index
               for (int j = 0; j < 3; j++) { // preorder index
                    if (i == j) {
                         continue;
                    }
                    int k = 3 - i - j; // remaining is postorder
                    if (isValid(t[i], t[j], t[k])) {
                         String[] label = new String[3];
                         label[i] = "Inorder";
                         label[j] = "Preorder";
                         label[k] = "Postorder";
                         return "Traversal 1 - " + label[0] + ", Traversal 2 - " + label[1] + ", Traversal 3 - " + label[2];
                    }
               }
          }
          return "Invalid traversals";
     }

     private static int[] parse(String s) {
          String[] parts = s.split("-");
          int[] result = new int[parts.length];
          for (int i = 0; i < parts.length; i++) {
               result[i] = Integer.parseInt(parts[i].trim());
          }
          return result;
     }

     private static boolean isValid(int[] in, int[] pre, int[] post) {
          if (in.length != pre.length || in.length != post.length) {
               return false;
          }

          // inorder of a BST must be the sorted list of its nodes
          int[] sorted = pre.clone();
          java.util.Arrays.sort(sorted);
          if (!java.util.Arrays.equals(sorted, in)) {
               return false;
          }

          // inserting nodes in preorder sequence rebuilds the unique BST with that preorder
          Node root = null;
          for (int v : pre) {
               root = insert(root, v);
          }

          java.util.List<Integer> actualPost = new java.util.ArrayList<>();
          postorder(root, actualPost);
          for (int i = 0; i < post.length; i++) {
               if (actualPost.get(i) != post[i]) {
                    return false;
               }
          }
          return true;
     }

     private static Node insert(Node node, int v) {
          if (node == null) {
               return new Node(v);
          }
          if (v < node.value) {
               node.left = insert(node.left, v);
          } else {
               node.right = insert(node.right, v);
          }
          return node;
     }

     private static void postorder(Node node, java.util.List<Integer> out) {
          if (node == null) {
               return;
          }
          postorder(node.left, out);
          postorder(node.right, out);
          out.add(node.value);
     }

     private static class Node {
          int value;
          Node left, right;

          Node(int value) {
               this.value = value;
          }
     }
}