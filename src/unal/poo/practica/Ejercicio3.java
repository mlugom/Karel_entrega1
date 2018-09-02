package unal.poo.practica;

import becker.robots.*;

/** 
 * Practica de los conceptos de Programacion Estructurada
 * @author Fabian Andres Giraldo */
public class Ejercicio3
{    
       //Declaracion de Variables -- Forma temporal - No es buena practica tener
       //variables estaticas
        public static City objetos;
        public static Robot estudiante;
	public static void main (String[] args){
            //Declarar la creacion de la ciudad
            objetos = new City("Field_ejer3(1).txt");
	    objetos.showThingCounts(true);
            
            //Direction.NORTH, EAST, SOUTH, WEST
            //Definicion de la ubicacion del robot, Ciudad, posicion, Direccion, Numero things en el bolso.
            estudiante = new Robot(objetos,6, 2, Direction.NORTH,0);
            
	    llenar_matriz(1);
            mover_entre_cuartos();
            llenar_matriz(2);
            mover_entre_cuartos();
            llenar_matriz(3);
            crear_matrices();
            comparar(1); comparar(2); comparar(3);
            
            imprimir_numero();
            
	}
        public static boolean matriz1[][] = new boolean[5][3];
        public static boolean matriz2[][] = new boolean[5][3];
        public static boolean matriz3[][] = new boolean[5][3];
        
        public static void llenar_matriz(int n){
            estudiante.move();
            estudiante.turnLeft();
            estudiante.move();
            turnRight();
            for(int i = 0; i < 4; i++){
                estudiante.move();            
            }
            estudiante.turnLeft();
            estudiante.turnLeft();
            boolean mat[][] = new boolean[5][3];
            for(int j = 0; j < 3; j++){
                for(int i = 0; i < 5; i++){
                    mat[i][j] = estudiante.canPickThing();
                    if(i < 4){
                        estudiante.move();
                    }else if(i == 4 && j < 2){
                        estudiante.turnLeft();
                        estudiante.move();
                        estudiante.turnLeft();
                        for(int k = 0; k < 4; k++){
                            estudiante.move();
                        }
                        estudiante.turnLeft();
                        estudiante.turnLeft();
                    }else if(i == 4 && j == 2){
                        turnRight();
                        estudiante.move();
                        estudiante.turnLeft();
                        estudiante.move();
                    }
                }
            }
            switch(n){
                case 1:
                    for(int i = 0; i < 5; i++){
                        for(int j = 0; j < 3; j++){
                            matriz1[i][j] = mat[i][j];
                        }
                    }
                    break;
                case 2:
                    for(int i = 0; i < 5; i++){
                        for(int j = 0; j < 3; j++){
                            matriz2[i][j] = mat[i][j];
                        }
                    }
                    break;
                case 3:
                    for(int i = 0; i < 5; i++){
                        for(int j = 0; j < 3; j++){
                            matriz3[i][j] = mat[i][j];
                        }
                    }
                    break;
            }
        }
        public static void turnRight(){
            for(int i = 0; i < 3; i++){
                estudiante.turnLeft();
            }
        }
        public static void mover_entre_cuartos(){
            estudiante.turnLeft();
            for(int i = 0; i < 3; i++){
                estudiante.move();
            }
            estudiante.turnLeft();
        }
        public static boolean cero[][] = new boolean[5][3];
        public static boolean uno[][] = new boolean[5][3];
        public static boolean dos[][] = new boolean[5][3];
        public static boolean tres[][] = new boolean[5][3];
        public static boolean cuatro[][] = new boolean[5][3];
        public static boolean cinco[][] = new boolean[5][3];
        public static boolean seis[][] = new boolean[5][3];
        public static boolean siete[][] = new boolean[5][3];
        public static boolean ocho[][] = new boolean[5][3];
        public static boolean nueve[][] = new boolean[5][3];
        
