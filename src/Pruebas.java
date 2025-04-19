import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Pruebas {
    private ArrayList<Float> numeros;

    public Pruebas(ArrayList<Float> numeros){
        this.numeros = numeros;
    }

    public void mostrarMenu(Scanner teclado) {
        int opcion;

        do {
            System.out.println("\n--- PRUEBAS ESTADISTICAS ---");
            System.out.println("1. Prueba del Promedio");
            System.out.println("2. Prueba de la Frecuencia");
            System.out.println("3. Prueba de la Serie");
            System.out.println("4. Prueba de Kolmogorov-Smirnov");
            System.out.println("5. Prueba de corrida arriba y abajo de la media");
            System.out.println("0. Volver al menu principal");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    if(listaVacia()){
                        promedio(teclado);
                    }
                    break;
                case 2:
                    if(listaVacia()){
                        frecuencia(teclado);
                    }
                    break;
                case 3:
                    if(listaVacia()){
                        serie(teclado);
                    }
                    break;
                case 4:
                    if(listaVacia()){
                        kS(teclado);
                    }
                    break;
                case 5:
                    if(listaVacia()){
                        corrida(teclado);
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion invalida, intente nuevamente");
            }

        } while (opcion != 0);
    }

    private void promedio(Scanner teclado) {
        System.out.println("Ingrese estadistico (Z): ");
        float Z = teclado.nextFloat();
        float estadistico;
        float suma = 0;
        float promedio;
        for(int i=0;i<numeros.size();i++){
            suma = suma + numeros.get(i);
        }
        promedio = suma/numeros.size();
        System.out.println("X (media):"+promedio);
        float prueba1 = (float) ((promedio - 0.5)*Math.sqrt(numeros.size()));
        estadistico = (float) (prueba1/0.288);
        System.out.println("Z0: "+estadistico);
        if(Math.abs(estadistico)<Z){
            System.out.println("No se rechaza la hipotesis de que los numeros provienen de un universo uniformemente distribuido.");
        }
        else{
            System.out.println("Se rechaza la hipotesis de que los numeros provienen de un universo uniformemente distribuido.");
        }

    }

    private void frecuencia(Scanner teclado) {
        System.out.println("Ingrese numero de subintervalos (x): ");
        int x = teclado.nextInt();
        System.out.println("Ingrese numero de estadistico (x2): ");
        float x2 = teclado.nextFloat();

        int n = numeros.size();
        float Fe = (float) (n/x);

        int[] frecuencias = new int[x];

        for(int i=0;i<n;i++){
            int intervalo = (int) (numeros.get(i)*x);
            if(intervalo==x)intervalo=x-1;
            frecuencias[intervalo]++;
        }
        float chiCuadrado= 0;
        System.out.println("\nSubintervalo | Frecuencia Observada |");
        System.out.println("---------------------------------------");

        for(int i=0;i<x;i++){
            float termino = (float) (Math.pow(frecuencias[i]-Fe,2));
            chiCuadrado+=termino;
            System.out.printf("   %d     |     %d     |\n",
                    i,frecuencias[i]);
        }
        chiCuadrado=((float)x/n)*chiCuadrado;
        System.out.println("---------------------------------------");
        System.out.println("Frecuencia esperada: "+Fe);
        System.out.println("Chi Cuadrado: "+chiCuadrado);

        if(chiCuadrado<x2){
            System.out.println("No se rechaza la hipotesis de que los numeros provienen de un universo uniformemente distribuido.");
        }
        else{
            System.out.println("Se rechaza la hipotesis de que los numeros provienen de un universo uniformemente distribuido.");
        }
    }

    private void serie(Scanner teclado) {
        System.out.println("Ingrese numero de subintervalos por eje (x): ");
        int x = teclado.nextInt();
        System.out.println("Ingrese estadistico (X2): ");
        float x2 = teclado.nextFloat();

        int n = numeros.size()/2 ;

        if (n < 2) {
            System.out.println("Error: Se necesitan al menos 2 numeros para la prueba de series");
            return;
        }

        int[][] frecuencias = new int[x][x];

        for (int i = 0; i < n*2; i++) {
            int intervaloX = (int) (numeros.get(i) * x);
            int intervaloY = (int) (numeros.get(i + 1) * x);

            if (intervaloX == x) intervaloX = x - 1;
            if (intervaloY == x) intervaloY = x - 1;

            frecuencias[intervaloX][intervaloY]++;
            i++;
        }

        float Fe = (float) n / (x * x);

        float chiCuadrado = 0;

        System.out.println("\nMatriz de frecuencias observadas:");
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                System.out.printf("%d ", frecuencias[i][j]);
                float termino = (float) (Math.pow(frecuencias[i][j] - Fe, 2));
                chiCuadrado += termino;
            }
            System.out.println();
        }
        chiCuadrado=((float)x*x/n)*chiCuadrado;
        System.out.println("\nFrecuencia esperada por celda (Fe): " + Fe);
        System.out.println("Estadistico chi-cuadrado calculado: " + chiCuadrado);

        if (chiCuadrado < x2) {
            System.out.println("No se rechaza la hipotesis de independencia entre los numeros.");
        } else {
            System.out.println("Se rechaza la hipotesis de independencia entre los numeros.");
        }
    }

    private void kS(Scanner teclado){
        System.out.println("Ingrese estadistico (da): ");
        float da = teclado.nextFloat();

        int n = numeros.size();

        numeros.sort(null);
        ArrayList<Float> distribucionAcumulada = new ArrayList<>();
        for(int i=1;i<=n;i++){
            float valor = (float) i / n;
            valor = (float) (Math.floor(valor * 1000) / 1000);
            distribucionAcumulada.add(valor);
        }

        ArrayList<Float> dnList = new ArrayList<>();
        for(int i=0;i<n;i++){
            float diferencia = distribucionAcumulada.get(i) - numeros.get(i);
            diferencia = (float) (Math.floor(diferencia * 1000) / 1000);
            dnList.add(diferencia);
        }

        float dn = Collections.max(dnList);
        dn = (float) (Math.floor(dn * 1000) / 1000);
        System.out.println("Dn: "+dn);

        if(dn<da){
            System.out.println("No se rechaza la hipotesis de que los numeros provienen de un universo uniformemente distribuido.");
        }
        else{
            System.out.println("Se rechaza la hipotesis de que los numeros provienen de un universo uniformemente distribuido.");
        }
    }

    private void corrida(Scanner teclado) {
        System.out.println("Ingrese estadistico (Za): ");
        float za = teclado.nextFloat();

        int n = numeros.size();
        ArrayList<Integer> binario = new ArrayList<>();
        ArrayList<Float> Fe = new ArrayList<>();
        ArrayList<Integer> Fo = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (numeros.get(i) <= 0.5) {
                binario.add(0);
            } else {
                binario.add(1);
            }
        }

        System.out.println("Secuencia binaria:");
        for (int i = 0; i < n; i++) {
            System.out.print(binario.get(i) + " ");
        }
        System.out.println();

        int longitudMaxima = 1;
        int longitudActual = 1;
        int valorActual= binario.get(0);


        for (int i = 1; i < n; i++) {
            if (binario.get(i).equals(valorActual)) {
                longitudActual++;
            } else {

                if (longitudActual > longitudMaxima) {
                    longitudMaxima = longitudActual;
                }

                valorActual = binario.get(i);
                longitudActual = 1;
            }
        }

        if (longitudActual > longitudMaxima) {
            longitudMaxima = longitudActual;
        }

        for (int i = 0; i < longitudMaxima; i++) {
            Fo.add(0);

            float fei = (float) ((n-(i+1)+3)/Math.pow(2,(i+1)+1));
            fei=(float) (Math.floor(fei*1000)/1000);
            Fe.add(fei);
        }

        longitudActual=1;
        valorActual= binario.get(0);

        for(int i=1;i<n;i++){
            if(binario.get(i).equals(valorActual)){
                longitudActual++;
            }
            else{
                Fo.set(longitudActual-1,Fo.get(longitudActual-1)+1);
                valorActual= binario.get(i);
                longitudActual=1;
            }
        }

        Fo.set(longitudActual-1,Fo.get(longitudActual-1)+1);


        System.out.println("Número de elementos (n): " + n);
        System.out.println("Frecuencias observadas (Fo):");
        for (int i = 0; i < Fo.size(); i++) {
            System.out.println("Secuencias de " + (i + 1) + " dígitos: " + Fo.get(i));
        }

        System.out.println("Frecuencias esperadas (Fe):");
        for (int i = 0; i < Fo.size(); i++) {
            if (i < Fe.size()) {
                System.out.println("Fe" + (i + 1) + " = " + Fe.get(i));
            }
        }

        float chiCuadrado = 0;
        for (int i = 0; i < Fo.size(); i++) {
            if (i < Fe.size() && i < Fo.size() && Fe.get(i) > 0) {
                float diferencia = Fo.get(i) - Fe.get(i);
                chiCuadrado += (diferencia * diferencia) / Fe.get(i);
            }
        }
        chiCuadrado=((float) (Math.floor(chiCuadrado*1000)/1000));
        System.out.println("Estadístico χ² calculado: " + chiCuadrado);
        System.out.println("Valor crítico χ²ɑ: " + za);

        if (chiCuadrado < za) {
            System.out.println("No se rechaza la hipótesis de que los números provienen de un universo uniformemente distribuido.");
        } else {
            System.out.println("Se rechaza la hipótesis de que los números provienen de un universo uniformemente distribuido.");
        }
    }

    private Boolean listaVacia(){
        if(numeros.isEmpty()){
            System.out.println("NO HAY NUMEROS A PROBAR");
            return false;
        }
        return true;
    }
}



