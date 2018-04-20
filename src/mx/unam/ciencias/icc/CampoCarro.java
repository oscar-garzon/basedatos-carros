package mx.unam.ciencias.icc;

/**
 * Enumeración para los campos de un {@link Carro}.
 */
public enum CampoEstudiante {

  /** Color del carro. */
  COLOR,
  /** Marca del carro. */
  MARCA,
  /** Año del carro. */
  AÑO
  /** Cilindraje del carro. */
  CILINDRAJE,
  /**Caballos de fuerza del carro. */
  CABALLOSDEFUERZA,
  /**Torque del carro. */
  TORQUE;

    /**
     * Regresa una representación en cadena del campo para ser usada en
     * interfaces gráficas.
     * @return una representación en cadena del campo.
     */
    @Override public String toString() {
      switch(this) {
          case COLOR:
              return "color";

          case MARCA:
              return "Marca";

          case AÑO:
              return "Año";

          case CILINDRAJE:
              return "Cilindraje";

          case CABALLOSDEFUERZA:
              return "Caballos de fuerza";

          case TORQUE:
              return "Torque";
          default: return "";
      }
    }
}
