/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.mongodb.BasicDBObject;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Momo
 */
public class Estudiante extends BasicDBObject {
    private static final String NOMBRE = "nombre";
    private static final String CODIGO = "codigo";
    private static final String SEMESTRE = "semestre";

    private boolean partial;

    public Estudiante() {
        this.partial = true;
    }
    
    public Estudiante(String nombre, String codigo, String semestre) {
        put(NOMBRE, nombre);
        put(CODIGO, codigo);
        put(SEMESTRE, semestre);

        this.markAsPartialObject();
    }
    
    
    @Override
    public void markAsPartialObject() {
        Set<String> set = keySet();
        set.remove("_id");

        Set<String> setThis = new HashSet<>();
        setThis.add(NOMBRE);
        setThis.add(CODIGO);
        setThis.add(SEMESTRE);

        partial = !set.equals(setThis);
    }

    public String getNOMBRE() {
        return getString(NOMBRE);
    }
    
    public void setNombre(String nombre) {
        put(NOMBRE, nombre);
    }

    public String getCODIGO() {
        return getString(CODIGO);
    }
    
    public void setCODIGO(String codigo) {
        put(CODIGO, codigo);
    }

    public String getSEMESTRE() {
        return getString(SEMESTRE);
    }
    
    public void setSemestre(String semestre) {
        put(SEMESTRE, semestre);
    }

    public static Estudiante create(BasicDBObject object) {
        String nombre = object.getString(NOMBRE);
        String codigo = object.getString(CODIGO);
        String semestre = object.getString(SEMESTRE);

        return new Estudiante(nombre, codigo, semestre);
    }
    
    
}
