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
package io.github.ldelpino.libs.builderlibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase abstracta que permite la creacion del patron de dise;o <b>Builder</b>
 * de la programacion orientada a objetos.
 * <p>
 * Cuando es necesario la creacion de instancias de entidades en un modelo de
 * negocios utilizando la programacion orientada a objetos, se hace uso
 * directamente del constructor de la clase a la cual estamos instanciando,
 * generando un alto acoplamiento y dependencias sobre ese tipo de datos en
 * especifico, n este punto es cuando el uso de patrones y principios de dise;o
 * se vuelven necesario en el mundo de la programacion.
 * </p>
 * <p>
 * El patron de dise;o <b>Builder</b> es un patron perteneciente al principio de
 * dise;o <b>Open/Close</b> el cual establece que el dise;o de un modelo de
 * negocios debe ser abierto a extension y cerrado a modificacion, o sea para
 * garantizar la reutilizacion y escalado de nuestro codigo para futuros cambios
 * y versiones nuestro codigo debe garantizar poder ser extendido y mejorado sin
 * necesidad de modificar el codigo fuente ya escrito con anterioridad, una de
 * las maneras de garantizar este principio e con el patron de dise;o Builder.
 * </p>
 * <p>
 * El patron de diseo; <b>Builder</b> se utiliza uando necesitamos crear nuevas
 * instancias de clases manteniendo un bajo acoplamiento sobre las clases mas
 * especificas, esto se logra delegando la creacion de instancias hacia otra
 * clase llamada <b>Builder</b> o delegando la creacion hacia la misma entidad
 * ej: patron <b>Singleton</b> en vez de mantener la responsabilidad
 * directamente sobre la clase controladora, contenedora o responsable de las
 * instancias.
 * </p>
 * <p>
 * La <b>reutilizacion</b> de la clase {@code BuilderPattern} permitira
 * disminuir el tiempo de programacion de este patron para los programadores y
 * mantener un dise;o ergonomico y eficiente, creando una clase que hereda de
 * esta clase abstracta y reimplementando el metodo
 * {@link io.github.ldelpino.libs.builderlibrary.BuilderPattern#buildInstance()},
 * donde en el se establece la creacion de la nueva instancia necesaria.
 * </p>
 *
 * @author ldelpino
 * @version 1.0-SNAPSHOT
 * @since jdk-18.0.2
 * @param <T> el tipo de dato de la instancia a crear.
 */
public abstract class BuilderPattern<T> {

    /**
     * Establece el conjunto de propiedades de este objeto con el cual crear
     * nuevas instancias.
     */
    protected Map<String, BuilderProperty<String, Object>> properties;

    /**
     * El validador del constructor de instancias.
     */
    protected BuilderValidator validator;

    /**
     * Construye una nueva instancia de esta clase.
     */
    public BuilderPattern() {
        this.properties = new HashMap<>();
    }

    /**
     * Devuelve un mapa inmodificable con el conjunto de propiedades que posee
     * este objeto.
     *
     * @return el mapa con las propiedades del objeto.
     */
    public Map<String, BuilderProperty<String, Object>> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    /**
     * Devuelve un nuevo mapa a partir del mapa de propiedades con la llave y el
     * valor de cada una de las propiedades de este objeto.
     *
     * @return el nuevo mapa con la llave y el valor de las propiedades de este
     * objeto.
     */
    public Map<String, Object> getMapProperties() {
        Map<String, Object> props = new HashMap<>();
        for (String s : getProperties().keySet()) {
            props.put(s, getProperties().get(s).getValue());
        }
        return props;
    }

    /**
     * Devuelve una coleccion de las llaves o identificadores de las propiedades
     * de este objeto.
     *
     * @return la coleccion de llaves de ste objeto.
     */
    public Collection<String> getPropertyKeys() {
        return getProperties().keySet();
    }

    /**
     * Devuelve una coleccion con los valores de las propiedades de este objeto.
     *
     * @return la coleccion con los valores de las propiedades de este objeto.
     */
    public Collection<Object> getPropertyValues() {
        Collection<Object> values = new ArrayList<>(getProperties().size());
        for (String s : getProperties().keySet()) {
            values.add(getProperties().get(s).getValue());
        }
        return values;
    }

    /**
     * Devuelve una propiedad a partir de la llave o identificador de esta.
     *
     * @param propertyName la llave que identifica a una propiedad.
     * @return una de las propiedades de este objeto que coincide con el
     * identificador, si existe.
     */
    public BuilderProperty<String, Object> getProperty(String propertyName) {
        return getProperties().get(propertyName);
    }

    /**
     * Establece una nueva propiedad para este objeto.
     *
     * @param property la nueva propiedad a establecer al objeto.
     * @throws IOException si la propiedad es nula, la llave ya existe o esta
     * duplicada o la propiedad no es valida.
     */
    public void putProperty(BuilderProperty<String, Object> property) throws IOException {
        if (property == null) {
            throw new IOException("Error.", new Throwable("The property cannot be null."));
        }
        if (getProperties().containsKey(property.getKey())) {
            throw new IOException("Error, duplicated property key",
                    new Throwable("The property key already exist"));
        }
        property.validate();
        properties.put(property.getKey(), property);
    }

    /**
     * Establece una nueva propiedad a partir de la llave que identifica a la
     * propiedad y su valor correspondiente.
     *
     * @param propertyName la llave o identificador de la propiedad.
     * @param value el valor de la propiedad.
     * @throws IOException si la llave ya existe o esta duplicada.
     */
    public void putProperty(String propertyName, Object value) throws IOException {
        putProperty(new BuilderProperty(propertyName, value));
    }

    /**
     * Devuelve el validador del patron con el cual validar la creacion de
     * nuevas instancias.
     *
     * @return el validador de la creacion de nuevas instancias.
     */
    public BuilderValidator getBuilderValidator() {
        return validator;
    }

    /**
     * Establece un nuevo validador para la creacion de instancias.
     *
     * @param validator el nuevo validador de instancias.
     */
    public void setBuilderValidator(BuilderValidator validator) {
        this.validator = validator;
    }

    /**
     * Valida y construye una nueva instancia del objeto a crear.
     *
     * @return la instancia creada del objeto instanciado a partir del patron
     * <b>Builder</b>.
     * @throws InstanceBuildException si ocurre un error durante el proceso de
     * validacion antes de crear la nueva instancia.
     */
    public final T build() throws InstanceBuildException {
        if (validator != null) {
            validator.validate();
        }
        return buildInstance();
    }

    /**
     * Devuelve la nueva instancia del objeto a necesitar instanciar a partir
     * del patron <b>Builder</b> y las propiedades almacenadas con anterioridad.
     *
     * @return la nueva instancia.
     */
    protected abstract T buildInstance();
}
