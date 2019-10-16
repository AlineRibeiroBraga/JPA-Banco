package br.com.invillia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.swing.*;

public class AccountDAO {

    final EntityManager EM;

    public AccountDAO(EntityManager EM) {
        this.EM = EM;
    }

    public void insert(Account account){

        final EntityTransaction transaction = EM.getTransaction();

        transaction.begin();
        EM.persist(account);
        transaction.commit();
    }

    public void withdraw(Account account, double amount){

        double balance = account.getBalance();

        if(account.getBound() + account.getBalance() >= amount){
            balance -= amount;
            account.setBalance(balance);
            upDate(account);
        }
        else{
            JOptionPane.showMessageDialog(null,"value unavailable","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deposit(Account account, double amount){

        double balance  = account.getBalance() + amount;
        account.setBalance(balance);

        upDate(account);
    }

    public Account findbyId(long id) {

        final TypedQuery<Account> query = EM.createQuery("SELECT a FROM Account a JOIN FETCH a.person p where p.id = :id",
                    Account.class);

        query.setParameter("id", id);

        return query.getSingleResult();
   }

    public void upDate(Account account){

        final EntityTransaction transaction = EM.getTransaction();

        transaction.begin();
        EM.merge(account);
        transaction.commit();
    }

    private long validarAgency(){

        long agency;

        agency = (long) (Math.random()*99999 + 10000);
        final TypedQuery<Account> query = EM.createQuery("SELECT a FROM Account a WHERE agency_account = :agency", Account.class);

        query.setParameter("agency",agency);

        if(!query.getResultList().isEmpty()){
            validarAgency();
        }

        return agency;
    }

    public void delete(Account account) {

        final EntityTransaction transaction = EM.getTransaction();

        transaction.begin();
        EM.remove(account);
        transaction.commit();
    }
}
