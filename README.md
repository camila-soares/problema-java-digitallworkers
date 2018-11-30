# problema-java-digitallworkers

- Foi criado o serviço de autenticação, com Spring Security JWT, gerando um token e passando-o pelo header da requisição, o token expira em 24h.
- Foi criado um endpoint para cadastro de usuário para que foi feito apartir dele o login.
- O serviço de listar todos comprovantes e baixar comprovante, está para todos usuários, porém para cadatrar um comprovante o usuário deve está logado
- Para o serviço de baixar comprovante utilizei a biblioteca JasperReport.
- Usei a biblioteca lombok, para que o código ficasse menos verboso.
- utilizei o swagger para melhor entendimento dos atributos que devem ser passados.

-Para rodar a aplicação necessário executar no postman o http://localhost:8080.
- para listar todos comprovantes o endpoint a ser passado:http://localhost:8080/receipt/listAll
- Para baixar um comprovante o endpoint : http://localhost:8080/receipt/download/{id}
- Para criar um comprovante o usuário deve está autenticado, primeiro é necessário cadastrar o usuario no endpoint: http://localhost:8080/user
{
  "name": "teste",
  "email": "teste10@gmail.com,
  "password": "12345"
}
retornará um 201, pois isso é necessário fazer login no endpoint: http://localhost:8080/login 

{
  "email": "teste10@gmail.com,
  "password": "12345"
}
retornará um 200, e no header do postman será gerado um token ex: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZTExQGdtYWlsLmNvbSIsImV4cCI6MTU0MzY3OTYwOH0.5M91C7N61g1wjtPepnO-QVEbfZqkZFzN2YS9RK2QIWOCYjIQh7UGQi7Owi_NrPCm14KFQcWVYAARIPY4L0VXsg

esse token deve ser copiado para o header da requisição passando o Authorization e o token e fará a requisição para o endpoint: http://localhost:8080/receipt

{
	"name": "teste",
	"cause": "teste",
	"expiration": "29/11/2018"
}
