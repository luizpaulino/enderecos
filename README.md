# Gerenciamento de consumo de energia
Aplicações para cadastro de Pessoas, Casas e Eletrodomésticos. 
Este sistema tem por finalidade calcular o consumo mensal de energia.

Relatório e decisões técnicas: [Documento técnico](/TECNICO.md)


Para execução da aplicaçao rodar o comando:
```
docker compose -f .\monitoramento.yml up --build
```

# Endereços [/{idUsuario}/enderecos]

### Adicionar um novo endereço (Create) [POST]

Recurso utlizado para adicionar um novo endereço para ser gerenciado pela aplicação.

+ Atributos (objeto)

    + rua (string, required)
    + numero (string, required)
    + bairro (string, required)
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
              "idEndereco": "64f5200923787143d49340d2",
              "rua": "Rua vergueiro",
              "numero": "3185",
              "bairro": "Vila Mariana",
              "cidade": "São Paulo",
              "estado": "São Paulo"
            }

### Listar os endereços [GET]
Recurso utlizado para listar os endereços de um usuário.
Se não passar nenhum filtro na query lista todos os registros.

+ QueryStrings

    + rua (string)
    + numero (string)
    + bairro (string)
    + cidade (string)
    + estado (string)

+ Responso 200 (application/json)
  + Body
  
        {
            "content": [
                {
                    "id": "64f4d779b4f14c3de0301662",
                    "rua": "Alterado rua suave",
                    "numero": "446",
                    "bairro": "Jardim Triunfo",
                    "cidade": "Guarulhos",
                    "estado": "São Paulo"
                },
                {
                    "id": "64f4d8e4b4f14c3de0301663",
                    "rua": "Rua doze de junho",
                    "numero": "446",
                    "bairro": "Jardim Triunfo",
                    "cidade": "Guarulhos",
                    "estado": "São Paulo"
                }
            ],
            "pageable": {
                "sort": {
                    "empty": true,
                    "sorted": false,
                    "unsorted": true
                },
                "offset": 0,
                "pageSize": 20,
                "pageNumber": 0,
                "unpaged": false,
                "paged": true
            },
            "last": true,
            "totalPages": 1,
            "totalElements": 2,
            "size": 20,
            "number": 0,
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "first": true,
            "numberOfElements": 2,
            "empty": false
        }

### /{idUsuario}/enderecos/{idEndereco} [PUT]

Recurso utilizado para alteração de um endereço, todos os campos abaixo podem ser alterados

+ Atributos (objeto)

    + rua (string)
    + numero (string)
    + bairro (string)
    + cidade (string)
    + estado (string)


+ Request (application/json)

    + Body

            {
              "rua": "Nova rua"
            }

+ Response 200 (application/json)

    + Body

            {
              "idEndereco": "64f5200923787143d49340d2",
              "rua": "Nova rua",
              "numero": "3185",
              "bairro": "Vila Mariana",
              "cidade": "São Paulo",
              "estado": "São Paulo"
            }

### /{idUsuario}/enderecos/{idEndereco} [DELETE]
Recurso utilizado para exclusao de um endereço.
+ Response 202 (application/json)

### /{idUsuario}/enderecos/{idEndereco}/vinculo [PUT]
Recurso utilizado para adicionar uma pessoa a um endereço
+ Atributos (objeto)

    + idPessoa (string)


+ Request (application/json)

    + Body

            {
              "idPessoa": "64f4d8e4b4f14c3de0301663"
            }


# Pessoas [/{idUsuario}/pessoas]

### Adicionar um nova pessoa (Create) [POST]

Recurso utlizado para adicionar um nova pessoa para ser gerenciado pela aplicação.

+ Atributos (objeto)

    + nome (string, required)
    + dataNascimento (Date (dd/MM/yyyy), required)
    + parentesco (string, required)
    + sexo (string, required)


+ Request (application/json)

    + Body

            {
              "nome": "Luiz",
              "sexo": "M",
              "parentesco": "Filho",
              "dataNascimento": "01/01/1999"
            }

+ Response 201 (application/json)

    + Body

            {
              "idPessoa": "64f4d8e4b4f14c3de0301663",
              "nome": "Luiz",
              "sexo": "M",
              "parentesco": "Filho",
              "dataNascimento": "01/01/1999"
            }

### Listar as pessoas [GET]
Recurso utlizado para listar os endereços de um usuário.
Se não passar nenhum filtro na query lista todos os registros.

+ QueryStrings

    + nome (string)
    + dataNascimento (Date (dd/MM/yyyy))
    + parentesco (string)
    + sexo (string)

