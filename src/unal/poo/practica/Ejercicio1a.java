//En este programa, el robot Karel deberá traducir braile (con los Things distribuidos en la ciudad) a texto

package unal.poo.practica;

import becker.robots.*;

public class Ejercicio1a{

  //Declaracion de Variables -- Forma temporal - No es buena practica tener
  //variables estaticas
  public static City ciudad;
  public static Robot estudiante;

	public static void main (String[] args){

    //Declarar la creacion de la ciudad
    ciudad = new City("Field_ejer1a(1).txt");
    ciudad.showThingCounts(true);

    //Direction.NORTH, EAST, SOUTH, WEST
    //Definicion de la ubicacion del robot, Ciudad, posicion, Direccion, Numero things en el bolso.
    estudiante = new Robot(ciudad,1, 1, Direction.SOUTH,10);

    boolean bool_fin = false; //Éste booleano nos indicará si ya se encontró el Thing de "fin"
    boolean bool_num = false; //Este booleano nos indicará que después de leer un numeral todo se considerará como número

    boolean[] arr_temp = new boolean[6]; //Éste arreglo se redifinirá cada que se entre en el ciclo, y tomará el "valor del arreglo" de la letra que el robot lea

    while(!bool_fin){ //Se ejecuta hasta que encuentra un Thing en la posición de fin
      arr_temp = mover_en_cajon(); //Al arreglo temporal se le asigna el arreglo de la letra
      if(is_arr_numeral(arr_temp)){ //Si aparece un numeral, todo lo siguiente que aparezca será considerado número
        bool_num = true;
      }
      revisa_arreglo(arr_temp, bool_num); //Se revisa con la matriz grande a qué equivale el arreglo de la letra, y se hace la impresión correspondiente
      bool_fin = mover_entre_cajones(bool_fin); //El booleano se vuelve verdadero si mientras se mueve entre cajones se encuentra el Thing de fin
    }

	}
  public static boolean[] mover_en_cajon(){ //Recibe hacia abajo y en la primera posición del arreglo; entrega hacia arriba en la posición "6" (1 afuera del cajón)
    boolean[] arr_Things = new boolean[6];
    for(int ii=0; ii<=2; ii++){
      arr_Things[ii] = estudiante.canPickThing(); //Si hay Thing, el elemento del arreglo se hace true
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
    return arr_Things; //Se retorna el arreglo, ya con "la letra que representa"
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
    boolean matrix_letras[][] = new boolean[][]{ //Cada fila representará una letra/caracter
      {true, false, false, false, false, false}, //a
      {true, true, false, false, false, false}, //b
      {true, false, false, false, false, true}, //c
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
      {false, false, true, true, true, true}, //# //Nemeth Code
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
        boolean bool_letra = true;
        for(int jj=0; jj<=5; jj++){ //Revisa si todos los elementos de una fila de la matriz coinciden con todos los elementos del arreglo que "retornó el robot" (Son la misma letra)
          if(arr_Things[jj] != matrix_letras[ii][jj]){
            bool_letra = false;
          }
        }
        if(bool_letra){ //Solo si todos los elementos de la fila son iguales se podrá ejecutar éste condicional
          pos_letra = ii;
          break;
        }
      }
      switch(pos_letra){ //La impresión depende de con qué posición de la matriz grande se encontró la equivalencia de la letra
        case 0:
          System.out.print('a');
          break;
        case 1:
          System.out.print('b');
          break;
        case 2:
          System.out.print('c');
          break;
        case 3:
          System.out.print('d');
          break;
        case 4:
          System.out.print('e');
          break;
        case 5:
          System.out.print('f');
          break;
        case 6:
          System.out.print('g');
          break;
        case 7:
          System.out.print('h');
          break;
        case 8:
          System.out.print('i');
          break;
        case 9:
          System.out.print('j');
          break;
        case 10:
          System.out.print('k');
          break;
        case 11:
          System.out.print('l');
          break;
        case 12:
          System.out.print('m');
          break;
        case 13:
          System.out.print('n');
          break;
        case 14:
          System.out.print('o');
          break;
        case 15:
          System.out.print('p');
          break;
        case 16:
          System.out.print('q');
          break;
        case 17:
          System.out.print('r');
          break;
        case 18:
          System.out.print('s');
          break;
        case 19:
          System.out.print('t');
          break;
        case 20:
          System.out.print('u');
          break;
        case 21:
          System.out.print('v');
          break;
        case 22:
          System.out.print('w');
          break;
        case 23:
          System.out.print('x');
          break;
        case 24:
          System.out.print('y');
          break;
        case 25:
          System.out.print('z');
          break;
      }
    }
    if(bool_num){ //La palabra/semipalabra inicia con el numeral, por lo que se imprimirá un número
      for(int ii=26; ii<=47; ii++){
        boolean bool_letra = true;
        for(int jj=0; jj<=5; jj++){ //Revisa si todos los elementos de una fila de la matriz coinciden con todos los elementos del arreglo que "retornó el robot" (Son la misma letra)
          if(arr_Things[jj] != matrix_letras[ii][jj]){
            bool_letra = false;
          }
        }
        if(bool_letra){ //Solo si todos los elementos de la fila son iguales se podrá ejecutar éste condicional
          pos_letra = ii;
          break;
        }
      }
      switch(pos_letra){
        case 26:
          //Si es el numeral no hay necesidad de imprimir nada
          break;
        case 27:
          System.out.print('0');
          break;
        case 28:
          System.out.print('1');
          break;
        case 29:
          System.out.print('2');
          break;
        case 30:
          System.out.print('3');
          break;
        case 31:
          System.out.print('4');
          break;
        case 32:
          System.out.print('5');
          break;
        case 33:
          System.out.print('6');
          break;
        case 34:
          System.out.print('7');
          break;
        case 35:
          System.out.print('8');
          break;
        case 36:
          System.out.print('9');
          break;
        case 37:
          //Si es el numeral no hay necesidad de imprimir nada
          break;
        case 38:
          System.out.print('0');
          break;
        case 39:
          System.out.print('1');
          break;
        case 40:
          System.out.print('2');
          break;
        case 41:
          System.out.print('3');
          break;
        case 42:
          System.out.print('4');
          break;
        case 43:
          System.out.print('5');
          break;
        case 44:
          System.out.print('6');
          break;
        case 45:
          System.out.print('7');
          break;
        case 46:
          System.out.print('8');
          break;
        case 47:
          System.out.print('9');
          break;
      }
    }
  }
  public static boolean is_arr_numeral(boolean[] arr_Things){ //Ésta función retorna verdadero si el arreglo que se utilizó en el argumento corresponde al '#' numeral
    boolean[] arr_numeral = {false, false, true, true, true, true};
    boolean bool_is_numeral = true;
    for(int ii=0; ii<=5; ii++){
      if(arr_Things[ii] != arr_numeral[ii]){
        bool_is_numeral = false;
      }
    }
    return bool_is_numeral;
  }
}
