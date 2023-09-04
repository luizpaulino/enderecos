# Relatório técnico

A aplicação foi feita em Java utilizando o framework Spring, que é o principal frame do universo da JVM hoje, 
responsável por facilitar a criaçao de aplicaçoes stand alone.

Decidi usar o lombok, que é uma lib que se conecta a IDE pra diminuir a quantidade de código escrito.

Para validação dos dados foi utilizado as anotações do jakarta.

Para definição do mapeamento json de entrada e saída da aplicação foi utilizado o jackson.

No mapeamento de um objeto para outro, como form para dominio e dominio para view decidi utilizar o ModelMapper,
por ter uma sintaxe simples e enxuta.

O grande desafio pra mim nesse momento foi decidir e definir como eu achava que seria a 
melhor forma de controlar os erros, eu decidi implementar o padrão de controller adivice por entender que o código 
ficaria mais elegante e de forma mais centralizada para provaveis evoluções.

# Fase 2

A base de dados escolhida foi o mongoDB por conta da facilidade na modelagem de dados, na performance de consulta das 
informações na transações de leitura no banco.