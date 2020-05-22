# Aquarium
Sistema de controle de aquários


##### Code Style Formatter

Na raiz do projeto está disponibilizado o arquivo `eclipse-java-google-style.xml`. Arquivo responsável por fazer a formatação do código segundo o padrão adotado pelo Google.
- Google Style Guide: https://github.com/google/styleguide
- Configuração: Project properties -> Java Code Style -> Formatter -> Import 
  

##### Logging - LOGBACK 

Logging padrão do springboot. Para ajustar configurações específicas do log, acessar application.properties. 

```java
logger.trace("A TRACE Message");
logger.debug("A DEBUG Message");
logger.info("An INFO Message");
logger.warn("A WARN Message");
logger.error("An ERROR Message");
```

##### Documentação API - SWAGGER

Obs: Aplicação deve estar em execução.

- link: http://localhost:8080/swagger-ui.html 


##### Spring-Data-Jpa
- Uso de transação: `@Transactional`
  - Por padrão, na ocorrência de um **RuntimeException** as alterações realizadas na transação **sofrem Rollback**, já as checked **Exceptions** não **executarão rollback**
  - Utilizando o **rollbackFor** `@Transactional(rollbackFor = Exception.class)`, será forçado o rollback para qualquer exceção lançada, conforme anotação.  
