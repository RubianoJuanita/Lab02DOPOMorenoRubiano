import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DecisionTreeCalculatorTest {

    private DecisionTreeCalculator calc;

    @Before
    public void setUp() {
        calc = new DecisionTreeCalculator();
    }

    // ======= CICLO 1 =======
    @Test
    public void shouldCreateVariable() {
        calc.create("A");
        assertTrue(calc.ok());
    }

    @Test
    public void shouldNotCreateDuplicateVariable() {
        calc.create("A");
        calc.create("A");
        assertFalse(calc.ok());
    }

    @Test
    public void shouldAssignTree() {
        calc.create("A");
        calc.assign("A", "root");
        assertTrue(calc.ok());
    }

    @Test
    public void shouldNotAssignToNonExistingVar() {
        calc.assign("B", "root");
        assertFalse(calc.ok());
    }

    // ======= CICLO 2 =======
    @Test
    public void shouldAddChildren() {
        calc.create("A");
        calc.assign("A", "root");
        calc.add("A", "root", "L", "R");
        assertTrue(calc.ok());
    }

    @Test
    public void shouldNotAddChildrenIfParentNotExist() {
        calc.create("A");
        calc.assign("A", "root");
        calc.add("A", "X", "L", "R");
        assertFalse(calc.ok());
    }

    @Test
    public void shouldRemoveNode() {
        calc.create("A");
        calc.assign("A", "root");
        calc.add("A", "root", "L", "R");
        calc.remove("A", "L");
        assertTrue(calc.ok());
    }

    @Test
    public void shouldNotRemoveNonExistentNode() {
        calc.create("A");
        calc.assign("A", "root");
        calc.remove("A", "X");
        assertFalse(calc.ok());
    }

    // ======= CICLO 3 =======
    @Test
    public void shouldUnionTrees() {
        calc.create("A");
        calc.assign("A", "aRoot");
        calc.create("B");
        calc.assign("B", "bRoot");
        calc.union("A", "B", "C");
        assertTrue(calc.ok());
    }

    @Test
    public void shouldNotUnionIfTreeMissing() {
        calc.create("A");
        calc.assign("A", "aRoot");
        calc.union("A", "Z", "C");
        assertFalse(calc.ok());
    }
}
