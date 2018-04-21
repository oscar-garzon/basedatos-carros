package mx.unam.ciencias.icc;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Proyecto 1: base de datos para carros.
 */
public class Proyecto1 {

    /* Hace búsquedas por marca y año en la base de datos. */
    private static void busquedas(BaseDeDatosCarro bdd) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        System.out.printf("Entra una marca para buscar: ");
        String marca = sc.next();

        Lista<Carro> r = bdd.buscaRegistros(CampoCarro.MARCA, marca);
        if (r.esVacia()) {
            System.out.printf("\nNo se hallaron carros " +
                              "con la marca \"%s\".\n",
                              marca);
        } else {
            System.out.printf("\nSe hallaron los siguientes " +
                              "carros con la marca \"%s\":\n\n",
                              marca);
            for (Carro c : r)
                System.out.println(c + "\n");
        }

        System.out.printf("Entra un año para buscar: ");
        int año = 0;
        try {
            año = sc.nextInt();
        } catch (InputMismatchException ime) {
            System.out.printf("Se entró un año inválido. " +
                              "Se interpretará como cero.\n");
        }

        r = bdd.buscaRegistros(CampoCarro.AÑO, String.valueOf(año));
        if (r.esVacia()) {
            System.out.printf("\nNo se hallaron carros " +
                              "de ese año. \"%d\".\n",
                              año);
        } else {
            System.out.printf("\nSe hallaron los siguientes " +
                              "carros del año \"%d\":\n\n",
                              año);
            for (Carro c : r)
                System.out.println(c + "\n");
        }
    }

    /* Crea una base de datos y la llena a partir de los datos que el usuario
       escriba a través del teclado. Después la guarda en disco duro y la
       regresa. */
    private static BaseDeDatosEstudiantes escritura(String nombreArchivo) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        File archivo = new File(nombreArchivo);

        if (archivo.exists()) {
            System.out.printf("El archivo \"%s\" ya existe.\n" +
                              "Presiona Ctrl-C si no quieres reescribirlo, " +
                              "o Enter para continuar...\n", nombreArchivo);
            sc.nextLine();
        }

        System.out.println("Entra carros a la base de datos.\n" +
                           "Cuando desees terminar, deja el nombre en blanco.\n");

        BaseDeDatosCarro bdd = new BaseDeDatosCarro();

        do {
            String color;
            String marca;
            int    año = 0;
            double cilindraje = 0.0;
            int    caballosDeFuerza = 0;
            int torque = 0;

            System.out.printf("Color   : ");
            color = sc.next();
            System.out.printf("Marca   : ");
            marca = sc.next();
            if (marca.equals(""))
                break;
            try {
                System.out.printf("Año   : ");
                año = sc.nextInt();
                System.out.printf("Cilindraje : ");
                cilindraje = sc.nextDouble();
                System.out.printf("Caballos de fuerza     : ");
                caballosDeFuerza = sc.nextInt();
                System.out.printf("Torque   :")
                torque = sc.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("\nNúmero inválido: se descartará " +
                                   "este carro.\n");
                continue;
            }
            Carro c = new Carro(color,
                                marca,
                                año,
                                cilindraje,
                                caballosDeFuerza,
                                torque);
            bdd.agregaRegistro(c);
            System.out.println();
        } while (true);

        int n = bdd.getNumRegistros();
        if (n == 1)
            System.out.printf("\nSe agregó 1 carro.\n");
        else
            System.out.printf("\nSe agregaron %d carros.\n", n);

        try {
            FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
            OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
            BufferedWriter out = new BufferedWriter(osOut);
            bdd.guarda(out);
            out.close();
        } catch (IOException ioe) {
            System.out.printf("No pude guardar en el archivo \"%s\".\n",
                              nombreArchivo);
            System.exit(1);
        }

        System.out.printf("\nBase de datos guardada exitosamente en \"%s\".\n",
                          nombreArchivo);

        return bdd;
    }

    /* Crea una base de datos y la llena cargándola del disco duro. Después la
       regresa. */
    private static BaseDeDatosCarro lectura(String nombreArchivo) {
        BaseDeDatosCarro bdd = new BaseDeDatosCarro();

        try {
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            InputStreamReader isIn = new InputStreamReader(fileIn);
            BufferedReader in = new BufferedReader(isIn);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            System.out.printf("No pude cargar del archivo \"%s\".\n",
                              nombreArchivo);
            System.exit(1);
        }

        System.out.printf("Base de datos cargada exitosamente de \"%s\".\n\n",
                          nombreArchivo);

        Lista<Carro> r = bdd.getRegistros();
        for (Carro c : r)
            System.out.println(c + "\n");

        return bdd;
    }

    /* Imprime en pantalla cómo debe usarse el programa y lo termina. */
    private static void uso() {
        System.out.println("Uso: java -jar practica7.jar [-g|-c] <archivo>");
        System.exit(1);
    }

    public static void main(String[] args) {
        if (args.length != 2)
            uso();

        String bandera = args[0];
        String nombreArchivo = args[1];

        if (!bandera.equals("-g") && !bandera.equals("-c"))
            uso();

        BaseDeDatosCarro bdd;

        if (bandera.equals("-g"))
            bdd = escritura(nombreArchivo);
        else
            bdd = lectura(nombreArchivo);

        busquedas(bdd);
    }
}
