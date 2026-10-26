/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.IcoProgra.Comercial.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 *
 * @author Vis
 */
@Entity
@Table(name = "Estudiante", uniqueConstraints = {
    @UniqueConstraint(columnNames = "Rut")
})
public class EstudianteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Estudiante")
    private Integer id;

    @Column(name="Nombre",nullable = false, length = 100)
    private String nombre;

    @Column(name="Rut",nullable = false, length = 12)
    private String rut;

    @Column(name="Curso",nullable = false, length = 50)
    private String curso;

    public EstudianteEntity() {
    }

    public EstudianteEntity(String nombre, String rut, String curso) {
        this.nombre = nombre;
        this.rut = rut;
        this.curso = curso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
