package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de estudiantes.
 */
public class BaseDeDatosCarro
    extends BaseDeDatos<Carro, CampoCarro> {

    /**
     * Crea un estudiante en blanco.
     * @return un estudiante en blanco.
     */
    @Override public Estudiante creaRegistro() {
        return new Estudiante(null,0,0.0,0);
    }

    /**
     * Busca estudiantes por un campo específico.
     * @param campo el campo del registro por el cuál buscar.
     * @param texto el texto a buscar.
     * @return una lista con los estudiantes tales que en el campo especificado
     *         contienen el texto recibido, o una lista vacía si el texto es
     *         <code>null</code> o la cadena vacía.
     */
    @Override public Lista<Estudiante> buscaRegistros(CampoEstudiante campo,
                                                      String texto) {
        if (!(campo instanceof CampoEstudiante))
            throw new IllegalArgumentException("El campo debe ser " +
                                               "CampoEstudiante");
        CampoEstudiante c = campo;
        Lista<Estudiante> estudiantes = new Lista<Estudiante>();
        if (texto == null || texto.equals(""))
            return estudiantes;
        IteradorLista it = registros.iteradorLista();
        switch (c) {
            case NOMBRE:
                       while (it.hasNext()) {
                           Estudiante e = (Estudiante)it.next();
                           if (e.getNombre().contains(texto))
                               estudiantes.agregaFinal(e);
                       }
                       break;
            case CUENTA:
                       while (it.hasNext()) {
                           Estudiante e = (Estudiante)it.next();
                           if (String.valueOf(e.getCuenta()).contains(texto))
                              estudiantes.agregaFinal(e);
                      }
                      break;
            case PROMEDIO:
                      while (it.hasNext()) {
                          Estudiante e = (Estudiante)it.next();
                          if (String.format("%2.2f", e.getPromedio()).contains(texto))
                             estudiantes.agregaFinal(e);
                      }
                      break;
            case EDAD:
                      while (it.hasNext()) {
                          Estudiante e = (Estudiante)it.next();
                          if (String.valueOf(e.getEdad()).contains(texto))
                             estudiantes.agregaFinal(e);
                      }
                      break;
        }
        return estudiantes;
    }
}
