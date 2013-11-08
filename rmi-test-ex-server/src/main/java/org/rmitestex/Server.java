package org.rmitestex;

import java.lang.Exception;
import java.lang.String;
import java.lang.System;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.rmitestex.service.*;
import org.rmitestex.service.AccountServiceImplementation;

public class Server{

    public static void main(String[] args) {

        try{
            System.setSecurityManager(new RMISecurityManager());
            AccountServiceImplementation service = new AccountServiceImplementation();
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Naming.rebind("server",service);
            System.out.println("Service registered");
            System.out.println("Now service waiting request");
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}