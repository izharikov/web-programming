package com.db.hibernate.entity;

import recording.entity.composition.Composition;
import recording.entity.duration.CompositionDuration;
import recording.factory.CompositionFactory;

import javax.persistence.*;

/**
 * Created by igor on 3.10.16.
 */
@Entity
@Table(name = "COMPOSITIONS", schema = "music", catalog = "")
public class CompositionsEntity {
    private int id;
    private String nameOfComposition;
    private String type;
    private Integer yearOfCreation;
    private Integer daysInTopList;
    private String duration;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME_OF_COMPOSITION")
    public String getNameOfComposition() {
        return nameOfComposition;
    }

    public void setNameOfComposition(String nameOfComposition) {
        this.nameOfComposition = nameOfComposition;
    }

    @Basic
    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "YEAR_OF_CREATION")
    public Integer getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(Integer yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    @Basic
    @Column(name = "DAYS_IN_TOP_LIST")
    public Integer getDaysInTopList() {
        return daysInTopList;
    }

    public void setDaysInTopList(Integer daysInTopList) {
        this.daysInTopList = daysInTopList;
    }

    @Basic
    @Column(name = "DURATION")
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositionsEntity that = (CompositionsEntity) o;

        if (id != that.id) return false;
        if (nameOfComposition != null ? !nameOfComposition.equals(that.nameOfComposition) : that.nameOfComposition != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (yearOfCreation != null ? !yearOfCreation.equals(that.yearOfCreation) : that.yearOfCreation != null)
            return false;
        if (daysInTopList != null ? !daysInTopList.equals(that.daysInTopList) : that.daysInTopList != null)
            return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameOfComposition != null ? nameOfComposition.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (yearOfCreation != null ? yearOfCreation.hashCode() : 0);
        result = 31 * result + (daysInTopList != null ? daysInTopList.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }

    public Composition generateComposition(){
        CompositionDuration dur = CompositionDuration.generateDurationFromString(getDuration());
        return new CompositionFactory().getComposition(getType(), getNameOfComposition(), dur,
                getYearOfCreation(), getDaysInTopList());
    }

}
