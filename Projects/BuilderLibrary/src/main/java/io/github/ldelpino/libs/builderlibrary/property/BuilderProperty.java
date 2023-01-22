/*
 * Copyright (C) 2022 ldelpino
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.ldelpino.libs.builderlibrary.property;

/**
 * La clase se encarga de la creacion de propiedades para el objeto
 * {@link io.github.ldelpino.libs.builderlibrary.BuilderInterface} en la creacion de nuevas instancias.
 * <p>
 * Cuando es necesario la creacion de nuevas instancias de las entidades del
 * modelo de negocios, es posible que sea necesario aplicar el patron de dise;o
 * <b>Builder</b>, en estos casos es cuando la libreria comienza a tener
 * sentido, para la creacion de nuevas instancias a traves del objeto
 * {@link io.github.ldelpino.libs.builderlibrary.BuilderInterface} es necesario añadir las propiedades de la entidad
 * con el cual el objeto pueda crear nuevas instancias, en este punto es donde
 * entra la clase {@code BuilderProperty}, clase encargada de almacenar y
 * validar las propiedades necesarias para la creacion de nuevas instancias.
 * </p>
 * <p>
 * La clase {@code BuilderProperty} contiene 3 atributos basicos, la llave de la
 * propiedad, el valor de la propiedad y el validador de la propiedad. La llave
 * de una propiedad es un atributo el cual no deberia ser nulo, vacio o estar
 * duplicado, dentro del conjunto de propiedades, establece un valor
 * identificativo para diferenciar y obtener las propiedades y los valores de
 * las mismas. El valor de una propiedad si puede estar nulo, vacio o duplicado,
 * el valor de una propiedad es el valor que se envia a la hora de crear nuevas
 * instancias de entidades del modelo de negocios. El validador es un objeto que
 * permitira realizar las validaciones de la propiedad si es necesario, en caso
 * de serlo, el objeto es el encargado de la validacion del valor de una
 * propiedad antes del proceso de almacenamiento en el objeto de tipo
 * {@code BuilderPattern}.
 * </p>
 *
 * @author ldelpino
 * @see io.github.ldelpino.libs.builderlibrary.BuilderInterface#getProperties()
 * @see
 * io.github.ldelpino.libs.builderlibrary.BuilderInterface#putProperty(io.github.ldelpino.libs.builderlibrary.BuilderProperty)
 * @version 1.0-SNAPSHOT
 * @since jdk-18.0.2
 * @param <K> el tipo de dato de la llave de la propiedad.
 * @param <V> el tipo de dato del valor de la propiedad.
 */
public class BuilderProperty<K, V> {

    /**
     * La llave que identifica a la propiedad.
     */
    protected K key;

    /**
     * El valor de la propiedad utilizado para la creacion de nuevas instancias.
     */
    protected V value;

    /**
     * El objeto validador del valor de la propiedad que garantiza que el valor
     * es un valor valido.
     */
    protected PropertyValidator<V> propertyValidator;

    /**
     * Construye una nueva propiedad a partir de la llave y el valor.
     * <p>
     * La nueva propiedad no posee un validador, por lo cual el valor de la
     * propiedad no es validado, tambien el valor de la nueva proiedad creada es
     * nulo.</p>
     *
     * @param key la llave que identifica a la propiedad.
     */
    public BuilderProperty(K key) {
        this(key, null);
    }

    /**
     * Construye una nueva propiedad a partir de la llave y el valor.
     * <p>
     * La nueva propiedad no posee un validador, por lo cual el valor de la
     * propiedad no es validado.</p>
     *
     * @param key la llave que identifica a la propiedad.
     * @param value el valor el cual es utilizado para la creacion de nuevas
     * instancias.
     */
    public BuilderProperty(K key, V value) {
        this(key, value, null);
    }

    /**
     * Construye una nueva propiedad a partir de la llave, el valor y el
     * validador de la propiedad.
     *
     * @param key la llave que identifica a la propiedad.
     * @param value el valor el cual es utilizado para la creacion de nuevas
     * instancias.
     * @param propertyValidator el validador del valor de la propiedad.
     */
    public BuilderProperty(K key, V value, PropertyValidator<V> propertyValidator) {
        this.key = key;
        this.value = value;
        this.propertyValidator = propertyValidator;
    }

    /**
     * Devuelve el identificador de la propiedad.
     *
     * @return la llave que identifica a la propiedad.
     */
    public K getKey() {
        return key;
    }

    /**
     * Establece una nueva llave para la propiedad.
     * <p>
     * Cuando se modifica la llave de la propiedad automaticamente se establece
     * la propiedad como no validada, para validarla debe ejecutar el metodo
     * {@code validate()}.</p>
     *
     * @param key la nueva llave de la propiedad.
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Devuelve el valor de la propiedad con el cual crear nuevas instancias.
     *
     * @return el valor de la propiedad.
     */
    public V getValue() {
        return value;
    }

    /**
     * Establece un nuevo valor para la propiedad.
     * <p>
     * Cuando se modifica el valor de la propiedad automaticamente se establece
     * la propiedad como no validada, para validarla debe ejecutar el metodo
     * {@code validate()}.</p>
     *
     * @param value el nuevo valor a establecer en la propiedad.
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Devuelve el valor establecido para vaildar el valor de la propiedad.
     *
     * @return el validador de la propiedad.
     */
    public PropertyValidator<V> getPropertyValidator() {
        return propertyValidator;
    }

    /**
     * Establece un nuevo validador para la propiedad.
     * <p>
     * Cuando se modifica el validador de la propiedad automaticamente se
     * establece la propiedad como no validada, para validarla debe ejecutar el
     * metodo {@code validate()}.</p>
     *
     * @param propertyValidator el nuevo validador de la propiedad.
     */
    public void setPropertyValidator(PropertyValidator<V> propertyValidator) {
        this.propertyValidator = propertyValidator;
    }

    /**
     * Realiza el proceso de validacion del valor actual de la propiedad.
     * <p>
     * El proceso de validacion solo se realiza si el validador de la propiedad
     * no es nulo. Cuando se realice el proceso de validacion si no ocurre el
     * lanzamiento de una excepcion, entonces se considera a la propiedad como
     * validada.</p>
     *
     * @throws InvalidPropertyException si occure un error durante el proceso de
     * validacion.
     */
    public void validate() throws InvalidPropertyException {
        if (getPropertyValidator() != null) {
            getPropertyValidator().validate(getValue());
        }
    }
}
