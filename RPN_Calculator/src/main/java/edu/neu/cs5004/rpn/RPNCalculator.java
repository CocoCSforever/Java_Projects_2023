package edu.neu.cs5004.rpn;

import edu.neu.cs5004.rpn.exceptions.IllegalOperationException;
import edu.neu.cs5004.rpn.exceptions.NotEnoughArgumentsException;

import java.util.List;
import java.util.Stack;

public class RPNCalculator {

    private static final List<String> operators = List.of("+", "-","*", "/", "%", "sin", "cos", "tan", "abs", "==", "&&", "||", "!");
    private static final List<String> literals = List.of("True", "False", "true", "false");
    private Stack<Double> stack = new Stack<>();

    public void processInput(String input) throws NotEnoughArgumentsException, IllegalOperationException {
        int index = literals.indexOf(input);
        // if input is a literal, push it to the stack
        if(index != -1){
            stack.push(index%2==0? 1.0: 0.0);
            return;
        }

        // check whether input is valid-an operator, call helper function
        index = operators.indexOf(input);
        if(index == -1){
            try{
                stack.push(Double.valueOf(input));
            }catch (NumberFormatException e){
                throw new IllegalOperationException();
            }
            return;
        }

        // if input is valid, check stack if not empty
        if(stack.size() == 0) throw new NotEnoughArgumentsException();
        // if stack.size == 1, only accept some operations, will throw exception for others
        if(stack.size() == 1){
            switch (index){
                case 1:
                    negate();
                    return;
                case 5:
                    sin();
                    return;
                case 6:
                    cos();
                    return;
                case 7:
                    tan();
                    return;
                case 8:
                    abs();
                    return;
                case 12:
                    boolNot();
                    return;
                default:
                    throw new NotEnoughArgumentsException();
            }
        }
        switch (index){
            case 0:
                plus();
                return;
            case 1:
                minus();
                return;
            case 2:
                multi();
                return;
            case 3:
                divide();
                return;
            case 4:
                mod();
                return;
            case 5:
                sin();
                return;
            case 6:
                cos();
                return;
            case 7:
                tan();
                return;
            case 8:
                abs();
                return;
            case 9:
                boolEquals();
                return;
            case 10:
                boolOperation(true);
                return;
            case 11:
                boolOperation(false);
                return;
            case 12:
                boolNot();
        }
    }

    private void plus(){
        double a = stack.pop();
        double b = stack.pop();
        stack.push(a+b);
    }

    private void minus(){
        double a = stack.pop();
        double b = stack.pop();
        stack.push(b-a);
    }

    private void negate(){
        double a = stack.pop();
        stack.push(-a);
    }

    private void multi(){
        double a = stack.pop();
        double b = stack.pop();
        stack.push(a*b);
    }

    private void divide(){
        double a = stack.pop();
        double b = stack.pop();
        stack.push(a/b);
    }

    private void mod(){
        double a = stack.pop();
        double b = stack.pop();
        stack.push(a%b);
    }


    private void sin(){
        double i = stack.pop();
        stack.push(Math.sin(i));
    }

    private void cos(){
        double i = stack.pop();
        stack.push(Math.cos(i));
    }

    private void tan(){
        double i = stack.pop();
        stack.push(Math.tan(i));
    }

    private void abs(){
        double i = stack.pop();
        stack.push(Math.abs(i));
    }

    private void boolEquals(){
        double i = stack.pop();
        if(i == stack.pop()){
            stack.push(1.0);
        }else{
            stack.push(0.0);
        }
        // ------ VERSION 2-------//
//        // compare object Double instead of comparing primitive values
//        if(stack.pop() == stack.pop()){
//            stack.push(1.0);
//        }else{
//            stack.push(0.0);
//        }
    }

    private void boolOperation(boolean x){
        double a = stack.pop();
        double b = stack.pop();
        // Throw an IllegalArgumentException if either of the arguments is not 0 or 1
        if(a != 0 && a != 1){ throw new IllegalArgumentException(); }
        if(b != 0 && b != 1){ throw new IllegalArgumentException(); }
        // TODO: call And/OR
        if(x){
            boolAnd(a, b);
        }else{
            boolOr(a, b);
        }
    }
    private void boolAnd(double a, double b){
        if(a == 1 && b == 1){
            stack.push(1.0);
        }else{
            stack.push(0.0);
        }
    }

    private void boolOr(double a, double b){
        if(a == 1 || b == 1){
            stack.push(1.0);
        }else{
            stack.push(0.0);
        }
    }

    private void boolNot(){
        double a = stack.pop();

        // Throw an IllegalArgumentException if the argument is not 0 or 1
        if(a != 0 && a != 1){ throw new IllegalArgumentException(); }

        if(a == 1){
            stack.push(0.0);
        }else{
            stack.push(1.0);
        }
    }

    public Double pop() {
        return stack.pop();
    }

    public Double peekItemAt(int index) {
        return stack.elementAt(index);
    }
}
