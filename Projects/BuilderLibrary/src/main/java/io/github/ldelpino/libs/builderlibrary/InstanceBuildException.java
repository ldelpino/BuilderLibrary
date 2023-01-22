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

/**
 * Clase que señala que ha ocurrido un error en la validación de un objeto
 * {@link io.github.ldelpino.libs.builderlibrary.property.BuilderProperty}.
 * <p>
 * La clase es utilizada cuando ocurre un error en la validación de los datos de
 * una propiedad para la construcción de un objeto
 * {@link io.github.ldelpino.libs.builderlibrary.BuilderPattern}, clase
 * utilizada para la creación de instancias a partir del patrón de diseño
 * conocido como <b>Builder</b>.
 * </p>
 * <p>
 * {@code InvalidPropertyException} es una clase que hereda de
 * {@code IOException} dado que se espera que los errores en la construcción de
 * propiedades para un objeto <b>Builder</b> ocurran a partir de la entrada y
 * validación de los datos.
 * </p>
 *
 * @author ldelpino
 * @see io.github.ldelpino.libs.builderlibrary.BuilderValidator
 * @see io.github.ldelpino.libs.builderlibrary.BuilderPattern#build()
 * @version 1.0-SNAPSHOT
 * @since jdk-18.0.2
 *
 */
public class InstanceBuildException extends Exception {

    /**
     * Construye una nueva instancia de {@code InstanceBuildException} a partir
     * de un mensaje que describe el error ocurrido.
     *
     * @param message un mensaje que describa el error ocurrido.
     */
    public InstanceBuildException(String message) {
        super(message);
    }

    /**
     * Construye una nueva instancia de {@code InstanceBuildException} a partir
     * de un mensaje que describe el error ocurrido y la posible causa.
     *
     * @param message un mensaje que describa el error ocurrido.
     * @param cause la posible causa del lanzamiento del error.
     */
    public InstanceBuildException(String message, Throwable cause) {
        super(message, cause);
    }
}
