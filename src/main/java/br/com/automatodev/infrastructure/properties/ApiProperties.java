package br.com.automatodev.infrastructure.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiProperties {

    private String urlProjeto;

    private String hookPedidos;
}
