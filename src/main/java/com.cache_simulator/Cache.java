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
  private int bitsIndex;
  private int bitsOffset;
  private Block[][] blocks;  //Cache vai ser uma matriz de blocos
  private Replacement_P replacement;


public Cache (int nsets, int bsize, int assoc){
  //Começando com todos resultados zerados
  numberAccesses = 0;
  numberHits = 0;
  numberMissesComp = 0;
  numberMissesCap = 0;
  numberMissesConf = 0;

  this.associativity = assoc;
  blocks = new Block[nsets][assoc];


  for(int i = 0; i < nsets; i++)
    for(int j = 0; j < assoc; j++)
      blocks[i][j] = new Block();
}

public void CacheOperation (){

}
}
