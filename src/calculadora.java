import java.util.Scanner;

public class calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicación");
            System.out.println("4. División");
            System.out.println("5. Potencia");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = obtenerEntero(scanner);

            if (opcion == 6) {
                System.out.println("Saliendo del programa.");
                break;
            }

            Operacion operacion;

            switch (opcion) {
                case 1:
                    operacion = new Suma();
                    break;
                case 2:
                    operacion = new Resta();
                    break;
                case 3:
                    operacion = new Multiplicacion();
                    break;
                case 4:
                    operacion = new Division();
                    break;
                case 5:
                    operacion = new Potencia();
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    continue;
            }

            double resultado = realizarOperacion(scanner, operacion);
            System.out.println("Resultado: " + resultado);
        }

        scanner.close();
    }

    private static int obtenerEntero(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Error: Introduce un número entero válido.");
                scanner.next(); // Consumir la entrada no válida
            }
        }
    }

    public static double obtenerDoble(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Error: Introduce un número decimal válido.");
                scanner.next(); // Consumir la entrada no válida
            }
        }
    }

    private static double realizarOperacion(Scanner scanner, Operacion operacion) {
        double resultado = 0;

        double[] operandos = operacion.obtenerOperandos(scanner);

        if (operandos != null) {
            resultado = operacion.calcular(operandos);
        }

        return resultado;
    }
}

interface Operacion {
    double[] obtenerOperandos(Scanner scanner);
    double calcular(double[] operandos);
}

class Suma implements Operacion {
    @Override
    public double[] obtenerOperandos(Scanner scanner) {
        double[] operandos = new double[2];
        operandos[0] = calculadora.obtenerDoble(scanner, "Ingrese el primer número: ");
        operandos[1] = calculadora.obtenerDoble(scanner, "Ingrese el segundo número: ");
        return operandos;
    }

    @Override
    public double calcular(double[] operandos) {
        return operandos[0] + operandos[1];
    }
}

class Resta implements Operacion {
    @Override
    public double[] obtenerOperandos(Scanner scanner) {
        double[] operandos = new double[2];
        operandos[0] = calculadora.obtenerDoble(scanner, "Ingrese el primer número: ");
        operandos[1] = calculadora.obtenerDoble(scanner, "Ingrese el segundo número: ");
        return operandos;
    }

    @Override
    public double calcular(double[] operandos) {
        return operandos[0] - operandos[1];
    }
}

class Multiplicacion implements Operacion {
    @Override
    public double[] obtenerOperandos(Scanner scanner) {
        double[] operandos = new double[2];
        operandos[0] = calculadora.obtenerDoble(scanner, "Ingrese el primer número: ");
        operandos[1] = calculadora.obtenerDoble(scanner, "Ingrese el segundo número: ");
        return operandos;
    }

    @Override
    public double calcular(double[] operandos) {
        return operandos[0] * operandos[1];
    }
}

class Division implements Operacion {
    @Override
    public double[] obtenerOperandos(Scanner scanner) {
        double[] operandos = new double[2];
        operandos[0] = calculadora.obtenerDoble(scanner, "Ingrese el dividendo: ");
        operandos[1] = calculadora.obtenerDoble(scanner, "Ingrese el divisor (distinto de 0): ");
        while (operandos[1] == 0) {
            System.out.println("Error: El divisor no puede ser 0.");
            operandos[1] = calculadora.obtenerDoble(scanner, "Ingrese el divisor (distinto de 0): ");
        }
        return operandos;
    }

    @Override
    public double calcular(double[] operandos) {
        return operandos[0] / operandos[1];
    }
}

class Potencia implements Operacion {
    @Override
    public double[] obtenerOperandos(Scanner scanner) {
        double[] operandos = new double[2];
        operandos[0] = calculadora.obtenerDoble(scanner, "Ingrese la base: ");
        operandos[1] = calculadora.obtenerDoble(scanner, "Ingrese el exponente: ");
        return operandos;
    }

    @Override
    public double calcular(double[] operandos) {
        return Math.pow(operandos[0], operandos[1]);
    }
}