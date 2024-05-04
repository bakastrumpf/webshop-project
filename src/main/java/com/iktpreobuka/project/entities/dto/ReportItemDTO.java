package com.iktpreobuka.project.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.util.Date;

public class ReportItemDTO {

//    • 3.1 za potrebe kreiranja izveštaja o prodaji po danima/kategorijama potrebno je napraviti DTO objekat
//    ReportItem u paketu com.iktpreobuka.project.entities.dto
//    sa atributima
//• date – datum za koji se vrši računanje zarade ,
//• income – zarada za dati datum i
//• numberOfOffers – broj prodatih ponuda te kategorije
//• odgovarajući geteri i seteri i konstruktor bez parametara

    @Column
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy")
    @JsonProperty("ri_date")
    public Date date;

    @Column
    @JsonProperty("ri_income")
    public String income;

    @Column
    @JsonProperty("ri_number_of_offers")
    public Integer numberOfOffers;

    public ReportItemDTO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public Integer getNumberOfOffers() {
        return numberOfOffers;
    }

    public void setNumberOfOffers(Integer numberOfOffers) {
        this.numberOfOffers = numberOfOffers;
    }
}
