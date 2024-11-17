# Digio Challenge

Este � o projeto Digio Challenge, desenvolvido como parte do desafio de backend. A aplica��o utiliza o Spring Boot e Swagger para fornecer uma API de compras.
Aplica��o dockerizada deve ser rodada usando docker pois usa solu��o em cache tamb�m.

## Pr�-requisitos

Antes de rodar o projeto, certifique-se de que voc� tenha os seguintes itens instalados:

- **Java 19 ou superior** (Recomendado: Java 21)
- **Maven** (para compilar o projeto)
- **Redis** (Redis para armazenar dados tempor�rios, deve rodar na maquina ou docker a porta 6379 
- **Docker** preferencia docker desktop

## Redis o porque da escolha dele!

	Foi feita a escolha do Redis para que os dados das urls sejam armazenados em cache. 
	
	Obs: Isso evita que se fa�a acesso v�rias vezes as urls json enquanto em testes permitindo que fiquem em cache, atribuindo maior velacidade as opera��es e nemos requisi��es as urls propostas sendo que neste caso ser� executado na carga em docker.
	
	Os dados do Redis ser�o armazenados no volume nomeado redis-data, garantindo que as informa��es n�o sejam perdidas ao reiniciar os containers.

	Logs da aplica��o:
	Se a montagem de logs estiver habilitada no docker-compose.yml, os logs da aplica��o ser�o gravados na pasta logs/ no hos


### Subir e rodar a aplica��o

	docker-compose up --build 
	
### Parar o servi�o

	docker-compose down
	
	
### Monitora��o de Logs

	verificar java:
		docker logs -f digio-challenge-app
	verificar redis:
		docker logs -f redis-server

### Endpoint do servi�o

	http://localhost:8080
	
	O Redis estar� dispon�vel no host redis e porta 6379 dentro do container.



### Doc Swagger endpoint
	
	http://localhost:8080/api/swagger-ui/index.html




