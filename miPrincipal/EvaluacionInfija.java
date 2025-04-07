package miPrincipal;

import java.util.Stack;

public class EvaluacionInfija {

    public static int evaluar(String expresion) {
        Stack<Integer> valores = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            // Ignorar espacios
            if (c == ' ') {
                continue;
            }

            // Si es un número, leerlo completo
            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < expresion.length() && Character.isDigit(expresion.charAt(i))) {
                    sb.append(expresion.charAt(i++));
                }
                valores.push(Integer.parseInt(sb.toString()));
                i--; // Retroceder el índice
            } else if (c == '(') {
                operadores.push(c);
            } else if (c == ')') {
                while (operadores.peek() != '(') {
                    valores.push(aplicarOperacion(operadores.pop(), valores.pop(), valores.pop()));
                }
                operadores.pop(); // Eliminar '('
            } else if (esOperador(c)) {
                while (!operadores.isEmpty() && precedencia(c) <= precedencia(operadores.peek())) {
                    valores.push(aplicarOperacion(operadores.pop(), valores.pop(), valores.pop()));
                }
                operadores.push(c);
            }
        }

        // Aplicar las operaciones restantes
        while (!operadores.isEmpty()) {
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
        }
        return -1;
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
                    throw new ArithmeticException("División por cero");
                }
                return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una expresión infija:");
        String expresion = scanner.nextLine();
        try {
            int resultado = evaluar(expresion);
            System.out.println("Resultado: " + resultado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        scanner.close();
    }
}