package com.iktpreobuka.project.entities.dto;

/*
VALIDACIJA
 * 1.4 Napraviti klasu BillDTO po ugledu na BillEntity u okviru projekta i dodati odgovarajuća ograničenja:
• nijedan od atributa ne sme imati null vrednost (bez atributa datum kreiranja)
• atributi paymentMade i paymentCanceled moraju na početku biti postavljeni na false
• atribut billMade ne sme biti datum u budućnosti
• 1.5 Izmeniti kontroler tako da koristi BillDTO prilikom pravljenja novog Bill-a.
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.entities.UserEntity;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class BillDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @NotNull
    @AssertFalse
    @Column
    protected Boolean paymentMade;

    @NotNull
    @AssertFalse
    @Column
    protected Boolean paymentCancelled;

    @Past(message = "Bill must be created in the past.")
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    protected LocalDate billCreated;

    private UserEntity buyer;

    private OfferEntity offer;

    public BillDTO() {
        super();
    }

    public BillDTO(Integer id, Boolean paymentMade, Boolean paymentCancelled, LocalDate billCreated) {
        super();
        this.id = id;
        this.paymentMade = paymentMade;
        this.paymentCancelled = paymentCancelled;
        this.billCreated = billCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull @AssertFalse Boolean getPaymentMade() {
        return paymentMade;
    }

    public void setPaymentMade(@NotNull @AssertFalse Boolean paymentMade) {
        this.paymentMade = paymentMade;
    }

    public @NotNull @AssertFalse Boolean getPaymentCancelled() {
        return paymentCancelled;
    }

    public void setPaymentCancelled(@NotNull @AssertFalse Boolean paymentCancelled) {
        this.paymentCancelled = paymentCancelled;
    }

    public @Past(message = "Bill must be created in the past.") LocalDate getBillCreated() {
        return billCreated;
    }

    public void setBillCreated(@Past(message = "Bill must be created in the past.") LocalDate billCreated) {
        this.billCreated = billCreated;
    }

    public UserEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(UserEntity buyer) {
        this.buyer = buyer;
    }

    public OfferEntity getOffer() {
        return offer;
    }

    public void setOffer(OfferEntity offer) {
        this.offer = offer;
    }
}
