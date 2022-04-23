package com.novikov.bank_app.bankapp.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "credits")
public class Credit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Length(min = 2, max = 30, message = "Название должно быть не меньше 2-х символов и не более 30-ти")
    @Column(name = "credit_name")
    private String creditName;

    @NotNull(message = "Введите кредитный лимит")
    @Positive
    @Column(name = "credit_limit")
    private long creditLimit;

    @NotNull(message = "Введите процентную ставку")
    @Positive
    @Column(name = "interest_rate")
    private double interestRate;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Offer> creditOfferList;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Bank> banks;

    public Credit() {
    }

    public Credit(String creditName, long creditLimit, double interestRate) {
        this.creditName = creditName;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public long getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(long creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public List<Offer> getCreditOfferList() {
        return creditOfferList;
    }

    public void setCreditOfferList(List<Offer> creditOfferList) {
        this.creditOfferList = creditOfferList;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credit)) return false;

        Credit credit = (Credit) o;

        if (creditLimit != credit.creditLimit) return false;
        if (Double.compare(credit.interestRate, interestRate) != 0) return false;
        if (id != null ? !id.equals(credit.id) : credit.id != null) return false;
        if (creditName != null ? !creditName.equals(credit.creditName) : credit.creditName != null) return false;
        if (creditOfferList != null ? !creditOfferList.equals(credit.creditOfferList) : credit.creditOfferList != null)
            return false;
        return banks != null ? banks.equals(credit.banks) : credit.banks == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (creditName != null ? creditName.hashCode() : 0);
        result = 31 * result + (int) (creditLimit ^ (creditLimit >>> 32));
        temp = Double.doubleToLongBits(interestRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (creditOfferList != null ? creditOfferList.hashCode() : 0);
        result = 31 * result + (banks != null ? banks.hashCode() : 0);
        return result;
    }
}
