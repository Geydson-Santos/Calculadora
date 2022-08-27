/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package com.jp.main;

import java.awt.Font;
import java.awt.event.*;


/**
 *
 * @author aluno
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /** Creates new form TelaPrincipal */
    boolean emOperacao = false; // Para saber se está em operação no momento
    boolean virgula = false; // Para saber se já foi clicado na vírgula
    String saida = "0"; // Para contar quantos caracteres de número tem na string
    int contSaida = 0;
    int limiteDeTamanho = 21;
    Font montserrat = null;
    Font montserratSemibold = null;
    
    public TelaPrincipal() {
        try {
            montserratSemibold = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("com/jp/fontes/Montserrat-SemiBold.ttf"));
            montserrat = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("com/jp/fontes/Montserrat-Regular.ttf"));
        } catch (Exception e) {
            System.out.println("Merda " + e);
        }
        
        initComponents();
        this.setLocationRelativeTo(null);
        jLabel11.setVisible(false);
        jLabel12.setVisible(false);
        jLabel13.setVisible(false);
        jLabel14.setVisible(false);
        jLabel15.setVisible(false);
        jLabel16.setVisible(false);
        jLabel17.setVisible(false);
        jLabel18.setVisible(false);
        jLabel19.setVisible(false);
        jLabel10.setVisible(false);
    }
    
    
    public void atualizarResultado(String saida){
        jLabelResultado.setText(saida);
    }
    
    public void tamanhodeCaracteres(){
        if(contSaida < 9){
            jLabelResultado.setFont(montserrat.deriveFont(Font.PLAIN, 40));
        }else{
            if(contSaida < 12){
                jLabelResultado.setFont(montserrat.deriveFont(Font.PLAIN, 36));
            }else{
                if(contSaida < 15){
                    jLabelResultado.setFont(montserrat.deriveFont(Font.PLAIN, 28));
                }else{
                    if(contSaida < limiteDeTamanho){
                        jLabelResultado.setFont(montserrat.deriveFont(Font.PLAIN, 20));
                    }
                }
            }
        }
    }
    
    public void numero(int numero){
        if(contSaida < limiteDeTamanho){
            if(saida.equals("0")){
                switch(numero){
                    case 0:
                        saida = "0";
                        break;
                    case 1:
                        saida = "1";
                        break;
                    case 2:
                        saida = "2";
                        break;
                    case 3:
                        saida = "3";
                        break;
                    case 4:
                        saida = "4";
                        break;
                    case 5:
                        saida = "5";
                        break;
                    case 6:
                        saida = "6";
                        break;
                    case 7:
                        saida = "7";
                        break;
                    case 8:
                        saida = "8";
                        break;
                    case 9:
                        saida = "9";
                        break;
                }
            }else{
                saida += numero;
            }
            atualizarResultado(saida);
            emOperacao = false;
            contSaida++;
        }
        
        tamanhodeCaracteres();
    }
    
    public void apagar(){
        char caractere[] = saida.toCharArray();
        boolean comVirgula = false; // Para saber se o número atual tem vírgula ou não
        boolean comOperacao = false; // Para saber se o número atual tem vírgula ou não
        saida = "";
        if(caractere.length <= 1){
            saida = "0";
        }else{
            for(int i = 0; i < caractere.length-1; i++){
                if(caractere[i] == ','){
                    comVirgula = true;
                    if(caractere[i] == '+' || caractere[i] == '-' || caractere[i] == 'x' || caractere[i] == '÷'){
                        comOperacao = true;
                    }
                    if(comVirgula){
                        comOperacao = false;
                    }
                }
                saida+= caractere[i];
            }
            contSaida--;
        }
        
        if(emOperacao && (caractere[caractere.length-1] == 'x' || caractere[caractere.length-1] == '÷' || caractere[caractere.length-1] == '^')){
            emOperacao = false;
        }else{
            if(virgula){
                virgula = false;
            }
        }
        
        if(comVirgula){
            if(!comOperacao){
                virgula = true;
            }
        }
        
        atualizarResultado(saida);
        tamanhodeCaracteres();
    }
    
    public void igual(){
        if(saida.contains("+") || saida.contains("-") || saida.contains("x") || saida.contains("÷") || saida.contains("^") || saida.contains("√")){ // Se na saida tem + ou tem -
            double calculo = 0;
            saida = saida.replace(",", ".");
            System.out.println(saida);
            char caracteres[] = saida.toCharArray();
            
            System.out.println("Tamanho de caractere: " + caracteres.length + ". Primeiro caractere:" + caracteres[0]);
            
            /*
            String operacoes[] = saida.split("0|1|2|3|4|5|6|7|8|9"); // Tirando os números e espaços para que fique apenas as operações
            //String operacoes[] = operando.split("");
            
            System.out.print("O tamanho de operacoes e " + operacoes.length);
            System.out.println("     operacoes:" + operacoes[0] + "|" + operacoes[1] + "|");
            */
            
            if(caracteres[caracteres.length-1] == '-' || caracteres[caracteres.length-1] == '+' || caracteres[caracteres.length-1] == 'x' || caracteres[caracteres.length-1] == '÷' || caracteres[caracteres.length-1] == '^' || caracteres[caracteres.length-1] == '√'){
                // Se a pessoa clicar em um número e depois em uma operação, tirar a operação e deixar apenas o número
                if(saida.equals("-") || saida.equals("+")){
                    saida = "0";
                }else{
                    if(caracteres[caracteres.length-1] == '-' && caracteres[caracteres.length-2] == 'x' || caracteres[caracteres.length-2] == '÷' || caracteres[caracteres.length-2] == '^' || caracteres[caracteres.length-2] == '√'){
                        saida = "";
                        for(int i = 0; i < caracteres.length-2; i++){
                            saida += caracteres[i];
                        }
                    }else{
                        saida = "";
                        for(int i = 0; i < caracteres.length-1; i++){
                            saida += caracteres[i];
                        }
                    }
                }
                atualizarResultado(saida);
            }
            
            if(saida.contains("+") || saida.contains("-") || saida.contains("x") || saida.contains("÷") || saida.contains("^") || saida.contains("√")){
                boolean vezes_divisao = false;
                if(saida.contains("x") || saida.contains("÷")){
                    vezes_divisao = true;
                }
                
                char caractere[] = saida.toCharArray();
                saida = saida.replace("+", "mais");
                saida = saida.replace("-", "menos");
                saida = saida.replace("x", "vezes");
                saida = saida.replace("÷", "divisao");
                saida = saida.replace("^", "potencia");
                saida = saida.replace("√", "raiz");
                
                //String operacao[] = saida.split("0|1|2|3|4|5|6|7|8|9");
                
                
                String resultado[] = saida.split("mais|menos|vezes|divisao|potencia|raiz"); // Tirando a soma e a subtração para que fique apenas os números
                
                double numeros[] = new double[resultado.length];
                
                for(int i = 0; i < numeros.length; i++){
                    if(!resultado[i].equals(""))
                        numeros[i] = Double.parseDouble(resultado[i]);
                }
                
                System.out.println("Primeiro Resultado:"+resultado[0]);
                
                
                String operacoes = "";
                for(int i = 0; i < caractere.length; i++){
                    System.out.println(caractere[i]);
                    if(caractere[i] == 'x' || caractere[i] == '÷' || caractere[i] == '-' || caractere[i] == '+' || caractere[i] == '^' || caractere[i] == '√'){
                        operacoes += caractere[i];
                    }
                }
                
                char operacao[] = operacoes.toCharArray();
                
                
                for(int i = 0; i < operacao.length; i++){ // Para os números negativos
                    if(operacao[i] == '-'){
                        if(i > 0){
                            if(saida.contains("vezesmenos") || saida.contains("divisaomenos") || saida.contains("potenciamenos") || saida.contains("raizmenos")){
                                numeros[i+1] *= -1;
                                numeros[i] = numeros[i+1];
                                numeros[i+1] = 0;
                                operacao[i] = ' ';
                            }else{
                                if(operacao[i+1] == '√'){
                                    numeros[i+2] = -Math.pow(numeros[i+2], 1.0/2);
                                    operacao[i+1] = ' ';
                                }else{
                                    numeros[i+1] *= -1;
                                    operacao[i] = ' ';
                                }
                            }
                        }else{
                            if(caractere[0] == '-'){
                                numeros[1] *= -1;

                                operacao[i] = ' ';
                            }else{
                                if(i+1 <= operacao.length-1){
                                    if(operacao[i+1] == '√'){
                                        numeros[i+2] = -Math.pow(numeros[i+2], 1.0/2);
                                        operacao[i+1] = ' ';
                                    }else{
                                        numeros[i+1] *= -1;
                                    }
                                }else{
                                    numeros[i+1] *= -1;
                                }
                                
                                operacao[i] = ' ';
                            }
                        }
                    }
                    
                    //contResultado++;
                }
                
                for(int i = 0; i < operacao.length; i++){
                    switch(operacao[i]){
                        case '^':
                            numeros[i] = Math.pow(numeros[i], numeros[i+1]);
                            numeros[i+1] = 0;
                            break;
                        case '√':
                            numeros[i+1] = Math.pow(numeros[i+1], 1.0/2);
                            break;
                    }
                }
                
                for(int i = 0; i < operacao.length; i++){
                    System.out.println(i+1 + " operacao: " + operacao[i]);
                }

                int contResultado = 0;
                //int contOperacoes = 0;
                
                if(vezes_divisao){
                    for(int i = 0; i < operacao.length; i++){
                        if(operacao[i] == 'x'){
                            numeros[contResultado+1] = numeros[contResultado] * numeros[contResultado+1];
                            numeros[contResultado] = 0;

                            /*
                            calculo *= Float.parseFloat(resultado[contResultado]);
                            calculo *= Float.parseFloat(resultado[contResultado+1]);*/
                        }else{
                            if(operacao[i] == '÷'){
                                numeros[contResultado+1] = numeros[contResultado] / numeros[contResultado+1];
                                numeros[contResultado] = 0;
                            }
                        }
                        contResultado++;
                        
                    }
                    
                    /*
                    if(contOperacoes > 2){
                        for(int i = 2; i < operacao.length-1; i++){
                            if(operacao[i].contains("vezes")){
                                numeros[i] *= numeros[i] * numeros[i+1];
                                //calculo *= Float.parseFloat(resultado[contResultado]);
                                contResultado++;
                                }else{
                                    if(operacao[i].contains("divisao")){
                                        
                                    }
                                }
                        }
                    }*/
                    
                    
                    /*
                    for(int i = 0; i < caractere.length; i++){
                        if(caractere[i] == 'x' || caractere[i] == '/' || caractere[i] == '+' || caractere[i] == '-'){
                            if(caractere[i] == 'x'){
                                calculo *= Float.parseFloat(resultado[contResultado]);
                                calculo *= Float.parseFloat(resultado[contResultado+1]);
                            }else{
                                if(caractere[i] == '/'){

                                }
                            }
                            contResultado++;
                        }

                    }*/
                }
                
                /*
                if(saida.contains("mais") || saida.contains("menos")){
                    contResultado = 0;
                    if(caractere[0] != '-' && caractere[0] != '+'){ // Se o primeiro caractere não for '-' ou '+'
                        //for(int i = 0; i < )
                        for(int i = 0; i < caractere.length; i++){
                            if(caractere[i] == '+'){
                                calculo += Float.parseFloat(resultado[contResultado]);
                                calculo += Float.parseFloat(resultado[contResultado+1]);
                                contResultado+= 2;
                            }else{
                                if(caractere[i] == '-'){
                                    calculo += Float.parseFloat(resultado[contResultado]);
                                    calculo -= Float.parseFloat(resultado[contResultado+1]);
                                    contResultado+= 2;
                                }
                            }
                        }
                    }else{ //Se o primeiro caractere for '-' ou '+'
                        contResultado = 1;
                        for(int i = 0; i < caractere.length; i++){
                            if(caractere[i] == '+'){
                                calculo += Float.parseFloat(resultado[contResultado]);
                                contResultado++;
                            }else{
                                if(caractere[i] == '-'){
                                    calculo -= Float.parseFloat(resultado[contResultado]);
                                    contResultado++;
                                }
                            }
                        }
                    }
                    
                    
                    
                }*/
                
                
                /*
                for(int i = 0; i < operacoes.length; i++){
                    if(operacoes[i].contains("+")){
                        calculo += Integer.parseInt(resultado[i]);
                    }else{
                        if(operacoes[i].contains("-")){
                            calculo -= Integer.parseInt(resultado[i]);
                        }
                    }
                }*/


                /*
                if(saida.contains("+") && !saida.contains("-")){ // Se na saida tem +, mas n tem -
                    calculo = Integer.parseInt(resultado[0]);
                    for(int i = 1; i < resultado.length; i++){
                        calculo += Integer.parseInt(resultado[i]);
                    }
                }else{
                    if(saida.contains("-") && !saida.contains("+")){ // Se na saida tem -, mas n tem +
                        String resultado[] = saida.split(" menos ");
                        System.out.println(resultado[0]);
                        calculo = Integer.parseInt(resultado[0]);

                        for(int i = 1; i < resultado.length; i++){
                            calculo -= Integer.parseInt(resultado[i]);
                        }
                    }else{ // Na saida tem - e tem +
                        saida = saida.replace("+", "mais");
                        String resultado[] = saida.split(" mais ");
                        for(int i = 1; i < resultado.length - 1; i++){
                            calculo += Integer.parseInt(resultado[i]);
                        }
                        resultado[resultado.length-1] = resultado[resultado.length-1].replace("-", "menos");
                    }
                }
                */
                
                for(int i = 0; i < numeros.length; i++){
                    System.out.println(numeros[i]);
                    calculo += numeros[i];
                }
                
                if(calculo == (int) (calculo)){ // Se a resposta for inteira, truncar
                    saida = (int) (calculo) + "";
                }else{ // Senão, só mostrar
                    saida = calculo + "";
                    virgula = true;
                }
                
                saida = saida.replace(".", ",");
                
                contSaida = saida.length();
                tamanhodeCaracteres();
                atualizarResultado(saida);
            }
            
        }
        
        /*
        saida = saida.replace("+", "mais");
        String resultado[] = saida.split(" mais ");
        
        for(int i = 0; i < resultado.length; i++){
            calculo += Integer.parseInt(resultado[i]);
        }*/
    }
    
    public void virgula(){
        if(contSaida < limiteDeTamanho){
            if(!virgula && !emOperacao){
                char transferir[] = saida.toCharArray();
                System.out.println(transferir[transferir.length-1]);
                if(transferir.length-1 > 0){
                    if(transferir[transferir.length-1] != ',' && transferir[transferir.length-1] != '+' && transferir[transferir.length-1] != '-' && transferir[transferir.length-1] != 'x' && transferir[transferir.length-1] != '÷' && transferir[transferir.length-1] != '^' && transferir[transferir.length-1] != '√'){
                        saida += ",";
                        atualizarResultado(saida);
                        virgula = true;
                    }
                }else{
                    if(transferir[transferir.length-1] != ',' && transferir[transferir.length-1] != '+' && transferir[transferir.length-1] != '-'){
                        saida += ",";
                        atualizarResultado(saida);
                        virgula = true;
                    }
                }
                contSaida++;
            }
            
            tamanhodeCaracteres();
        }
    }
    
    public void menos(){
        if(contSaida < limiteDeTamanho){
            if(emOperacao){
                emOperacao = false;
                char transferir[] = saida.toCharArray();
                if(transferir[transferir.length-1] != 'x' && transferir[transferir.length-1] != '÷' && transferir[transferir.length-1] != '^' && transferir[transferir.length-1] != '√'){
                    saida = "";
                    for(int i = 0; i < transferir.length - 1; i++){
                        saida += transferir[i];
                    }
                    contSaida--;
                }
                /*
                if(saida.equals("+") || saida.equals("x") || saida.equals("-")){
                    saida = "";
                    for(int i = 0; i < transferir.length - 1; i++){
                        saida += transferir[i];
                    }
                    contSaida--;
                }else{
                    if(transferir[transferir.length-2] != 'x' && transferir[transferir.length-2] != '÷'){
                        saida = "";
                        for(int i = 0; i < transferir.length - 1; i++){
                            saida += transferir[i];
                        }
                        contSaida--;
                    }

                }*/

            }

            if(saida.equals("0") || saida.equals("")){
                saida ="-";
            }else{
                saida +="-";
                contSaida++;
            }
            atualizarResultado(saida);

            emOperacao = true;
            virgula = false;
            tamanhodeCaracteres();
        }
    }
    
    public void mais(){
        if(contSaida < limiteDeTamanho){
            if(emOperacao){
                emOperacao = false;
                char transferir[] = saida.toCharArray();
                if(saida.contains("x-") || saida.contains("÷-") || saida.contains("^-") || saida.contains("√-")){
                    saida = "";
                    for(int i = 0; i < transferir.length - 2; i++){
                        saida += transferir[i];
                    }
                    contSaida-= 2;
                }else{
                    saida = "";
                    for(int i = 0; i < transferir.length - 1; i++){
                        saida += transferir[i];
                    }
                    contSaida--;
                }
                
                /*
                if(saida.equals("-") || saida.equals("x") || saida.equals("÷")){
                    saida = "";
                    for(int i = 0; i < transferir.length - 1; i++){
                        saida += transferir[i];
                    }
                    contSaida--;
                }else{
                    if(saida.contains(" x  - ") || saida.contains(" ÷  - ")){
                        saida = "";
                        for(int i = 0; i < transferir.length - 6; i++){
                            saida += transferir[i];
                        }
                        contSaida-= 6;
                    }else{
                        saida = "";
                        for(int i = 0; i < transferir.length - 3; i++){
                            saida += transferir[i];
                        }
                        contSaida -= 3;
                    }
                }*/

            }

            if(saida.equals("0") || saida.equals("")){
                saida = "+";
            }else{
                saida +="+";
                contSaida++;
            }
            atualizarResultado(saida);

            emOperacao = true;
            virgula = false;
            tamanhodeCaracteres();
        }
    }
    
    public void vezes(){
        if(contSaida < limiteDeTamanho){
            if(emOperacao){
                emOperacao = false;
                char transferir[] = saida.toCharArray();
                if(saida.contains("x-") || saida.contains("÷-") || saida.contains("^-") || saida.contains("√-")){
                    saida = "";
                    for(int i = 0; i < transferir.length - 2; i++){
                        saida += transferir[i];
                    }
                    contSaida-= 2;
                }else{
                    saida = "";
                    for(int i = 0; i < transferir.length - 1; i++){
                        saida += transferir[i];
                    }
                    contSaida--;
                }
                
                /*
                if(saida.equals("+") || saida.equals("-")){
                    saida = "";
                    for(int i = 0; i < transferir.length - 1; i++){
                        saida += transferir[i];
                    }
                    contSaida--;
                }else{
                    if(saida.contains(" x  - ") || saida.contains(" ÷  - ")){
                        saida = "";
                        for(int i = 0; i < transferir.length - 6; i++){
                            saida += transferir[i];
                        }
                        contSaida-= 6;
                    }else{
                        saida = "";
                        for(int i = 0; i < transferir.length - 3; i++){
                            saida += transferir[i];
                        }
                        contSaida-= 3;
                    }

                }*/

            }

            if(saida.equals("0") || saida.equals("")){

            }else{
                saida +="x";
                contSaida++;
                atualizarResultado(saida);
                
                emOperacao = true;
                virgula = false;
                tamanhodeCaracteres();
            }
            
        }
    }
    
    public void divisao(){
        if(contSaida < limiteDeTamanho){
            if(emOperacao){
                emOperacao = false;
                char transferir[] = saida.toCharArray();
                if(saida.contains("x-") || saida.contains("÷-") || saida.contains("^-") || saida.contains("√-")){
                    saida = "";
                    for(int i = 0; i < transferir.length - 2; i++){
                        saida += transferir[i];
                    }
                    contSaida-= 2;
                }else{
                    saida = "";
                    for(int i = 0; i < transferir.length - 1; i++){
                        saida += transferir[i];
                    }
                    contSaida--;
                }
                
                /*
                if(saida.equals("+") || saida.equals("-")){
                    saida = "";
                    for(int i = 0; i < transferir.length - 1; i++){
                        saida += transferir[i];
                    }
                    contSaida--;
                }else{
                    if(saida.contains(" x  - ") || saida.contains(" ÷  - ")){
                        saida = "";
                        System.out.println("oi");
                        for(int i = 0; i < transferir.length - 6; i++){
                            saida += transferir[i];
                        }
                        contSaida-= 6;
                    }else{
                        saida = "";
                        for(int i = 0; i < transferir.length - 3; i++){
                            saida += transferir[i];
                        }
                        contSaida -= 3;
                    }
                }*/

            }

            if(saida.equals("0") || saida.equals("")){

            }else{
                saida +="÷";
                contSaida++;
                atualizarResultado(saida);

                emOperacao = true;
                virgula = false;
                tamanhodeCaracteres();
            }
        }
    }
    
    public void clear(){
        saida = "0";
        contSaida = 0;
        tamanhodeCaracteres();
        atualizarResultado(saida);
    }
    
    public void potencia(){
        if(contSaida < limiteDeTamanho){
            if(emOperacao){
                emOperacao = false;
                char transferir[] = saida.toCharArray();
                if(saida.contains("x-") || saida.contains("÷-") || saida.contains("^-") || saida.contains("√-")){
                    saida = "";
                    for(int i = 0; i < transferir.length - 2; i++){
                        saida += transferir[i];
                    }
                    contSaida-= 2;
                }else{
                    saida = "";
                    for(int i = 0; i < transferir.length - 1; i++){
                        saida += transferir[i];
                    }
                    contSaida--;
                }
            }
            
            if(saida.equals("0") || saida.equals("")){

            }else{
                saida +="^";
                contSaida++;
                atualizarResultado(saida);

                emOperacao = true;
                virgula = false;
                tamanhodeCaracteres();
            }
        }
    }
    
    public void raiz(){
        if(contSaida < limiteDeTamanho){
            if(emOperacao){
                emOperacao = false;
                char transferir[] = saida.toCharArray();
                if(transferir[transferir.length-1] != '+' && transferir[transferir.length-1] != '-'){
                    if(saida.contains("x-") || saida.contains("÷-") || saida.contains("^-") || saida.contains("√-")){
                        saida = "";
                        for(int i = 0; i < transferir.length - 2; i++){
                            saida += transferir[i];
                        }
                        contSaida-= 2;
                    }else{
                        saida = "";
                        for(int i = 0; i < transferir.length - 1; i++){
                            saida += transferir[i];
                        }
                        contSaida--;
                    }
                }
            }
            
            if(saida.equals("0") || saida.equals("")){
                saida = "√";
            }else{
                char transferir[] = saida.toCharArray();
                if(transferir[transferir.length-1] == '+' || transferir[transferir.length-1] == '-'){
                    System.out.println("oi");
                    saida +="√";
                }else{
                    saida +="+√";
                }
                contSaida++;
            }
            atualizarResultado(saida);

            emOperacao = true;
            virgula = false;
            tamanhodeCaracteres();
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel0 = new javax.swing.JLabel();
        jLabelApagar = new javax.swing.JLabel();
        jLabelDivisao = new javax.swing.JLabel();
        jLabelVezes = new javax.swing.JLabel();
        jLabelMenos = new javax.swing.JLabel();
        jLabelMais = new javax.swing.JLabel();
        jLabelIgual = new javax.swing.JLabel();
        jLabelResultado = new javax.swing.JLabel();
        jLabelClear = new javax.swing.JLabel();
        jLabelPotencia = new javax.swing.JLabel();
        jLabelRaiz = new javax.swing.JLabel();
        jLabelVirgula = new javax.swing.JLabel();
        jLabelFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculadora");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(270, 450));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png"))); // NOI18N
        getContentPane().add(jLabel11);
        jLabel11.setBounds(10, 280, 60, 80);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png"))); // NOI18N
        getContentPane().add(jLabel12);
        jLabel12.setBounds(70, 280, 60, 80);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png"))); // NOI18N
        getContentPane().add(jLabel13);
        jLabel13.setBounds(130, 280, 60, 80);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png"))); // NOI18N
        getContentPane().add(jLabel14);
        jLabel14.setBounds(10, 220, 60, 80);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png"))); // NOI18N
        getContentPane().add(jLabel15);
        jLabel15.setBounds(70, 220, 60, 80);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png"))); // NOI18N
        getContentPane().add(jLabel16);
        jLabel16.setBounds(130, 220, 60, 80);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png"))); // NOI18N
        getContentPane().add(jLabel17);
        jLabel17.setBounds(10, 160, 60, 80);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png"))); // NOI18N
        getContentPane().add(jLabel18);
        jLabel18.setBounds(70, 160, 60, 80);

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png"))); // NOI18N
        getContentPane().add(jLabel19);
        jLabel19.setBounds(130, 160, 60, 80);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png"))); // NOI18N
        getContentPane().add(jLabel10);
        jLabel10.setBounds(70, 340, 60, 80);

        jLabel1.setFont(montserrat.deriveFont(Font.PLAIN, 40));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("1");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel1MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 290, 60, 60);

        jLabel2.setFont(montserrat.deriveFont(Font.PLAIN, 40));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("2");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel2MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 290, 60, 60);

        jLabel3.setFont(montserrat.deriveFont(Font.PLAIN, 40));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("3");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel3MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel3);
        jLabel3.setBounds(130, 290, 60, 60);

        jLabel4.setFont(montserrat.deriveFont(Font.PLAIN, 40));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("4");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel4MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 230, 60, 60);

        jLabel5.setFont(montserrat.deriveFont(Font.PLAIN, 40));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("5");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel5MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel5);
        jLabel5.setBounds(70, 230, 60, 60);

        jLabel6.setFont(montserrat.deriveFont(Font.PLAIN, 40));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("6");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel6MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel6);
        jLabel6.setBounds(130, 230, 60, 60);

        jLabel7.setFont(montserrat.deriveFont(Font.PLAIN, 40));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("7");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel7MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 170, 60, 60);

        jLabel8.setFont(montserrat.deriveFont(Font.PLAIN, 40));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("8");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel8MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel8);
        jLabel8.setBounds(70, 170, 60, 60);

        jLabel9.setFont(montserrat.deriveFont(Font.PLAIN, 40));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("9");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel9MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel9);
        jLabel9.setBounds(130, 170, 60, 60);

        jLabel0.setBackground(new java.awt.Color(51, 51, 51));
        jLabel0.setFont(montserrat.deriveFont(Font.PLAIN, 40));
        jLabel0.setForeground(new java.awt.Color(255, 255, 255));
        jLabel0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel0.setText("0");
        jLabel0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel0MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel0MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel0MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel0MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel0MouseReleased(evt);
            }
        });
        getContentPane().add(jLabel0);
        jLabel0.setBounds(70, 350, 60, 60);

        jLabelApagar.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabelApagar.setForeground(new java.awt.Color(255, 0, 0));
        jLabelApagar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelApagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Apagar.png"))); // NOI18N
        jLabelApagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelApagarMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelApagar);
        jLabelApagar.setBounds(200, 90, 50, 60);

        jLabelDivisao.setFont(montserratSemibold.deriveFont(Font.PLAIN, 56));
        jLabelDivisao.setForeground(new java.awt.Color(204, 204, 0));
        jLabelDivisao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDivisao.setText("÷");
        jLabelDivisao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelDivisaoMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelDivisao);
        jLabelDivisao.setBounds(200, 150, 50, 50);

        jLabelVezes.setFont(montserratSemibold.deriveFont(Font.PLAIN, 36));
        jLabelVezes.setForeground(new java.awt.Color(204, 204, 0));
        jLabelVezes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVezes.setText("x");
        jLabelVezes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelVezesMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelVezes);
        jLabelVezes.setBounds(200, 200, 50, 50);

        jLabelMenos.setFont(montserratSemibold.deriveFont(Font.PLAIN, 56));
        jLabelMenos.setForeground(new java.awt.Color(204, 204, 0));
        jLabelMenos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMenos.setText("-");
        jLabelMenos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMenosMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelMenos);
        jLabelMenos.setBounds(200, 250, 50, 50);

        jLabelMais.setFont(montserratSemibold.deriveFont(Font.PLAIN, 56));
        jLabelMais.setForeground(new java.awt.Color(204, 204, 0));
        jLabelMais.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMais.setText("+");
        jLabelMais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMaisMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelMais);
        jLabelMais.setBounds(200, 300, 50, 50);

        jLabelIgual.setFont(montserratSemibold.deriveFont(Font.PLAIN, 56));
        jLabelIgual.setForeground(new java.awt.Color(255, 255, 255));
        jLabelIgual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIgual.setText("=");
        jLabelIgual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelIgualMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelIgual);
        jLabelIgual.setBounds(200, 350, 50, 50);

        jLabelResultado.setFont(montserrat.deriveFont(Font.PLAIN, 40));
        jLabelResultado.setForeground(new java.awt.Color(255, 255, 255));
        jLabelResultado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelResultado.setText("0");
        getContentPane().add(jLabelResultado);
        jLabelResultado.setBounds(10, 20, 240, 50);

        jLabelClear.setFont(montserratSemibold.deriveFont(Font.PLAIN, 36));
        jLabelClear.setForeground(new java.awt.Color(206, 104, 104));
        jLabelClear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelClear.setText("C");
        jLabelClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelClearMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelClear);
        jLabelClear.setBounds(10, 110, 60, 50);

        jLabelPotencia.setFont(montserratSemibold.deriveFont(Font.PLAIN, 36));
        jLabelPotencia.setForeground(new java.awt.Color(204, 204, 0));
        jLabelPotencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPotencia.setText("^");
        jLabelPotencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPotenciaMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelPotencia);
        jLabelPotencia.setBounds(70, 110, 60, 50);

        jLabelRaiz.setFont(montserratSemibold.deriveFont(Font.PLAIN, 36));
        jLabelRaiz.setForeground(new java.awt.Color(204, 204, 0));
        jLabelRaiz.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRaiz.setText("√");
        jLabelRaiz.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabelRaiz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelRaizMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelRaiz);
        jLabelRaiz.setBounds(130, 110, 60, 50);

        jLabelVirgula.setFont(montserrat.deriveFont(Font.PLAIN, 40));
        jLabelVirgula.setForeground(new java.awt.Color(255, 255, 255));
        jLabelVirgula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVirgula.setText(",");
        jLabelVirgula.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabelVirgula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelVirgulaMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelVirgula);
        jLabelVirgula.setBounds(130, 350, 60, 50);

        jLabelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/TelaPrincipal.png"))); // NOI18N
        jLabelFundo.setRequestFocusEnabled(false);
        jLabelFundo.setVerifyInputWhenFocusTarget(false);
        jLabelFundo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jLabelFundoKeyTyped(evt);
            }
        });
        getContentPane().add(jLabelFundo);
        jLabelFundo.setBounds(0, 0, 260, 420);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        numero(1);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        numero(2);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        numero(3);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        numero(4);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        numero(5);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        numero(6);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        numero(7);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        numero(8);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        numero(9);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel0MouseClicked
        // TODO add your handling code here:
        numero(0);
    }//GEN-LAST:event_jLabel0MouseClicked

    private void jLabelMaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMaisMouseClicked
        // TODO add your handling code here:
        mais();
    }//GEN-LAST:event_jLabelMaisMouseClicked

    private void jLabelMenosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMenosMouseClicked
        // TODO add your handling code here:
        menos();
    }//GEN-LAST:event_jLabelMenosMouseClicked

    private void jLabelIgualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelIgualMouseClicked
        // TODO add your handling code here:
        igual();
    }//GEN-LAST:event_jLabelIgualMouseClicked

    private void jLabelApagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelApagarMouseClicked
        // TODO add your handling code here:
        apagar();
    }//GEN-LAST:event_jLabelApagarMouseClicked

    private void jLabelVezesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelVezesMouseClicked
        // TODO add your handling code here:
        vezes();
    }//GEN-LAST:event_jLabelVezesMouseClicked

    private void jLabelDivisaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDivisaoMouseClicked
        // TODO add your handling code here:
        divisao();
    }//GEN-LAST:event_jLabelDivisaoMouseClicked

    private void jLabelClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelClearMouseClicked
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jLabelClearMouseClicked

    private void jLabelVirgulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelVirgulaMouseClicked
        // TODO add your handling code here:
        virgula();
    }//GEN-LAST:event_jLabelVirgulaMouseClicked

    private void jLabelPotenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPotenciaMouseClicked
        // TODO add your handling code here:
        potencia();
    }//GEN-LAST:event_jLabelPotenciaMouseClicked

    private void jLabelRaizMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelRaizMouseClicked
        // TODO add your handling code here:
        raiz();
    }//GEN-LAST:event_jLabelRaizMouseClicked

    private void jLabelFundoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelFundoKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jLabelFundoKeyTyped

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        // TODO add your handling code here:
        char e = evt.getKeyChar();
        
        switch(e){
            case '1':
                numero(1);
                break;
            case '2':
                numero(2);
                break;
            case '3':
                numero(3);
                break;
            case '4':
                numero(4);
                break;
            case '5':
                numero(5);
                break;
            case '6':
                numero(6);
                break;
            case '7':
                numero(7);
                break;
            case '8':
                numero(8);
                break;
            case '9':
                numero(9);
                break;
            case '0':
                numero(0);
                break;
            case '-':
                menos();
                break;
            case '+':
                mais();
                break;
            case '*':
                vezes();
                break;
            case '/':
                divisao();
                break;
            case '~':
                potencia();
                break;
            case 'r':
                raiz();
                break;
        }
        
    }//GEN-LAST:event_formKeyTyped

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        
        switch(evt.getKeyCode()){
            case KeyEvent.VK_BACK_SPACE:
                apagar();
                break;
            case KeyEvent.VK_ENTER:
                igual();
                break;
            case KeyEvent.VK_COMMA:
                virgula();
                break;
            case KeyEvent.VK_ESCAPE:
                clear();
                break;
        }
    }//GEN-LAST:event_formKeyPressed

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
        jLabel11.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        // TODO add your handling code here:
        jLabel11.setVisible(false);
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        // TODO add your handling code here:
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Click.png")));
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseReleased
        // TODO add your handling code here:
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png")));
    }//GEN-LAST:event_jLabel1MouseReleased

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
        jLabel12.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // TODO add your handling code here:
        jLabel12.setVisible(false);
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        // TODO add your handling code here:
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Click.png")));
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseReleased
        // TODO add your handling code here:
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png")));
    }//GEN-LAST:event_jLabel2MouseReleased

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        // TODO add your handling code here:
        jLabel13.setVisible(true);
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        // TODO add your handling code here:
        jLabel13.setVisible(false);
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        // TODO add your handling code here:
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Click.png")));
    }//GEN-LAST:event_jLabel3MousePressed

    private void jLabel3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseReleased
        // TODO add your handling code here:
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png")));
    }//GEN-LAST:event_jLabel3MouseReleased

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        // TODO add your handling code here:
        jLabel14.setVisible(true);
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        // TODO add your handling code here:
        jLabel14.setVisible(false);
    }//GEN-LAST:event_jLabel4MouseExited

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        // TODO add your handling code here:
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Click.png")));
    }//GEN-LAST:event_jLabel4MousePressed

    private void jLabel4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseReleased
        // TODO add your handling code here:
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png")));
    }//GEN-LAST:event_jLabel4MouseReleased

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        // TODO add your handling code here:
        jLabel15.setVisible(true);
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        // TODO add your handling code here:
        jLabel15.setVisible(false);
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        // TODO add your handling code here:
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Click.png")));
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseReleased
        // TODO add your handling code here:
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png")));
    }//GEN-LAST:event_jLabel5MouseReleased

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
        // TODO add your handling code here:
        jLabel16.setVisible(true);
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
        // TODO add your handling code here:
        jLabel16.setVisible(false);
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        // TODO add your handling code here:
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Click.png")));
    }//GEN-LAST:event_jLabel6MousePressed

    private void jLabel6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseReleased
        // TODO add your handling code here:
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png")));
    }//GEN-LAST:event_jLabel6MouseReleased

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        // TODO add your handling code here:
        jLabel17.setVisible(true);
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        // TODO add your handling code here:
        jLabel17.setVisible(false);
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MousePressed
        // TODO add your handling code here:
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Click.png")));
    }//GEN-LAST:event_jLabel7MousePressed

    private void jLabel7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseReleased
        // TODO add your handling code here:
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png")));
    }//GEN-LAST:event_jLabel7MouseReleased

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        // TODO add your handling code here:
        jLabel18.setVisible(true);
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseExited
        // TODO add your handling code here:
        jLabel18.setVisible(false);
    }//GEN-LAST:event_jLabel8MouseExited

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        // TODO add your handling code here:
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Click.png")));
    }//GEN-LAST:event_jLabel8MousePressed

    private void jLabel8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseReleased
        // TODO add your handling code here:
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png")));
    }//GEN-LAST:event_jLabel8MouseReleased

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
        // TODO add your handling code here:
        jLabel19.setVisible(true);
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
        // TODO add your handling code here:
        jLabel19.setVisible(false);
    }//GEN-LAST:event_jLabel9MouseExited

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        // TODO add your handling code here:
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Click.png")));
    }//GEN-LAST:event_jLabel9MousePressed

    private void jLabel9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseReleased
        // TODO add your handling code here:
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png")));
    }//GEN-LAST:event_jLabel9MouseReleased

    private void jLabel0MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel0MouseEntered
        // TODO add your handling code here:
        jLabel10.setVisible(true);
    }//GEN-LAST:event_jLabel0MouseEntered

    private void jLabel0MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel0MouseExited
        // TODO add your handling code here:
        jLabel10.setVisible(false);
    }//GEN-LAST:event_jLabel0MouseExited

    private void jLabel0MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel0MousePressed
        // TODO add your handling code here:
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Click.png")));
    }//GEN-LAST:event_jLabel0MousePressed

    private void jLabel0MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel0MouseReleased
        // TODO add your handling code here:
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jp/imagens/Circulo_Transparente_Enter.png")));
    }//GEN-LAST:event_jLabel0MouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
      
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel0;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelApagar;
    private javax.swing.JLabel jLabelClear;
    private javax.swing.JLabel jLabelDivisao;
    private javax.swing.JLabel jLabelFundo;
    private javax.swing.JLabel jLabelIgual;
    private javax.swing.JLabel jLabelMais;
    private javax.swing.JLabel jLabelMenos;
    private javax.swing.JLabel jLabelPotencia;
    private javax.swing.JLabel jLabelRaiz;
    private javax.swing.JLabel jLabelResultado;
    private javax.swing.JLabel jLabelVezes;
    private javax.swing.JLabel jLabelVirgula;
    // End of variables declaration//GEN-END:variables

}
