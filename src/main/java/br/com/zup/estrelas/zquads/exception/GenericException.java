package br.com.zup.estrelas.zquads.exception;

public class GenericException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public GenericException(String response) {
        super(response);
    }

}
