package com.novikov.bank_app.bankapp.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private BankClient bankClient;

    @ManyToOne
    @JoinColumn(name = "credits_id")
    private Credit credit;

    @Positive
    @NotNull(message = "Введите сумму кредита")
    @Column(name = "credit_amount")
    private long creditAmount;

    @Positive
    @NotNull(message = "Введите продолжительность кредита в месяцах")
    @Column(name = "loan_duration")
    private int loanDuration;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private List<PaymentSchedule> paymentScheduleList;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private List<Bank> banks;

    public Offer() {
    }

    public Offer(BankClient bankClient, Credit credit, long creditAmount, int loanDuration) {
        this.bankClient = bankClient;
        this.credit = credit;
        this.creditAmount = creditAmount;
        this.loanDuration = loanDuration;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BankClient getBankClient() {
        return bankClient;
    }

    public void setBankClient(BankClient bankClient) {
        this.bankClient = bankClient;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public long getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(long creditAmount) {
        this.creditAmount = creditAmount;
    }

    public int getLoanDuration() {
        return loanDuration;
    }

    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration;
    }

    public List<PaymentSchedule> getPaymentScheduleList() {
        return paymentScheduleList;
    }

    public void setPaymentScheduleList(List<PaymentSchedule> paymentScheduleList) {
        this.paymentScheduleList = paymentScheduleList;
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
        if (!(o instanceof Offer)) return false;

        Offer offer = (Offer) o;

        if (creditAmount != offer.creditAmount) return false;
        if (loanDuration != offer.loanDuration) return false;
        if (id != null ? !id.equals(offer.id) : offer.id != null) return false;
        if (bankClient != null ? !bankClient.equals(offer.bankClient) : offer.bankClient != null) return false;
        if (credit != null ? !credit.equals(offer.credit) : offer.credit != null) return false;
        if (paymentScheduleList != null ? !paymentScheduleList.equals(offer.paymentScheduleList) : offer.paymentScheduleList != null)
            return false;
        return banks != null ? banks.equals(offer.banks) : offer.banks == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bankClient != null ? bankClient.hashCode() : 0);
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        result = 31 * result + (int) (creditAmount ^ (creditAmount >>> 32));
        result = 31 * result + loanDuration;
        result = 31 * result + (paymentScheduleList != null ? paymentScheduleList.hashCode() : 0);
        result = 31 * result + (banks != null ? banks.hashCode() : 0);
        return result;
    }
}
