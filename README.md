# Aquarium
Sistema de controle de aquários


##### Code Style Formatter / CleanUp
É utilizado um padrão de formatação e um padrão de limpeza (clean-up) para todas classes do sistema.

- Antes de fazer o commit de qualquer classe, deve ser realizado o clean-up, seguido pelo format. 
  - ALT+SHIFT+S: Clean-Up / CTRL+SHIFT+F: Formmater
- Na raiz do projeto está disponibilizado ambos os arquivos XML de configuração:
  - Arquivo `padrao-eclipse-java-google-style.xml`: responsável por fazer a formatação do código segundo o padrão adotado pelo Google.
    - Google Style Guide: https://github.com/google/styleguide
    - Configuração: Project properties -> Java Code Style -> Formatter -> Import 
  - Arquivo `padrao-eclipse-java-cleanup-style.xml`: responsável por fazer a limpeza do código.
    - Configuração: Project properties -> Java Code Style -> Clean Up -> Import    
  

##### Logging - LOGBACK 
Logging padrão do springboot. Para ajustar configurações específicas do log, acessar application.properties. 

```java
logger.trace("A TRACE Message");
logger.debug("A DEBUG Message");
logger.info("An INFO Message");
logger.warn("A WARN Message");
logger.error("An ERROR Message");
```

##### Versionamento
- Adotado o versiomaneto semântico como padrão de versionamento do sistema. 
- https://semver.org/lang/pt-BR/
  

##### Documentação API - SWAGGER
Obs: Aplicação deve estar em execução.

- link: http://localhost:8080/swagger-ui.html 


##### Spring-Data-Jpa
- Uso de transação: `@Transactional`
  - Por padrão, na ocorrência de um **RuntimeException** as alterações realizadas na transação **sofrem Rollback**, já as checked **Exceptions** não **executarão rollback**
  - Utilizando o **rollbackFor** `@Transactional(rollbackFor = Exception.class)`, será forçado o rollback para qualquer exceção lançada, conforme anotação.
  
  
##### Arquitetura Sistema
- Todas respostas ao cliente deverá ser feita através da interface `com.paulorpc.aquarium.api.response.Response`. A utilização deste response força uma padronização dos objetos retornados, lista de erros e exceptions. 
- Camadas Principais do sitema (Layers):
  - Controller -> Service -> Repository
  - DTOs servem para trafegar dados de fora do sistema para dentro e vice versa apenas, servindo como uma camada de abstração e segurança da implementação do sistema
    - Não é possível enviar um DTO para camadas internas, pois seria necessário utizar os conversores da camada controller, por exemplo, com acesso `service -> controler` caracterizando uma violação arquitetural.
      

##### Gerenciamento de Exceções
`com.paulorpc.aquarium.api.exceptions.ApiExceptionHandler`

- Utilizando controller advice para gerenciamento de exceções o qual formatan um response padronizado para retornar ao client. 
- O gerenciamento de exceção está definido para controlar todas exceções lançadas em um controller, o qual não foi tratado com um try/catch.