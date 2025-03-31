# Projeto VollMED - JAVA API REST em SPRING BOOT
## Segue instruções para disparo das requisições:

* Após importar o projeto na sua IDE de preferência, rode o projeto.
* Garantindo que tenha o acesso ao MySQL e com o DataBase VollMed Criado.
* Acesse : https://localhost:8080/
* Com o projeto rodando de forma local já é possivel disparar requisições.
* Este disparo de requisições pode ser realizado de duas maneiras:
  - 1º Atravez do PostMan ou Insomnia com os arquivos abaixo:
    
 **Json - CADASTRAR MÉDICO** 
```
POST - localhost:8080/medicos
 {
	"nome": "Pedro Alcantara",
    "email": "pedro.alcantara@vollmed.com",
    "telefone":"(47)5595-3645",
    "especialidade": "DERMATOLOGIA",
    "crm":"123525",
    "endereco": {
        "logradouro": "Rua 01",
        "numero": "1",
        "complemento": "complemento",
        "bairro": "Bairro",
        "cep": "12345-123",
        "cidade": "Itu",
        "uf": "SP"
    }
}

```
 **Json - CADASTRAR PACIENTE** 
```
POST - localhost:8080/pacientes
{
	"nome": "Joana Santos",
    "email": "joana.santos@hotmail.com",
    "telefone": "(47) 9999-1798",
    "cpf":"012.345.350-90",
    "endereco": {
        "logradouro": "Rua 0045",
        "bairro": "Bairro1",
        "cep": "12345-124",
        "cidade": "Ita",
        "uf": "SP",
        "numero": "112",
        "complemento": "complemento"
    }
}
```
 **Json - ATUALIZAR MÉDICO** 
```
PUT - localhost:8080/medicos
{
    "id":3,
	"nome": "Ana Luzia Jardim",
    "telefone":"(47)5555-3625",
     "endereco": {
        "logradouro": "Rua 07"
    }
}
```

 **Json - ATUALIZAR PACIENTE** 
```
PUT - localhost:8080/pacientes
{
    "id": 3,
    "nome":"Maria Clara dos Anjos",
    "endereco":{
        "logradouro":"R:24"
    }
}
```

 **Json - EFETUAR LOGIN** 
```
GET - localhost:8080/login
{
    "login": "ana.clara@voll.med",
    "senha": "123456"
}
```

 **Json - AGENDAR CONSULTA** 
```
POST - localhost:8080/consultas
{
"idPaciente": 2,
"data": "2025-03-25T09:00",
"idMedico": 9
}
```

 **Json - CANCELAR CONSULTA** 
```
DEL - localhost:8080/consultas
{
"idConsulta": 12,
"motivo": "PACIENTE_DESISTIU",
"dataCancelamento":"2025-03-15T08:50"
}
```

**Json - EXCLUIR MEDICO(DESATIVAR)**
> DEL - localhost:8080/medicos/${id_do_medico_a_ser_desativado}


**Json - DETALHAR MEDICO**
> GET - localhost:8080/medicos/${id_do_medico_a_ser_detalhado}


**Json - LISTAR MEDICOS**
> GET - localhost:8080/medicos


**Json - EXCLUIR PACIENTESO(DESATIVAR)**
> DEL - localhost:8080/pacientes/${id_do_paciente_a_ser_desativado}


**Json - DETALHAR PACIENTE**
> GET - localhost:8080/pacientes/${id_do_paciente_a_ser_detalhado}


**Json - LISTAR PACIENTES**
> GET - localhost:8080/pacientes


- 2º Outra forma de disparar as requisições é atraves do swagger do SPRING DOC acessando
- http://localhost:8080/swagger-ui/index.html
