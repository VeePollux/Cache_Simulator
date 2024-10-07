package com.cache_simulator;

import java.util.Random;

public class Random_P extends Replacement_P {
    private final Random randomGenerator = new Random();

    @Override
    public void replace(Block[][] block,int associativity, int index, int tag) {
        if (block == null || block.length == 0) {
            throw new IllegalArgumentException("Cache não pode ser nulo ou vazio.");
        }

        // Gera um índice aleatório entre 0 e cache.length - 1
        int randomIndex = randomGenerator.nextInt(associativity);

        // Substitui o bloco com a nova tag e define o bit de validade como verdadeiro
        block[index][randomIndex].setTag(tag);
        block[index][randomIndex].setBitValidity(true);
    }
}
