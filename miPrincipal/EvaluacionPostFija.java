import java.util.Stack;

public class EvaluacionPostFija {
    private static boolean esNumero(char c) {
        return c >= '0' && c <= '9';
    }

    public static int evaluarExpresionPostfija(String expresion) {
        Stack<Integer> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            // Si el carácter es un número, lo apilamos
            if (Character.isDigit(c)) {
                pila.push(c - '0'); // Convertimos el carácter a número
            } else {
                // Si es un operador, sacamos dos elementos de la pila
                int operando2 = pila.pop();
                int operando1 = pila.pop();

                // Realizamos la operación correspondiente
                switch (c) {
                    case '+':
                        pila.push(operando1 + operando2);
                        break;
                    case '-':
                        pila.push(operando1 - operando2);
                        break;
                    case '*':
                        pila.push(operando1 * operando2);
                        break;
                    case '/':
                        pila.push(operando1 / operando2);
                        break;
                    default:
                        throw new IllegalArgumentException("Operador no válido: " + c);
                }
            }
        }

        // El resultado final estará en la cima de la pila
        return pila.pop();
    }

    public static void main(String[] args) {
        String expresion = "53+82-*"; // Ejemplo: (5 + 3) * (8 - 2)
        int resultado = evaluarExpresionPostfija(expresion);
        System.out.println("El resultado de la expresión postfija es: " + resultado);
    }
}
