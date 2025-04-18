import java.util.ArrayList;
import java.util.Scanner;

public class Generadores {
    private ArrayList<Float> numeros;

    public Generadores(ArrayList<Float> numeros) {
        this.numeros = numeros;
    }

    public void mostrarMenu(Scanner teclado) {
        int opcion;

        do {
            System.out.println("\n--- GENERADORES PSEUDOALEATORIOS ---");
            System.out.println("1. Metodo de la Parte Central");
            System.out.println("2. Metodo de Lehmer");
            System.out.println("3. Metodo Congruencial Aditivo");
            System.out.println("4. Metodo Congruencial Multiplicativo");
            System.out.println("5. Metodo Congruencial Mixto");
            System.out.println("0. Volver al menu principal");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    parteCentral(teclado);
                    break;
                case 2:
                    lehmer(teclado);
                    break;
                case 3:
                    congruencialAditivo(teclado);
                    break;
                case 4:
                    congruencialMultiplicativo(teclado);
                    break;
                case 5:
                    congruencialMixto(teclado);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion invalida, intente nuevamente");
            }

        } while (opcion != 0);
    }

    private void parteCentral(Scanner teclado) {
        System.out.println("Ingrese semilla (M): ");
        int M = teclado.nextInt();
        System.out.println("Ingrese cantidad de digitos de parte central (N): ");
        int N = teclado.nextInt();
        System.out.println("Ingrese cantidad numeros a generar: ");
        int cantidad = teclado.nextInt();

        int Resultado;
        float numero;
        int divisor = 1;
        for (int j = 0; j < N; j++) {
            divisor = divisor * 10;
        }
        for (int i = 0; i < cantidad; i++) {
            Integer X = M * M;
            int Longitud = X.toString().length();
            if ((Longitud - N) % 2 == 0) {
                int Partida = (Longitud - N) / 2;
                Resultado = Integer.parseInt(X.toString().substring(Partida, Partida + N));
            } else {
                X = X * 10;
                Longitud = X.toString().length();
                int Partida = (Longitud - N) / 2;
                Resultado = Integer.parseInt(X.toString().substring(Partida, Partida + N));

            }
            M = Resultado;

            numero = (float) Resultado / divisor;
            numeros.add(numero);

            System.out.println("Valor de X: " + X);
            System.out.println("Valor de M: " + M);
            System.out.println("Valor generado: " + numero);
            System.out.println("\n");

        }
    }

    private void lehmer(Scanner teclado) {
        System.out.println("Ingrese semilla (N): ");
        int N = teclado.nextInt();
        System.out.println("Ingrese multiplicador (t): ");
        int t = teclado.nextInt();
        System.out.println("Ingrese cantidad numeros a generar: ");
        int cantidad = teclado.nextInt();

        float numero;
        int divisor = 1;


        for (int i = 0; i < cantidad; i++) {
            Integer preNum = N * t;
            int Longitud = preNum.toString().length();
            int Resultado = Integer.parseInt(preNum.toString().substring(2, Longitud)) - Integer.parseInt(preNum.toString().substring(0, 2));
            N = Resultado;
            Integer predivisor = N;
            for (int j = 0; j < predivisor.toString().length(); j++) {
                divisor = divisor * 10;
            }
            numero = (float) Resultado / divisor;
            numeros.add(numero);

            System.out.println("Valor de N"+(i+1)+": " + N);
            System.out.println("Valor generado: " + numero);
            System.out.println("\n");
            divisor = 1;
        }
    }

    private void congruencialAditivo(Scanner teclado) {
        ArrayList<Integer> numerosInternos = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            System.out.println("Ingrese semillas (n) ("+(i)+" de 3): ");
            int n = teclado.nextInt();
            numerosInternos.add(n);
        }
        System.out.println("Ingrese modulo (m): ");
        int m = teclado.nextInt();
        System.out.println("Ingrese cantidad numeros a generar: ");
        int cantidad = teclado.nextInt();

        for (int i = 0; i < cantidad; i++) {
            int resultado = (numerosInternos.get(i) + numerosInternos.get(i + 3)) % m;
            numerosInternos.add(resultado);
            float mostrar = (float) resultado / m;
            float truncado = (float) ((int) (mostrar * 1000)) / 1000;

            System.out.println("Valor de N"+(i+4)+": " + resultado);
            System.out.println("Valor generado: " + truncado);
            System.out.println("\n");
            numeros.add(truncado);

        }
    }

    private void congruencialMultiplicativo(Scanner teclado) {

        System.out.println("Ingrese multiplicativo (a): ");
        int a = teclado.nextInt();
        System.out.println("Ingrese semilla (n): ");
        int n = teclado.nextInt();
        System.out.println("Ingrese modulo (m): ");
        int m = teclado.nextInt();
        System.out.println("Ingrese cantidad numeros a generar: ");
        int cantidad = teclado.nextInt();

        for(int i=0;i<cantidad;i++){
            int resultado = (a*n)%m;
            n=resultado;
            float mostrar = (float)resultado/m ;
            float truncado = (float) ((int) (mostrar * 1000)) / 1000;
            System.out.println("Valor de N"+(i+1)+": " + resultado);
            System.out.println("Valor generado: " + truncado);
            System.out.println("\n");
            numeros.add(truncado);

        }
    }

    private void congruencialMixto(Scanner teclado) {
        System.out.println("Ingrese multiplicativo (a): ");
        int a = teclado.nextInt();
        System.out.println("Ingrese semilla (n): ");
        int n = teclado.nextInt();
        System.out.println("Ingrese aditivo (c): ");
        int c = teclado.nextInt();
        System.out.println("Ingrese modulo (m): ");
        int m = teclado.nextInt();
        System.out.println("Ingrese cantidad numeros a generar: ");
        int cantidad = teclado.nextInt();

            for(int i=0;i<cantidad;i++){
            int resultado = ((a*n)+c)%m;
            n=resultado;
            float mostrar = (float)resultado/m ;
            float truncado = (float) ((int) (mostrar * 1000)) / 1000;
            System.out.println("Valor de N"+(i+1)+": " + resultado);
            System.out.println("Valor generado: " + truncado);
            System.out.println("\n");
            numeros.add(truncado);
        }

    }
}



