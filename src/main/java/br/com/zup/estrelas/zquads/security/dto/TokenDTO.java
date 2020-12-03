package br.com.zup.estrelas.zquads.security.dto;

public class TokenDTO {

    private String jwtToken;

    public TokenDTO(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
