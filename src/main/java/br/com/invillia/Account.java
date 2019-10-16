package br.com.invillia;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name= "balance", nullable = false)
    private double balance; //saldo

    @Column(name ="bound", nullable = false)
    private int bound; //limite

    @Column(name = "agency_account", nullable = false, unique = true)
    private long agency;

    @ManyToOne(fetch= FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "id_person", nullable = false)
    private Person person;

    public Account() {
    }

    public Account(Person person, long agency) {
        this.person = person;
        this.bound = 500;
        this.balance = 0;
        this.agency = agency;
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getBound() {
        return bound;
    }

    public Person getPerson() {
        return person;
    }

    public long getAgency() {
        return agency;
    }

    public void setAgency(long agency) {
        this.agency = agency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", bound=" + bound +
                ", agency=" + agency +
                ", person=" + person +
                '}';
    }
}
