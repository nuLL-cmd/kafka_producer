package br.com.automatodev.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ExceptionDto implements Serializable {

    public static final long serialVersionUID = 1L;

    private String mensagem;

    private Integer status;

    private Long timestamp;

    private List<CamposErro> camposErro;

    private List<String> camposValidacao;
}
