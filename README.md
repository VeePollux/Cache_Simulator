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

### Requisitos
Antes de executar o projeto, é essencial garantir que os seguintes pacotes estejam instalados: gcc, make, python3, tkinter e numpy. Se algum desses pacotes ainda não estiver instalado, siga os comandos abaixo para realizá-lo:
```
sudo apt update
sudo apt-get install gcc
sudo apt install make
sudo apt-get install python3.9
sudo apt install python3-tk
sudo apt install python3-numpy
```

### Execução
Para compilar e executar o projeto, você pode usar o Makefile fornecido:
```
make
```
Alternativamente, você pode compilar e executar manualmente com os seguintes comandos:
```
gcc -fPIC -shared -o mandelbrot.so mandelbrot.c
python3 main.py
```

## Windows :computer:

### Requisitos
Antes de executar o projeto, é essencial garantir que os seguintes pacotes estejam instalados:
- **gcc:**
Para instalar o pacote gcc no Windows, recomendamos seguir o seguinte tutorial: [Como Instalar GCC/G++ MinGW no Windows](https://terminalroot.com.br/2022/12/como-instalar-gcc-gpp-mingw-no-windows.html)
É necessário que o gcc instalado seja de 64 bits. Uma outra opção para a instalação do gcc é utilizando este software e colocá-lo no PATH: [MSYS2](https://www.msys2.org)
- **python:**
Para instalar Python no Windows, recomendamos a seguinte página: [Python](https://www.python.org/downloads/windows/), **é importante que a versão mais recente do python seja instalada para evitar possíveis erros. Recomendamos versão >= 3.12.5**
- **tkinter:**
```
pip install tk
```
- **numpy:**
```
pip install numpy
```

### Execução
Para compilar e executar o projeto, você pode usar o Makefile fornecido:
```
mingw32-make
```
Alternativamente, você pode compilar e executar manualmente com os seguintes comandos:
```
gcc -fPIC -shared -o mandelbrot.dll mandelbrot.c
python main.py
```

## Execução alterando o zoom da imagem
Para alterar o zoom da imagem resultante, alterar as seguintes variáveis na função inicializa() do arquivo mandelbrot.c,

De valores padrões que ja estavam na função:
```
    M->x_ini = -2;
    M->y_ini = +2;
    M->passo = 4;
```
Para valores escolhidos aleatoriamente como por exemplo:
```
    M->x_ini = -0.3787675;
    M->y_ini = +0.6212361;
    M->passo = 5e-7;
```
Logo após seguir os mesmos passos da Execução padrão utilizando o Makefile ou compilar e executar manualmente.

