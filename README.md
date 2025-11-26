## Identificação
Bernardo Trentin Bottega

Ciência da Computação

## Proposta

O objetivo desse trabalho é criar um jogo voltado ao ensino da separação do lixo entre as lixeiras azul, vermelha, verde, marrom e amarela, com o intuito de tornar
o aprendizado mais divertido e eficaz.

## Processo de desenvolvimento

### 06 - 09/11/25

Usei esses 3 dias para arrumar arquivos do repositório e aprender como o libGDX funciona. Algumas dificuldades que tive foram: entender como a biblioteca funciona
(comandos, métodos e atributos de classes, por exemplo) e para visualizar como poderia "traduzir" a ideia do meu jogo para código a partir do libgdx.

No dia 09 comecei a me sentir mais acostumado com o ambiente da biblioteca. Usei vídeos no youtube e o site do libGDX para me ajudar.
Usei IA para me auxiliar a organizar as classes do jogo, para entender o que cada uma estava fazendo (no caso das classes que já vinham juntas, como Drop e GameScreen)
e o que as classes que eu teria que implementar iriam fazer (caso da classe Reciclagem).

### 10/11/25

Comecei a criar o jogo com base no jogo do balde. Usei partes do que já existia para implementar o que eu preciso para meu jogo. Isso também me ajudou bastante a entender
melhor os métodos (e funcionamento, de maneira geral) do libGDX.

Adicionei as classes `Reciclagem`, `EndScreen` e `ListaReciclagem` (mas não dei commit no dia 10 para `ListaReciclagem`).

A classe Reciclagem representa tanto as lixeiras quanto os resíduos. Decidi fazer dessa maneira porque a lixera e os resíduos teriam os mesmos atributos.

A classe EndScreen é para mostrar a pontuação final do jogador quando o jogo acabar e possibilitar jogar novamente.

A classe ListaReciclagem foi feita para simular a lista de resíduos que aparecem na tela, bem como a ordem que aparecem.

### 12/11/25

Corrigi alguns erros do código que haviam sobrado do dia 10 e continuei adaptando o código para ter as características do meu jogo.

A maior parte dos erros que tive ao longo da implementação eram relacionados aos métodos disponibilizados pelo libGDX (muitas vezes eu confundia quais podem ser usados por
quais classes). Após arrumar todos erros, criei os métodos `spawnResiduo` e `verificarELidarColisao` para evitar algumas repetições que tinha.

Aso testar o jogo, não estava dando certo para sair da tela inicial. Esse bug foi resolvido com ajuda de IA. Segundo ela, isso poderia ser devido a uma imagem não estar sendo
encontrada. De fato, eu tinha escrito `embalagem.png` no arquivo ListaReciclagem.java e na pasta `assets` estava `embalagem.jpg`. Corrigido isso, o jogo estava rodando, embora
com as figuras com tamanhos muito maiores do que deveria.
<img src="https://github.com/user-attachments/assets/ffe8ef73-9c5b-417c-8010-1fa0a3c524fc"
     alt="Tela do jogo"
     width="700" />

### 14/11/25

Analisando o código do jogo "A simple game", percebi que para desenhar algo na tela também é possível especificar o tamanho da imagem.
Assim, corrigi os trechos com `jogo.batch.draw()` especificando uma altura e largura.

