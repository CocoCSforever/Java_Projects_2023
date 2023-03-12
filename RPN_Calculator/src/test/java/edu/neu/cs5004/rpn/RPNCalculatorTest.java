package edu.neu.cs5004.rpn;

import edu.neu.cs5004.rpn.exceptions.IllegalOperationException;
import edu.neu.cs5004.rpn.exceptions.NotEnoughArgumentsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RPNCalculatorTest {
    RPNCalculator testRPN;

    @BeforeEach
    void setUp() {
        testRPN = new RPNCalculator();
    }

    @Test
    void processMathOp() throws IllegalOperationException, NotEnoughArgumentsException {
        double a = 4, b = 5;
        String testA = "4", testB = "5";

        // plus
        testRPN.processInput(testA);
        testRPN.processInput(testB);
        testRPN.processInput("+");
        assertEquals(a+b, testRPN.pop());

        // negate
        testRPN.processInput(testA);
        testRPN.processInput("-");
        assertEquals(-a, testRPN.pop());
        // minus
        testRPN.processInput(testA);
        testRPN.processInput(testB);
        testRPN.processInput("-");
        assertEquals(a-b, testRPN.pop());
        // multiply
        testRPN.processInput(testA);
        testRPN.processInput(testB);
        testRPN.processInput("*");
        assertEquals(a*b, testRPN.pop());
        // divide
        testRPN.processInput(testA);
        testRPN.processInput(testB);
        testRPN.processInput("/");
        assertEquals(b/a, testRPN.pop());
        // mod
        testRPN.processInput(testA);
        testRPN.processInput(testB);
        testRPN.processInput("%");
        assertEquals(b%a, testRPN.pop());

        // -----θ-------//
        int c = 60;
        String testC = "60";

        // sin
        testRPN.processInput(testA);
        testRPN.processInput(testC);
        testRPN.processInput("sin");
        assertEquals(Math.sin(c), testRPN.pop());
        // cos
        testRPN.processInput(testC);
        testRPN.processInput("cos");
        assertEquals(Math.cos(c), testRPN.pop());
        // tan
        testRPN.processInput(testC);
        testRPN.processInput("tan");
        assertEquals(Math.tan(c), testRPN.pop());
        // abs
        testRPN.processInput(testC);
        testRPN.processInput("abs");
        assertEquals(Math.abs(c), testRPN.pop());
    }

    @Test
    void processBoolEquals() throws IllegalOperationException, NotEnoughArgumentsException {
        double a = 4, b = 5, c = 4;
        String testA = "4", testB = "5", testC = "4";

        testRPN.processInput(testA);
        testRPN.processInput(testB);
        testRPN.processInput("==");
        assertEquals(0, testRPN.pop());

        testRPN.processInput(testA);
        testRPN.processInput(testC);
        testRPN.processInput("==");
        assertEquals(1, testRPN.pop());

        // conversion between boolean and 0/1
        testA = "true";
        testB = "1";
        testC = "false";
        String testD = "0";
        // true == 1
        testRPN.processInput(testA);
        testRPN.processInput(testB);
        testRPN.processInput("==");
        assertEquals(1, testRPN.pop());
        // false == 0
        testRPN.processInput(testC);
        testRPN.processInput(testD);
        testRPN.processInput("==");
        assertEquals(1, testRPN.pop());
        // true != 0
        testRPN.processInput(testA);
        testRPN.processInput(testD);
        testRPN.processInput("==");
        assertEquals(0, testRPN.pop());
        // false != 1
        testRPN.processInput(testB);
        testRPN.processInput(testC);
        testRPN.processInput("==");
        assertEquals(0, testRPN.pop());
    }

    @Test
    void processBoolAnd() throws IllegalOperationException, NotEnoughArgumentsException {
        String testA = "True", testB = "true", testC = "False", testD = "false";
        testRPN.processInput(testA);
        testRPN.processInput(testB);
        testRPN.processInput("&&");
        assertEquals(1, testRPN.pop());

        testRPN.processInput(testA);
        testRPN.processInput(testC);
        testRPN.processInput("&&");
        assertEquals(0, testRPN.pop());

        testRPN.processInput(testD);
        testRPN.processInput(testB);
        testRPN.processInput("&&");
        assertEquals(0, testRPN.pop());

        testRPN.processInput(testC);
        testRPN.processInput(testD);
        testRPN.processInput("&&");
        assertEquals(0, testRPN.pop());
    }

    @Test
    void processBoolOr() throws IllegalOperationException, NotEnoughArgumentsException {
        String testA = "True", testB = "true", testC = "False", testD = "false";

        testRPN.processInput(testA);
        testRPN.processInput(testB);
        testRPN.processInput("||");
        assertEquals(1, testRPN.pop());

        testRPN.processInput(testB);
        testRPN.processInput(testD);
        testRPN.processInput("||");
        assertEquals(1, testRPN.pop());

        testRPN.processInput(testB);
        testRPN.processInput(testC);
        testRPN.processInput("||");
        assertEquals(1, testRPN.pop());

        testRPN.processInput(testC);
        testRPN.processInput(testD);
        testRPN.processInput("||");
        assertEquals(0, testRPN.pop());
    }

    @Test
    void processBoolNot() throws IllegalOperationException, NotEnoughArgumentsException {
        String testA = "True", testB = "true", testC = "False", testD = "false";

        testRPN.processInput(testA);
        testRPN.processInput("!");
        assertEquals(0, testRPN.pop());

        testRPN.processInput(testB);
        testRPN.processInput("!");
        assertEquals(0, testRPN.pop());

        testRPN.processInput(testC);
        testRPN.processInput("!");
        assertEquals(1, testRPN.pop());

        testRPN.processInput(testD);
        testRPN.processInput("!");
        assertEquals(1, testRPN.pop());
    }

    @Test
    void processBoolException() throws IllegalOperationException, NotEnoughArgumentsException {
        // IllegalArgumentException
        double a = 4, b = 40000;
        String testA = "4", testB = "40000";

        // Not(!)
        testRPN.processInput(testA);
        assertThrows(IllegalArgumentException.class, () -> testRPN.processInput("!"));
        testRPN.processInput(testB);
        assertThrows(IllegalArgumentException.class, () -> testRPN.processInput("!"));

        // Or(||)
        String testC = "true";
        testRPN.processInput(testA);
        testRPN.processInput(testC);
        assertThrows(IllegalArgumentException.class, () -> testRPN.processInput("||"));
        testRPN.processInput(testC);
        testRPN.processInput(testA);
        assertThrows(IllegalArgumentException.class, () -> testRPN.processInput("||"));

        // And(&&)
        testRPN.processInput(testA);
        testRPN.processInput(testC);
        assertThrows(IllegalArgumentException.class, () -> testRPN.processInput("&&"));
        testRPN.processInput(testC);
        testRPN.processInput(testA);
        assertThrows(IllegalArgumentException.class, () -> testRPN.processInput("&&"));
    }

    @Test
    void IllegalOperationException() {
        assertThrows(IllegalOperationException.class, () -> testRPN.processInput("ILLEGAL"));
        assertThrows(IllegalOperationException.class, () -> testRPN.processInput("OTHER"));
        assertThrows(IllegalOperationException.class, () -> testRPN.processInput("12true"));
        assertThrows(IllegalOperationException.class, () -> testRPN.processInput("g00d"));
        assertThrows(IllegalOperationException.class, () -> testRPN.processInput("//"));
        assertThrows(IllegalOperationException.class, () -> testRPN.processInput("("));
        assertThrows(IllegalOperationException.class, () -> testRPN.processInput("="));
    }

    @Test
    void NotEnoughArgumentsException() throws IllegalOperationException, NotEnoughArgumentsException {
        // size == 0
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("+"));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("-"));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("*"));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("/"));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("%"));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("sin"));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("abs"));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("!"));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("=="));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("&&"));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("||"));

        // size == 1
        String testA = "1";
        testRPN.processInput(testA);
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("+"));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("*"));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("/"));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("%"));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("=="));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("&&"));
        assertThrows(NotEnoughArgumentsException.class, () -> testRPN.processInput("||"));
        testRPN.processInput("-");
        assertEquals(-1, testRPN.pop());
        testRPN.processInput(testA);
        testRPN.processInput("!");
        assertEquals(0, testRPN.pop());
        testRPN.processInput(testA);
        testRPN.processInput("sin");
        assertEquals(Math.sin(1), testRPN.pop());
        testRPN.processInput(testA);
        testRPN.processInput("tan");
        assertEquals(Math.tan(1), testRPN.pop());
    }

    @Test
    void pop() throws IllegalOperationException, NotEnoughArgumentsException {
        // single push
        double a = 4, b = 400, c = 40000;
        String testA = "True", testB = "true", testC = "False", testD = "false";
        testRPN.processInput(testA);
        assertEquals(1, testRPN.pop());
        testRPN.processInput(testB);
        assertEquals(1, testRPN.pop());
        testRPN.processInput(testC);
        assertEquals(0, testRPN.pop());
        testRPN.processInput(testD);
        assertEquals(0, testRPN.pop());
        testRPN.processInput("4");
        assertEquals(a, testRPN.pop());
        testRPN.processInput("400");
        assertEquals(b, testRPN.pop());
        testRPN.processInput("40000");
        assertEquals(c, testRPN.pop());

        // multiple push
        testRPN.processInput(testA);
        testRPN.processInput(testB);
        testRPN.processInput(testC);
        assertEquals(0, testRPN.pop());
        testRPN.processInput("4");
        testRPN.processInput("400");
        assertEquals(b, testRPN.pop());
        testRPN.processInput("40000");
        testRPN.processInput(testC);
        assertEquals(0, testRPN.pop());
    }

    @Test
    void peekItemAt() throws IllegalOperationException, NotEnoughArgumentsException {
        double a = 4, b = 400, c = 40000;
        String testA = "True", testB = "true", testC = "False", testD = "false";

        testRPN.processInput(testA);
        testRPN.processInput(testB);
        testRPN.processInput(testC);
        assertEquals(1, testRPN.peekItemAt(0));
        assertEquals(1, testRPN.peekItemAt(1));
        assertEquals(0, testRPN.peekItemAt(2));
        testRPN.processInput("4");
        testRPN.processInput("400");
        assertEquals(a, testRPN.peekItemAt(3));
        assertEquals(b, testRPN.peekItemAt(4));
        assertEquals(b, testRPN.pop());
        testRPN.processInput("40000");
        testRPN.processInput(testC);
        assertEquals(c, testRPN.peekItemAt(4));
        assertEquals(0, testRPN.peekItemAt(5));
    }
}