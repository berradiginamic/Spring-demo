package fr.diginamic.spring.demo.daos;

public class DepartementNotFoundException extends RuntimeException {
    public DepartementNotFoundException() {
        super();
    }

    public DepartementNotFoundException(String message) {
        super(message);
    }
}