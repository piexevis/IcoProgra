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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Vis
 */
@Entity
@Table(name = "Reserva")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Reserva")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Id_estudiante", nullable = false)
    private EstudianteEntity estudiante;

    @Column(name = "contenido", nullable = false, columnDefinition = "text")
    private String contenido;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)   // <input type="date"> -> yyyy-MM-dd
    @Column(name = "Fecha", nullable = false)
    private LocalDate fecha;

    @DateTimeFormat(pattern = "HH:mm")               // <input type="time"> -> HH:mm
    @Column(name = "Hora", nullable = false)
    private LocalTime hora;

    public ReservaEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstudianteEntity getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteEntity estudiante) {
        this.estudiante = estudiante;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
