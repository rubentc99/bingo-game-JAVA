/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoimf;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author RubenTábaraCenador
 */
public class BingoIMF{
    //defino una funcion para limpiar pantalla
    public static void limpiar_pantalla(){
        for(int i=0;i<20;i++) //para un i de 0 a 200, te escribe un espacio (LIMPIAR PANTALLA)
            System.out.println("");
    }
    
    public static int aleatorios(){ // Genera y devuelve un número aleatorio
        Random aleatorio = new Random();
        int numerosAleatorios = aleatorio.nextInt(99)+1;
        return numerosAleatorios;
    }
    
    public static void burbuja(int[] A){
        int i, j, aux;
        for (i = 0; i < A.length - 1;i++){
            for (j = 0; j < A.length - i - 1;j++){
                if (A[j+1] < A[j]){
                    aux = A[j +1];
                    A[j+1] = A[j];
                    A[j] = aux;
                }
            }
        }
    }
    
    //Inicializa y devuelve un carton de un jugador
    public static int[] generarCartonJugador(int carton[], int numJugador, int numeroCarton){
        for (int i = 0; i < carton.length; i++) {
            carton[i]=aleatorios();
            for (int j = 0; j < i; j++) {
                if(i>0 && carton[j]==carton[i]){
                    i--;
                    j=i;        
                } 
            }
        }
        burbuja(carton);
      
        System.out.println("");
        System.out.println("");
        System.out.println("El carton "+numeroCarton+" del jugador "+numJugador +" es:");
        System.out.println(""); 
        for (int i = 0; i < carton.length; i++) {
            System.out.println(carton[i]);   
        }
        
        return carton;

    }
    
    public static int[] sacarBolaBingo(int bolasBingo[]){
        boolean numeroOk = false;
        boolean repetido = false;
        int nuevaBola = -1;
        int i = 0;
        
        while (!numeroOk){
            nuevaBola = aleatorios();
            repetido = false;
            i = 0;
            while (i < bolasBingo.length  && !repetido && bolasBingo[i]!=0){
                if(bolasBingo[i]==nuevaBola){
                    repetido = true;
                }else{
                    i++;
                }
            }
            if (!repetido){
                numeroOk = true;
                bolasBingo[i]=nuevaBola;
            }
            
        }
        System.out.println("Nueva Bola con número: "+nuevaBola);
      
        return bolasBingo;
    }
    
