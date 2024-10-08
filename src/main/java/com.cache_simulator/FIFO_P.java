package com.cache_simulator;

public class FIFO_P extends Replacement_P {
    @Override
    public void replace(Block[][] block, int associativity, int index, int tag) {
        int i;

        // Substitui o bloco mais antigo que seria a primeira via pois ela é a mais antiga, deslocando as vias
        for (i = 0; i < associativity - 1; i++) {
            block[index][i].setTag(block[index][i + 1].getTag());
        }
        block[index][i].setTag(tag);
    }
}
