//En este programa, el robot Karel deberá o bien traducir braile (distribución de Things en la pantalla) a texto, o bien traducir texto a braile disponiendo los Things de una manera específica

package unal.poo.practica;

import becker.robots.*;
import java.util.Scanner;

public class Ejercicio2{

  //Declaracion de Variables -- Forma temporal - No es buena practica tener
  //variables estaticas
  public static City ciudad;
  public static Robot estudiante;

  public static boolean matrix_letras[][] = new boolean[][]{
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

	public static void main (String[] args){

    //Declarar la creacion de la ciudad
    ciudad = new City("Field.txt");
    ciudad.showThingCounts(true);


    Scanner lectura = new Scanner(System.in);
    System.out.println("Ingrese la palabra\t");
    String palabra = lectura.next();

    int num_Things = palabra.length()*5; //Nos garantiza que siempre habrán things suficientes para formar la palabra
    //Direction.NORTH, EAST, SOUTH, WEST
    //Definicion de la ubicacion del robot, Ciudad, posicion, Direccion, Numero things en el bolso.
    estudiante = new Robot(ciudad,1, 1, Direction.SOUTH, num_Things);

    boolean primer_numero = true;
    for(int ii=0; ii<=palabra.length()-1; ii++){
      if(palabra.charAt(ii)>=48 && palabra.charAt(ii)<=57 && primer_numero){ //Qué pasa si es la primera vez que encuentra un número
        pone_Things(arr_numeral());
        mover_entre_cajones();
        primer_numero = false;
        ii--;
      }else{
        pone_Things(get_arr_letra(palabra.charAt(ii)));
        mover_entre_cajones();
      }
    }
	}
  public static boolean[] fragmentar_matrix(int fila){
    boolean[] fila_i = new boolean[6];
    for(int jj=0; jj<=5; jj++){
      fila_i[jj] = matrix_letras[fila][jj];
    }
    return fila_i;
  }
  public static boolean[] get_arr_letra(char letra_i){
    boolean[] arr_letra = new boolean[6];
    switch(letra_i){
      case'a':
        arr_letra = fragmentar_matrix(0);
        break;
      case'b':
        arr_letra = fragmentar_matrix(1);
        break;
      case'c':
        arr_letra = fragmentar_matrix(2);
        break;
      case'd':
        arr_letra = fragmentar_matrix(3);
        break;
      case'e':
        arr_letra = fragmentar_matrix(4);
        break;
      case'f':
        arr_letra = fragmentar_matrix(5);
        break;
      case'g':
        arr_letra = fragmentar_matrix(6);
        break;
      case'h':
        arr_letra = fragmentar_matrix(7);
        break;
      case'i':
        arr_letra = fragmentar_matrix(8);
        break;
      case'j':
        arr_letra = fragmentar_matrix(9);
        break;
      case'k':
        arr_letra = fragmentar_matrix(10);
        break;
      case'l':
        arr_letra = fragmentar_matrix(11);
        break;
      case'm':
        arr_letra = fragmentar_matrix(12);
        break;
      case'n':
        arr_letra = fragmentar_matrix(13);
        break;
      case'o':
        arr_letra = fragmentar_matrix(14);
        break;
      case'p':
        arr_letra = fragmentar_matrix(15);
        break;
      case'q':
        arr_letra = fragmentar_matrix(16);
        break;
      case'r':
        arr_letra = fragmentar_matrix(17);
        break;
      case's':
        arr_letra = fragmentar_matrix(18);
        break;
      case't':
        arr_letra = fragmentar_matrix(19);
        break;
      case'u':
        arr_letra = fragmentar_matrix(20);
        break;
      case'v':
        arr_letra = fragmentar_matrix(21);
        break;
      case'w':
        arr_letra = fragmentar_matrix(22);
        break;
      case'x':
        arr_letra = fragmentar_matrix(23);
        break;
      case'y':
        arr_letra = fragmentar_matrix(24);
        break;
      case'z':
        arr_letra = fragmentar_matrix(25);
        break;
      case'#':
        arr_letra = fragmentar_matrix(26);
        break;
      case'0':
        arr_letra = fragmentar_matrix(27);
        break;
      case'1':
        arr_letra = fragmentar_matrix(28);
        break;
      case'2':
        arr_letra = fragmentar_matrix(29);
        break;
      case'3':
        arr_letra = fragmentar_matrix(30);
        break;
      case'4':
        arr_letra = fragmentar_matrix(31);
        break;
      case'5':
        arr_letra = fragmentar_matrix(32);
        break;
      case'6':
        arr_letra = fragmentar_matrix(33);
        break;
      case'7':
        arr_letra = fragmentar_matrix(34);
        break;
      case'8':
        arr_letra = fragmentar_matrix(35);
        break;
      case'9':
        arr_letra = fragmentar_matrix(36);
        break;
    }
    return arr_letra;
  }
  public static void pone_Things(boolean[] arr_letra){
    for(int ii=0; ii<=2; ii++){
      if(arr_letra[ii]){
        estudiante.putThing();
      }
      estudiante.move();
    }
    estudiante.turnLeft();
    estudiante.move();
    estudiante.turnLeft();
    estudiante.move();
    for(int ii=3; ii<=5; ii++){
      if(arr_letra[ii]){
        estudiante.putThing();
      }
      estudiante.move();
    }
  }
  public static void mover_entre_cajones(){
    for(int ii=1; ii<=3; ii++){
      estudiante.turnLeft();
    }
    estudiante.move();
    for(int ii=1; ii<=3; ii++){
      estudiante.turnLeft();
    }
    estudiante.move();
  }
  public static boolean[] arr_numeral(){
    boolean[] arr_numeral = {false, false, true, true, true, true};
    return arr_numeral;
  }
}
