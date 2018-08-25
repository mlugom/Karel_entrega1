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

    int[][] matrixLote = crearMatrix(maxAvenue, maxStreet);

    for(int ii=0; ii<=arrStreet.length-1; ii++){ //Imprime ARREGLOS
      System.out.println(arrStreet[ii]+"\t"+arrAvenue[ii]);
    }

    matrixLote = defineEspacios(matrixLote, arrAvenue, arrStreet);
    for(int ii=0; ii<=matrixLote.length-1; ii++){ //IMPRESIÓN MATRIZ
      for(int jj=0; jj<=matrixLote[0].length-1; jj++){
        System.out.print(matrixLote[ii][jj]+"\t");
      }
      System.out.println();
    }

    int[][] matrixLoteSemi1 = matrixLote;
    int[][] matrixLoteSemi2 = matrixLote;
    matrixLoteSemi1 = filtroHorizontal(matrixLoteSemi1);


    matrixLoteSemi2 = transformacionMatriz(matrixLoteSemi2);
    matrixLoteSemi2 = filtroHorizontal(matrixLoteSemi2);
    int mts2 = cuentaMetros(matrixLote);

    System.out.println();
    for(int ii=0; ii<=matrixLoteSemi1.length-1; ii++){ //IMPRESIÓN MATRIZ
      for(int jj=0; jj<=matrixLoteSemi1[0].length-1; jj++){
        System.out.print(matrixLoteSemi1[ii][jj]+"\t");
      }
      System.out.println();
    }
    // for(int ii=0; ii<=matrixLoteSemi2.length-1; ii++){ //IMPRESIÓN MATRIZ
    //   for(int jj=0; jj<=matrixLoteSemi2[0].length-1; jj++){
    //     System.out.print(matrixLoteSemi2[ii][jj]+"\t");
    //   }
    //   System.out.println();
    // }

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
    for(int ii=0; ii<=array.length-1; ii++){
      array[ii] -= min;
    }
    return array;
  }
  public static int[][] crearMatrix(int maxAvenue, int maxStreet){
    int[][] matrixLote = new int[maxAvenue+1][maxStreet+1];
    for(int ii=0; ii<=maxAvenue; ii++){
      for(int jj=0; jj<=maxStreet; jj++){
        matrixLote[ii][jj] = 1;
      }
    }
    return matrixLote;
  }
  public static int[][] transformacionMatriz(int[][] matrix){
    int[][] matrixTransformada = new int[matrix[0].length][matrix.length];
    for(int ii=0; ii<=matrix.length-1; ii++){
      for(int jj=0; jj<=matrix[0].length-1; jj++){
        matrixTransformada[jj][ii] = matrix[ii][jj];
      }
    }
    return matrixTransformada;
  }
  public static int[][] defineEspacios(int[][] matrixLote, int[] arrAvenue, int[] arrStreet){
    for(int ii=0; ii<=matrixLote.length-1; ii++){
      for(int jj=0; jj<=matrixLote[0].length-1; jj++){
        boolean iiPerteneceArrStreet = false;
        boolean jjPerteneceArrAvenue = false;
        for(int kk=0; kk<=arrAvenue.length-1; kk++){
          if(ii == arrStreet[kk] && jj == arrAvenue[kk]){
            matrixLote[ii][jj] = 0;
            break;
          }
        }
        // if(ii == 0 || jj == 0){
        //   matrixLote[ii][jj] = 0; //Los bordes del cuadro grande
        // }
      }
    }
    return matrixLote;
  }
  public static int[][] filtroHorizontal(int[][] matrixLote){

    for(int ii=0; ii<=matrixLote.length-1; ii++){ //En este primer ciclo se definen los "pivotes" especiales
      for(int jj=1; jj<=matrixLote[0].length-1; jj++){
        if(jj < matrixLote[0].length-1){
          if((matrixLote[ii][jj-1]==0 && matrixLote[ii][jj]==1) && !(matrixLote[ii][jj]==1 && matrixLote[ii][jj+1]==0)){ //Si hay 1DespuésDe0 y no hay 0DespuesDe1
            matrixLote[ii][jj-1] = 2;
          }
          if((matrixLote[ii][jj-1]==1 && matrixLote[ii][jj]==0) && (matrixLote[ii][jj]==0 && matrixLote[ii][jj+1]==1)){ //Si hay 0DespuesDe1 y 1DespuésDe0
            matrixLote[ii][jj] = 4;
          }
          if((matrixLote[ii][jj-1]==1 && matrixLote[ii][jj]==0) && !(matrixLote[ii][jj]==0 && matrixLote[ii][jj+1]==1)){ //Si hay 0DespuesDe1 y no hay 1DespuesDe0
              matrixLote[ii][jj] = 3;
          }
        }else{
          if((matrixLote[ii][jj-1]==1 && matrixLote[ii][jj]==0)){ //Los 3 que pueden estar en la última columna de la matriz
              matrixLote[ii][jj] = 3;
          }
        }
      }
    }

    for(int ii=0; ii<=matrixLote.length-1; ii++){//En este segundo ciclo se usan los "pivotes" especiales para borrar los espacios que fueron pasados como bloques ()
      for(int jj=0; jj<=matrixLote[0].length-1; jj++){
        boolean noHay2Antes = true;
        boolean noHay3Despues = true;
        if(matrixLote[ii][jj] == 2 || matrixLote[ii][jj] == 4){
          for(int kk=jj-1; kk>=0; kk--){
            if(matrixLote[ii][kk] == 2 || matrixLote[ii][kk] == 4){
              noHay2Antes = false;
              break;
            }
          }
          if(noHay2Antes){
            for(int kk=jj-1; kk>=0; kk--){
              matrixLote[ii][kk] = 0;
            }
          }
        }
        if(matrixLote[ii][jj] == 3 || matrixLote[ii][jj] == 4){
          for(int kk=jj+1; kk<=matrixLote[0].length-1; kk++){
            if(matrixLote[ii][kk] == 3 || matrixLote[ii][kk] == 4){
              noHay3Despues = false;
              break;
            }
          }
          if(noHay3Despues){
            for(int kk=jj+1; kk<=matrixLote[0].length-1; kk++){
              matrixLote[ii][kk] = 0;
            }
          }
        }
        // if(matrixLote[ii][jj] == 2 || matrixLote[ii][jj] == 4){
        //   for(int kk=jj-1; matrixLote[ii][kk]!=3 && matrixLote[ii][kk]!=4; kk--){
        //     matrixLote[ii][kk] = 0;
        //     if(kk == 0){ //Si ya no hay qué más revisar
        //       break;
        //     }
        //   }
        // }
      }
    }

    return matrixLote;
  }
  public static int cuentaMetros(int[][] matrixLote){
    int contTemp = 0;
    for(int ii=0; ii<=matrixLote.length-1; ii++){
      for(int jj=0; jj<=matrixLote[0].length-1; jj++){
        if(matrixLote[ii][jj] == 1){
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



// 0	0	0	0	0	1	0	0	0	0	0
// 0	1	1	1	0	0	0	1	1	1	0
// 0	0	1	1	0	0	1	1	1	0	0
// 1	0	1	1	0	1	1	1	0	0	1
// 1	0	1	1	1	1	1	0	0	1	1
// 1	0	0	1	1	1	0	0	1	1	1
// 1	0	0	1	1	1	1	0	0	1	1
// 1	0	1	1	1	1	1	1	0	0	1
// 0	0	1	1	0	0	1	1	1	0	0
// 0	1	1	1	1	0	0	1	1	1	0
// 0	0	0	0	0	0	0	0	0	0	0

// 0	0	0	0	0	1	3	0	0	0	0
// 2	1	1	1	3	0	2	1	1	1	3
// 0	2	1	1	3	2	1	1	1	3	0
// 0	4	1	1	4	1	1	1	3	0	0
// 0	4	1	1	1	1	1	3	0	0	0
// 0	0	2	1	1	1	3	0	0	0	0
// 0	0	2	1	1	1	1	3	0	0	0
// 0	4	1	1	1	1	1	1	3	0	0
// 0	2	1	1	3	2	1	1	1	3	0
// 2	1	1	1	1	3	2	1	1	1	3
// 0	0	0	0	0	0	0	0	0	0	0

// 0	2	0	0	0	0	0	0	0	2	0
// 2	1	2	4	4	0	0	4	2	1	3
// 2	1	1	1	1	2	2	1	1	1	3
// 2	1	1	1	1	1	1	1	1	1	3
// 0	0	0	4	1	1	1	1	3	1	3
// 0	0	2	1	1	1	1	1	2	3	0
// 0	2	1	1	1	3	0	0	0	0	0
// 2	1	1	1	3	0	3	1	1	1	3
// 2	1	1	3	0	0	0	3	1	1	3
// 2	1	3	0	0	0	0	0	3	1	3
// 0	3	0	0	0	0	0	0	0	3	0
