<h1 align="center">MatchFruit</h1>

<h2>Arquitetura de Solução</h2>

![arquitetura-fruit](https://user-images.githubusercontent.com/42544892/132958757-2e495c6b-54c6-4f58-a1d2-949224971b5c.jpg)


<h2>Descrição do Projeto</h2>

<p align="justify">O sistema será composto por dois ambientes, um ambiente voltado ao
consumidor final um aplicativo onde o usuário irá realizar um login e após isso
poderá acessar uma tela relacionada às frutas, nessa tela o usuário poderá
realizar consultas personalizadas sobre às frutas, tais como: melhores frutas por
estação climática do ano, detalhes sobre as frutas e até dicas de como utilizálas. Além disso, o usuário poderá selecionar e adicionar suas frutas favoritas e
receber notificações sobre às melhores épocas de colheita, ou seja, em quais
estações do ano determinada fruta tem uma safra maior.
</p>

<p align="justify">Também será possível que os usuários consigam verificar através da câmera
do aparelho, seja Android ou IOS, se a probabilidade de uma determinada fruta
está estragada ou não. O aplicativo irá consumir uma API integrada a nuvem,
que utiliza inteligência artificial e caso o usuário aceite as permissões de acesso
a câmera durante o uso do aplicativo, quando a função de verificação de uma
fruta for acionada, a API será chamada e irá realizar uma verificação da imagem
através de um classificador, que apresentará uma margem aproximada se
determinada fruta está estragada ou não. </p>

<p align="justify">O mesmo mecanismo de inteligência artificial irá se aplicar para os usuários
corporativos, através da Internet das Coisas (IOT), utilizaremos um robô
desenvolvido em Arduino que utiliza sensores que também irão consumir a API
de classificação de imagens que será armazenada na nuvem. Portanto, após a
verificação de determinada fruta o Arduino irá compartilhar esses dados através
de uma integração com um sistema web. </p>


<p align="justify">O sistema web será utilizado pelo time de logística dos estabelecimentos, com
isso os operadores poderão verificar os índices de frutas estragadas em
determinados períodos.</p>

<p align="justify">Posteriormente, se aplicável, o operador poderá iniciar uma análise de uma
empresa que ele sinalizar seja pelo aplicativo mobile corporativo (que pode ser
implementado) ou pela aplicação web, para realizar verificações das cargas de
frutas, isso possibilitará um alto controle sobre os produtos por fornecedores, e
ao final do mês poderá ser gerado um relatório, caso necessário, para reportar
a porcentagem de itens que foram fornecidos de forma inapropriada para o
consumo dos clientes.</p>


<h2>Tabela de Endpoints</h2>

<table border="1">
    <tr>
        <td>UserController</td>
        <td>FruitController</td>
        <td>APIFruit</td>
        <td>APIUser</td>
    </tr>
    <tr>
        <td>/user</td>
        <td>/fruit</td>
        <td>/api/fruit/{id}</td>
        <td>/{userId}/fruit/{fruitId}</td>
    </tr>
    <tr>
        <td>/user/new</td>
        <td>/fruit/new</td>
        <td> </td>
        <td> </td>
    </tr>

</table>
























