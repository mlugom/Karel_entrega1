//Ejercicio para calcular el área de diferentes "lotes"

package unal.poo.practica;

import becker.robots.*;
import java.util.Arrays;

public class EjercicioK
{
    //Declaracion de Variables -- Forma temporal - No es buena practica tener
    //variables estaticas
    public static City objetos;
    public static Robot estudiante;

	public static void main (String[] args){
    //Declarar la creacion de la ciudad
    objetos = new City("Field_6.txt");
    objetos.showThingCounts(true);

    //Direction.NORTH, EAST, SOUTH, WEST
    //Definicion de la ubicacion del robot, Ciudad, posicion, Direccion, Numero things en el bolso.
    estudiante = new Robot(objetos, 10, 3, Direction.WEST,0);
    // estudiante = new Robot(objetos, 7, 2, Direction.NORTH,0);

    int[] arrStreet = new int[0];
    int[] arrAvenue = new int[0];

    int[][] matrixTemp = hacerRecorrido(arrStreet, arrAvenue);
    arrStreet = matrixTemp[0];
    arrAvenue = matrixTemp[1];


    int minStreet = getMin(arrStreet);
    int maxStreet = getMax(arrStreet);

    int minAvenue = getMin(arrAvenue);
    int maxAvenue = getMax(arrAvenue);

    arrStreet = canonizarArr(arrStreet, minStreet);
    maxStreet -= minStreet;
    arrAvenue = canonizarArr(arrAvenue, minAvenue);
    maxAvenue -= minAvenue;

    boolean[][] matrixLote = crearMatrix(maxAvenue, maxStreet);
    matrixLote = defineEspacios(matrixLote, arrAvenue, arrStreet);
    int mts2 = cuentaMetros(matrixLote);

    System.out.println("///////////////////////");
    System.out.println("Área del Lote = "+mts2+" m2");
    System.out.println("///////////////////////");
	}
  public static int[][] hacerRecorrido(int[] arrStreet, int[] arrAvenue){
    //Se hace esto por el simple hecho de que no se puede retornar cada array por separado
    boolean hubo_avance = false;
    int qq=0;
    do{
      hubo_avance = false;
      turnRight();
      if(estudiante.frontIsClear()){
        arrStreet = getStreetChange(arrStreet, qq);
        arrAvenue = getAvenueChange(arrAvenue, qq);
        estudiante.move();
        hubo_avance = true;
      }else{
        estudiante.turnLeft();
        if(estudiante.frontIsClear()){
          arrStreet = getStreetChange(arrStreet, qq);
          arrAvenue = getAvenueChange(arrAvenue, qq);
          estudiante.move();
          hubo_avance = true;
        }else{
          estudiante.turnLeft();
        }
      }
      if(!hubo_avance){ //Esto para no saltar una posición de los arreglos y pedir acceso a posiciones que no existen
        qq--;
      }
      qq++;
    }while(!estudiante.canPickThing());
    arrStreet = getStreetChange(arrStreet, qq);
    arrAvenue = getAvenueChange(arrAvenue, qq);
    
    int[][] matrixTemp = new int[2][0];
    matrixTemp[0] = arrStreet;
    matrixTemp[1] = arrAvenue;

    for(int ii=0; ii<=arrStreet.length-1; ii++){
      System.out.println(arrStreet[ii]+"\t"+arrAvenue[ii]);
    }

    return matrixTemp;
  }
  public static int[] getStreetChange(int[] arrStreet, int ii){
    arrStreet = Arrays.copyOf(arrStreet, arrStreet.length+1);
    arrStreet[ii] = estudiante.getStreet();
    return arrStreet;
  }
  public static int[] getAvenueChange(int[] arrAvenue, int ii){
    arrAvenue = Arrays.copyOf(arrAvenue, arrAvenue.length+1);
    arrAvenue[ii] = estudiante.getAvenue();
    return arrAvenue;
  }
  public static int getMin(int[] array){
    int min = array[0];
    for(int ii=1; ii<=array.length-1; ii++){
      if(array[ii] < min){
        min = array[ii];
      }
    }
    return min;
  }
  public static int getMax(int[] array){
    int max = array[0];
    for(int ii=1; ii<=array.length-1; ii++){
      if(array[ii] > max){
        max = array[ii];
      }
    }
    return max;
  }
  public static int[] canonizarArr(int[] array, int min){
    for(int ii=0; ii<=array.length; ii++){
      array[ii] -= min;
    }
    return array;
  }
  public static boolean[][] crearMatrix(int maxAvenue, int maxStreet){
    boolean[][] matrixLote = new boolean[maxAvenue][maxStreet];
    for(int ii=0; ii<=maxAvenue; ii++){
      for(int jj=0; jj<=maxStreet; jj++){
        matrixLote[ii][jj] = true;
      }
    }
    return matrixLote;
  }
  public static boolean[][] defineEspacios(boolean[][] matrixLote, int[] arrAvenue, int[] arrStreet){
    for(int ii=0; ii<=matrixLote.length-1; ii++){
      for(int jj=0; jj<=matrixLote[0].length-1; jj++){
        boolean iiPerteneceArrAvenue = false;
        boolean jjPerteneceArrStreet = false;
        for(int kk=0; kk<=arrAvenue.length-1; kk++){
          if(ii == arrAvenue[kk]){
            iiPerteneceArrAvenue = true;
            break;
          }
        }
        for(int kk=0; kk<=arrStreet.length-1; kk++){
          if(jj == arrStreet[kk]){
            jjPerteneceArrStreet = true;
            break;
          }
        }
        if(iiPerteneceArrAvenue && jjPerteneceArrStreet){
          matrixLote[ii][jj] = false;
        }
      }
    }
    return matrixLote;
  }
  public static int cuentaMetros(boolean[][] matrixLote){
    int contTemp = 0;
    for(int ii=0; ii<=matrixLote.length-1; ii++){
      for(int jj=0; jj<=matrixLote[0].length; jj++){
        if(matrixLote[ii][jj] == true){
          contTemp++;
        }
      }
    }
    return contTemp;
  }
  public static void turnRight(){
    for(int ii=1; ii<=3; ii++){
      estudiante.turnLeft();
    }
  }
}
