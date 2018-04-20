package mx.unam.ciencias.icc;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas implementan la interfaz {@link Iterable}, y por lo tanto se
 * pueden recorrer usando la estructura de control <em>for-each</em>. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Iterable<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        public T elemento;
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nodo con un elemento. */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    /* Clase Iterador privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nuevo iterador. */
        public Iterador() {
            siguiente = cabeza;
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return siguiente != null;
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() throws NoSuchElementException {
            if(siguiente == null)
                throw new NoSuchElementException();
            else {
                anterior = siguiente;
                siguiente = siguiente.siguiente;
                return anterior.elemento;
            }
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            return anterior != null;
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            if(anterior == null)
                throw new NoSuchElementException();
            else {
                siguiente = anterior;
                anterior = anterior.anterior;
                return siguiente.elemento;
            }
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            siguiente = cabeza;
            anterior = null;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            siguiente = null;
            anterior = rabo;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        if (cabeza == null)
            return true;
        else
            return false;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) throws IllegalArgumentException {
        if (elemento == null)
            throw new IllegalArgumentException();
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null)
            cabeza = rabo = nuevo;
        else{
            nuevo.anterior = rabo;
            rabo.siguiente = nuevo;
            rabo = nuevo;
        }
        longitud++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) throws IllegalArgumentException {
        if (elemento == null)
            throw new IllegalArgumentException();
        Nodo nuevo = new Nodo(elemento) ;
        if (cabeza == null)
            cabeza = rabo = nuevo;
        else{
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
        longitud++;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) throws IllegalArgumentException{
        if (elemento == null)
            throw new IllegalArgumentException();
        if(i <= 0 || i >= longitud ) {
            if (i <= 0)
                agregaInicio(elemento);
            if(i >= longitud)
                agregaFinal(elemento);
        }
        else
            auxiliarInserta(i, elemento, cabeza, 0);
    }

    /*
       Método auxiliar de "inserta". Se mueve por la lista hasta llegar al
       índice indicado e inserta el nodo(e).
     */
     private void auxiliarInserta(int i, T elemento, Nodo n, int contador){
        if (contador == i) {
            Nodo nuevo = new Nodo(elemento);
            nuevo.anterior = n.anterior;
            nuevo.siguiente = n;
            n.anterior.siguiente = nuevo;
            n.anterior = nuevo;
            longitud++;
         }
        else
            auxiliarInserta(i, elemento, n.siguiente, ++contador);
       }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(T elemento) {
        Nodo nodoEliminar = buscaNodo(elemento,cabeza);
        if(nodoEliminar == null)
            return;
        if(nodoEliminar == cabeza){ //Eliminando el nodo cabeza  (longitud >= 2)
            T p = eliminaPrimero();
            return;
        }
        if(nodoEliminar != cabeza && nodoEliminar != rabo){   //eliminando nodo interno de la lista (no es cabeza ni rabo)
            nodoEliminar.anterior.siguiente = nodoEliminar.siguiente;
            nodoEliminar.siguiente.anterior = nodoEliminar.anterior;
            nodoEliminar.siguiente = nodoEliminar.anterior = null;
            longitud--;
            return;
        }
        if(nodoEliminar == rabo){
            T u = eliminaUltimo();
            return;
        }
  }

  /*
   * Busca al nodo que contiene al elemento.
   * elemento el elemento que estamos buscando.
   * n el nodo donde va buscar (y comparar) a elemento.
   * el nodo que contiene al elemento, o
   * <code>null</code> si ningún nodo contiene ese elemento.
   */
    private Nodo buscaNodo(T elemento, Nodo n){
        while (n != null){
            if(n.elemento.equals(elemento))
                return n;
            n = n.siguiente;
        }
        return null;
  }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero()throws NoSuchElementException{
        if (cabeza == null)
            throw new NoSuchElementException();
        else{
            longitud--;
            Nodo n = cabeza;
            if (cabeza.siguiente == null){
                cabeza = rabo = null;
                return n.elemento;
            }
            else{
                cabeza = cabeza.siguiente;
                n.siguiente = null;
                cabeza.anterior = null;
                return n.elemento;
            }
        }
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() throws NoSuchElementException {
        if (esVacia())
            throw new NoSuchElementException();
        if(longitud == 1){
            Nodo n = cabeza;
            cabeza = rabo = null;
            longitud--;
            return n.elemento;
        }
        else{
            Nodo n = rabo;
            rabo = n.anterior;
            rabo.siguiente = null;
            n.anterior = null;
            longitud--;
            return n.elemento;
        }
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(T elemento) {
        return cabeza != null ? auxiliarContiene(cabeza, elemento) : false;
   }

  // Metodo auxiliar de "contiene".
  private boolean auxiliarContiene(Nodo n, T elemento){
        if(n.elemento.equals(elemento) || n.siguiente == null)
            return (n.elemento.equals(elemento)) ? true : false;
        return auxiliarContiene(n.siguiente, elemento);
  }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        Nodo n = rabo;
        int e = longitud-1;
        Lista<T> nuevaLista = new Lista<T>();
        for(int i = 0; i < longitud; i++ )
            nuevaLista.inserta(i, get(e--));
        return nuevaLista;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        Lista<T> lista = this.reversa();
        return lista.reversa();
    }

    /**
     * Limpia la lista de elementos. El llamar este método es equivalente a
     * eliminar todos los elementos de la lista.
     */
    public void limpia() {
        while(longitud != 0)
            this.eliminaPrimero();
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() throws NoSuchElementException {
        if(esVacia())
            throw new NoSuchElementException();
        else
            return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() throws NoSuchElementException{
        if (esVacia())
            throw new NoSuchElementException();
        else
            return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) throws ExcepcionIndiceInvalido {
        if ( i < 0 || i >= longitud)
            throw new ExcepcionIndiceInvalido();
        return auxiliarGet(i, cabeza, 0);
      }

      //Método auxiliar de get.
    private T auxiliarGet(int indice, Nodo n, int contador){
        return indice == contador ? n.elemento : auxiliarGet(indice, n.siguiente, ++contador);
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        int contador = 0;
        Nodo n = cabeza;
        while(n != null){
            if(n.elemento.equals(elemento))
                return contador;
            contador++;
            n = n.siguiente;
        }
        return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        String s = "[";
        if(esVacia())
            return "[]";
        else{
            for (int i = 0; i < longitud-1; i++)
                s += String.format("%d, ", get(i));
            s += String.format("%d]", get(longitud-1));
            return s;
        }
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param o el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la lista es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)o;
        if (this.longitud != lista.longitud)
            return false;
        else {
            for (int i = 0; i < longitud; i++)
                if (!this.get(i).equals(lista.get(i)))
                    return false;
            return true;
        }
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
}
