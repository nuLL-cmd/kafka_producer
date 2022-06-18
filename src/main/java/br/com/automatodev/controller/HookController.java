package br.com.automatodev.controller;

import java.util.Arrays;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.automatodev.b2b.dto.vtex.OrderHook;
import br.com.automatodev.dto.MensagemRetorno;
import br.com.automatodev.enuns.EnumContaVtex;
import br.com.automatodev.exception.KafkaHookException;
import br.com.automatodev.service.ProducerPedidoB2bService;
import br.com.automatodev.service.ProducerPedidoB2cService;
import br.com.automatodev.util.UtilLog;

@RestController
public class HookController {

    @Autowired private ProducerPedidoB2bService producerB2bService;

    @Autowired private ProducerPedidoB2cService producerB2cService;

    @Autowired private ModelMapper mapper;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(
            value = "/hook-pedidos-vtex",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> hookPedidosVtex(
            @RequestBody OrderHook orderHook, HttpServletRequest request) {

      
        try {

            MensagemRetorno mensagemRetorno = new MensagemRetorno();

            if (Objects.isNull(orderHook.getOrigin())) {

                mensagemRetorno.setMensagem(
                        "Não foi possível obter o nome da conta. Aguardando novos WebHooks.");
                mensagemRetorno.setStatusHttp(HttpStatus.OK.value());

                UtilLog.getLog().info(new Gson().toJson(mensagemRetorno));

                return ResponseEntity.status(mensagemRetorno.getStatusHttp())
                        .body(Arrays.asList(mensagemRetorno));
            }

            if (orderHook.getOrigin().getAccount().equals(EnumContaVtex.DISTRIBUIDOR.getCodigo())) {

                producerB2bService.publicTopico(orderHook);

            } else if (orderHook.getOrigin().getAccount().equals(EnumContaVtex.VAREJO.getCodigo())) {

                br.com.automatodev.pedido.b2c.dto.vtex.OrderHook orderHookB2c =
                        mapper.map(orderHook, br.com.automatodev.pedido.b2c.dto.vtex.OrderHook.class);
                producerB2cService.publicTopico(orderHookB2c);

            } else {

                mensagemRetorno.setMensagem(
                        "Não foi possivel identificar o nome da conta vtex. Aguardando novos WebHooks.");
                mensagemRetorno.setStatusHttp(HttpStatus.OK.value());

                return ResponseEntity.status(mensagemRetorno.getStatusHttp())
                        .body(Arrays.asList(mensagemRetorno));
            }

            mensagemRetorno.setMensagem(
                    "Recebido cabeçalho do pedido e iniciado a produção para o Kafka.");
            mensagemRetorno.setStatusHttp(HttpStatus.OK.value());

            return ResponseEntity.status(mensagemRetorno.getStatusHttp())
                    .body(Arrays.asList(mensagemRetorno));

        } catch (KafkaHookException e) {

            UtilLog.getLog().error(e.getMessage(), e);
            throw new KafkaHookException(e.getMessage(), e.getStatus());
        }
    }
}
