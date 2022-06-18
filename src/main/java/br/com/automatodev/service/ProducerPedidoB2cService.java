package br.com.automatodev.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.automatodev.exception.KafkaHookException;
import br.com.automatodev.pedido.b2c.dto.vtex.OrderHook;
import br.com.automatodev.util.UtilLog;

@Service
public class ProducerPedidoB2cService {

    @Value("${kafka.topicIntegradorPedidoB2c}")
    private String topico;

    @Autowired private KafkaTemplate<String, OrderHook> template;

    public void publicTopico(OrderHook orderHook) {

        try {

            UtilLog.getLog()
                    .info("### PRODUZINDO MMENSAGEM PARA O PEDIDO B2C:  " + orderHook.getOrderId() + " ###");

            template.setCloseTimeout(Duration.ofSeconds(2));
            template.send(topico, 0, orderHook.getOrderId(), orderHook);

            
            UtilLog.getLog().info(new Gson().toJson(orderHook));


        } catch (Exception e) {

            UtilLog.getLog().error(e.getMessage(), e);
            throw new KafkaHookException(e.getMessage());
        }
    }
}
