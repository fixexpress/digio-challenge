# Digio Challenge

Este é o projeto Digio Challenge, desenvolvido como parte do desafio de backend. A aplicação utiliza o Spring Boot e Swagger para fornecer uma API de compras.
Aplicação dockerizada deve ser rodada usando docker pois usa solução em cache também.

## Pré-requisitos

Antes de rodar o projeto, certifique-se de que você tenha os seguintes itens instalados:

- **Java 19 ou superior** (Recomendado: Java 21)
- **Maven** (para compilar o projeto)
- **Redis** (Redis para armazenar dados temporários, deve rodar na maquina ou docker a porta 6379 
- **Docker** preferencia docker desktop

## Redis o porque da escolha dele!

	Foi feita a escolha do Redis para que os dados das urls sejam armazenados em cache. 
	
	Obs: Isso evita que se faça acesso várias vezes as urls json enquanto em testes permitindo que fiquem em cache, atribuindo maior velacidade as operações e nemos requisições as urls propostas sendo que neste caso será executado na carga em docker.
	
	Os dados do Redis serão armazenados no volume nomeado redis-data, garantindo que as informações não sejam perdidas ao reiniciar os containers.

	Logs da aplicação:
	Se a montagem de logs estiver habilitada no docker-compose.yml, os logs da aplicação serão gravados na pasta logs/ no hos


### Subir e rodar a aplicação

	docker-compose up --build 
	
### Parar o serviço

	docker-compose down
	
	
### Monitoração de Logs

	verificar java:
		docker logs -f digio-challenge-app
	verificar redis:
		docker logs -f redis-server

### Endpoint do serviço

	http://localhost:8080
	
	O Redis estará disponível no host redis e porta 6379 dentro do container.



### Doc Swagger endpoint
	
	http://localhost:8080/api/swagger-ui/index.html




