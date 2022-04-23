package com.novikov.bank_app.bankapp.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "bank")
public class Bank {

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

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    public Bank() {
    }

    public Bank(BankClient bankClient, Credit credit, Offer offer) {
        this.bankClient = bankClient;
        this.credit = credit;
        this.offer = offer;
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

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bank)) return false;

        Bank bank = (Bank) o;

        if (id != null ? !id.equals(bank.id) : bank.id != null) return false;
        if (bankClient != null ? !bankClient.equals(bank.bankClient) : bank.bankClient != null) return false;
        if (credit != null ? !credit.equals(bank.credit) : bank.credit != null) return false;
        return offer != null ? offer.equals(bank.offer) : bank.offer == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bankClient != null ? bankClient.hashCode() : 0);
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        result = 31 * result + (offer != null ? offer.hashCode() : 0);
        return result;
    }
}