    public static int[] tacharNumberoCarton (int bolasBingo[],int carton[], int numJugador, int numeroCarton){
        int i=0;
        while (i < bolasBingo.length  &&  bolasBingo[i]!=0){
            i ++;
        }
        //el bucle termmina en la posición siguiente a la última bola.
        
        int ultimaBola = 0;
            
        if (i ==0){
           ultimaBola= bolasBingo[i];
        }else{
           ultimaBola= bolasBingo[i-1];
        }
        
        i=0;
        boolean encontrado = false;
        //buscar numero en carton
        while (i < carton.length && !encontrado && carton[i] <= ultimaBola){
            if (carton[i] == ultimaBola){
                encontrado = true;
            }else{
                i++;
            }   
        }
        
        if (encontrado){
            carton[i] = 0;  // tachamos ese numero del carton inicializándolo a 0
            System.out.println("Número "+ultimaBola+" encontrado en el carton "+numeroCarton+" del jugador "+numJugador);
        }
        
        return carton;
    }
    
    
    public static boolean esBingo(int carton[]){
        boolean esBingo = false;
        boolean encontrado = false;
        int i = 0;
        
        while (i < carton.length && !encontrado ){
            if (carton[i] > 0){  //buscamos un número mayor que 0 que nos diga que todavía no hay Bingo.
                encontrado = true;
            }else{
                i++;
            }   
        }
     
        if (!encontrado){ //Si no se ha encontrado ningún número mayor que 0, todos los números del cartón han sido tachados, porl o tanto tenemos Bingo.
            esBingo = true;
        }
                
        return esBingo;
    }
    
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        //defino e inicializo las variables que voy a utilizar
        int jugadores=0; //numero total de jugadores
        int contadorJugadores=0;
        int numJugador = 0;
        int carton=0; //verifica si se ha introducido una cantidad de cartones errónea
        //número de cartones de cada jugador
        int cartonJugador1=0;  
        int cartonJugador2=0;
        int cartonJugador3=0;
        int cartonJugador4=0;
        //empieza el juego
        System.out.println("Bienvenido al bingo de IMF, escriba de 1 a 4 el número de jugadores.");
        jugadores=lector.nextInt();
        numJugador=jugadores;
        while (jugadores<1 || jugadores>4){ //mientras se introduzca un numero erroneo de jugadores, dará un error
            System.out.println("");
            System.out.println("Error, el número de jugadores tiene que estar entre 1 y 4.");
            System.out.println("Escriba entre 1 y 4 el número de jugadores.");
            jugadores=lector.nextInt();
            numJugador=jugadores; //lleva la cuenta de jugadores para mostrarla por pantalla
        }
        limpiar_pantalla();
        while (numJugador>0){ //bucle encargado de ir preguntado jugador por jugador cuantos cartones va a usar 
            while(carton<=0 || carton>2){ //buble que pregunta cuantos cartones va a querer
                System.out.println("jugador "+numJugador+" , escriba 1 o 2 según los cartones que va a jugar (cada carton tiene un precio de 5€).");
                carton=lector.nextInt();
                if(carton<=0 || carton>2){ //si se introduce un numero de cartones erroneo, saltará este mensaje
                    System.out.println("");
                    System.out.println("Error, la cantidad minima de cartones es 1 y la maxima es 2");
                    System.out.println(""); 
                }
            }
            //asignacion de cartones a cada variable correspondiente
            if(numJugador==1){  
                cartonJugador1=carton;
            }
            if (numJugador==2){
                cartonJugador2=carton;
            }
            if (numJugador==3){
                cartonJugador3=carton;
            }
            if (numJugador==4){
                cartonJugador4=carton;
            }
            numJugador--;
            carton=0;
        }
        //creo los array que voy a utilizar
        int bolasBingo[]=new int[99];
        int carton1[]=new int [15];
        int carton2[]=new int [15];
        int carton3[]=new int [15];
        int carton4[]=new int [15];
        int carton5[]=new int [15];
        int carton6[]=new int [15];
        int carton7[]=new int [15];
        int carton8[]=new int [15];
        
        
        numJugador = 0;
        int numeroCarton = 0;
        
        if (cartonJugador1 > 0){
            numJugador = 1;
            numeroCarton = 1;
            carton1 = generarCartonJugador(carton1, numJugador, numeroCarton);
            if (cartonJugador1 == 2){
                numeroCarton = 2;
                carton2 = generarCartonJugador(carton2, numJugador, numeroCarton);
            }
        }
        if (cartonJugador2 > 0){
            numJugador = 2;
            numeroCarton = 1;
            carton3 = generarCartonJugador(carton3, numJugador, numeroCarton);
            if (cartonJugador2 == 2){
                numeroCarton = 2;
                carton4 = generarCartonJugador(carton4, numJugador, numeroCarton);
            }
        }
        if (cartonJugador3 > 0){
            numJugador = 3;
            numeroCarton = 1;
            carton5 = generarCartonJugador(carton5, numJugador, numeroCarton);
            if (cartonJugador3 == 2){
                numeroCarton = 2;
                carton6 = generarCartonJugador(carton6, numJugador, numeroCarton);
            }
        }
        if (cartonJugador4 > 0){
            numJugador = 4;
            numeroCarton = 1;
            carton7 = generarCartonJugador(carton7, numJugador, numeroCarton);
            if (cartonJugador4 == 2){
                numeroCarton = 2;
                carton8 = generarCartonJugador(carton8, numJugador, numeroCarton);
            }
        }
        
        int bingo = 0;
        
