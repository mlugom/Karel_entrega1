//En este programa, el robot Karel deberá o bien traducir braile (distribución de Things en la pantalla) a texto, o bien traducir texto a braile disponiendo los Things de una manera específica

package unal.poo.practica;

import becker.robots.*;

public class Ejercicio1{

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
    estudiante = new Robot(ciudad,1, 1, Direction.SOUTH,10);

    boolean bool_fin = false;
    boolean bool_num = false;

    boolean[] arr_inicial = new boolean[6]; //Se hace el movimiento del primer cajón aparte para ver si se leerá un número o una palabra
    arr_inicial = mover_en_cajon();
    if(arr_inicial == arr_numeral()){ //Si la primera letra es un numeral
      bool_num = true;
    }else{
      revisa_arreglo(arr_inicial, bool_num); //Si no es un numeral se imprime la letra correspondiente
    }
    bool_fin = mover_entre_cajones(bool_fin); //Se determina si la palabre es de sólo una letra

    while(!bool_fin){ //Se ejecuta hasta que encuentra un Thing en la posición de fin
      revisa_arreglo(mover_en_cajon(), bool_num);
      bool_fin = mover_entre_cajones(bool_fin);
    }

	}
  public static boolean[] mover_en_cajon(){ //Recibe hacia abajo y en la primera posición del arreglo; entrega hacia arriba en la posición "6" (1 afuera del cajón)
    boolean[] arr_Things = new boolean[6];
    for(int ii=0; ii<=2; ii++){
      arr_Things[ii] = estudiante.canPickThing();
      estudiante.move();
    }
    estudiante.turnLeft();
    estudiante.move();
    estudiante.turnLeft();
    estudiante.move();
    for(int ii=3; ii<=5; ii++){
      arr_Things[ii] = estudiante.canPickThing();
      estudiante.move();
    }
    return arr_Things;
  }
  public static boolean mover_entre_cajones(boolean bool_fin){
    for(int ii=1; ii<=3; ii++){
      estudiante.turnLeft();
    }
    estudiante.move();
    bool_fin = estudiante.canPickThing(); //Si encuentra un Thing en la posición (ver ejemplo) éste boolean se vuelve true; dando orden para que más adelante termine la ejecución del Programacion
    for(int ii=1; ii<=3; ii++){
      estudiante.turnLeft();
    }
    estudiante.move();
    return bool_fin;
  }
  public static void revisa_arreglo(boolean[] arr_Things, boolean bool_num){
    boolean[][] matrix_letras = new boolean[][]{
      {true, false, false, false, false, false}, //a
      {true, true, false, false, false, false}, //b
      {true, false, false, true, false, false}, //c
      {true, false, false, false, true, true}, //d
      {true, false, false, false, true, false}, //e
      {true, true, false, false, false, true}, //f
      {true, true, false, false, true, true}, //g
      {true, true, false, false, true, false}, //h
      {false, true, false, false, false, true}, //i
      {false, true, false, false, true, true}, //j
      {true, false, true, false, false, false}, //k
      {true, true, true, false, false, false}, //l
      {true, false, true, false, false, true}, //m
      {true, false, true, false, true, true}, //n
      {true, false, true, false, true, false}, //o
      {true, true, true, false, false, true}, //p
      {true, true, true, false, true, true}, //q
      {true, true, true, false, true, false}, //r
      {false, true, true, false, false, true}, //s
      {false, true, true, false, true, true}, //t
      {true, false, true, true, false, false}, //u
      {true, true, true, true, false, false}, //v
      {false, true, false, true, true, true}, //w
      {true, false, true, true, false, true}, //x
      {true, true, true, true, true, true}, //y
      {true, false, true, true, true, false}, //z
      {false, false, true, true, true, true}, //# //Literary Code
      {false, true, false, false, true, true}, //0
      {true, false, false, false, false, false}, //1
      {true, true, false, false, false, false}, //2
      {true, false, false, false, false, true}, //3
      {true, false, false, false, true, true}, //4
      {true, false, false, false, true, false}, //5
      {true, true, false, false, false, true}, //6
      {true, true, false, false, true, true}, //7
      {true, true, false, false, true, false}, //8
      {false, true, false, false, false, true}, //9
      {true, true, true, true, true, true}, //# //Nemeth Code
      {false, false, true, true, true, false}, //0
      {false, true, false, false, false, false}, //1
      {false, true, true, false, false, false}, //2
      {false, true, false, false, true, false}, //3
      {false, true, false, true, true, false}, //4
      {false, true, false, true, false, false}, //5
      {false, true, true, false, true, false}, //6
      {false, true, true, true, true, false}, //7
      {false, true, true, true, false, false}, //8
      {false, false, true, false, true, false}, //9
    };
    int pos_letra = 0;
    if(!bool_num){ //Si la palabra no inició con el numeral se imprime una letra
      for(int ii=0; ii<=25; ii++){
        if(arr_Things == matrix_letras[ii]){ //Revisa en el arreglo si la letra que leyó el robot coincide
          pos_letra = ii;
          break;
        }
      }
      switch(pos_letra){
        case 0:
          System.out.println('a');
          break;
        case 1:
          System.out.println('b');
          break;
        case 2:
          System.out.println('c');
          break;
        case 3:
          System.out.println('d');
          break;
        case 4:
          System.out.println('e');
          break;
        case 5:
          System.out.println('f');
          break;
        case 6:
          System.out.println('g');
          break;
        case 7:
          System.out.println('h');
          break;
        case 8:
          System.out.println('i');
          break;
        case 9:
          System.out.println('j');
          break;
        case 10:
          System.out.println('k');
          break;
        case 11:
          System.out.println('l');
          break;
        case 12:
          System.out.println('m');
          break;
        case 13:
          System.out.println('n');
          break;
        case 14:
          System.out.println('o');
          break;
        case 15:
          System.out.println('p');
          break;
        case 16:
          System.out.println('q');
          break;
        case 17:
          System.out.println('r');
          break;
        case 18:
          System.out.println('s');
          break;
        case 19:
          System.out.println('t');
          break;
        case 20:
          System.out.println('u');
          break;
        case 21:
          System.out.println('v');
          break;
        case 22:
          System.out.println('w');
          break;
        case 23:
          System.out.println('x');
          break;
        case 24:
          System.out.println('y');
          break;
        case 25:
          System.out.println('z');
          break;
      }
    }
    if(bool_num){ //La palabra inicia con el numeral, por lo que se imprimirá un número
      for(int ii=26; ii<=47; ii++){
        if(arr_Things == matrix_letras[ii]){ //Revisa en el arreglo si la letra que leyó el robot coincide
          pos_letra = ii;
          break;
        }
      }
      switch(pos_letra){
        case 26:
          //Si es el numeral no hay necesidad de imprimir nada
          break;
        case 27:
          System.out.println('0');
          break;
        case 28:
          System.out.println('1');
          break;
        case 29:
          System.out.println('2');
          break;
        case 30:
          System.out.println('3');
          break;
        case 31:
          System.out.println('4');
          break;
        case 32:
          System.out.println('5');
          break;
        case 33:
          System.out.println('6');
          break;
        case 34:
          System.out.println('7');
          break;
        case 35:
          System.out.println('8');
          break;
        case 36:
          System.out.println('9');
          break;
        case 37:
          //Si es el numeral no hay necesidad de imprimir nada
          break;
        case 38:
          System.out.println('0');
          break;
        case 39:
          System.out.println('1');
          break;
        case 40:
          System.out.println('2');
          break;
        case 41:
          System.out.println('3');
          break;
        case 42:
          System.out.println('4');
          break;
        case 43:
          System.out.println('5');
          break;
        case 44:
          System.out.println('6');
          break;
        case 45:
          System.out.println('7');
          break;
        case 46:
          System.out.println('8');
          break;
        case 47:
          System.out.println('9');
          break;
      }
    }
  }
  public static boolean[] arr_numeral(){
    boolean arr_numeral[] = {true, true, true, true, true, true};
    return arr_numeral;
  }
}
