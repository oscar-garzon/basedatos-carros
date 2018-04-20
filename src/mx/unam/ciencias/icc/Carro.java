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
    /* Cilindraje del carro. */
    private double cilindraje;
    /*Caballos de fuerza del carro. */
    private int caballosDeFuerza;
    /*Torque del carro. */
    private int torque;

    /**
     * Construye un carro con todas sus propiedades.
     * @param color el color del carro.
     * @param marca la marca del carro.
     * @param año el año del carro..
     * @param cilindraje el cilindraje del carro.
     * @param caballosDeFuerza los caballos de fuerza del carro.
     * @param torque el torque del carro.
     */
    public Estudiante(String color,
                      String marca,
                      int    año,
                      double cilindraje,
                      int    caballosDeFuerza
                      int torque) {
        this.color = color;
        this.marca = marca;
        this.año = año;
        this.cilindraje =  cilindraje;
        this.caballosDeFuerza = caballosDeFuerza;
        this.torque = torque;
    }

    /**
     * Regresa el color del carro.
     * @return el color del carro.
     */
    public String getColor() {
        return color;
    }

    /**
     * Define el color del carro.
     * @param color el nuevo color del carro.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Regresa la marca del carro.
     * @return la marca del carro.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define la marca del carro.
     * @param marca la nueva marca del carro.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Regresa el año del carro.
     * @return el año del carro.
     */
    public int getAño() {
        return año;
    }

    /**
     * Define el año del carro.
     * @param año el nuevo año del carro.
     */
    public void setAño(int año) {
        this.año = año;
    }

    /**
     * Regresa el cilindraje del carro.
     * @return el cilindraje del carro.
     */
    public double getCilindraje() {
        return cilindraje;
    }

    /**
     * Define el cilindraje del carro.
     * @param cilindraje el nuevo cilindraje del carro.
     */
    public void setCilindraje(double cilindraje) {
        this.cilindraje = cilindraje;
    }

    /**
     * Regresa los caballos de fuerza del carro.
     * @return los caballos de fuerza del carro.
     */
    public int getCaballosDeFuerza() {
        return caballosDeFuerza;
     }

    /**
     *  Define los caballos de fuerza del carro.
     * @param caballosDeFuerza los nuevos caballos de fuerza del carro.
     */
    public void setCaballosDeFuerza(int caballosDeFuerza) {
        this.caballosDeFuerza = caballosDeFuerza;
     }

    /**
     * Regresa el torque del carro.
     * @return el torque del carro.
     */
    public int getTorque() {
          return torque;
     }

    /**
     * Define el torque del carro.
     * @param torque el nuevo torque del carro.
    */
    public void setTorque(int torque) {
        this.torque = torque;
    }


    /**
     * Nos dice si el objeto recibido es un carro igual al que manda llamar
     * el método.
     * @param o el objeto con el que el carro se comparará.
     * @return <tt>true</tt> si el objeto o es un carro con las mismas
     *         propiedades que el objeto que manda llamar al método,
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
        if (!(o instanceof Carro))
            return false;
        Carro c = (Carro)o;
        if (this.color.equals(c.getColor()) &&
        this.marca.equals(c.getMarca()) &&
        this.año == c.getAño(); &&
        this.cilindraje == e.getCilindraje() &&
        this.caballosDeFuerza == e.getCaballosDeFuerza() &&
        this.torque == e.getTorque())
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
