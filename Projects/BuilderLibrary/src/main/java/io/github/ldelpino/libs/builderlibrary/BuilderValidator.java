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
 * Interface que garantiza la correcta validacion de los datos antes de la
 * creacion de nuevas instancias por parte de la clase {@code BuilderPattern}.
 * <p>
 * {@code BuilderValidator} es utilizada cuando es necesario realizar
 * validaciones antes de la creacion de nuevas instancias de objetos, la
 * interfaz ha sido dise;ada para ser reutilizable y escalable a variadas formas
 * de validacion.
 * </p>
 * <p>
 * La forma recomendada para su <b>reutilizacion</b> es la creacion de nuevas
 * clases que implementen la interfaz, las cuales estaran en la obligacion de
 * reimplementar el metodo
 * {@link io.github.ldelpino.libs.builderlibrary.BuilderValidator#validate()},
 * donde en la implementacion del mismo se realizara el proceso de validacion y
 * si no es valido entonces lanzar una nueva instancia de la excepcion
 * {@link io.github.ldelpino.libs.builderlibrary.InstanceBuildException}.
 * </p>
 * <p>
 * La forma recomendada para su utilizacion es agregar una nueva instancia de la
 * clase que implementa la interfaz {@code BuilderValidator} en el metodo
 * {@link io.github.ldelpino.libs.builderlibrary.BuilderPattern#setBuilderValidator(io.github.ldelpino.libs.builderlibrary.BuilderValidator)},
 * antes del proceso de creacion de nuevas instancias.
 * </p>
 *
 * @author ldelpino
 * @see io.github.ldelpino.libs.builderlibrary.BuilderPattern#build()
 * @see
 * io.github.ldelpino.libs.builderlibrary.BuilderPattern#getBuilderValidator()
 * @version 1.0-SNAPSHOT
 * @since jdk-18.0.2
 */
public interface BuilderValidator {

    /**
     * Realiza el proceso de validacion de los datos antes de crear nuevas
     * instancias por parte del objeto {@code BuilderPattern} correspondiente.
     *
     * @throws InstanceBuildException si ocurre un error en la validacion de los
     * datos y por tanto la nueva instancia no debe ser creada.
     */
    public void validate() throws InstanceBuildException;
}
