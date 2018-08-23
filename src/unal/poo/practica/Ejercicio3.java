//Ejercicio de imprimir el numero

package unal.poo.practica;

import becker.robots.*;

public class Ejercicio3{

  //Declaracion de Variables -- Forma temporal - No es buena practica tener
  //variables estaticas
  public static City ciudad;
  public static Robot estudiante;

	public static void main (String[] args){

    //Declarar la creacion de la ciudad
    ciudad = new City("Field.txt");
    ciudad.showThingCounts(true);

    //Direction.NORTH, EAST, SOUTH, WEST
    //Definicion de la ubicacion del robot, Ciudad, posicion, Direccion, Numero things en el bolso.
    estudiante = new Robot(ciudad,5, 1, Direction.NORTH,10);

    int[] numero_para_imprimir = new int[3];
    boolean[] arr_Temporal;

    for(int ii=0; ii<=2; ii++){
      arr_Temporal = mover_en_cajon();
      numero_para_imprimir[ii] = compara_con_matrix(arr_Temporal);
      if(ii != 2){
        mover_entre_cajones();
      }
    }

    for(int ii=0; ii<=2; ii++){
      System.out.print(numero_para_imprimir[ii]);
    }
    System.out.print("\n");
  }
  public static boolean[] mover_en_cajon(){
    boolean[] arr_Things = new boolean[13];
    for(int ii=0; ii<=3; ii++){
      arr_Things[ii] = estudiante.canPickThing();
      estudiante.move();
    }
    arr_Things[4] = estudiante.canPickThing();
    turnRight();
    estudiante.move();
    arr_Things[5] = estudiante.canPickThing();
    turnRight();
    estudiante.move();
    estudiante.move();
    arr_Things[6] = estudiante.canPickThing();
    estudiante.move();
    estudiante.move();
    arr_Things[7] = estudiante.canPickThing();
    estudiante.turnLeft();
    estudiante.move();
    estudiante.turnLeft();
    for(int ii=8; ii<=11; ii++){
      arr_Things[ii] = estudiante.canPickThing();
      estudiante.move();
    }
    arr_Things[12] = estudiante.canPickThing();

    return arr_Things;
  }
  public static void mover_entre_cajones(){
    estudiante.turnLeft();
    estudiante.move();
    estudiante.turnLeft();
    for(int ii=1; ii<=5; ii++){
      estudiante.move();
    }
    estudiante.turnLeft();
    for(int ii=1; ii<=3; ii++){
      estudiante.move();
    }
    estudiante.turnLeft();
    estudiante.move();
    estudiante.turnLeft();
    estudiante.move();
    turnRight();
  }
  public static int compara_con_matrix(boolean[] arr_Things){
    boolean matrix_numeros[][] = new boolean[][]{ //Cada fila representará una letra/caracter
      {true, true, true, true, true, true, false, true, true, true, true, true, true}, //0
      {false, false, false, false, false, false, false, false, true, true, true, true, true}, //1
      {true, true, true, false, true, true, true, true, true, false, true, true, true}, //2
      {true, false, true, false, true, true, true, true, true, true, true, true, true}, //3
      {false, false, true, true, true, false, true, false, true, true, true, true, true}, //4
      {true, false, true, true, true, true, true, true, true, true, true, false, true}, //5
      {true, true, true, true, true, true, true, true, true, true, true, false, true}, //6
      {false, false, false, false, true, true, false, false, true, true, true, true, true}, //7
      {true, true, true, true, true, true, true, true, true, true, true, true, true}, //8
      {true, false, true, true, true, true, true, true, true, true, true, true, true}, //9
    };

    int num_definitivo = 0;
    boolean emparejamiento = true;
    for(int ii=0; ii<=9; ii++){
      emparejamiento = true;
      for(int jj=0; jj<=12; jj++){
        if(arr_Things[jj] != matrix_numeros[ii][jj]){
          emparejamiento = false;
        }
      }
      if(emparejamiento){
        num_definitivo = ii;
        break;
      }
    }
    return num_definitivo; //Garantiza el retorno de algo
  }

  public static void turnRight(){
    for(int ii=1; ii<=3; ii++){
      estudiante.turnLeft();
    }
  }
}
