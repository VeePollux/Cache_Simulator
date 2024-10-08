#!/bin/bash

# Verificar se os argumentos foram passados corretamente
if [ "$#" -ne 6 ]; then
    echo "Número de argumentos inválido. Siga a estrutura: cache_simulator <nsets> <bsize> <assoc> <substituição> <flag_saida> <arquivo_de_entrada>"
    exit 1
fi

# Compilar o programa Java
#echo "Compilando Cache_Simulator.java..."
javac -d . src/main/java/com.cache_simulator/cache_simulator.java src/main/java/com.cache_simulator/Cache.java src/main/java/com.cache_simulator/Replacement_P.java src/main/java/com.cache_simulator/LRU_P.java src/main/java/com.cache_simulator/FIFO_P.java src/main/java/com.cache_simulator/Random_P.java  src/main/java/com.cache_simulator/Block.java


if [ $? -ne 0 ]; then
    echo "Erro durante a compilação."
    exit 1
fi

# Executar o programa Java
#echo "Executando Cache _Simulator..."
java -cp . com.cache_simulator.cache_simulator "$@"

if [ $? -ne 0 ]; then
    echo "Erro durante a execução."
    exit 1
fi