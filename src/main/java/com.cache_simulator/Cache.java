/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cache_simulator;


import javax.swing.*;

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


  public Cache(int nsets, int bsize, int assoc, char flagReplacement, boolean flagOutput) {
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
    //else if(flagReplacement == 'L')
      //  replacement = new LRU_P();
     //   else if(flagReplacement == 'F')
     //   replacement = new FIFO_P();


    bitsOffset = (int) (Math.log(bsize) / Math.log(2));
    bitsIndex = (int) (Math.log(nsets) / Math.log(2));

    this.associativity = assoc;
    this.nsets = nsets;
    blocks = new Block[nsets][assoc];

    //Criando os blocos
    for (int i = 0; i < nsets; i++)
      for (int j = 0; j < assoc; j++)
        blocks[i][j] = new Block();}

  public void CacheOperation(int adress) {
    int tag = adress >> (bitsOffset + bitsIndex);
    int indice = (adress >> bitsOffset) & (int) (Math.pow(2, bitsIndex) - 1);
    boolean flagMissesComp = false;
    int flagMissesCap = 0;
    boolean flagMissesConf = false;
    numberAccesses++;

   //Direto
    if (associativity==1)
      if(blocks[indice][0].getBitValidity() && blocks[indice][0].getTag() == tag){
        numberHits++;
        return;}
      else {
        if (!blocks[indice][0].getBitValidity())
            numberMissesComp++;
        else if(numberMissesComp == nsets)
            numberMissesCap++;
        else
            numberMissesConf++;
        blocks[indice][0].setTag(tag);
        blocks[indice][0].setBitValidity(true);}
    //Totalmente Associativo
    else if(nsets == 1){
        for (int i = 0; i < associativity; i++) {
          if(blocks[0][i].getBitValidity() && blocks[0][i].getTag() == tag){
            numberHits++;
            return;}}
        for (int i = 0; i < associativity; i++){
          if (!blocks[0][i].getBitValidity()) {
              numberMissesComp++;
              blocks[0][i].setTag(tag);
              blocks[0][i].setBitValidity(true);
              return;}
          else
            flagMissesCap++;}
        if(flagMissesCap == associativity){
            numberMissesCap++;
            replacement.replace(blocks, associativity, 0, tag);}
        }
    //Associativo por Conjunto
    else{
      for (int i = 0; i < associativity; i++) {
        if(blocks[indice][i].getBitValidity() && blocks[indice][i].getTag() == tag){
          numberHits++;
          return;}}
      for (int i = 0; i < associativity; i++){
        if (!blocks[indice][i].getBitValidity()) {
          numberMissesComp++;
          blocks[indice][i].setTag(tag);
          blocks[indice][i].setBitValidity(true);
          return;}
        else
          flagMissesCap++;}
      if((flagMissesCap == associativity) && (numberMissesComp == nsets*associativity)){
        numberMissesCap++;
        replacement.replace(blocks, associativity, indice, tag);}
      else {
        numberMissesConf++;
        replacement.replace(blocks, associativity, indice, tag);}
    }
  }


  public void printOUT(int flagOut){
    double taxaHit, taxaMiss, taxaMissCompulsorio, taxaMissCapacidade, taxaMissConflito;
    int numberMisses = numberMissesComp + numberMissesCap + numberMissesConf;
    System.out.println("CONF: " + numberMissesConf + " COMP: " + numberMissesComp + " CAP: " + numberMissesCap);
    System.out.println("Hit: " + numberHits);
    taxaMiss = (double) numberMisses/numberAccesses;
    taxaHit = (double) numberHits/numberAccesses;
    taxaMissCompulsorio = (double) numberMissesComp/numberMisses;
    taxaMissCapacidade = (double) numberMissesCap/numberMisses;
    taxaMissConflito = (double) numberMissesConf/numberMisses;

    taxaMiss = Math.round(taxaMiss * 10000.0)/10000.0;
    taxaHit = Math.round(taxaHit * 10000.0)/10000.0;
    taxaMissCompulsorio = Math.round(taxaMissCompulsorio * 10000.0)/10000.0;
    taxaMissCapacidade = Math.round(taxaMissCapacidade * 10000.0)/10000.0;
    taxaMissConflito = Math.round(taxaMissConflito * 10000.0)/10000.0;

    if(flagOut == 1){
      System.out.println(numberAccesses + " " + taxaHit + " "+ taxaMiss +  " " + taxaMissCompulsorio + " " + taxaMissCapacidade + " " + taxaMissConflito);
    }else{
      System.out.println("Número de acessos: " + numberAccesses);
      System.out.println("Número de hits: " + numberHits);
      System.out.println("Taxa de hits: " + taxaHit);
      System.out.println("Número de misses: " + numberMisses);
      System.out.println("Taxa de misses: " + taxaMiss);
      System.out.println("Número de misses compulsórios: " + numberMissesComp);
      System.out.println("Taxa de misses compulsórios: " + taxaMissCompulsorio);
      System.out.println("Número de misses de conflito: " + numberMissesConf);
      System.out.println("Taxa de misses de conflito: " + taxaMissConflito);
      System.out.println("Número de misses de capacidade: " + numberMissesCap);
      System.out.println("Taxa de misses de capacidade: " + taxaMissCapacidade);
    }
  }

  public int bytesToInt(byte[] bytes){
    int bits = 0;
    for(int i = 0; i < 4; i++){
      bits = (bits << 8) | (bytes[i] & 0xFF);
    }
    return bits;
  }

}