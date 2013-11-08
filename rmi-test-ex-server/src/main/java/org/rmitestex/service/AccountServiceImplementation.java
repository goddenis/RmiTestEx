package org.rmitestex.service;

import org.rmitestex.api.AccountService;

import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class AccountServiceImplementation extends UnicastRemoteObject implements AccountService {

    private Map<Integer, Long> accounts;

    public AccountServiceImplementation() throws RemoteException {
        super();
        accounts = new HashMap<Integer, Long>();
    }

    @Override
    public Long getAmount(Integer id) throws RemoteException {
        return accounts.get(id) == null ? 0 : accounts.get(id);
    }

    @Override
    public void addAmount(Integer id, Long amount) throws RemoteException {
        if (!accounts.containsKey(id)){
            accounts.put(id,amount);
        } else {
            Long am = accounts.get(id) + amount;
            accounts.put(id,am);
        }
    }
}