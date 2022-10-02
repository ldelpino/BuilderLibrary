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
 * Interface que garantiza la correcta validacion de los datos de una propiedad
 * de un objeto {@code BuilderPattern}.
 * <p>
 * {@code PropertyValidator} es utilizada cuando es necesario realizar
 * validaciones de los valores o llaves de una propiedad, esto permite que cada
 * una de las propiedades que requieran pasar por un proceso de validacion, lo
 * hagan manteniendo la logica de creacion de nuevas instancias separadas del
 * proceso de validacion de los datos de entrada y si existe un mecanismo de
 * validacion para una propiedad cualquiera, esta sea validada antes de ser
 * almacenada y utilizada por el objeto {@code BuilderPattern} en la creacion de
 * nuevas instancias.
 * </p>
 * <p>
 * La forma recomendada para su <b>reutilizacion</b> es la creacion de nuevas
 * clases que implementen la interfaz, las cuales estaran en la obligacion de
 * reimplementar el metodo
 * {@link io.github.ldelpino.libs.builderlibrary.PropertyValidator#validate() },
 * donde en la implementacion del mismo se realizara el proceso de validacion y
 * si no es valido entonces lanzar una nueva instancia de la excepcion
 * {@link io.github.ldelpino.libs.builderlibrary.InvalidPropertyException}.
 * </p>
 * <p>
 * La forma recomendada para su utilizacion es agregar una nueva instancia de la
 * clase que implementa la interfaz {@code PropertyValidator} en el metodo
 * {@link io.github.ldelpino.libs.builderlibrary.BuilderProperty#setPropertyValidator(io.github.ldelpino.libs.builderlibrary.PropertyValidator)},
 * antes del proceso de insercion de la propiedad en el objeto
 * {@code BuilderPattern} correspondiente.
 * </p>
 *
 * @author ldelpino
 * @see io.github.ldelpino.libs.builderlibrary.BuilderProperty
 * @see
 * io.github.ldelpino.libs.builderlibrary.BuilderProperty#setPropertyValidator(io.github.ldelpino.libs.builderlibrary.PropertyValidator)
 * @version 1.0-SNAPSHOT
 * @since jdk-18.0.2
 */
public interface PropertyValidator {

    /**
     * Realiza el proceso de validacion de los datos de una propiedad.
     *
     * @throws InvalidPropertyException si ocurre un error en la validacion de
     * los datos y por tanto la nueva propiedad no debe ser aceptada.
     */
    public void validate() throws InvalidPropertyException;
}
