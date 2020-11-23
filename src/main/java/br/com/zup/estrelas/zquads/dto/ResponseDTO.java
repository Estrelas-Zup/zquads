package br.com.zup.estrelas.zquads.dto;

public class ResponseDTO {

    private String response;

    public ResponseDTO(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ResponseDTO other = (ResponseDTO) obj;
        if (response == null) {
            if (other.response != null)
                return false;
        } else if (!response.equals(other.response))
            return false;
        return true;
    }
    
}
