package com.cache_simulator;

public class Block {
    private boolean bitValidity;
    private int tag;

    public Block() {
        bitValidity = false; //Começando bit de validade falso e tag zerada.
        tag = 0;
    }

    public boolean getBitValidity() { return bitValidity; }
    public void setBitValidity(boolean bitValidity) { this.bitValidity = bitValidity; }

    public int getTag() { return tag; }
    public void setTag(int tag) { this.tag = tag; }
    }

