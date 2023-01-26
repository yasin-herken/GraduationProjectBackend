package graduationproject.backend.Exception.controller;

public class ResourceAlreadyExists extends Exception {
    private static final long serialVersionUID = 1L;

    public ResourceAlreadyExists(String msg) {
        super(msg);
    }
}
