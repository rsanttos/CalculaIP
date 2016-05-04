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
		return bin;
	}
	
	public int converteBinarioDecimal(String binario){
		int decimal = Integer.parseInt(binario, 2);
		return decimal;
	}
}
