package miPrincipal;
import java.util.LinkedList;

class Pila<T> {
    private Lista<T> lista;

    public Pila() {
        lista = new Lista<>();
    }

    public void apilar(T elemento) {
        lista.agregarInicio(elemento);
    }

    public T desapilar() {
        return lista.eliminarInicio();
    }

    public T cima() {
        return lista.obtenerInicio();
    }

    public boolean estaVacia() {
        return lista.estaVacia();
    }
}

public class EvaluacionExpresiones {
    private static boolean esNumero(char c) {
        return Character.isDigit(c);
    }

    private static int prioridadOperador(char operador) {
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

    public static String convertirInfijaAPostfija(String expresion) {
        Pila<Character> pila = new Pila<>();
        StringBuilder resultado = new StringBuilder();

        for (char c : expresion.toCharArray()) {
            if (esNumero(c)) {
                resultado.append(c);
            } else if (c == '(') {
                pila.apilar(c);
            } else if (c == ')') {
                while (!pila.estaVacia() && pila.cima() != '(') {
                    resultado.append(pila.desapilar());
                }
                pila.desapilar(); // Eliminar '('
            } else {
                while (!pila.estaVacia() && prioridadOperador(c) <= prioridadOperador(pila.cima())) {
                    resultado.append(pila.desapilar());
                }
                pila.apilar(c);
            }
        }

        while (!pila.estaVacia()) {
            resultado.append(pila.desapilar());
        }

        return resultado.toString();
    }

    public static int evaluarExpresionPostfija(String expresion) {
        Pila<Integer> pila = new Pila<>();

        for (char c : expresion.toCharArray()) {
            if (esNumero(c)) {
                pila.apilar(c - '0');
            } else {
                int operando2 = pila.desapilar();
                int operando1 = pila.desapilar();

                switch (c) {
                    case '+':
                        pila.apilar(operando1 + operando2);
                        break;
                    case '-':
                        pila.apilar(operando1 - operando2);
                        break;
                    case '*':
                        pila.apilar(operando1 * operando2);
                        break;
                    case '/':
                        pila.apilar(operando1 / operando2);
                        break;
                    default:
                        throw new IllegalArgumentException("Operador no válido: " + c);
                }
            }
        }

        return pila.desapilar();
    }

    public static void main(String[] args) {
        String expresionInfija = "(5+3)*(8-2)";
        String expresionPostfija = convertirInfijaAPostfija(expresionInfija);
        System.out.println("Expresión postfija: " + expresionPostfija);

        int resultado = evaluarExpresionPostfija(expresionPostfija);
        System.out.println("Resultado de la evaluación: " + resultado);
    }
}
