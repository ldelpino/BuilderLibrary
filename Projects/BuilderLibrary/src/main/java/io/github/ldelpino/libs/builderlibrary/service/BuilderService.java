/*
 * Copyright (C) 2023 ldelpino
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
package io.github.ldelpino.libs.builderlibrary.service;

import io.github.ldelpino.libs.builderlibrary.BuilderInterface;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Servicio que permite la gestion de los constructores de instancias.
 * <p>
 * El servicio se encarga del almacenamiento de los constructores de instancias
 * de un sistema, a partir de el mismo es posible obtener los constructores de
 * instancias agregados y con ello crear nuevas instancias de objetos del
 * negocio.</p>
 *
 * @author ldelpino
 * @version 1.0-SNAPSHOT
 * @since jdk-18.0.2
 */
public class BuilderService {

    private static BuilderService instance;

    private final Map<String, BuilderInterface> builders;

    private BuilderService() {
        builders = new HashMap<>();
    }

    /**
     * Devuelve la unica instancia del servicio de constructores.
     *
     * @return la instancia por defecto del servicio de construccion.
     */
    public static BuilderService getDefault() {
        if (instance == null) {
            instance = new BuilderService();
        }
        return instance;
    }

    /**
     * Devuelve un mapa inmodificable con los constructores de instancias.
     *
     * @return el conjunto de todos los constructores de instancias agregados.
     */
    public Map<String, BuilderInterface> getBuilders() {
        return Collections.unmodifiableMap(builders);
    }

    /**
     * Devuelve el constructor de instancias dado el nombre del mismo.
     *
     * @param builderName el nombre identificativo del constructor de
     * instancias.
     * @return el contructor de instancias o {@code null} sino existe.
     */
    public BuilderInterface getBuilder(String builderName) {
        return getBuilders().get(builderName);
    }

    /**
     * Agrega o sustituye el constructor de instancias que coincide con el
     * nombre.
     *
     * @param builderName el nombre que identifica al constructor de instancias.
     * @param builder el constructor de instancias.
     */
    public void putBuilder(String builderName, BuilderInterface builder) {
        builders.put(builderName, builder);
    }

    /**
     * Remueve el constructor de instancias del conjunto de constructores
     * agregados.
     *
     * @param builderName el nombre identificativo del constructor de
     * instancias.
     * @return el constructor de instancias removido o {@code null} sino existe.
     */
    public BuilderInterface removeBuilder(String builderName) {
        return builders.remove(builderName);
    }
}
