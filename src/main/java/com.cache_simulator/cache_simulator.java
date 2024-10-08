/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.cache_simulator;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author vee
 */
public class cache_simulator {
    public static void main(String[] args) throws Exception {
        if (args.length != 6){
            System.out.println(args.length);
            System.out.println("Número de argumentos inválido. Siga a estrutura abaixo: \n cache_simulator <nsets> <bsize> <assoc> <substituição> <flag_saida> <arquivo_de_entrada>");
            System.exit(1);}

        int nsets = Integer.parseInt(args[0]);
        int bsize = Integer.parseInt(args[1]);
        int assoc = Integer.parseInt(args[2]);
        String subst = args[3];
        int flagOut = Integer.parseInt(args[4]);
        String arquivoEntrada = args[5];

        Cache cache = new Cache(nsets, bsize, assoc, subst.charAt(0));
        FileInputStream fileInputStream = null;
        try{
            String filePath =  "src/main/resources/" + arquivoEntrada;
            fileInputStream = new FileInputStream(filePath);
            byte[] bytes = new byte[4];
            int bitsAdress;
            while(fileInputStream.read(bytes) != -1){
                bitsAdress = cache.bytesToInt(bytes);
                cache.CacheOperation(bitsAdress);}
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);}
        finally{
            try{
                if(fileInputStream != null){
                    fileInputStream.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }}
        cache.printOUT(flagOut);
    }
}
