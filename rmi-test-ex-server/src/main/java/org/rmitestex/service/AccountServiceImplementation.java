package org.rmitestex.service;

import org.rmitestex.api.AccountService;
import org.rmitestex.model.Account;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

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
    public void addAmount(Integer id, Long amount) throws RemoteException {

        Account acc = getAccountById(id);

        if (acc==null) {
           addAccount(id,amount);
        } else {
            Long am = acc.getAmmount() + amount;
            em.getTransaction().begin();
            acc.setAmmount(am);
            em.getTransaction().commit();
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