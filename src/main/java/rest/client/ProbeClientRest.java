package rest.client;
import rest.domain.Probe;
import rest.services.ServiceExceptionRest;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class ProbeClientRest {

    public static final String URL = "http://localhost:8080/inscrieri/probe";
    private final RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception ex) {
            throw new ServiceExceptionRest(ex);
        }
    }

    public Probe[] findAll() {
        return execute(() -> restTemplate.getForObject(URL, Probe[].class));
    }

    public Probe getById(Long id) {
        return execute(() -> restTemplate.getForObject(String.format("%s/%s", URL, id.toString()), Probe.class));
    }

    public Probe create(Probe show) {
        return execute(() -> restTemplate.postForObject(URL, show, Probe.class));
    }

    public void update(Probe show) {
        execute(() -> {
            restTemplate.put(String.format("%s/%s", URL, show.getId()), show);
            return null;
        });
    }

    public void delete(Long id) {
        execute(() -> {
            restTemplate.delete(String.format("%s/%s", URL, id));
            return null;
        });
    }
}

