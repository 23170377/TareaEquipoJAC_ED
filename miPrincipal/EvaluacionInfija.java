package miPrincipal;
import java.util.Stack;
public class EvaluacionInfija {

    public static int evaluarExpresionInfija(String expresion) throws PosicionIlegalException {
        Stack<Integer> valores = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (Character.isWhitespace(c)) {
                continue;
            }

            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < expresion.length() && Character.isDigit(expresion.charAt(i))) {
                    sb.append(expresion.charAt(i++));
                }
                i--;
                valores.push(Integer.parseInt(sb.toString()));
            } else if (c == '(') {
                operadores.push(c);
            } else if (c == ')') {
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    valores.push(aplicarOperacion(operadores.pop(), valores.pop(), valores.pop()));
                }
                if (operadores.isEmpty() || operadores.pop() != '(') {
                    throw new PosicionIlegalException("Paréntesis desbalanceados en la posición: " + i);
                }
            } else if (esOperador(c)) {
                while (!operadores.isEmpty() && precedencia(c) <= precedencia(operadores.peek())) {
                    valores.push(aplicarOperacion(operadores.pop(), valores.pop(), valores.pop()));
                }
                operadores.push(c);
            } else {
                throw new PosicionIlegalException("Carácter ilegal en la posición: " + i);
            }
        }

        while (!operadores.isEmpty()) {
            if (operadores.peek() == '(') {
                throw new PosicionIlegalException("Paréntesis desbalanceados en la expresión.");
            }
            valores.push(aplicarOperacion(operadores.pop(), valores.pop(), valores.pop()));
        }

        return valores.pop();
    }

    private static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private static int aplicarOperacion(char operador, int b, int a) {
        switch (operador) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("División por cero.");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Operador desconocido: " + operador);
        }
    }

    public static class PosicionIlegalException extends Exception {
        public PosicionIlegalException(String mensaje) {
            super(mensaje);
        }
    }

    public static void main(String[] args) {
        try {
            String expresion = "3 + (2 * 4) - 5";
            int resultado = evaluarExpresionInfija(expresion);
            System.out.println("Resultado: " + resultado);
        } catch (PosicionIlegalException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}