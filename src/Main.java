import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Float> numeros = new ArrayList<>();

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        int opcion;
        Generadores generadores = new Generadores(numeros);
        Pruebas pruebas = new Pruebas(numeros);
        System.out.println("Bienvenido al sistema FFFA");


        do {
            System.out.println("\n----- MENU PRINCIPAL -----");
            System.out.println("1. Ingresar valores");
            System.out.println("2. Generadores pseudoaleatorios");
            System.out.println("3. Pruebas estadisticas");
            System.out.println("4. Mostrar numeros actuales");
            System.out.println("0. Salir");

            opcion = teclado.nextInt();


            switch (opcion) {
                case 1:
                    ingresarValores(teclado);
                    break;
                case 2:
                    generadores.mostrarMenu(teclado);
                    break;
                case 3:
                    pruebas.mostrarMenu(teclado);
                    break;
                case 4:
                    mostrarNumeros();
                    break;
                case 0:
                    System.out.println("Hasta luego");
                    break;
                default:
                    System.out.println("Opcion invalida, intente nuevamente");
            }

        } while (opcion != 0);
        teclado.close();
    }

    private static void ingresarValores(Scanner teclado){
        System.out.println("\n--- INGRESAAR VALORES ---");
        System.out.print("Ingrese la cantidad de numeros a leer: ");
        int cantidad = teclado.nextInt();
        for(int i=0;i<cantidad;i++){
            System.out.print("Ingrese el valor "+(i+1)+": ");
            numeros.add(teclado.nextFloat());
        }
        System.out.println("Se han ingresado "+cantidad+" valores");
    }

    private static void mostrarNumeros(){
        if(numeros.isEmpty()){
            System.out.println("No hay numeros a mostrar");
            return;
        }
        System.out.println("\n--- NUMEROS ACTUALES ---");
        for(int i=0;i<numeros.size();i++){
            System.out.println("Valor "+(i)+": "+numeros.get(i));
        }
    }

}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.





