package org.rmitestex.api;

import java.lang.Integer;
import java.lang.Long;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccountService extends Remote {
    Long getAmount(Integer id ) throws RemoteException;
    void addAmount(Integer id, Long value) throws RemoteException;
}