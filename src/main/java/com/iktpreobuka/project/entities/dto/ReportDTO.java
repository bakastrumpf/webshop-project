package com.iktpreobuka.project.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import java.util.List;

public class ReportDTO {

//    • 3.2 kreirati i ReportDto objekat sa sledećim atributima
//• categoryName – naziv kategorije za koju se generiše izveštaj,
//• listom ReportItemDto objekata,
//• sumOfIncomes – ukupna zarada za traženi period i
//• totalNumberOfSoldOffers – ukupan broj prodatih ponuda kategorije za traženi period
// odgovarajući geteri i seteri i konstruktor bez parametara

    @Column
    @JsonProperty("category_name")
    public String categoryName;

    @Column
    @JsonProperty("category_name")
    public List<ReportItemDTO> reportItemDtoList;

    @Column
    @JsonProperty("sum_of_incomes")
    public String sumOfIncomes;

    @Column
    @JsonProperty("total_nr_sold_offers")
    public String totalNumberOfSoldOffers;

    public ReportDTO() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ReportItemDTO> getReportItemDtoList() {
        return reportItemDtoList;
    }

    public void setReportItemDtoList(List<ReportItemDTO> reportItemDtoList) {
        this.reportItemDtoList = reportItemDtoList;
    }

    public String getSumOfIncomes() {
        return sumOfIncomes;
    }

    public void setSumOfIncomes(String sumOfIncomes) {
        this.sumOfIncomes = sumOfIncomes;
    }

    public String getTotalNumberOfSoldOffers() {
        return totalNumberOfSoldOffers;
    }

    public void setTotalNumberOfSoldOffers(String totalNumberOfSoldOffers) {
        this.totalNumberOfSoldOffers = totalNumberOfSoldOffers;
    }
}
