# Gerenciamento de consumo de energia
Aplicações para cadastro de Pessoas, Casas e Eletrodomésticos. 
Este sistema tem por finalidade calcular o consumo mensal de energia.

Relatório e decisões técnicas: [Documento técnico](/TECNICO.md)

# Endereços [/enderecos]

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
              "rua": "Rua vergueiro",
              "numero": "3185",
              "bairro": "Vila Mariana",
              "cidade": "São Paulo",
              "estado": "São Paulo"
            }

# Pessoas [/pessoas]

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
              "nome": "Luiz",
              "sexo": "M",
              "parentesco": "Filho",
              "dataNascimento": "01/01/1999"
            }


# Eletrodomesticos [/eletrodomesticos]

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
              "nome": "Air Fryer",
              "modelo": "PFR2200P",
              "potencia": 1700
            }