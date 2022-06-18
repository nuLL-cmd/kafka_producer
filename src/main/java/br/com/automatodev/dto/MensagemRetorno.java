package br.com.automatodev.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MensagemRetorno implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String mensagem;
    private int statusHttp;
}