Quando rodei o jogo, percebi que as lixeiras não estavam sendo reposicionadas corretamente, como mostra o vídeo abaixo.
![video1](https://github.com/user-attachments/assets/b89bd1da-8b8d-4f82-a8b7-c4b447793d9d)

Para resolver, criei um método `reposicionaLixeira()` e removi um trecho de código que controlava o tempo que as lixeiras demoravam para cair.
![video2](https://github.com/user-attachments/assets/76e79323-0bed-4a1a-97fa-9086b82cb17c)

### 19/11/25

Adicionei uma tela para um tutorial simples do jogo e criei alguns botões para permitir a navegação entre telas.
Na tela de menu adicionei dois botões: um para jogar e outro para ler o tutorial.
Na tela do tutorial, adicionei um para voltar à tela de menu.
Na tela final, adicionei um para voltar à tela de menu.

Tela de menu:

<img width="700" height="500" alt="Tela de menu" src="https://github.com/user-attachments/assets/ac08e674-1c79-463a-b55e-483cd459c1b4" />


Tela do tutorial:

<img width="700" height="500" alt="Tela tutorial" src="https://github.com/user-attachments/assets/b61c32c8-ba45-4790-855e-e9a5c28f0455" />


Os botões foram criados de maneira mais "manual", sem usar o rescurso `Button` disponível pelo libgdx. Preferi dessa maneira por parecer mais simples
e atender bem a necessidade de cada botão para o jogo.

Eles foram feitos com uma imagem e com detecção de clique na tela. Se o usuário clicar numa posição que contenha a imagem, o botão foi clicado.

Essa ideia de não usar o `Button` e fazer de maneira manual foi dada pela IA depois de eu pedir algo simples para criar um botão. Ela me forneceu um
exemplo genérico de uso e eu adaptei para meu código.

### 22/11/25

Mudei a maneira de mostrar a pontuação no final do jogo de número de acertos para porcentagem de acertos.

Outra mudança que fiz foi retirar o método `dispose()` antes de mudar de tela. Na classe GameScreen, antes de mudar para o final do jogo, isso estava causando
que os sons de erro e acerto fossem liberados antes do que deviam e, por isso, o último resíduo não tinha som de acerto/erro.

Depois dessas correções, decidi que deveria adicionar mais opções de resíduos para o jogo. Fiz isso e decidi colocar uma 5° lixeira para deixar o
jogo mais completo. 

Além disso, coloquei um botão na tela de jogo para voltar para o menu principal. O intuito disso foi dar mais controle ao jogador para navegar entre as telas, caso
ele queira recomeçar do zero ou voltar para ler o tutorial, por exemplo.


### 23/11/25

Comecei a criar uma opção de "desafio" para o jogador. A ideia é que cada jogo tenha um desafio para
ser alcançado.

Para isso, criei uma classe `ListaDesafios`. Mas como ela teria as mesmas coisas que a lista `ListaReciclagem`, 
decidi criar uma classe abstrata `Lista`.

```java
abstract class Lista {
    Array<> lista = new Array<>;

    public void embaralhar() {
        lista.shuffle();
    }
 
    public ? elemento(int indice) {
        return lista.get(indice);
    }

    public int tamanho() {
        return lista.size;
    }

    public abstract void adicionarElementos();
}
```

No método `elemento(int indice)`, por exemplo, não sabia como poderia especificar o tipo de retorno, já
que poderia ser tanto String (no caso de ListaDesafio) ou Reciclagem (no caso de ListaReciclagem). Aqui precisei de ajuda de IA para resolver. A solução
encontrada foi usar algo chamado de `Generics`, que permite representar um tipo genérico. No caso do código, 
eu adicionei `<T>` para permitir que a classe receba um "tipo" T.

`abstract class Lista<T> { `

`Array<T> lista = new Array<>;`

e `public T elemento(int indice) `

E, nas classes ListaDesafios e ListaReciclagem:
`public class ListaDesafios extends Lista<String> {`

`public class ListaReciclagem extends Lista<Reciclagem> {`

Assim, funciona tanto para ListaReciclagem quanto ListaDesafios.

### 24/11/2025

Continuei e terminei de fazer a opção de desafios. 

Isso envolveu:

- Adicionar a descrição do desafio na tela de menu, além de um botão para gerar outro desafio
- Adicionar na tela final se o desafio foi alcançado ou não
- Implementar a validação de cada desafio na classe `GameScreen()`
- Fazer alguns ajustes nos construtores de cada classe de tela

Com isso feito, o jogo foi finalizado.

## Diagrama de classes

(terminar)

## Instruções para rodar o jogo

1. Clique em 'Code', selecione 'Codespaces' e clique '+' para criar um Codespace

2. No terminal do Codespace recém criado:

    i. Atualize a versão do Java

    ```bash
    sdk install java 17.0.8-tem
    sdk default java 17.0.8-tem
    ```

    ii. Faça build do projeto em HTML

    ```bash
    ./gradlew html:dist
    ```

    iii. Rode o projeto

    ```bash
    cd html/build/dist
    python -m http.server
    ```

## Resultado final

![resultado_final](https://github.com/user-attachments/assets/e1227737-79fa-4bab-903d-2e4657ba789c)

Observação: foi feito um corte no meio do jogo para diminuir o tamanho do vídeo.

## Referências

ChatGPT OPENAI. ChatGPT. Disponível em: https://chatgpt.com/

Geeks for Geeks. Generics in Java. Disponível em: https://www.geeksforgeeks.org/java/generics-in-java/

LibGDX Documentation. LibGDX. Disponível em: https://libgdx.com/dev/

MARTINY TECH. libGDX Framework FULL Course. Learn How to create Professional Java Games. Disponível em: https://www.youtube.com/watch?v=W_Cyyu_qP60&t=2386s

RAELEUS. Creating a Simple Game in LibGDX. Disponível em: https://www.youtube.com/watch?v=aipDYyh1Mlc&t=539s






