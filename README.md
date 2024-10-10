# Cache Simulator
Este simulador de cache foi desenvolvido em Java como parte do projeto para a disciplina Arquitetura e Organização de Computadores 2. Ele permite a parametrização de vários aspectos da cache, como número de conjuntos, tamanho do bloco, associatividade e políticas de substituição (FIFO, LRU, Random). A implementação tem como objetivo demonstrar o funcionamento prático desses conceitos, fornecendo métricas detalhadas sobre hits, misses (compulsórios, de capacidade e de conflito) e eficiência em diferentes configurações.

## Autores :busts_in_silhouette:
- Vanessa Aldrighi ([vanessa.a@inf.ufpel.edu.br](mailto:vanessa.a@inf.ufpel.edu.br))
- Guilherme Hepp da Fonseca ([ghfonseca@inf.ufpel.edu.br](mailto:ghfonseca@inf.ufpel.edu.br))


## Repositório :file_folder:
Repositório composto pelos seguintes arquivos:
- **`src/main`**: Pasta responsavel por comportar o codigo fonte do simulador de cache.
  - **`java/com.cache_simulator`**: Pasta composta pelas classes em java do simulador, contendo todas as classes.
  - **`resources`**: Pasta composta pelos scripts de teste.
- **`com/cache_simulator`**: Pasta com todas as classes do codigo java.
- **`target/classes`**: Pasta com todas as classes do codigo java.
- **Arquivos extras**: Arquivos de utilidade para o projeto.
  - **`mvnw.cmd`**: Arquivo de criação de projeto com Maven.
  - **`pom.xml`**: Arquivo criado pela utilização do intelij.

## Linux :penguin:

*****
O codigo em Java necessita da ferramenta JDK ja instalada na maquina que serve para a compilação e contem as API's Java necessarias para a execução do código.
A execução do projeto deve ser feita dentro do diretório inicial, cache_simulator.
Através do terminal, execute o comando, por exemplo:
```
./cache_simulator 256 4 1 R 1 bin_100.bin
```

Caso deseje executar sem ``` ./``` execute o comando:
```
export PATH=$PATH:.
```
Em seguida, é possivel executar o comando normalmente, por exemplo:
```
cache_simulator 256 4 1 R 1 bin_100.bin
```

