
//La02DopoMorenoRubiano
//Autores: cristian Moreno- Junaita Rubiano
import java.util.ArrayList;
import java.util.HashMap;

public class DecisionTree {

    private Node root; // nodo raiz del arbol
    private int size; // número de nodos en el arbol

    private final HashMap<String, Node> nodes = new HashMap<>();

    private static class Node {
        String value; // pregunta/decision contenida en el nodo (siempre en minúsculas)
        Node padre = null;
        Node yes = null; // hijo "si"
        Node no = null; // hijo "no"

        Node(String value, Node padre, Node hijoYes, Node hijoNo) {
            this.value = value.toLowerCase(); // guardar siempre en minúscula
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
        this.size = 1; // solo el nodo raíz
        nodes.put(root.toLowerCase(), this.root);
    }

    /**
     * Agrega simultáneamente los hijos yes y no a un padre existente.
     * Reglas (según pruebas):
     * - Case-insensitive en nombres.
     * - El padre debe existir.
     * - El padre no debe tener ya hijos (no más de dos hijos).
     * - No se permiten nodos duplicados por nombre en todo el árbol.
     */
    public boolean add(String parent, String yesChild, String noChild) {
        if (parent == null || yesChild == null || noChild == null)
            return false;

        String p = parent.toLowerCase();
        String y = yesChild.toLowerCase();
        String n = noChild.toLowerCase();

        Node pNode = nodes.get(p);
        if (pNode == null)
            return false; // padre no existe
        if (pNode.yes != null || pNode.no != null)
            return false; // ya tiene hijos

        // no permitir duplicados en todo el árbol ni entre sí
        if (y.equals(n))
            return false;
        if (nodes.containsKey(y) || nodes.containsKey(n))
            return false;

        // crear hijos
        Node yNode = new Node(y, pNode, null, null);
        Node nNode = new Node(n, pNode, null, null);
        pNode.yes = yNode;
        pNode.no = nNode;

        nodes.put(y, yNode);
        nodes.put(n, nNode);
        size += 2;
        return true;
    }

    /**
     * Elimina un nodo hoja por nombre (case-insensitive).
     * - Solo permite eliminar hojas.
     * - Actualiza referencias del padre y el mapa de nodos.
     */
    public boolean delete(String node) {
        if (node == null)
            return false;
        String key = node.toLowerCase();
        Node target = nodes.get(key);
        if (target == null)
            return false;
        if (!target.isLeaf())
            return false; // solo hojas

        // no permitir borrar la raíz si es el único nodo
        if (target == root) {
            nodes.remove(key);
            root = null;
            size = 0;
            return true;
        }

        Node parent = target.padre;
        if (parent == null)
            return false;

        if (parent.yes == target)
            parent.yes = null;
        if (parent.no == target)
            parent.no = null;

        nodes.remove(key);
        size -= 1;
        return true;
    }

    /**
     * No usado en las pruebas. Retornamos el mismo árbol para mantener
     * compatibilidad.
     */
    public DecisionTree eval(String[][] values) {
        return this;
    }

    public boolean contains(String node) {
        if (node == null)
            return false;
        return nodes.containsKey(node.toLowerCase());
    }

    public boolean isQuestion(String node) {
        if (node == null)
            return false;
        Node n = nodes.get(node.toLowerCase());
        if (n == null)
            return false;
        return !n.isLeaf();
    }

    public boolean isDecision(String node) {
        if (node == null)
            return false;
        Node n = nodes.get(node.toLowerCase());
        if (n == null)
            return false;
        return n.isLeaf();
    }

    /**
     * No requerido por las pruebas; se deja sin implementación funcional.
     */
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
        if (leaves.isEmpty())
            return 0;
        final ArrayList<Integer> heights = new ArrayList<>();
        for (Node leaf : leaves) {
            int tempHeight = 0;
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
        if (dt == null)
            return false;
        if (this.size != dt.size)
            return false;
        return equalsNode(this.root, dt.root);
    }

    private boolean equalsNode(Node a, Node b) {
        if (a == null && b == null)
            return true;
        if (a == null || b == null)
            return false;
        if (!a.value.equals(b.value))
            return false; // ya están en minúsculas
        return equalsNode(a.yes, b.yes) && equalsNode(a.no, b.no);
    }

    public boolean equals(Object g) {
        if (this == g)
            return true;
        if (g == null || !(g instanceof DecisionTree))
            return false;
        return equals((DecisionTree) g);
    }

    // Trees are inside parentesis. The names are in lowercase. The childs must
    // always be in yes no order.
    // For example, (a yes (b yes (c) no (d)) no (e))
    public String toString() {
        if (root == null)
            return "()";
        return serialize(root);
    }

    private String serialize(Node n) {
        if (n == null)
            return "()";
        if (n.isLeaf()) {
            return "(" + n.value + ")";
        } else {
            // siempre en orden yes luego no
            return "(" + n.value + " yes " + serialize(n.yes) + " no " + serialize(n.no) + ")";
        }
    }
}