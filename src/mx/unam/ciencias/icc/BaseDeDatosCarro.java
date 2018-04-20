package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de estudiantes.
 */
public class BaseDeDatosCarro
    extends BaseDeDatos<Carro, CampoCarro> {

    /**
     * Crea un carro en blanco.
     * @return un carro en blanco.
     */
    @Override public Carro creaRegistro() {
        return new Carro(null,null,0,0.0,0,0);
    }

    /**
     * Busca carros por un campo específico.
     * @param campo el campo del registro por el cuál buscar.
     * @param texto el texto a buscar.
     * @return una lista con los carros tales que en el campo especificado
     *         contienen el texto recibido, o una lista vacía si el texto es
     *         <code>null</code> o la cadena vacía.
     */
    @Override public Lista<Carro> buscaRegistros(CampoCarro campo,
                                                      String texto) {
        if (!(campo instanceof CampoEstudiante))
            throw new IllegalArgumentException("El campo debe ser " +
                                               "CampoEstudiante");
        CampoCarro c = campo;
        Lista<Carro> carros = new Lista<Carro>();
        if (texto == null || texto.equals(""))
            return carros;
        IteradorLista it = registros.iteradorLista();
        switch (c) {
            case COLOR:
                       while (it.hasNext()) {
                           Carro c = (Carro)it.next();
                           if (c.getColor().contains(texto))
                               carros.agregaFinal(c);
                       }
                       break;
            case MARCA:
                       while (it.hasNext()) {
                           Carro c = (Carro)it.next();
                           if (c.getMarca().contains(texto))
                              carros.agregaFinal(c);
                      }
                      break;
            case AÑO:
                      while (it.hasNext()) {
                          Carro c = (Carro)it.next();
                          if (String.valueOf(c.getAño()).contains(texto))
                             carros.agregaFinal(c);
                      }
                      break;
            case CILINDRAJE:
                      while (it.hasNext()) {
                          Carro c = (Carro)it.next();
                          if (String.format("%2.2f", c.getCilindraje()).contains(texto))
                              carros.agregaFinal(c);
                      }
                      break;
            case CABALLOSDEFUERZA:
                      while (it.hasNext()) {
                          Carro c = (Carro)it.next();
                          if (String.valueOf(c.getCaballosDeFuerza()).contains(texto))
                             carros.agregaFinal(c);
                      }
                      break;
            case TORQUE:
                      while (it.hasNext()) {
                          Carro c = (Carro)it.next();
                          if (String.valueOf(c.getTorque()).contains(texto))
                             carros.agregaFinal(c);
                      }
                      break;

        }
        return carros;
    }
}
