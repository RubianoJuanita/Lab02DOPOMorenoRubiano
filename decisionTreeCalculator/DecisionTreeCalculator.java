import java.util.HashMap;

public class DecisionTreeCalculator {

    private HashMap<String, DecisionTree> variables;
    private boolean lastOk; // guarda si la última operación fue exitosa

    public DecisionTreeCalculator() {
        this.variables = new HashMap<>();
        this.lastOk = true;
    }

    // Crear una nueva variable (sin asignar árbol todavía)
    public void create(String name) {
        if (name == null || name.isEmpty()) {
            lastOk = false;
            return;
        }
        if (variables.containsKey(name)) {
            lastOk = false; // ya existe
            return;
        }
        variables.put(name, null);
        lastOk = true;
    }

    // Crear un árbol y asignarlo a una variable existente
    // Ejemplo: a := decisionTree con raíz "raiz"
    public void assign(String a, String root) {
        if (a == null || root == null || !variables.containsKey(a)) {
            lastOk = false;
            return;
        }
        DecisionTree dt = new DecisionTree(root);
        variables.put(a, dt);
        lastOk = true;
    }

    // Consultar el árbol en forma de String
    public String toString(String decisionTree) {
        if (decisionTree == null || !variables.containsKey(decisionTree)) {
            lastOk = false;
            return null;
        }
        DecisionTree dt = variables.get(decisionTree);
        if (dt == null) {
            lastOk = false;
            return null;
        }
        lastOk = true;
        return dt.toString();
    }

    // Saber si la última operación fue exitosa
    public boolean ok() {
        return lastOk;
    }
}