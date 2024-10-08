package com.cache_simulator;

public class LRU_P extends Replacement_P {
    private int[] lastUsed;  // Array para acompanhar a última vez que cada bloco foi usado

    public LRU_P(int nsets, int associativity) {
        lastUsed = new int[nsets * associativity];  // Inicializa o array de uso
        for (int i = 0; i < lastUsed.length; i++) {
            lastUsed[i] = -1;  // Inicializa com -1 indicando que nenhum bloco foi usado ainda
        }
    }
    @Override
    public void replace(Block[][] block, int associativity, int index, int tag) {
        int leastRecentlyUsedIndex = -1;
        int oldest = Integer.MAX_VALUE;

        // Encontra o bloco menos recentemente usado
        for (int i = 0; i < associativity; i++) {
            // Verifica qual bloco foi usado há mais tempo
            if (lastUsed[index * associativity + i] < oldest) {
                oldest = lastUsed[index * associativity + i];
                leastRecentlyUsedIndex = i;}
        }

        // Substitui o bloco menos recentemente usado
        block[index][leastRecentlyUsedIndex].setTag(tag);
        lastUsed[index * associativity + leastRecentlyUsedIndex] = 0;  // Atualiza o tempo de uso
    }

    //Métod para atualizar o tempo de uso de todos os blocos
    public void updateUsage(int associativity, int index) {
        for (int i = 0; i < associativity; i++) {
            lastUsed[index * associativity + i]++;}
    }
}
