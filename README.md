# kafka-producer
 Java project producing messaging using Kafka
 
# PROJETO KAFKA-PRODUCER<br>
Documentação auxiliar para entender como o projeto funciona e para qual fim deve ser utilizado:

>* O repositório oficial do projeto pode ser encontrado em<br><br>
> <https://github.com/nuLL-cmd/kafka-producer.git>


# Iniciando

### Observações da aplicação
>* Java versão 11.
>* URL's de acesso ao gera-log e broker do kafka ja estão configurados nos perfis de execuções, e podem ser
alterados de acordo com a necessidade.

### Projeto
O projeto kafka-producer foi desenvolvido para ser um sevidor de mensageria, ou seja, N aplicações que precisarem trabalhar de forma assincrona, podem enviar seus dados para o kafka, onde este ira produzir e armazenar esses dados distribuindo-os em ***fila*** para aplicações ***consumidoras***

As aplicações consumidoras a principio podem ser projetos que estão rodando atualmente, como ***integradores do e-commerce***, ***gera-log***, e qualquer um que precise processar dados de forma assincrona sem precisar informar resposta para o remetente, provendo assim agilidade nas respostas, embaralhamento nas resqusições, travamentos na aplicação, erros de timeout por parte do remetente, entre outros beneficios.

Alem dos beneficios citados acima, o kafka garante a integridade dos dados por parte dos ***consumidores***, pois se um dos consumidores estiver indisponivel, o kafka armazena os dados em disco, disponibilizando assim para este consumidor quando o mesmo estiver disponível, além de trabalhar com balanceamento de carga entre multiplas intâncias de um determinado consumidor e garantir que não haverá perda dos dados por parte do proprio kafka, ja que é possivel trabalhar com clusters contendo mais de um broker kafka.

# Considerações
Projeto criado para produzir mensagens para um consumidor do tipo e-commerce ficticion náo incluso nesse projeto.<br>
Obrigado por ler!