        while (bingo ==0){
            
            bolasBingo = sacarBolaBingo (bolasBingo);
            
            if (cartonJugador1 > 0){
                numJugador = 1;
                numeroCarton = 1;
                carton1 = tacharNumberoCarton (bolasBingo ,carton1, numJugador, numeroCarton);
                
                if (esBingo(carton1)){
                    bingo=1;
                }
                
                if (cartonJugador1 == 2){
                    numeroCarton = 2;
                    carton2 = tacharNumberoCarton (bolasBingo ,carton2, numJugador, numeroCarton);
                    
                    if (esBingo(carton2)){
                        bingo=1;
                    }
                }
            }
            if (cartonJugador2 > 0){
                numJugador = 2;
                numeroCarton = 1;
                carton3 = tacharNumberoCarton (bolasBingo ,carton3, numJugador, numeroCarton);
                if (esBingo(carton3)){
                    bingo=2;
                }
                if (cartonJugador2 == 2){
                    numeroCarton = 2;
                    carton4 = tacharNumberoCarton (bolasBingo ,carton4, numJugador, numeroCarton);
                    if (esBingo(carton4)){
                        bingo=2;
                    }
                }
            }
            if (cartonJugador3 > 0){
                numJugador = 3;
                numeroCarton = 1;
                carton5 = tacharNumberoCarton (bolasBingo ,carton5, numJugador, numeroCarton);
                if (esBingo(carton5)){
                    bingo=3;
                }
                if (cartonJugador3 == 2){
                    numeroCarton = 2;
                    carton6 = tacharNumberoCarton (bolasBingo ,carton6, numJugador, numeroCarton);
                    if (esBingo(carton6)){
                        bingo=3;
                    }                    
                }
            }
            if (cartonJugador4 > 0){
                numJugador = 4;
                numeroCarton = 1;
                carton7 = tacharNumberoCarton (bolasBingo ,carton7, numJugador, numeroCarton);
                if (esBingo(carton7)){
                    bingo=4;
                }
                if (cartonJugador4 == 2){
                    numeroCarton = 2;
                    carton8 = tacharNumberoCarton (bolasBingo ,carton8, numJugador, numeroCarton);
                    if (esBingo(carton8)){
                        bingo=4;
                    }                    
                }
            }  
            
        }
        
        System.out.println("BINGO!!! Ha ganado el jugador " +bingo);
        //while bingo ==0 and not finDeJuego 
            //sacar bola
             
