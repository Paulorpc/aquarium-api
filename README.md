# AQUARIUM
Sistema de gestão de aquários


### CODE STYLE FORMATTER / CLEANUP
É utilizado um padrão de formatação e um padrão de limpeza (clean-up). Ambos os arquivos estão na raiz do projeto. 
- Clean-up: padrao-eclipse-java-cleanup-style.xml
- Formatação (Google Style): `mvn com.coveo:fmt-maven-plugin:format` 
  

### LOGGING - LOGBACK 
Logging padrão do springboot. Para ajustar configurações específicas do log, acessar application.properties. 


### VERSIONAMENTO
- Adotado o versiomaneto semântico como padrão de versionamento do sistema. 
- https://semver.org/lang/pt-BR/
  

### DOCUMENTAÇÃO API - SWAGGER
- link: http://localhost:8080/swagger-ui.html 


### SPRING-DATA-JPA
- Uso de transação: `@Transactional`
  - Por padrão, na ocorrência de um **RuntimeException** as alterações realizadas na transação **sofrem Rollback**, já as checked **Exceptions** não **executarão rollback**
  - Utilizando o **rollbackFor** `@Transactional(rollbackFor = Exception.class)`, será forçado o rollback para qualquer exceção lançada.
  
  
### ARQUITETURA SISTEMA
- Todas respostas ao cliente deverá ser feita através da interface `Response`. A utilização do response força uma padronização dos objetos retornados, com uma lista de erros, exceptions, dados de retorno e uri. 
- Camadas Principais do sitema (Layers):
  - `Controllers` -> `Services` -> `Repositories`
  - DTOs servem para trafegar dados de fora do sistema para dentro e vice versa apenas, servindo como uma camada de abstração e segurança da implementação do sistema.
- Gerenciamento de Exceções através de `ControllerAdvice`.
- Uso de Mappers (mapstruct) para conversão de objetos, especialmente entre `DTO <-> Entidade`


### GERENCIAMENTO DE EXCEÇÕES
- Utilizando `ControllerAdvice` na classe `ApiExceptionHandler` para gerenciamento de exceções. 
- Sua responsabilidade é gerar um response padronizado para retornar ao cliente ao ser lançado alguma exceção no sistema que não tenha sido tratada.


#### TESTES 
Ao retornar o conteúdo do response (`MockMvcResultMatcher.content()`) o charset default retornado não é mais o UTF-8. Portanto, é necessário adicionar o `.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)`. Apesar de depreciado, a opção que deveria substituir esse tipo não resolve o problema, então por hora será usado este.
- Por defaul os testes de integração (final IT) não são executados, devido a sua menor performance. Para executá-los, basta rodar o comando abaixo.
```shell
mvn test # testes unitários
mvn failsafe:integration-test # Testes de Integração
```


#### MAPSTRUCT
MapStruct ajuda na criação de classes para conversão entre objetos de forma automatizada. É necessário adicionar seu processor no POM para gerar as classes durante execução do maven. 
https://mapstruct.org/documentation/stable/reference/html/#basic-mappings