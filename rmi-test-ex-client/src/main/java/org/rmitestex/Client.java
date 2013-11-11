package org.rmitestex;

import org.rmitestex.api.AccountService;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class Client {

    public static void main(String argsp[]) {


        Integer[] ids = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};


        try {


            final AccountService service = (AccountService) Naming.lookup("rmi://127.0.0.1/server");

            for (final Integer i : ids) {

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println("Starting add thread on id:"+i);

                        Long am = (long) (Math.random() * 1000);
                        try {
                            service.addAmount(i, am);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                        System.out.println("Finishing add thread on id:"+i);

                    }
                });

                thread.start();

            }

            for (final Integer i :ids){
                Long amount = service.getAmount(i);

                System.out.println(String.format("Account's %d ammount %d ",i,amount));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
