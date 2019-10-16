package br.com.invillia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class PersonDAO {

    final EntityManager EM;

    public PersonDAO(EntityManager EM) {
        this.EM = EM;
    }

    public void insert(Person person){

        final EntityTransaction transaction = EM.getTransaction();

        transaction.begin();
        EM.persist(person);
        transaction.commit();
    }

    public Person findByCPF(String CPF){

        final TypedQuery<Person> query = EM.createQuery("SELECT p FROM Person p WHERE cpf = :cpf", Person.class);

        query.setParameter("cpf",CPF);
        return query.getSingleResult();
    }
}

