package br.com.zup.estrelas.zquads.security;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final String PLEASE_LOGIN = "Please log-in to access this resource";
    private static final long serialVersionUID = -5325187178862034401L;

    @Autowired
    ObjectMapper objectMapper;
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(new ResponseDTO(PLEASE_LOGIN)));
    }
}