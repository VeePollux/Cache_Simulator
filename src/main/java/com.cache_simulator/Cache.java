/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cache_simulator;

/**
 *
 * @author vee
 */
public class Cache {
  private int numberAccesses;
  private int numberHits;
  private int numberMissesComp;
  private int numberMissesCap;
  private int numberMissesConf;

  private int associativity;
  private int nsets;
  private int bitsIndex;
  private int bitsOffset;
  private Block[][] blocks;  //Cache vai ser uma matriz de blocos
  private Replacement_P replacement;

  //Construtor
  public Cache() {
  }

  public Cache(int nsets, int bsize, int associativity, char flagReplacement, boolean flagOutput) {
    //Começando com todos resultados zerados
    numberAccesses = 0;
    numberHits = 0;
    numberMissesComp = 0;
    numberMissesCap = 0;
    numberMissesConf = 0;

    //Criando o objeto de substituição
    replacement = null;

    if(flagReplacement == 'R')
      replacement = new Random_P();
    else if(flagReplacement == 'L')
        replacement = new LRU_P(nsets,associativity);
    else if(flagReplacement == 'F')
        replacement = new FIFO_P();


    bitsOffset = (int) (Math.log(bsize) / Math.log(2));
    bitsIndex = (int) (Math.log(nsets) / Math.log(2));

    this.associativity = associativity;
    this.nsets = nsets;
    blocks = new Block[nsets][associativity];

    //Criando os blocos
    for (int i = 0; i < nsets; i++)
      for (int j = 0; j < associativity; j++)
        blocks[i][j] = new Block();}

  public void CacheOperation(int adress) {
    int tag = adress >> (bitsOffset + bitsIndex);
    int index= (adress >> bitsOffset) & (int) (Math.pow(2, bitsIndex) - 1);
    int flagMissesCap = 0;
    numberAccesses++;

   //Direto
    if (associativity==1)
      if(blocks[index][0].getBitValidity() && blocks[index][0].getTag() == tag){
        numberHits++;
        return;}
      else {
        if (!blocks[index][0].getBitValidity())
            numberMissesComp++;
        else if(numberMissesComp == nsets)
            numberMissesCap++;
        else
            numberMissesConf++;
        blocks[index][0].setTag(tag);
        blocks[index][0].setBitValidity(true);

          }
    //Totalmente Associativo
    else if(nsets == 1){
        for (int i = 0; i < associativity; i++) {
          if(blocks[0][i].getBitValidity() && blocks[0][i].getTag() == tag){
            numberHits++;
            if (replacement instanceof LRU_P){
              ((LRU_P)replacement).updateUsage(i, index);  // Atualiza o tempo de uso para LRU
              System.out.println(numberHits);}
            return;}}
        for (int i = 0; i < associativity; i++){
          if (!blocks[0][i].getBitValidity()) {
              numberMissesComp++;
              blocks[0][i].setTag(tag);
              blocks[0][i].setBitValidity(true);
            if (replacement instanceof LRU_P)
              ((LRU_P)replacement).updateUsage(i, index);  // Atualiza o tempo de uso para LRU
            return;}
          else
            flagMissesCap++;}
        if(flagMissesCap == associativity){
            numberMissesCap++;
            replacement.replace(blocks, associativity, index, tag);}
        }
    //Associativo por Conjunto
    else{
      for (int i = 0; i < associativity; i++) {
        if(blocks[index][i].getBitValidity() && blocks[index][i].getTag() == tag){
          numberHits++;
          if (replacement instanceof LRU_P)
            ((LRU_P)replacement).updateUsage(i, index);  // Atualiza o tempo de uso para LRU
          return;}}
      for (int i = 0; i < associativity; i++){
        if (!blocks[index][i].getBitValidity()) {
          numberMissesComp++;
          blocks[index][i].setTag(tag);
          blocks[index][i].setBitValidity(true);
          if (replacement instanceof LRU_P)
            ((LRU_P)replacement).updateUsage(i, index);  // Atualiza o tempo de uso para LRU
          return;}
        else
          flagMissesCap++;}
      if((flagMissesCap == associativity) && (numberMissesComp == nsets*associativity)){
        numberMissesCap++;
        replacement.replace(blocks, associativity, index, tag);}
      else {
        numberMissesConf++;
        replacement.replace(blocks, associativity, index, tag);}
    }
  }

  public void printOUT(int flagOut){
    double hitRate, missRate, missCompRate, missCapRate, missConfRate;
    int numberMisses = numberMissesComp + numberMissesCap + numberMissesConf;
    hitRate = Math.round(((double) numberHits/numberAccesses)*10000.0)/10000.0;
    missRate = Math.round(((double) numberMisses/numberAccesses)*10000.0)/10000.0;
    missCompRate = Math.round(((double) numberMissesComp/numberMisses)*10000.0)/10000.0;
    missCapRate = Math.round(((double) numberMissesCap/numberMisses)*10000.0)/10000.0;
    missConfRate = Math.round(((double) numberMissesConf/numberMisses)*10000.0)/10000.0;

    if(flagOut == 1){
      System.out.println(numberAccesses + " " + hitRate + " "+ missRate +  " " + missCompRate + " " + missCapRate + " " + missConfRate);
    }else{
      System.out.println("Número de acessos: " + numberAccesses);
      System.out.println("Número de hits: " + numberHits);
      System.out.println("Taxa de hits: " + hitRate);
      System.out.println("Número de misses: " + numberMisses);
      System.out.println("Taxa de misses: " + missRate);
      System.out.println("Número de misses compulsórios: " + numberMissesComp);
      System.out.println("Taxa de misses compulsórios: " + missCompRate);
      System.out.println("Número de misses de conflito: " + numberMissesConf);
      System.out.println("Taxa de misses de conflito: " + missConfRate);
      System.out.println("Número de misses de capacidade: " + numberMissesCap);
      System.out.println("Taxa de misses de capacidade: " + missCapRate);
    }
  }
  public int bytesToInt(byte[] bytes){
    int bits = 0;
    for(int i = 0; i < 4; i++){
      bits = (bits << 8) | (bytes[i] & 0xFF); //desloca e converte cada byte
    }
    return bits;}
}