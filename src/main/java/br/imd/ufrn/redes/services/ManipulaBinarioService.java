package br.imd.ufrn.redes.services;

public class ManipulaBinarioService {
	private String binario;
	
	public ManipulaBinarioService(){
		this.binario = new String();
	}

	public String getBinario() {
		return binario;
	}

	public void setBinario(String binario) {
		this.binario = binario;
	}
	
	public static String converteDecimalBinario(int decimal){
		String bin = new String();
		bin = Integer.toString(decimal, 2);
		bin = completaZerosBinario(bin);
		return bin;
	}
	
	public static String completaZerosBinario(String binario){
		String strAux = new String();
		if(binario.length() <= 8){
			int qtdFaltante = 8 - binario.length();
			for(int i = 0 ; i < qtdFaltante ; i++){
				strAux += "0";
			}
			strAux += binario;
		}
		return strAux;
	}
	
	public static int converteBinarioDecimal(String binario){
		int decimal = Integer.parseInt(binario, 2);
		return decimal;
	}
}
