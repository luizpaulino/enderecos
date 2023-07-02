# Endereços [/enderecos]

### Adicionar um novo endereço (Create) [POST]

Recurso utlizado para adicionar um novo endereço para ser gerenciado pela aplicação.

+ Atributos (objeto)

    + rua (string, required)
    + numero (string, required)
    + bairro (array, required)
    + cidade (string, required)
    + estado (string, required)
   

+ Request (application/json)

    + Body

            {
              "rua": "Rua vergueiro",
              "numero": "3185",
              "bairro": "Vila Mariana",
              "cidade": "São Paulo",
              "estado": "São Paulo"
            }

+ Response 201 (application/json)

    + Body

            {
              "rua": "Rua vergueiro",
              "numero": "3185",
              "bairro": "Vila Mariana",
              "cidade": "São Paulo",
              "estado": "São Paulo"
            }