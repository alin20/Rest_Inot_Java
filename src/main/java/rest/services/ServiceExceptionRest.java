package rest.services;

public class ServiceExceptionRest extends RuntimeException{
    public ServiceExceptionRest(Exception ex) {
            super(ex);
        }

    public ServiceExceptionRest(String message) {
            super(message);
        }
}