+ Responso 200 (application/json)
    + Body

          {
              "content": [
                  {
                    "id": "64f43c1e37dbf531d4f8c336",
                    "nome": "Nova pessoa",
                    "dataNascimento": "11/10/1995",
                    "sexo": "F",
                    "parentesco": "Filha"
                  },
                  {
                      "id": "64f43c2937dbf531d4f8c337",
                      "nome": "Outra Pessoa",
                      "dataNascimento": "11/10/1995",
                      "sexo": "M",
                      "parentesco": "Filho"
                  },
                  {
                      "id": "64f4bfa4901a144e78eb731c",
                      "nome": "Natália Lemes",
                      "dataNascimento": "11/10/1995",
                      "sexo": "F",
                      "parentesco": "Esposa"
                  },
                  {
                      "id": "64f4fdb41c2fa42ead9bb11d",
                      "nome": "Luiz Paulino",
                      "dataNascimento": "11/10/1995",
                      "sexo": "M",
                      "parentesco": "Marido"
                  }
              ],
              "pageable": {
                  "sort": {
                      "empty": true,
                      "sorted": false,
                      "unsorted": true
                  },
                  "offset": 0,
                  "pageSize": 20,
                  "pageNumber": 0,
                  "unpaged": false,
                  "paged": true
              },
              "last": true,
              "totalPages": 1,
              "totalElements": 4,
              "size": 20,
              "number": 0,
              "sort": {
                  "empty": true,
                  "sorted": false,
                  "unsorted": true
              },
              "first": true,
              "numberOfElements": 2,
              "empty": false
          }

### /{idUsuario}/pessoas/{idPessoa} [PUT]

Recurso utilizado para alteração de um endereço, todos os campos abaixo podem ser alterados

+ Atributos (objeto)

    + nome (string, required)
    + dataNascimento (Date (dd/MM/yyyy), required)
    + parentesco (string, required)
    + sexo (string, required)


+ Request (application/json)

    + Body

            {
              "nome": "Luiz Paulino"
            }

+ Response 200 (application/json)

    + Body

            {
              "idPessoa": "64f4d8e4b4f14c3de0301663",
              "nome": "Luiz Paulino",
              "sexo": "M",
              "parentesco": "Filho",
              "dataNascimento": "01/01/1999"
            }

### /{idUsuario}/pessoas/{idPessoa} [DELETE]
Recurso utilizado para exclusao de uma pessoa.
+ Response 202 (application/json)

# Eletrodomesticos [{idUsuario}/eletrodomesticos]

### Adicionar um nova eletrodomestico (Create) [POST]

Recurso utlizado para adicionar um novo eletrodomestico para ser gerenciado pela aplicação.

+ Atributos (objeto)

    + nome (string, required)
    + modelo (string, required)
    + potencia (BigDecimal, required)


+ Request (application/json)

    + Body

            {
              "nome": "Air Fryer",
              "modelo": "PFR2200P",
              "potencia": 1700
            }

+ Response 201 (application/json)

    + Body

            {
              "id": "64f5200923787143d49340d2",
              "nome": "Air Fryer",
              "modelo": "PFR2200P",
              "potencia": 1700
            }

### Listar os eletrodomésticos [GET]
Recurso utlizado para listar os eletrodomésticos de um usuário.
Se não passar nenhum filtro na query lista todos os registros.

+ QueryStrings

    + nome (string)
    + modelo (string)
    + potencia (BigDecimal)


+ Responso 200 (application/json)
    + Body

          {
              "content": [
              {
                "id": "64f5200923787143d49340d2",
                "nome": "Air Fryer",
                "modelo": "Forno",
                "potencia": 10
              },
              {
                  "id": "64f5202823787143d49340d3",
                  "nome": "Microondas",
                  "modelo": "200T",
                  "potencia": 10
              }
              ],
              "pageable": {
                  "sort": {
                      "empty": true,
                      "sorted": false,
                      "unsorted": true
                  },
                  "offset": 0,
                  "pageSize": 20,
                  "pageNumber": 0,
                  "unpaged": false,
                  "paged": true
              },
              "last": true,
              "totalPages": 1,
              "totalElements": 2,
              "size": 20,
              "number": 0,
              "sort": {
                  "empty": true,
                  "sorted": false,
                  "unsorted": true
              },
              "first": true,
              "numberOfElements": 2,
              "empty": false
          }

### /{idUsuario}/eletrodomesticos/{idEletrodomestico} [PUT]

Recurso utilizado para alteração de um endereço, todos os campos abaixo podem ser alterados

+ Atributos (objeto)

    + nome (string)
    + modelo (string)
    + potencia (BigDecimal)


+ Request (application/json)

    + Body

            {
              "nome": "Luiz Paulino"
            }

+ Response 200 (application/json)

    + Body

            {
              "idPessoa": "64f4d8e4b4f14c3de0301663",
              "nome": "Luiz Paulino",
              "sexo": "M",
              "parentesco": "Filho",
              "dataNascimento": "01/01/1999"
            }

### /{idUsuario}/eletrodomesticos/{idEletrodomestico} [DELETE]
Recurso utilizado para exclusao de um eletrodomestico.
+ Response 202 (application/json)
