import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;


public class MD5 {

	public static void main (String[] args){

		int[] Hamming = new int[128];// Aqui guardaremos las distancias de Hamming 
		byte[] cadena_Entrada = new byte [32];// Guardamos la cadena de Bytes de entrada que se le pasa al hash
		byte[] cadena_Salida = new byte [32];// Guardamos la cadena de Bytes de la primera salida
		byte[] cadenaSal_Rotacion = new byte [32];// Guardamos las sucesivas cadenas de Bytes de salida de las rotaciones de bits aplicadas a la cadena base
		
		int h = 0;
		float prob[] = new float[128];
		
		for(int i=0; i<32; i++)		// Ponemos cadena de entrada  a 0
					cadena_Entrada[i] = 0;

				try{
					
					MessageDigest hash = MessageDigest.getInstance("MD5");//Instanciamos la funcion MD5
					hash.update(cadena_Entrada);// Pasamos a la funcion MD5 la cadena de bytes como parametro
					cadena_Salida = hash.digest();// Ejecutamos la funcion MD5 y guardamos la cadena de bytes cifrada
					
					
					System.out.println("HEXADECIMAL ini: "+DatatypeConverter.printHexBinary(cadena_Entrada));
					System.out.println("HEXADECIMAL fin: "+DatatypeConverter.printHexBinary(cadena_Salida));
					System.out.println();
					
					// Calculamos la distancia de Hamming entre la salida inicial y las sucesivas salidas 
					for(int i=0; i<32; i++){
						for(int j=0; j<4; j++){
							
							cadena_Entrada[i] = (byte) Math.pow(2, j);
							hash.update(cadena_Entrada);// Pasamos a la funcion MD5 el complementado convertido a cadena de bytes como parametro
							cadenaSal_Rotacion = hash.digest();
					
							
							Funciones obj = new Funciones();
							
							Hamming[h] = obj.dist_Hamm(cadena_Salida, cadenaSal_Rotacion);
							System.out.println("Distancia de Hamming: " + Hamming[h]);
							h++;

							
							System.out.println("HEXADECIMAL original: " + DatatypeConverter.printHexBinary(cadena_Entrada));
							System.out.println("HEXADECIMAL resultante: "+DatatypeConverter.printHexBinary(cadenaSal_Rotacion));
							System.out.println();
						}
						cadena_Entrada[i] = (byte) 0;
					}
					
					//Distribucion de probabilidad
					for(int i=0; i<Hamming.length; i++){
						int vecesAparece = 0;
						for(int j=0; j<Hamming.length; j++){
							if(Hamming[j]==Hamming[i]){
								vecesAparece++;
							}
						}
						prob[i]=(float)vecesAparece/(float)Hamming.length;
						//System.out.println(prob[i]);
						
					}
					h++;
					
					Funciones obj1 = new Funciones();
					Funciones obj2 = new Funciones();
					Funciones obj3 = new Funciones();
					Funciones obj4 = new Funciones();
					
					int moda = obj1.moda_Hamm(Hamming);
					int media = obj2.media_Hamm(Hamming);
					float desviacion = obj3.desviacion_Hamm(Hamming);
					float asimetria = obj4.asimetria_Hamm(Hamming);
								
								
					System.out.println("Moda: " + moda);
					System.out.println("Media: " + media);
					System.out.println("Desviacion: " + desviacion);
					System.out.println("Asimetria: " + asimetria);
					}
				catch(NoSuchAlgorithmException e){
					System.exit(1);
					}
				}
	}