package br.imd.ufrn.redes.services;

import java.util.ArrayList;
import java.util.List;

import br.imd.ufrn.redes.dominio.IP;

public class IPService {

	private IP ip;
	private static ManipulaBinarioService manipulaBinarioService;

	public IPService() {
		this.ip = new IP();
		manipulaBinarioService = new ManipulaBinarioService();
	}

	public IP getIp() {
		return ip;
	}

	public void setIp(IP ip) {
		this.ip = ip;
	}

	public static void main(String args[]) {
		IP ip = new IP("122.168.255.000");
		separaBlocos(ip);
		imprimeBlocosInteirosEBinarios(ip);
		defineClasseIP(ip);
		System.out.println(ip.getClasse());	
		defineMascaraPadraoIP(ip);
		System.out.println(ip.getMascaraPadrao());	
		defineNetID(ip);
		defineHostID(ip);
		System.out.println(ip.getNetID());
		System.out.println(ip.getHostID());
	}

	public static void separaBlocos(IP ip) {
		String aux = new String();
		int inteiroAux = 0;
		int tamanho = ip.getEnderecoIP().length();
		int j = 0;
		List<String> listaBlocosInteiros = new ArrayList<String>();
		List<String> listaBlocosBinarios = new ArrayList<String>();
		while (j <= tamanho) {
			if (j < tamanho) {
				if (ip.getEnderecoIP().charAt(j) != '.') {
					aux += ip.getEnderecoIP().charAt(j);
				} else {
					listaBlocosInteiros.add(aux);
					inteiroAux = Integer.parseInt(aux);
					listaBlocosBinarios.add(ManipulaBinarioService.converteDecimalBinario(inteiroAux));
					aux = new String();
				}
			}else{
				listaBlocosInteiros.add(aux);
				inteiroAux = Integer.parseInt(aux);
				listaBlocosBinarios.add(ManipulaBinarioService.converteDecimalBinario(inteiroAux));
			}
			j++;
		}
		ip.setBlocosInteiros(listaBlocosInteiros);
		ip.setBlocosBinarios(listaBlocosBinarios);
	}
	
	public static void defineClasseIP(IP ip){
		int primeiroByte = Integer.parseInt(ip.getBlocosInteiros().get(0));
		if(primeiroByte >= 0 && primeiroByte <= 127){
			ip.setClasse('A');
		} else if(primeiroByte >= 128 && primeiroByte <= 191){
			ip.setClasse('B');
		} else if(primeiroByte >= 192 && primeiroByte <= 223){
			ip.setClasse('C');
		} else if(primeiroByte >= 224 && primeiroByte <= 239){
			ip.setClasse('D');
		} else if(primeiroByte >= 240 && primeiroByte <= 255){
			ip.setClasse('E');
		}
	}
	
	public static void defineMascaraPadraoIP(IP ip){
		char classe = ip.getClasse();
		switch(classe){
		case 'A':
			ip.setMascaraPadrao("255.0.0.0");
			break;
		case 'B':
			ip.setMascaraPadrao("255.255.0.0");
			break;
		case 'C':
			ip.setMascaraPadrao("255.255.255.0");
			break;
		case 'D':
			ip.setMascaraPadrao("Não se aplica");
			break;
		case 'E':
			ip.setMascaraPadrao("Não se aplica");
			break;
		}
	}
	public static void defineNetID(IP ip){
		char classe = ip.getClasse();
		switch(classe){
		case 'A':
			ip.setNetID(ip.getBlocosInteiros().get(0));
			break;
		case 'B':
			ip.setNetID(ip.getBlocosInteiros().get(0) + "." + ip.getBlocosInteiros().get(1));
			break;
		case 'C':
			ip.setNetID(ip.getBlocosInteiros().get(0) + "." + ip.getBlocosInteiros().get(1) + "." + ip.getBlocosInteiros().get(2));
			break;
		case 'D':
			ip.setNetID("Não se aplica");
			break;
		case 'E':
			ip.setNetID("Não se aplica");
			break;
		}
	}
	public static void defineHostID(IP ip){
		char classe = ip.getClasse();
		switch(classe){
		case 'A':
			ip.setHostID(ip.getBlocosInteiros().get(1) + "." + ip.getBlocosInteiros().get(2) + "." + ip.getBlocosInteiros().get(3));
			break;
		case 'B':
			ip.setHostID(ip.getBlocosInteiros().get(2) + "." + ip.getBlocosInteiros().get(3));
			break;
		case 'C':
			ip.setHostID(ip.getBlocosInteiros().get(3));
			break;
		case 'D':
			ip.setHostID("Não se aplica");
			break;
		case 'E':
			ip.setHostID("Não se aplica");
			break;
		}
	}
	
	
	public static void imprimeBlocosInteirosEBinarios(IP ip) {
		for (int i = 0; i < ip.getBlocosInteiros().size(); i++) {
			System.out.println("Posicao " + i + ":" + ip.getBlocosInteiros().get(i));
		}
		for(int i = 0 ; i < ip.getBlocosBinarios().size() ; i++){
			System.out.println("Posicao " + i + ":" + ip.getBlocosBinarios().get(i));
		}
	}
}
