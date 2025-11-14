## 06 - 09/11/25

Usei esses 3 dias para arrumar arquivos do repositório e aprender como o libGDX funciona. Algumas dificuldades que tive foram: entender como a biblioteca funciona
(comandos, métodos e atributos de classes, por exemplo) e para visualizar como poderia "traduzir" a ideia do meu jogo para código a partir do libgdx.

No dia 09 comecei a me sentir mais acostumado com o ambiente da biblioteca. Usei vídeos no youtube e o site do libGDX para me ajudar.
Usei IA para me auxiliar a organizar as classes do jogo, para entender o que cada uma estava fazendo (no caso das classes que já vinham juntas, como Drop e GameScreen)
e o que as classes que eu teria que implementar iriam fazer (caso da classe Reciclagem).

## 10/11/25

Comecei a criar o jogo com base no jogo do balde. Usei partes do que já existia para implementar o que eu preciso para meu jogo. Isso também me ajudou bastante a entender
melhor os métodos (e funcionamento, de maneira geral) do libGDX.

Adicionei as classes `Reciclagem`, `EndScreen` e `ListaReciclagem` (mas não dei commit no dia 10 para `ListaReciclagem`).

A classe Reciclagem representa tanto as lixeiras quanto os resíduos. Decidi fazer dessa maneira porque a lixera e os resíduos teriam os mesmos atributos.

A classe EndScreen é para mostrar a pontuação final do jogador quando o jogo acabar e possibilitar jogar novamente.

A classe ListaReciclagem foi feita para simular a lista de resíduos que aparecem na tela, bem como a ordem que aparecem.

## 12/11/25

Corrigi alguns erros do código que haviam sobrado do dia 10 e continuei adaptando o código para ter as características do meu jogo.

A maior parte dos erros que tive ao longo da implementação eram relacionados aos métodos disponibilizados pelo libGDX (muitas vezes eu confundia quais podem ser usados por
quais classes). Após arrumar todos erros, criei os métodos `spawnResiduo` e `verificarELidarColisao` para evitar algumas repetições que tinha.

Aso testar o jogo, não estava dando certo para sair da tela inicial. Esse bug foi resolvido com ajuda de IA. Segundo ela, isso poderia ser devido a uma imagem não estar sendo
encontrada. De fato, eu tinha escrito `embalagem.png` no arquivo ListaReciclagem.java e na pasta `assets` estava `embalagem.jpg`. Corrigido isso, o jogo estava rodando, embora
com as figuras com tamanhos muito maiores do que deveria.
<img src="https://github.com/user-attachments/as![video1](https://github.com/user-attachments/assets/f4e94c2b-f254-4e3b-aac6-b47270a07f00)
sets/ffe8ef73-9c5b-417c-8010-1fa0a3c524fc"
     alt="Tela do jogo"
     width="900" />

## 14/11/25

Analisando o código do jogo "A simple game", percebi que para desenhar algo na tela também é possível especificar o tamanho da imagem.
Assim, corrigi os trechos com `jogo.batch.draw()` especificando uma altura e largura.

Quando rodei o jogo, percebi que as lixeiras não estavam sendo reposicionadas corretamente, como mostra o vídeo abaixo.
![Uploading video1.gif…]()

Para resolver, criei um método `reposicionaLixeira()` e removi um trecho de código que controlava o tempo que as lixeiras demoravam para cair.
![video2](https://github.com/user-attachments/assets/76e79323-0bed-4a1a-97fa-9086b82cb17c)


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
