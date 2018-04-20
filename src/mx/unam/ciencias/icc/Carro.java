package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase para representar carros. Un carro tiene color, marca, año,
 * cilindraje, caballos de fuerza y torque . La clase implementa {@link
 * Registro}, por lo que puede cargarse y guardarse utilizando objetos
 * de las clases {@link BufferedReader} y {@link BufferedWriter} como
 *entrada y salida respectivamente.
 */
public class Carro implements Registro {

    /* Color del carro. */
    private String color;
    /* Marca del carro. */
    private String marca;
    /* Año del carro. */
    private int año;
    /* Cilindraje del carro.*/
    private double cilindraje;
    /*Caballos de fuerza del carro*/
    private int caballosDeFuerza;
    /*Torque del carro*/
    private int torque;

    /**
     * Construye un estudiante con todas sus propiedades.
     * @param nombre el nombre del estudiante.
     * @param cuenta el número de cuenta del estudiante.
     * @param promedio el promedio del estudiante.
     * @param edad la edad del estudiante.
     */
    public Estudiante(String nombre,
                      int    cuenta,
                      double promedio,
                      int    edad) {
        this.nombre = nombre;
        this.cuenta = cuenta;
        this.promedio = promedio;
        this.edad = edad;
    }

    /**
     * Regresa el nombre del estudiante.
     * @return el nombre del estudiante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el nombre del estudiante.
     * @param nombre el nuevo nombre del estudiante.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Regresa el número de cuenta del estudiante.
     * @return el número de cuenta del estudiante.
     */
    public int getCuenta() {
        return cuenta;
    }

    /**
     * Define el número cuenta del estudiante.
     * @param cuenta el nuevo número de cuenta del estudiante.
     */
    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Regresa el promedio del estudiante.
     * @return el promedio del estudiante.
     */
    public double getPromedio() {
        return promedio;
    }

    /**
     * Define el promedio del estudiante.
     * @param promedio el nuevo promedio del estudiante.
     */
    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    /**
     * Regresa la edad del estudiante.
     * @return la edad del estudiante.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Define la edad del estudiante.
     * @param edad la nueva edad del estudiante.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Nos dice si el objeto recibido es un estudiante igual al que manda llamar
     * el método.
     * @param o el objeto con el que el estudiante se comparará.
     * @return <tt>true</tt> si el objeto o es un estudiante con las mismas
     *         propiedades que el objeto que manda llamar al método,
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
        if (!(o instanceof Estudiante))
            return false;
        Estudiante e = (Estudiante)o;
        if (this.nombre.equals(e.getNombre()) &&
            this.edad == e.getEdad()     &&
            this.cuenta == e.getCuenta() &&
            this.promedio == e.getPromedio())
                return true;
        return false;
    }

    /**
     * Regresa una representación en cadena del estudiante.
     * @return una representación en cadena del estudiante.
     */
    @Override public String toString() {
      return   String.format("Nombre   : %s\n" +
                             "Cuenta   : %d\n" +
                             "Promedio : %2.2f\n" +
                             "Edad     : %d",
                             nombre, cuenta, promedio, edad);
    }

    /**
     * Guarda al estudiante en la salida recibida.
     * @param out la salida dónde hay que guardar al estudiante.
     * @throws IOException si un error de entrada/salida ocurre.
     */
    @Override public void guarda(BufferedWriter out) throws IOException {
        out.write(String.format("%s\t%d\t%2.2f\t%d\n",
                                nombre, cuenta, promedio, edad));
    }

    /**
     * Carga al estudiante de la entrada recibida.
     * @param in la entrada de dónde hay que cargar al estudiante.
     * @return <tt>true</tt> si el método carga un estudiante válido,
     *         <tt>false</tt> en otro caso.
     * @throws IOException si un error de entrada/salida ocurre, o si la entrada
     *         recibida no contiene a un estudiante.
     */
    @Override public boolean carga(BufferedReader in) throws IOException {
        String linea = in.readLine();
        if (linea == null)
            return false;
        linea.trim();
        if (linea.equals(""))
            return false;
        String camposEstudiante[] = linea.split("\t");
        if (camposEstudiante.length != 4)
            throw new IOException("El archivo es inválido");
        this.nombre = camposEstudiante[0];
        try {
          this.cuenta = Integer.parseInt(camposEstudiante[1]);
          this.promedio = Double.parseDouble(camposEstudiante[2]);
          this.edad = Integer.parseInt(camposEstudiante[3]);
        }catch (NumberFormatException nfe) {
            throw new IOException("Registro inválido");
        }
        return true;
    }
}
