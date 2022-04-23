package com.novikov.bank_app.bankapp.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client")
public class BankClient {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Length(min = 2, max = 30, message = "Имя должно быть не меньше 2-х символов и не более 30-ти")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 30, message = "Отчество должно быть не меньше 2-х символов и не более 30-ти")
    @Column(name = "middle_name")
    private String middleName;

    @NotBlank
    @Length(min = 2, max = 30, message = "Фамилия должна быть не меньше 2-х символов и не более 30-ти")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Введите номер телефона")
    @Positive
    @Column(name = "phone_number")
    private long phoneNumber;

    @NotBlank(message = "Введите электронную почту")
    @Email
    @Length(min = 2, max = 255)
    @Column(name = "email")
    private String email;

    @NotNull(message = "Введите номер паспорта")
    @Positive
    @Column(name = "passport_number")
    private long passportNumber;

    @OneToMany(mappedBy = "bankClient", cascade = CascadeType.ALL)
    private List<Offer> creditOfferList;

    @OneToMany(mappedBy = "bankClient", cascade = CascadeType.ALL)
    private List<Bank> banks;

    public BankClient() {
    }

    public BankClient(String firstName, String middleName, String lastName, long phoneNumber, String email, long passportNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passportNumber = passportNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(long passportNumber) {
        this.passportNumber = passportNumber;
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
        if (!(o instanceof BankClient)) return false;

        BankClient that = (BankClient) o;

        if (phoneNumber != that.phoneNumber) return false;
        if (passportNumber != that.passportNumber) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (creditOfferList != null ? !creditOfferList.equals(that.creditOfferList) : that.creditOfferList != null)
            return false;
        return banks != null ? banks.equals(that.banks) : that.banks == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (int) (phoneNumber ^ (phoneNumber >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) (passportNumber ^ (passportNumber >>> 32));
        result = 31 * result + (creditOfferList != null ? creditOfferList.hashCode() : 0);
        result = 31 * result + (banks != null ? banks.hashCode() : 0);
        return result;
    }
}
