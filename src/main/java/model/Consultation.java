package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation implements Serializable {
    private Doctor doctor;
    private Patient patient;
    private LocalDate date;
    private LocalTime consultationStartTime;
    private LocalTime consultationEndTime;
    private int cost;
    private byte[] notes;
    private byte[][] imagesArray;

    public Consultation(Doctor doctor, Patient patient, LocalDate date, LocalTime consultationStartTime, LocalTime consultationEndTime, int cost, byte[] notes, byte[][] imagesArray) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.consultationStartTime = consultationStartTime;
        this.consultationEndTime = consultationEndTime;
        this.cost = cost;
        this.notes = notes;
        this.imagesArray = imagesArray;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getConsultationStartTime() {
        return consultationStartTime;
    }

    public void setConsultationStartTime(LocalTime consultationStartTime) {
        this.consultationStartTime = consultationStartTime;
    }

    public LocalTime getConsultationEndTime() {
        return consultationEndTime;
    }

    public void setConsultationEndTime(LocalTime consultationEndTime) {
        this.consultationEndTime = consultationEndTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public byte[] getNotes() {
        return notes;
    }

    public void setNotes(byte[] notes) {
        this.notes = notes;
    }

    public byte[][] getImagesArray() {
        return imagesArray;
    }

    public void setImagesArray(byte[][] imagesArray) {
        this.imagesArray = imagesArray;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "doctor=" + doctor +
                ", patient=" + patient +
                ", date=" + date +
                ", consultationStartTime=" + consultationStartTime +
                ", consultationEndTime=" + consultationEndTime +
                ", cost=" + cost +
                ", notes='" + notes + '\'' +
                ", images='" + imagesArray + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consultation consultation = (Consultation) o;
        return this.getDoctor().equals(consultation.getDoctor()) && this.getDate().equals(consultation.getDate())
                && this.getPatient().equals(consultation.getPatient())
                && this.getConsultationStartTime().equals(consultation.getConsultationStartTime())
                &&this.getConsultationEndTime().equals(consultation.getConsultationEndTime());
    }
}
