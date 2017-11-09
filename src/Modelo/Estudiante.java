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
    private static final String FOTO = "foto";

    private boolean partial;

    public Estudiante() {
        this.partial = true;
    }

    public Estudiante(String nombre, String codigo, String semestre, byte[] foto) {
        put(NOMBRE, nombre);
        put(CODIGO, codigo);
        put(SEMESTRE, semestre);
        put(FOTO, foto);

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
        setThis.add(FOTO);

        partial = !set.equals(setThis);
    }

    public String getNombre() {
        return getString(NOMBRE);
    }

    public void setNombre(String nombre) {
        put(NOMBRE, nombre);
    }

    public String getCodigo() {
        return getString(CODIGO);
    }

    public void setCodigo(String codigo) {
        put(CODIGO, codigo);
    }

    public String getSemestre() {
        return getString(SEMESTRE);
    }

    public void setSemestre(String semestre) {
        put(SEMESTRE, semestre);
    }

    public byte[] getFoto() {
        return (byte[]) get((Object)FOTO);
    }

    public void setFoto(byte[] foto) {
        put(FOTO, foto);
    }

}
