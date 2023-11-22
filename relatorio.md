<style>
.custom-font {
font-family: 'Arial', sans-serif;
}
</style>

<div class="custom-font">

<p align="center">
<img src="https://i.pinimg.com/originals/1a/21/6f/1a216fb0afdce66e7ffd9c9dbfce393b.jpg" alt="Descrição da Imagem" width="200"/>
</p>

<h2 align="center">Implementação de Sistema Cadastral com Interface Web, Baseado nas Tecnologias de Servlets, JPA e JEE</h2>
<h3 align="center">Missão Prática | Nível 4 | Mundo 3</h3>

* **Aluna:** Amanda Duque Kawauchi
* **Matrícula:** 202209170254
* **Campus:** Morumbi
* **Curso:** Desenvolvimento Full-Stack
* **Disciplina:** Nível 4: Vamos Integrar Sistemas
* **Turma:** 2023.3
* **Semestre Letivo:** 3º Semestre
* **Repositório GitHub:** [Link do Repositório](https://github.com/madukisp/java-missao4-mundo3)

## Objetivo da Prática

O objetivo desta prática é aprofundar o conhecimento e habilidade na implementação de persistência de dados com Java Persistence API (JPA), desenvolvimento de regras de negócios com Enterprise JavaBeans (EJBs), e a criação de um sistema cadastral web utilizando Servlets e JavaServer Pages (JSPs). Além disso, objetiva-se aprimorar a interface do usuário com o uso da biblioteca Bootstrap, habilidade essencial para aplicação em cenários reais de desenvolvimento Java Web.

## Análise e Conclusão

### 👉 1º Procedimento | Camadas de Persistência e Controle

#### Como é organizado um projeto corporativo no NetBeans?
No NetBeans, um projeto corporativo é estruturado modularmente, facilitando a gestão de diferentes camadas de uma aplicação JEE. Esta abordagem promove a separação de responsabilidades e otimiza o gerenciamento do código, com ferramentas integradas auxiliando desde a configuração do servidor até o mapeamento das entidades de banco de dados.

#### Qual o papel das tecnologias JPA e EJB?
JPA e EJB são essenciais para a construção de aplicativos web robustos no ambiente Java. Enquanto JPA gerencia a persistência dos dados, EJB encapsula a lógica de negócios, oferecendo serviços transacionais e escaláveis, fundamentais para aplicações empresariais.

#### Como o NetBeans melhora a produtividade com JPA e EJB?
O NetBeans impulsiona a produtividade ao simplificar o uso de JPA e EJB, com assistentes de código, templates, e ferramentas integradas. Isso minimiza o esforço manual e permite foco nos aspectos cruciais do desenvolvimento.

#### O que são Servlets e seu suporte no NetBeans?
Servlets são componentes Java para processamento de solicitações e geração de respostas em aplicações web. O NetBeans facilita o desenvolvimento de Servlets com assistentes e templates, além de oferecer integração com o servidor de aplicação para testes e implantação eficientes.

#### Comunicação entre Servlets e Session Beans em EJBs
A comunicação é realizada via injeção de dependência, onde Servlets utilizam anotações para acessar Session Beans, promovendo uma separação efetiva entre a lógica de apresentação e de negócios.

---

### 👉 2º Procedimento | Interface Cadastral com Servlet e JSPs

#### Implementação do padrão Front Controller
O padrão Front Controller, implementado através de um Servlet, centraliza o tratamento de requisições, roteando-as para os controladores e visões adequadas, em conformidade com a arquitetura MVC.

#### Diferenças e semelhanças entre Servlets e JSPs
Servlets e JSPs colaboram para a criação de aplicações web dinâmicas, com Servlets focados no processamento de solicitações e JSPs na apresentação de conteúdo. Ambos acessam objetos de solicitação HTTP, mas diferem na aplicação prática - Servlets para lógica de negócios e JSPs para interfaces de usuário.

#### Redirecionamento vs. método forward
Redirecionamentos geram novas solicitações e mudanças visíveis na URL, enquanto o método forward do RequestDispatcher mantém a solicitação original, encaminhando-a internamente. Parâmetros e atributos em HttpRequest são usados para transferir informações e gerenciar o estado entre os componentes do servidor.

---

### 👉 3º Procedimento | Melhorando o Design da Interface

#### Uso do framework Bootstrap
Bootstrap é integrado para desenvolver interfaces web responsivas e atrativas, utilizando seu sistema de grid e componentes estilizados para adaptar-se a diferentes dispositivos.

#### Bootstrap e independência estrutural do HTML
Bootstrap possibilita alterações de estilo sem impactar a estrutura HTML, promovendo a manutenção e a atualização do design do site de maneira eficiente.

#### Relação entre Bootstrap e responsividade
O Bootstrap garante que a página web se ajuste adequadamente a dispositivos móveis e desktops, proporcionando uma experiência de usuário consistente e de qualidade.


