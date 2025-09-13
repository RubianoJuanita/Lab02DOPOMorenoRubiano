public class DecisionTree {

    private Node root; // nodo raiz del arbol
    private int size;// número de nodos en el arbol

    private static class Node {
        String value;// pregunta contenida ene l nodo
        Node yes;// hijo "si"
        Node no;// hijo "no"

        Node(String value) {
            this.value = value.toLowerCase();// lo guardamos siemroe en minuscula.
            this.yes = null;
        }

        boolean isLeaf() {
            return yes == null && no == null;
        }

    }

    // Constructor
    public DecisionTree(String root) {
        this.root = new Node(root);
        this.size = 1;// solo tomamos el nodo raíz
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
        return 1;
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
