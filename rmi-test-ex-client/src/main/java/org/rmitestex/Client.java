package org.rmitestex;

import org.rmitestex.api.AccountService;

import java.rmi.Naming;

public class Client {

    public static void main(String argsp[]){
        try {
            AccountService service = (AccountService) Naming.lookup("rmi://127.0.0.1/server");

            System.out.println(service.getAmount(1));
            service.addAmount(1, Long.valueOf(3));
            System.out.println(service.getAmount(1));

            service.addAmount(1, 7l);
            System.out.println(service.getAmount(1));

            service.getAmount(2);

            service.addAmount(2,4l);
            service.addAmount(2,6l);
            System.out.println(service.getAmount(2));

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
