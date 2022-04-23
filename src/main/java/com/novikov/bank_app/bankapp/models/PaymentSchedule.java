package com.novikov.bank_app.bankapp.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "payment_schedule")
public class PaymentSchedule {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "date")
    private Date date;

    @Column(name = "payment")
    private double payment;

    @Column(name = "loan_body")
    private double loanBody;

    @Column(name = "interest_payment")
    private double interestPayment;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    public PaymentSchedule() {
    }

    public PaymentSchedule(Date date, double payment, double loanBody, double interestPayment, Offer offer) {
        this.date = date;
        this.payment = payment;
        this.loanBody = loanBody;
        this.interestPayment = interestPayment;
        this.offer = offer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getLoanBody() {
        return loanBody;
    }

    public void setLoanBody(double loanBody) {
        this.loanBody = loanBody;
    }

    public double getInterestPayment() {
        return interestPayment;
    }

    public void setInterestPayment(double interestPayment) {
        this.interestPayment = interestPayment;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentSchedule)) return false;

        PaymentSchedule that = (PaymentSchedule) o;

        if (Double.compare(that.payment, payment) != 0) return false;
        if (Double.compare(that.loanBody, loanBody) != 0) return false;
        if (Double.compare(that.interestPayment, interestPayment) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return offer != null ? offer.equals(that.offer) : that.offer == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(payment);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(loanBody);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(interestPayment);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (offer != null ? offer.hashCode() : 0);
        return result;
    }
}
