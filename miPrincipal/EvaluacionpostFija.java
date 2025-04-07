import java.util.Stack;

public class EvaluacionPostfija {

    public static int evaluarPostfija(String expresion) {
        return evaluarPostfijaRecursivaHelper(expresion.split(" "), new Stack<>());
    }

    private static int evaluarPostfijaRecursivaHelper(String[] tokens, Stack<Integer> pila) {
        if (tokens.length == 0) {
            return pila.pop();
        }

        String token = tokens[0];
        String[] tokensRestantes = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, tokensRestantes, 0, tokens.length - 1);

        if (esOperador(token)) {
            int operando2 = pila.pop();
            int operando1 = pila.pop();
            pila.push(aplicarOperacion(operando1, operando2, token));
        } else {
            pila.push(Integer.parseInt(token));
        }

        return evaluarPostfijaRecursiva(tokensRestantes, pila);
    }

    private static boolean esOperador(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private static int aplicarOperacion(int operando1, int operando2, String operador) {
        switch (operador) {
            case "+":
                return operando1 + operando2;
            case "-":
                return operando1 - operando2;
            case "*":
                return operando1 * operando2;
            case "/":
                if (operando2 == 0) {
                    throw new ArithmeticException("División por cero");
                }
                return operando1 / operando2;
            default:
                throw new IllegalArgumentException("Operador no válido: " + operador);
        }
    }

    public static void main(String[] args) {
        String expresion = "3 4 + 2 * 7 /"; // Ejemplo: (3 + 4) * 2 / 7
        System.out.println("Resultado: " + evaluarPostfija(expresion));
    }
}