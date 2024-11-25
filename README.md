# API-Aluno-Online
Olá, Professor. Boa tarde! Estou tendo dificuldades com banco de dados, mas tentarei explicar o máximo que entendi sobre a última atividade.

No Spring Boot:

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

No Insomnia, criamos uma coleção para simular as requisições do frontend, já que não temos o código frontend para este projeto. Dentro dessa coleção, foi criada a requisição "criarAluno", utilizando o método HTTP POST. Nessa requisição, utilizamos a URL "http://localhost:8080/alunos", que corresponde ao endpoint definido no Controller. Definimos o corpo da requisição como JSON (para que o @RequestBody possa convertê-lo para Java), e dentro desse JSON incluímos informações como nome, e-mail e CPF.


