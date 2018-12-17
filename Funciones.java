
public class Funciones {
//Distancia de Hamming entre dos cadenas de Bytes
				public static int dist_Hamm(byte[] B1, byte[] B2){
					int conv = 0, conv2 = 0;
					int Hamming = 0;
					int i = 0;

					while(i<B1.length) {
						conv = (int) B1[i] & 0x000000FF;
						conv2 = (int) B2[i] & 0x000000FF;						
						Hamming += Integer.bitCount(conv ^ conv2);
						i++;
					}

					return Hamming;
				}
				
//Moda de Hamming
				public static int moda_Hamm(int[] Hamming){
					int moda = 0;
					int n_apariciones = 0;
					int maxVeces_Aparece = 0;
					
					for(int i=0; i<Hamming.length; i++){
						n_apariciones = 0;
						
						int j = 0;
						while(j<Hamming.length) {
							if(Hamming[j]==Hamming[i]) {
								n_apariciones++;
							}
							j++;
						}
						
						if(n_apariciones > maxVeces_Aparece){
							moda = Hamming[i];
							maxVeces_Aparece = n_apariciones;
						}
					}
					
					return moda;
				}
				
//Media de Hamming
				public static int media_Hamm(int[] Hamming){
					int media = 0;
					long aux = 0;
					int i = 0;

					while (i<Hamming.length) {
						aux += Hamming[i];
						i++;
					}
					
					media = (int) (aux/Hamming.length);
					
					return media;
				}

//Desviacion de Hamming
				public static float desviacion_Hamm(int[] Hamming){
					float desviacion = 0;
					long aux = 0;
					int media = media_Hamm(Hamming);
					int i = 0;
					
					while(i<Hamming.length) {
						aux += Math.pow(Hamming[i]-media, 2);
						i++;
					}
					
					desviacion = (float) Math.sqrt(aux/Hamming.length);
					
					return desviacion;
				}
				
//Asimetria de Hamming
				public static float asimetria_Hamm(int[] Hamming){
					float asimetria = 0;
					int moda = moda_Hamm(Hamming);
					int media = media_Hamm(Hamming);
					float desviacion = desviacion_Hamm(Hamming);
					
					asimetria = (media-moda)/desviacion;				
					
					return asimetria;
				}
}