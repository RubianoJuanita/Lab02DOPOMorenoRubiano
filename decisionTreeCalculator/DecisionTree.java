import java.util.ArrayList;
import java.util.HashMap;

public class DecisionTree {

    private Node root; // nodo raiz del arbol
    private int size;// número de nodos en el arbol

    private final HashMap<String, Node> nodes = new HashMap<>();

    private static class Node {
        String value;// pregunta contenida ene l nodo
        Node padre = null;
        Node yes = null;// hijo "si"
        Node no = null;// hijo "no"

        Node(String value, Node padre, Node hijoYes, Node hijoNo) {
            this.value = value.toLowerCase();// lo guardamos siemroe en minuscula.
            this.yes = hijoYes;
            this.no = hijoNo;
            this.padre = padre;
        }

        public Node getPadre() {
            return this.padre;
        }

        boolean isLeaf() {
            return yes == null && no == null;
        }

    }

    // Constructor
    public DecisionTree(String root) {
        this.root = new Node(root, null, null, null);
        this.size = 1;// solo tomamos el nodo raíz
        nodes.put(root.toLowerCase(), this.root);
    }

    public boolean add(String parent, String yesChild, String noChild) {
        return true;
    }

    public boolean delete(String node) {
        return true;
    }

    public DecisionTree eval(String[][] values) {
        return null;
    }

    public boolean contains(String node) {
        return false;
    }

    public boolean isQuestion(String node) {
        return false;
    }

    public boolean isDecision(String node) {
        return false;
    }

    public DecisionTree union(DecisionTree dt) {
        return null;
    }

    public int nodes() {
        return this.size;
    }

    public int height() {
        final ArrayList<Node> leaves = new ArrayList<>();
        for (Node n : nodes.values()) {
            if (n.isLeaf()) {
                leaves.add(n);
            }
        }
        final ArrayList<Integer> heights = new ArrayList<>();
        for (Node leaf : leaves) {
            int tempHeight = 0;
            // Iteracion entre todos los posibles padres del nodos hojas hasta que no haya
            // padre.
            for (Node n = leaf; n != null; n = n.getPadre()) {
                tempHeight++;
            }
            heights.add(tempHeight);
        }
        int maxHeight = 0;
        for (Integer h : heights) {
            if (h > maxHeight) {
                maxHeight = h;
            }
        }
        return maxHeight;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.yes);
        int rightHeight = getHeight(node.no);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public boolean equals(DecisionTree dt) {
        return false;
    }

    public boolean equals(Object g) {
        return equals((DecisionTree) g);
    }

    // Trees are inside parentesis. The names are in lowercase. The childs must
    // always be in yes no order.
    // For example, (a yes (b yes (c) no (d)) no (e))
    public String toString() {
        return "";
    }
}
