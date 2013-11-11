package org.rmitestex.service;

import org.rmitestex.api.AccountService;
import org.rmitestex.model.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AccountServiceImplementation extends UnicastRemoteObject implements AccountService {


    private EntityManager em;

    public AccountServiceImplementation() throws RemoteException {
        super();

        em = Persistence.createEntityManagerFactory("org.rmitestex.model").createEntityManager();
    }

    @Override
    public Long getAmount(Integer id) throws RemoteException {

        Account acc = getAccountById(id);

        return acc == null ? 0 : acc.getAmmount();
    }

    @Override
    public synchronized void addAmount(Integer id, Long amount) throws RemoteException {

        long delay = (long) (Math.random()*1000);

        System.out.println("waiting for "+ delay);

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Account acc = getAccountById(id);

        if (acc==null) {
           addAccount(id,amount);
        } else {
            Long am = acc.getAmmount() + amount;
            EntityTransaction tr = em.getTransaction();
            tr.begin();
            System.out.println(tr.toString());
            acc.setAmmount(am);
            tr.commit();
        }
    }

    private void addAccount(Integer id, Long amount ){
        em.getTransaction().begin();
        em.persist(new Account(id,amount));
        em.getTransaction().commit();
    }

    private Account getAccountById(Integer id) {
        System.out.println("queried account "+ id);
        return em.find(Account.class,id);

    }
}