package br.com.invillia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("---------------------------------");

        PersonDAO personDAO = new PersonDAO(entityManager);
        AccountDAO accountDAO = new AccountDAO(entityManager);

        Person p1 = personDAO.findByCPF("446.877.998-50");
        Account c1 = accountDAO.findbyId(p1.getId());

        //accountDAO.deposit(c1,1500);
        //accountDAO.withdraw(c1,100);
        //accountDAO.delete(c1);
    }
}
