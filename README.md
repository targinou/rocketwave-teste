# rocketwave-teste


* [Informações gerais](#informações-geral)
* [Tecnologias](#tecnologias)
* [Rodando o projeto](#rodando-o-projeto)


## Informações gerais
Desenvolvimento de uma API REST para um sistema de pedidos de uma loja, utilizando o framework Spring Boot na linguagem Java.
	
##  Tecnologias
Este projeto é criado com:
* Java 17
* PostgreSql 14
* Spring boot 2.7.3
	
## Rodando o projeto
* Faça git clone do projeto no repositório (git clone https://github.com/targinou/rocketwave-teste.git)

* Importe como projeto Maven na sua IDE de preferência. Nesse momento o projeto irá baixar todas suas dependencias de acordo com o que está definido no pom.

* Crie o banco de dados de acordo como está definido o datasource no arquivo application.properties. Você pode criar localmente o banco de dados postgreSQL no seu SGBD de preferencia.

* Para garantir que está tudo certo com o projeto montado na sua IDE rode: maven clean; maven instal;

* Rode o projeto pela primeira vez, a classe main é RocketWaveTesteApplication.java, se tudo estiver certo serão criadas as tabelas no banco de dados e você irá conseguir acessar a aplicação em http://localhost:8082/swagger-ui/index.html#/ e verificar a documentação;

