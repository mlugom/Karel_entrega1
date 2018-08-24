//Ejercicio para calcular el área de diferentes "lotes"

package unal.poo.practica;

import becker.robots.*;

public class EjercicioK
{
    //Declaracion de Variables -- Forma temporal - No es buena practica tener
    //variables estaticas
    public static City objetos;
    public static Robot estudiante;

	public static void main (String[] args){
    //Declarar la creacion de la ciudad
    objetos = new City("Field.txt");
    objetos.showThingCounts(true);

    //Direction.NORTH, EAST, SOUTH, WEST
    //Definicion de la ubicacion del robot, Ciudad, posicion, Direccion, Numero things en el bolso.
    estudiante = new Robot(objetos, 10, 3, Direction.WEST,0);
    hacerRecorrido();
	}
  public static void hacerRecorrido(){
    while(!estudiante.canPickThing()){
      turnRight();
      if(estudiante.frontIsClear()){
        estudiante.move();
      }else{
        estudiante.turnLeft();
        if(estudiante.frontIsClear()){
          estudiante.move();
        }else{
          estudiante.turnLeft();
        }
      }
    }
  }
  public static void turnRight(){
    for(int ii=1; ii<=3; ii++){
      estudiante.turnLeft();
    }
  }
}
