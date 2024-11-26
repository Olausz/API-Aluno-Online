# API-Aluno-Online
# Resumo
Este projeto tem como objetivo simular um sistema escolar, onde informações sobre matérias, notas, alunos e professores serão gerenciadas e relacionadas entre si. O sistema foi desenvolvido como uma API RESTful utilizando o framework Spring, seguindo boas práticas de arquitetura e organização.
# Tecnologias Usadas

Java e Spring framework: Desenvolvimento Back-end.<br>
IntelliJ IDEA: IDE usada.<br>
Insominia: Requisições HTTP simulação do Front-end.<br>
PostgreSQL: Banco de dados.<br>
Dbeaver: SGBD usado.<br>

# Funcionamento da API
Criamos 4 pacotes que servem para o funcionamento da API:

Controller: Serve para receber as informações do frontend e repassá-las para outras camadas. Injetamos as informações do Repository e utilizamos as seguintes anotações:
@RestController: Define que será um controlador REST, responsável por controlar as requisições HTTP e as respostas em JSON.
@RequestMapping: Define o endpoint "/alunos".
@RequestBody: Recebe o arquivo JSON, que será convertido para Java e associado ao modelo Aluno, criando um objeto com as informações contidas no JSON. Dentro do Controller, há o método "criarAluno", que serve para criar um novo aluno, conforme o nome sugere.

Service: Faz a ponte entre o Controller e o Repository, sendo também responsável por validações e regras de negócio. Utilizamos as seguintes anotações:
@Service: Informa ao Spring Boot que essa classe é um Service. Criamos o método "criarAluno", que serve para pegar as informações do Controller e enviá-las para o Model.

Repository: Responsável por coletar e requisitar informações do banco de dados. Utilizamos a anotação @Repository para informar ao Spring Boot que ele é um Repository e o JpaRepository para implementar o CRUD (Create, Read, Update, Delete).

Model: Criamos o objeto Aluno, que será representado como uma entidade no banco de dados. No modelo Aluno, utilizamos as seguintes anotações:
@NoArgsConstructor: Cria um construtor padrão sem argumentos.
@AllArgsConstructor: Cria um construtor com todos os argumentos. Esses dois construtores facilitam a criação automática de objetos Aluno.
@Entity: Informa que a classe no código será representada como uma tabela no banco de dados.

Dtos: Os DTOs (Data Transfer Objects) são utilizados para transportar dados de forma flexível e segura. Eles permitem ocultar informações sensíveis dos usuários, garantindo maior segurança ao sistema. Com os DTOs, é possível selecionar quais dados serão exibidos ou inseridos, oferecendo um controle mais refinado sobre a comunicação entre as camadas da aplicação.

Config: Na pasta Config, está configurado o Swagger, uma ferramenta amplamente utilizada para documentação e testes de APIs. O Swagger gera automaticamente as requisições para os endpoints do sistema, permitindo validar se o código está funcionando corretamente e se os endpoints cumprem suas respectivas funções. Além disso, o Swagger oferece uma interface onde é possível testar os endpoints diretamente, tornando o processo de desenvolvimento e validação mais eficiente.


No Insomnia, criamos uma coleção para simular as requisições do frontend, já que não temos o código frontend para este projeto. Dentro dessa coleção, foi criada a requisição "criarAluno", utilizando o método HTTP POST. Nessa requisição, utilizamos a URL "http://localhost:8080/alunos", que corresponde ao endpoint definido no Controller. Definimos o corpo da requisição como JSON (para que o @RequestBody possa convertê-lo para Java), e dentro desse JSON incluímos informações como nome, e-mail e CPF.
