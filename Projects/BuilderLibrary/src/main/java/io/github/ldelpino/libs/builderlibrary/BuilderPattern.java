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

import io.github.ldelpino.libs.builderlibrary.property.BuilderProperty;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase abstracta que permite la creacion del patron de diseño <b>Builder</b>
 * en la programacion orientada a objetos.
 * <p>
 * Cuando es necesario la creacion de instancias de entidades en un modelo de
 * negocios utilizando la programacion orientada a objetos, se hace uso
 * directamente del constructor de la clase a la cual estamos instanciando,
 * generando un alto acoplamiento y dependencias sobre ese tipo de datos en
 * especifico, en este punto es cuando el uso de patrones y principios de diseño
 * se vuelven necesario en el mundo de la programacion.
 * </p>
 * <p>
 * El patrón de diseño <b>Builder</b> es un patrón perteneciente al principio de
 * diseño <b>Open/Close</b> el cual establece que el diseño de un modelo de
 * negocios debe ser abierto a extensión y cerrado a modificación, o sea para
 * garantizar la reutilización y escalado de nuestro código para futuros cambios
 * y versiones nuestro código debe garantizar poder ser extendido y mejorado sin
 * necesidad de modificar el código fuente ya escrito con anterioridad, una de
 * las maneras de garantizar este principio es con el patrón de diseño Builder.
 * </p>
 * <p>
 * El patrón de diseño <b>Builder</b> se utiliza cuando necesitamos crear nuevas
 * instancias de clases manteniendo un bajo acoplamiento sobre las clases mas
 * específicas, esto se logra delegando la creación de instancias hacia otra
 * clase llamada <b>Builder</b> o delegando la creación hacia la misma entidad
 * ej: patrón <b>Singleton</b> en vez de mantener la responsabilidad
 * directamente sobre la clase controladora, contenedora o responsable de las
 * instancias.
 * </p>
 * <p>
 * La <b>reutilización</b> de la clase {@code BuilderPattern} permitirá
 * disminuir el tiempo de programación de este patrón para los programadores y
 * mantener un diseño ergonómico y eficiente, creando una clase que hereda de
 * esta clase abstracta y reimplementando el método
 * {@link io.github.ldelpino.libs.builderlibrary.BuilderPattern#buildInstance()},
 * donde en él se establece la creación de la nueva instancia necesaria.
 * </p>
 *
 * @author ldelpino
 * @version 1.0-SNAPSHOT
 * @since jdk-18.0.2
 * @param <T> el tipo de dato de la instancia a crear.
 */
public abstract class BuilderPattern<T> implements BuilderInterface<T> {

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
        validator = null;
    }

    /**
     * Devuelve un mapa inmodificable con el conjunto de propiedades que posee
     * este objeto.
     *
     * @return el mapa con las propiedades del objeto.
     */
    @Override
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
    @Override
    public Map<String, Object> getMapProperties() {
        Map<String, Object> props = new HashMap<>(getProperties().size());
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
    @Override
    public Collection<String> getPropertyKeys() {
        return getProperties().keySet();
    }

    /**
     * Devuelve una coleccion con los valores de las propiedades de este objeto.
     *
     * @return la coleccion con los valores de las propiedades de este objeto.
     */
    @Override
    public Collection<Object> getPropertyValues() {
        return getMapProperties().values();
    }

    /**
     * Establece si existe o no una propiedad dado la llave.
     *
     * @param propertyName la llave de la propiedad a verificar si esta
     * contenida.
     * @return <code>true</code> si existe una propiedad contenida con la llave
     * especificada, de lo contrario devuelve <code>false</code>.
     */
    @Override
    public boolean existProperty(String propertyName) {
        return getProperties().containsKey(propertyName);
    }

    /**
     * Devuelve una propiedad a partir de la llave o identificador de esta.
     *
     * @param propertyName la llave que identifica a una propiedad.
     * @return una de las propiedades de este objeto que coincide con el
     * identificador, si existe.
     */
    @Override
    public BuilderProperty<String, Object> getProperty(String propertyName) {
        return getProperties().get(propertyName);
    }

    /**
     * Establece una nueva propiedad para este objeto.
     *
     * @param property la nueva propiedad a establecer al objeto.
     * @throws IOException si la propiedad es nula, la llave ya existe, esta
     * duplicada o la propiedad no es valida.
     */
    @Override
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
    @Override
    public void putProperty(String propertyName, Object value) throws IOException {
        putProperty(new BuilderProperty(propertyName, value));
    }

    /**
     * Devuelve el validador del patron con el cual validar la creacion de
     * nuevas instancias.
     *
     * @return el validador de la creacion de nuevas instancias.
     */
    @Override
    public BuilderValidator getBuilderValidator() {
        return validator;
    }

    /**
     * Establece un nuevo validador para la creacion de instancias.
     *
     * @param validator el nuevo validador de instancias.
     */
    @Override
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
    @Override
    public final T build() throws InstanceBuildException {
        T instance = buildInstance();
        if (validator != null) {
            validator.validate(instance);
        }
        return instance;
    }

    /**
     * Devuelve la nueva instancia del objeto a necesitar instanciar a partir
     * del patron <b>Builder</b> y las propiedades almacenadas con anterioridad.
     *
     * @return la nueva instancia.
     */
    protected abstract T buildInstance();
}
