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
