/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.ldelpino.libs.builderlibrary.service;

import io.github.ldelpino.libs.builderlibrary.property.PropertyValidator;
import io.github.ldelpino.libs.builderlibrary.BuilderInterface;
import io.github.ldelpino.libs.builderlibrary.property.BuilderProperty;

/**
 * Servicio de gestion de los validadores existentes en el sistema.
 * <p>
 * El servicio se encarga del almacenamiento de los validadores de las
 * propiedades del sistema, a partir de el mismo es posible obtener los
 * validadores agregados y con ello validar las propiedades de un objeto de
 * negocio.</p>
 *
 * @author ldelpino
 * @version 1.0-SNAPSHOT
 * @since jdk-18.0.2
 */
public class ValidatorService {

    private static ValidatorService instance;

    private ValidatorService() {

    }

    public static ValidatorService getDefault() {
        if (instance == null) {
            instance = new ValidatorService();
        }
        return instance;
    }

    private BuilderInterface getBuilder(String builderName) {
        return BuilderService.getDefault().getBuilder(builderName);
    }

    public PropertyValidator getValidator(String builderName, String key) {
        BuilderInterface builder = getBuilder(builderName);
        PropertyValidator validator = null;
        if (builder != null) {
            BuilderProperty property = builder.getProperty(key);
            if (property != null) {
                validator = property.getPropertyValidator();
            }
        }
        return validator;
    }
}