            //tachar numbero en jugador 
            
        
        //print ha ganado el jugador bingo
        
        
        //comprar carton 
        
    }
    
 /*   
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        //defino e inicializo las variables que voy a utilizar
        int jugadores=0;
        int contadorJugadores=0;
        int carton=0;
        int contadorBingo=0;
        boolean bingo=false;
        int cartonJugador1=0;
        int cartonJugador2=0;
        int cartonJugador3=0;
        int cartonJugador4=0;
        //empieza el juego
        System.out.println("Bienvenido al bingo de IMF, escriba de 1 a 4 el número de jugadores.");
        jugadores=lector.nextInt();
        contadorJugadores=jugadores;
        while (jugadores<1 || jugadores>4){ //mientras se introduzca un numero erroneo de jugadores, dara un error
            System.out.println("");
            System.out.println("Error, el número de jugadores tiene que estar entre 1 y 4.");
            System.out.println("Escriba entre 1 y 4 el número de jugadores.");
            jugadores=lector.nextInt();
            contadorJugadores=jugadores; //lleva la cuenta de jugadores para mostrarla por pantalla
        }
        limpiar_pantalla();
        while (contadorJugadores>0){ //bucle encargado de ir preguntado jugador por jugador cuantos cartones va a usar 
            while(carton<=0 || carton>2){ //buble que pregunta cuantos cartones va a querer
                System.out.println("jugador "+contadorJugadores+" , escriba 1 o 2 según los cartones que va a jugar (cada carton tiene un precio de 5€).");
                carton=lector.nextInt();
                if(carton<=0 || carton>2){ //si se introduce un numero de cartones erroneo, saltará este mensaje
                    System.out.println("");
                    System.out.println("Error, la cantidad minima de cartones es 1 y la maxima es 2");
                    System.out.println(""); 
                }
            }
            //asignacion de cartones a cada variable correspondiente
            if(contadorJugadores==1){  
                cartonJugador1=carton;
            }
            if (contadorJugadores==2){
                cartonJugador2=carton;
            }
            if (contadorJugadores==3){
                cartonJugador3=carton;
            }
            if (contadorJugadores==4){
                cartonJugador4=carton;
            }
            contadorJugadores--;
            carton=0;
        }
        int bolasBingo[]=new int[99];
        int carton1[]=new int [15];
        int carton2[]=new int [15];
        int carton3[]=new int [15];
        int carton4[]=new int [15];
        int carton5[]=new int [15];
        int carton6[]=new int [15];
        int carton7[]=new int [15];
        int carton8[]=new int [15];
        contadorJugadores=jugadores; //vuelvo a asignar al contador el numero de jugadores
        while(contadorJugadores>0){ //bucle que asigna los numeros a los cartones de los jugadores hasta que el contador de jugadores sea 0
            System.out.println("");
            System.out.println("Primer carton del jugador"+contadorJugadores);
            System.out.println("");
            //jugador4
            if(contadorJugadores==4){
                for (int i = 0; i < carton7.length; i++) {
                    carton7[i]=aleatorios();
                    for (int j = 0; j < i; j++) {
                        if(i>0 && carton7[j]==carton7[i]){
                            i--;
                            j=i;        
                        } 
                    }
                } 
                burbuja(carton7);
                for (int i = 0; i < carton7.length; i++) {
                    System.out.println(carton7[i]);   
                }
                if (cartonJugador4==2){
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Segundo carton del jugador"+contadorJugadores);
                    System.out.println("");
                    for (int i = 0; i < carton8.length; i++) {
                        carton8[i]=aleatorios();
                        for (int j = 0; j < i; j++) {
                            if(i>0 && carton8[j]==carton8[i]){
                                i--;
                                j=i;        
                            } 
                        }
                    } 
                    burbuja(carton8);
                    for (int i = 0; i < carton8.length; i++) {
                        System.out.println(carton8[i]);   
                    }
                }
            }
            //jugador3
            if(contadorJugadores==3){
                for (int i = 0; i < carton5.length; i++) {
                    carton5[i]=aleatorios();
                    for (int j = 0; j < i; j++) {
                        if(i>0 && carton5[j]==carton5[i]){
                            i--;
                            j=i;        
                        } 
                    }
                } 
                burbuja(carton5);
                for (int i = 0; i < carton5.length; i++) {
                    System.out.println(carton5[i]);   
                }
                if (cartonJugador3==2){
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Segundo carton del jugador"+contadorJugadores);
                    System.out.println("");
                    for (int i = 0; i < carton6.length; i++) {
                        carton6[i]=aleatorios();
                        for (int j = 0; j < i; j++) {
                            if(i>0 && carton6[j]==carton6[i]){
                                i--;
                                j=i;        
                            } 
                        }
                    } 
                    burbuja(carton6);
                    for (int i = 0; i < carton6.length; i++) {
                        System.out.println(carton6[i]);   
                    }
                }
            }
            //jugador2
            if(contadorJugadores==2){
                for (int i = 0; i < carton3.length; i++) {
                    carton3[i]=aleatorios();
                    for (int j = 0; j < i; j++) {
                        if(i>0 && carton3[j]==carton3[i]){
                            i--;
                            j=i;        
                        } 
                    }
                } 
                burbuja(carton3);
                for (int i = 0; i < carton3.length; i++) {
                    System.out.println(carton3[i]);   
                }
                if (cartonJugador2==2){
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Segundo carton del jugador"+contadorJugadores);
                    System.out.println("");
                    for (int i = 0; i < carton4.length; i++) {
                        carton4[i]=aleatorios();
                        for (int j = 0; j < i; j++) {
                            if(i>0 && carton4[j]==carton4[i]){
                                i--;
                                j=i;        
                            } 
                        }
                    } 
                    burbuja(carton4);
                    for (int i = 0; i < carton4.length; i++) {
                        System.out.println(carton4[i]);   
                    }
                }
            }
            //jugador1
            if(contadorJugadores==1){
                for (int i = 0; i < carton1.length; i++) {
                    carton1[i]=aleatorios();
                    for (int j = 0; j < i; j++) {
                        if(i>0 && carton1[j]==carton1[i]){
                            i--;
                            j=i;        
                        } 
                    }
                } 
                burbuja(carton1);
                for (int i = 0; i < carton1.length; i++) {
                    System.out.println(carton1[i]);   
                }
                if (cartonJugador1==2){
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Segundo carton del jugador"+contadorJugadores);
                    System.out.println("");
                    for (int i = 0; i < carton2.length; i++) {
                        carton2[i]=aleatorios();
                        for (int j = 0; j < i; j++) {
                            if(i>0 && carton2[j]==carton2[i]){
                                i--;
                                j=i;        
                            } 
                        }
                    } 
                    burbuja(carton2);
                    for (int i = 0; i < carton2.length; i++) {
                        System.out.println(carton2[i]);   
                    }
                }
            }
            contadorJugadores--;   
        }
        System.out.println("");
        System.out.println("");
        System.out.println("Cartones asignados. Comienza el bingo.");
        System.out.println("");
        System.out.println("");
        //lleno un array aleatoriamente con las bolas que iré sacando
        for (int i = 0; i < bolasBingo.length; i++) {
                bolasBingo[i]=aleatorios();
                for (int j = 0; j < 1; j++) {
                    if(i>0 && bolasBingo[i]==bolasBingo[j]){
                        i--;
                        j=i;
                    }    
                }
            }
            int k=0;
        while(bingo==false){
            System.out.println("Numero "+bolasBingo[k]);
            for (int i = 0; i < 15; i++){
                //compruebo si la bola está en el carton1
                if(carton1[i]==bolasBingo[k]){
                    carton1[i]=0; //pongo un 0 en esa casilla del array
                    for (int j = 0; j < 15; j++) { //leo todo el array
                        if(carton1[j]==0){
                            contadorBingo++; //voy contando los 0
                        }        
                    }
                    if(contadorBingo==15){ //cuando todas las casillas sean 0, bingo
                        bingo=true;
                    }
                    else{
                        contadorBingo=0; //si no son todas 0, reseteo ese contador
                    }
                }
                //combruebo si la bola está en el carton2
                if(carton2[i]==bolasBingo[k]){
                    carton2[i]=0;
                    for (int j = 0; j < carton2.length; j++) {
                        if(carton2[j]==0){
                            contadorBingo++;
                        }        
                    }
                    if(contadorBingo==15){
                        bingo=true;
                    }
                    else{
                        contadorBingo=0;
                    }
                }
                //combruebo si la bola está en el carton3
                if(carton3[i]==bolasBingo[k]){
                    carton3[i]=0;
                    for (int j = 0; j < carton3.length; j++) {
                        if(carton3[j]==0){
                            contadorBingo++;
                        }        
                    }
                    if(contadorBingo==15){
                        bingo=true;
                    }
                    else{
                        contadorBingo=0;
                    }
                }
                //combruebo si la bola está en el carton4
                if(carton4[i]==bolasBingo[k]){
                    carton4[i]=0;
                    for (int j = 0; j < carton4.length; j++) {
                        if(carton4[j]==0){
                            contadorBingo++;
                        }        
                    }
                    if(contadorBingo==15){
                        bingo=true;
                    }
                    else{
                        contadorBingo=0;
                    }
                }
                //combruebo si la bola está en el carton5
                if(carton5[i]==bolasBingo[k]){
                    carton5[i]=0;
                    for (int j = 0; j < carton5.length; j++) {
                        if(carton5[j]==0){
                            contadorBingo++;
                        }        
                    }
                    if(contadorBingo==15){
                        bingo=true;
                    }
                    else{
                        contadorBingo=0;
                    }
                }
                //combruebo si la bola está en el carton6
                if(carton6[i]==bolasBingo[k]){
                    carton6[i]=0;
                    for (int j = 0; j < carton6.length; j++) {
                        if(carton6[j]==0){
                            contadorBingo++;
                        }        
                    }
                    if(contadorBingo==15){
                        bingo=true;
                    }
                    else{
                        contadorBingo=0;
                    }
                }
                //combruebo si la bola está en el carton7
                if(carton7[i]==bolasBingo[k]){
                    carton7[i]=0;
                    for (int j = 0; j < carton7.length; j++) {
                        if(carton7[j]==0){
                            contadorBingo++;
                        }        
                    }
                    if(contadorBingo==15){
                        bingo=true;
                    }
                    else{
                        contadorBingo=0;
                    }
                }
                //combruebo si la bola está en el carton8
                if(carton8[i]==bolasBingo[k]){
                    carton8[i]=0;
                    for (int j = 0; j < carton8.length; j++) {
                        if(carton8[j]==0){
                            contadorBingo++;
                        }        
                    }
                    if(contadorBingo==15){
                        bingo=true;
                    }
                    else{
                        contadorBingo=0;
                    }
                }
            }
            k++;
        }
        System.out.println("BINGOOOOOOOOOOOOOOO");
    }

*/
}
