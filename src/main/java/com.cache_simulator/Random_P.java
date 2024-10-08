package com.cache_simulator;

import java.util.Random;

public class Random_P extends Replacement_P {
    private final Random randomGenerator = new Random();

    @Override
    public void replace(Block[][] block,int associativity, int index, int tag) {

        // Gera um índice aleatório entre 0 e o tamanho da associatividade
        int randomIndex = randomGenerator.nextInt(associativity);

        // Substitui o bloco com a nova tag e define o bit de validade como verdadeiro
        block[index][randomIndex].setTag(tag);
        block[index][randomIndex].setBitValidity(true);
    }
}