        public static void crear_matrices(){
            cero[0][0] = true; cero[0][1] = true; cero[0][2] = true;
            cero[1][0] = true; cero[1][1] = false; cero[1][2] = true;
            cero[2][0] = true; cero[2][1] = false; cero[2][2] = true;
            cero[3][0] = true; cero[3][1] = false; cero[3][2] = true;
            cero[4][0] = true; cero[4][1] = true; cero[4][2] = true;
            
            uno[0][0] = false; uno[0][1] = false; uno[0][2] = true;
            uno[1][0] = false; uno[1][1] = false; uno[1][2] = true;
            uno[2][0] = false; uno[2][1] = false; uno[2][2] = true;
            uno[3][0] = false; uno[3][1] = false; uno[3][2] = true;
            uno[4][0] = false; uno[4][1] = false; uno[4][2] = true;
            
            dos[0][0] = true; dos[0][1] = true; dos[0][2] = true;
            dos[1][0] = false; dos[1][1] = false; dos[1][2] = true;
            dos[2][0] = true; dos[2][1] = true; dos[2][2] = true;
            dos[3][0] = true; dos[3][1] = false; dos[3][2] = false;
            dos[4][0] = true; dos[4][1] = true; dos[4][2] = true;
            
            tres[0][0] = true; tres[0][1] = true; tres[0][2] = true;
            tres[1][0] = false; tres[1][1] = false; tres[1][2] = true;
            tres[2][0] = true; tres[2][1] = true; tres[2][2] = true;
            tres[3][0] = false; tres[3][1] = false; tres[3][2] = true;
            tres[4][0] = true; tres[4][1] = true; tres[4][2] = true;
            
            cuatro[0][0] = true; cuatro[0][1] = false; cuatro[0][2] = true;
            cuatro[1][0] = true; cuatro[1][1] = false; cuatro[1][2] = true;
            cuatro[2][0] = true; cuatro[2][1] = true; cuatro[2][2] = true;
            cuatro[3][0] = false; cuatro[3][1] = false; cuatro[3][2] = true;
            cuatro[4][0] = false; cuatro[4][1] = false; cuatro[4][2] = true;
            
            cinco[0][0] = true; cinco[0][1] = true; cinco[0][2] = true;
            cinco[1][0] = true; cinco[1][1] = false; cinco[1][2] = false;
            cinco[2][0] = true; cinco[2][1] = true; cinco[2][2] = true;
            cinco[3][0] = false; cinco[3][1] = false; cinco[3][2] = true;
            cinco[4][0] = true; cinco[4][1] = true; cinco[4][2] = true;
            
            seis[0][0] = true; seis[0][1] = true; seis[0][2] = true;
            seis[1][0] = true; seis[1][1] = false; seis[1][2] = false;
            seis[2][0] = true; seis[2][1] = true; seis[2][2] = true;
            seis[3][0] = true; seis[3][1] = false; seis[3][2] = true;
            seis[4][0] = true; seis[4][1] = true; seis[4][2] = true;
            
            siete[0][0] = true; siete[0][1] = true; siete[0][2] = true;
            siete[1][0] = false; siete[1][1] = false; siete[1][2] = true;
            siete[2][0] = false; siete[2][1] = false; siete[2][2] = true;
            siete[3][0] = false; siete[3][1] = false; siete[3][2] = true;
            siete[4][0] = false; siete[4][1] = false; siete[4][2] = true;
            
            ocho[0][0] = true; ocho[0][1] = true; ocho[0][2] = true;
            ocho[1][0] = true; ocho[1][1] = false; ocho[1][2] = true;
            ocho[2][0] = true; ocho[2][1] = true; ocho[2][2] = true;
            ocho[3][0] = true; ocho[3][1] = false; ocho[3][2] = true;
            ocho[4][0] = true; ocho[4][1] = true; ocho[4][2] = true;
            
            nueve[0][0] = true; nueve[0][1] = true; nueve[0][2] = true;
            nueve[1][0] = true; nueve[1][1] = false; nueve[1][2] = true;
            nueve[2][0] = true; nueve[2][1] = true; nueve[2][2] = true;
            nueve[3][0] = false; nueve[3][1] = false; nueve[3][2] = true;
            nueve[4][0] = true; nueve[4][1] = true; nueve[4][2] = true;
        }
        public static int pdigito, sdigito, tdigito;
        public static void comparar(int n){
            boolean matriz_comprobante[][] = new boolean[5][3];
            boolean comprobante = true;
            switch(n){
                case 1:
                    for(int i = 0; i < 5; i++){
                        for(int j = 0; j < 3; j++){
                            matriz_comprobante[i][j] = matriz1[i][j] == cero[i][j];
                        }
                    }
                    for(int i = 0; i < 5; i++){
                        for(int j = 0; j < 3; j++){
                            comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                        }
                    }
                    if(comprobante){
                        pdigito = 0;
                    }else{
                        comprobante = true;
                        for(int i = 0; i < 5; i++){
                            for(int j = 0; j < 3; j++){
                                matriz_comprobante[i][j] = matriz1[i][j] == uno[i][j];
                            }
                        }
                        for(int i = 0; i < 5; i++){
                            for(int j = 0; j < 3; j++){
                                comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                            }
                        }
                        if(comprobante){
                            pdigito = 1;
                        }else{
                            comprobante = true;
                            for(int i = 0; i < 5; i++){
                                for(int j = 0; j < 3; j++){
                                    matriz_comprobante[i][j] = matriz1[i][j] == dos[i][j];
                                }
                            }
                            for(int i = 0; i < 5; i++){
                                for(int j = 0; j < 3; j++){
                                    comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                }
                            }                            
                            if(comprobante){
                                pdigito = 2;
                            }else{
                                comprobante = true;
                                for(int i = 0; i < 5; i++){
                                    for(int j = 0; j < 3; j++){
                                        matriz_comprobante[i][j] = matriz1[i][j] == tres[i][j];
                                    }
                                }
                                for(int i = 0; i < 5; i++){
                                    for(int j = 0; j < 3; j++){
                                        comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                    }
                                }
                                if(comprobante){
                                    pdigito = 3;
                                }else{
                                    comprobante = true;
                                    for(int i = 0; i < 5; i++){
                                        for(int j = 0; j < 3; j++){
                                            matriz_comprobante[i][j] = matriz1[i][j] == cuatro[i][j];
                                        }
                                    }
                                    for(int i = 0; i < 5; i++){
                                        for(int j = 0; j < 3; j++){
                                            comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                        }
                                    }
                                    if(comprobante){
                                        pdigito = 4;
                                    }else{
                                        comprobante = true;
                                        for(int i = 0; i < 5; i++){
                                            for(int j = 0; j < 3; j++){
                                                matriz_comprobante[i][j] = matriz1[i][j] == cinco[i][j];
                                            }
                                        }
                                        for(int i = 0; i < 5; i++){
                                            for(int j = 0; j < 3; j++){
                                                comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                            }
                                        }
                                        if(comprobante){
                                            pdigito = 5;
                                        }else{
                                            comprobante = true;
                                            for(int i = 0; i < 5; i++){
                                                for(int j = 0; j < 3; j++){
                                                    matriz_comprobante[i][j] = matriz1[i][j] == seis[i][j];
                                                }
                                            }
                                            for(int i = 0; i < 5; i++){
                                                for(int j = 0; j < 3; j++){
                                                    comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                                }
                                            }
                                            if(comprobante){
                                                pdigito = 6;
                                            }else{
                                                comprobante = true;
                                                for(int i = 0; i < 5; i++){
                                                    for(int j = 0; j < 3; j++){
                                                        matriz_comprobante[i][j] = matriz1[i][j] == siete[i][j];
                                                    }
                                                }
                                                for(int i = 0; i < 5; i++){
                                                    for(int j = 0; j < 3; j++){
                                                        comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                                    }
                                                }
                                                if(comprobante){
                                                    pdigito = 7;
                                                }else{
                                                    comprobante = true;
                                                    for(int i = 0; i < 5; i++){
                                                        for(int j = 0; j < 3; j++){
                                                            matriz_comprobante[i][j] = matriz1[i][j] == ocho[i][j];
                                                        }
                                                    }
                                                    for(int i = 0; i < 5; i++){
                                                        for(int j = 0; j < 3; j++){
                                                            comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                                        }
                                                    }
                                                    if(comprobante){
                                                        pdigito = 8;
                                                    }else{
                                                        comprobante = true;
                                                        for(int i = 0; i < 5; i++){
                                                            for(int j = 0; j < 3; j++){
                                                                matriz_comprobante[i][j] = matriz1[i][j] == nueve[i][j];
                                                            }
                                                        }
                                                        for(int i = 0; i < 5; i++){
                                                            for(int j = 0; j < 3; j++){
                                                                comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                                            }
                                                        }
                                                        if(comprobante){
                                                            pdigito = 9;
                                                        }
                                                    }
                                                }
                                            }
                                        }       
                                    }         
                                }
                            } 
                        }
                    }
                    break;
                case 2:
                    for(int i = 0; i < 5; i++){
                        for(int j = 0; j < 3; j++){
                            matriz_comprobante[i][j] = matriz2[i][j] == cero[i][j];
                        }
                    }
                    for(int i = 0; i < 5; i++){
                        for(int j = 0; j < 3; j++){
                            comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                        }
                    }
                    if(comprobante){
                        sdigito = 0;
                    }else{
                        comprobante = true;
                        for(int i = 0; i < 5; i++){
                            for(int j = 0; j < 3; j++){
                                matriz_comprobante[i][j] = matriz2[i][j] == uno[i][j];
                            }
                        }
                        for(int i = 0; i < 5; i++){
                            for(int j = 0; j < 3; j++){
                                comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                            }
                        }
                        if(comprobante){
                            sdigito = 1;
                        }else{
                            comprobante = true;
                            for(int i = 0; i < 5; i++){
                                for(int j = 0; j < 3; j++){
                                    matriz_comprobante[i][j] = matriz2[i][j] == dos[i][j];
                                }
                            }
                            for(int i = 0; i < 5; i++){
                                for(int j = 0; j < 3; j++){
                                    comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                }
                            }                            
                            if(comprobante){
                                sdigito = 2;
                            }else{
                                comprobante = true;
                                for(int i = 0; i < 5; i++){
                                    for(int j = 0; j < 3; j++){
                                        matriz_comprobante[i][j] = matriz2[i][j] == tres[i][j];
                                    }
                                }
                                for(int i = 0; i < 5; i++){
                                    for(int j = 0; j < 3; j++){
                                        comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                    }
                                }
                                if(comprobante){
                                    sdigito = 3;
                                }else{
                                    comprobante = true;
                                    for(int i = 0; i < 5; i++){
                                        for(int j = 0; j < 3; j++){
                                            matriz_comprobante[i][j] = matriz2[i][j] == cuatro[i][j];
                                        }
                                    }
                                    for(int i = 0; i < 5; i++){
                                        for(int j = 0; j < 3; j++){
                                            comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                        }
                                    }
                                    if(comprobante){
                                        sdigito = 4;
                                    }else{
                                        comprobante = true;
                                        for(int i = 0; i < 5; i++){
                                            for(int j = 0; j < 3; j++){
                                                matriz_comprobante[i][j] = matriz2[i][j] == cinco[i][j];
                                            }
                                        }
                                        for(int i = 0; i < 5; i++){
                                            for(int j = 0; j < 3; j++){
                                                comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                            }
                                        }
                                        if(comprobante){
                                            sdigito = 5;
                                        }else{
                                            comprobante = true;
                                            for(int i = 0; i < 5; i++){
                                                for(int j = 0; j < 3; j++){
                                                    matriz_comprobante[i][j] = matriz2[i][j] == seis[i][j];
                                                }
                                            }
                                            for(int i = 0; i < 5; i++){
                                                for(int j = 0; j < 3; j++){
                                                    comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                                }
                                            }
                                            if(comprobante){
                                                sdigito = 6;
                                            }else{
                                                comprobante = true;
                                                for(int i = 0; i < 5; i++){
                                                    for(int j = 0; j < 3; j++){
                                                        matriz_comprobante[i][j] = matriz2[i][j] == siete[i][j];
                                                    }
                                                }
                                                for(int i = 0; i < 5; i++){
                                                    for(int j = 0; j < 3; j++){
                                                        comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                                    }
                                                }
                                                if(comprobante){
                                                    sdigito = 7;
                                                }else{
                                                    comprobante = true;
                                                    for(int i = 0; i < 5; i++){
                                                        for(int j = 0; j < 3; j++){
                                                            matriz_comprobante[i][j] = matriz2[i][j] == ocho[i][j];
                                                        }
                                                    }
                                                    for(int i = 0; i < 5; i++){
                                                        for(int j = 0; j < 3; j++){
                                                            comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                                        }
                                                    }
                                                    if(comprobante){
                                                        sdigito = 8;
                                                    }else{
                                                        comprobante = true;
                                                        for(int i = 0; i < 5; i++){
                                                            for(int j = 0; j < 3; j++){
                                                                matriz_comprobante[i][j] = matriz2[i][j] == nueve[i][j];
                                                            }
                                                        }
                                                        for(int i = 0; i < 5; i++){
                                                            for(int j = 0; j < 3; j++){
                                                                comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                                            }
                                                        }
                                                        if(comprobante){
                                                            sdigito = 9;
                                                        }
                                                    }
                                                }
                                            }
                                        }       
                                    }         
                                }
                            } 
                        }
                    }
                    break;
                case 3:
                    for(int i = 0; i < 5; i++){
                        for(int j = 0; j < 3; j++){
                            matriz_comprobante[i][j] = matriz3[i][j] == cero[i][j];
                        }
                    }
                    for(int i = 0; i < 5; i++){
                        for(int j = 0; j < 3; j++){
                            comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                        }
                    }
                    if(comprobante){
                        tdigito = 0;
                    }else{
                        comprobante = true;
                        for(int i = 0; i < 5; i++){
                            for(int j = 0; j < 3; j++){
                                matriz_comprobante[i][j] = matriz3[i][j] == uno[i][j];
                            }
                        }
                        for(int i = 0; i < 5; i++){
                            for(int j = 0; j < 3; j++){
                                comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                            }
                        }
                        if(comprobante){
                            tdigito = 1;
                        }else{
                            comprobante = true;
                            for(int i = 0; i < 5; i++){
                                for(int j = 0; j < 3; j++){
                                    matriz_comprobante[i][j] = matriz3[i][j] == dos[i][j];
                                }
                            }
                            for(int i = 0; i < 5; i++){
                                for(int j = 0; j < 3; j++){
                                    comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                }
                            }                            
                            if(comprobante){
                                tdigito = 2;
                            }else{
                                comprobante = true;
                                for(int i = 0; i < 5; i++){
                                    for(int j = 0; j < 3; j++){
                                        matriz_comprobante[i][j] = matriz3[i][j] == tres[i][j];
                                    }
                                }
                                for(int i = 0; i < 5; i++){
                                    for(int j = 0; j < 3; j++){
                                        comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                    }
                                }
                                if(comprobante){
                                    tdigito = 3;
                                }else{
                                    comprobante = true;
                                    for(int i = 0; i < 5; i++){
                                        for(int j = 0; j < 3; j++){
                                            matriz_comprobante[i][j] = matriz3[i][j] == cuatro[i][j];
                                        }
                                    }
                                    for(int i = 0; i < 5; i++){
                                        for(int j = 0; j < 3; j++){
                                            comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                        }
                                    }
                                    if(comprobante){
                                        tdigito = 4;
                                    }else{
                                        comprobante = true;
                                        for(int i = 0; i < 5; i++){
                                            for(int j = 0; j < 3; j++){
                                                matriz_comprobante[i][j] = matriz3[i][j] == cinco[i][j];
                                            }
                                        }
                                        for(int i = 0; i < 5; i++){
                                            for(int j = 0; j < 3; j++){
                                                comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                            }
                                        }
                                        if(comprobante){
                                            tdigito = 5;
                                        }else{
                                            comprobante = true;
                                            for(int i = 0; i < 5; i++){
                                                for(int j = 0; j < 3; j++){
                                                    matriz_comprobante[i][j] = matriz3[i][j] == seis[i][j];
                                                }
                                            }
                                            for(int i = 0; i < 5; i++){
                                                for(int j = 0; j < 3; j++){
                                                    comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                                }
                                            }
                                            if(comprobante){
                                                tdigito = 6;
                                            }else{
                                                comprobante = true;
                                                for(int i = 0; i < 5; i++){
                                                    for(int j = 0; j < 3; j++){
                                                        matriz_comprobante[i][j] = matriz3[i][j] == siete[i][j];
                                                    }
                                                }
                                                for(int i = 0; i < 5; i++){
                                                    for(int j = 0; j < 3; j++){
                                                        comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                                    }
                                                }
                                                if(comprobante){
                                                    tdigito = 7;
                                                }else{
                                                    comprobante = true;
                                                    for(int i = 0; i < 5; i++){
                                                        for(int j = 0; j < 3; j++){
                                                            matriz_comprobante[i][j] = matriz3[i][j] == ocho[i][j];
                                                        }
                                                    }
                                                    for(int i = 0; i < 5; i++){
                                                        for(int j = 0; j < 3; j++){
                                                            comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                                        }
                                                    }
                                                    if(comprobante){
                                                        tdigito = 8;
                                                    }else{
                                                        comprobante = true;
                                                        for(int i = 0; i < 5; i++){
                                                            for(int j = 0; j < 3; j++){
                                                                matriz_comprobante[i][j] = matriz3[i][j] == nueve[i][j];
                                                            }
                                                        }
                                                        for(int i = 0; i < 5; i++){
                                                            for(int j = 0; j < 3; j++){
                                                                comprobante = (matriz_comprobante[i][j] == true) && comprobante;
                                                            }
                                                        }
                                                        if(comprobante){
                                                            tdigito = 9;
                                                        }
                                                    }
                                                }
                                            }
                                        }       
                                    }         
                                }
                            } 
                        }
                    }
                    break;
            }
        }
        public static void imprimir_numero(){
            System.out.print("El numero es "+pdigito+sdigito+tdigito);
        }
}

