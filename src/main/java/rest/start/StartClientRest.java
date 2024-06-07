package rest.start;

import rest.client.ProbeClientRest;
import rest.domain.Distanta;
import rest.domain.Probe;
import rest.services.ServiceExceptionRest;

public class StartClientRest {

    private final static ProbeClientRest probeClient = new ProbeClientRest();

    public static void main(String[] args) {
        try {
            Probe  aux = probeClient.getById(1L);

            //get by id
            show(() -> {
                System.out.println("get by id");
                System.out.println(probeClient.getById(2L));
            });

            //create
            // s = new Show("Test Rest", aux.getDate().plusDays(75), aux.getTime().plusHours(2), aux.getVenue(), aux.getTotalSeats(), aux.getSoldSeats());
            Probe p = new Probe(6L,aux.getDst(),aux.getSt());
            show(() -> {
                System.out.println("create");
                System.out.println(probeClient.create(p));
            });

            //findAll
            show(()-> {
                System.out.println("find all");
                print(probeClient.findAll());
            });

            Probe[] temp = probeClient.findAll();

            //update
            p.setId(temp[temp.length - 1].getId());
            p.setDst(Distanta.m1500);
            show(() -> {
                System.out.println("update");
                probeClient.update(p);
            });

            //findAll
            show(()-> {
                System.out.println("find all");
                print(probeClient.findAll());
            });

            //delete
            show(() -> probeClient.delete(p.getId()));

            //findAll
            show(()-> {
                System.out.println("find all");
                print(probeClient.findAll());
            });
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
    }

    private static void show(Runnable task) {
        try {
            task.run();
        } catch (ServiceExceptionRest e) {
            System.out.println("Service exception " + e);
        }
    }

    public static void print(Probe[] lst){
        for (Probe p : lst){
            System.out.println(p);
        }
    }
}
