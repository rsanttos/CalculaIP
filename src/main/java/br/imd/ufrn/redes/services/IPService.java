package br.imd.ufrn.redes.services;

import java.util.ArrayList;
import java.util.List;

import br.imd.ufrn.redes.dominio.IP;

public class IPService {

	private IP ip;

	public IPService() {
		this.ip = new IP();
	}

	public IP getIp() {
		return ip;
	}

	public void setIp(IP ip) {
		this.ip = ip;
	}

	public static void main(String args[]) {
		IP ip = new IP("192.162.32.125");
		IP mascara = new IP("255.255.240.0");
		separaBlocos(ip);
		// imprimeBlocosInteirosEBinarios(ip);
		defineClasseIP(ip);
//		System.out.println(ip.getClasse());
		// defineMascaraPadraoIP(ip);
		// System.out.println(ip.getMascaraPadrao());
		// defineNetIDSimples(ip);
		// defineHostIDSimples(ip);
		// System.out.println(ip.getNetID());
		// System.out.println(ip.getHostID());
		// boolean testePonto = validarSeparacaoPorPontoIP(ip);
		// System.out.println(testePonto);
		// boolean testeIntervalo = validarIntervaloBloco(ip);
		// System.out.println(testeIntervalo);
		// defineNetIDDinamico(ip, mascara);
		// calculaAND(ip, mascara);
		//defineEnderecoIPBinarioSemPonto(ip);
		//defineEnderecoIPBinarioSemPonto(mascara);
		//defineNetIDDinamico(ip, mascara);
		//defineHostIDDinamico(ip, mascara);
//		System.out.println(ip.getEnderecoIPBinario());
//		System.out.println(ip.getNetIDBinario());
//		System.out.println(ip.getHostIDBinario());
//		calculaNetID(ip, 12);
		//System.out.println(ip.getNetID());
		//System.out.println(ip.getNetIDBinario());
		//calculaHostID(ip, 20);
		//System.out.println(ip.getHostID());
		//System.out.println(ip.getHostIDBinario());
		System.out.println(verificaEnderecoPrivado(ip));
		//System.out.println(calculaQtdUmDireita(mascara));
	}

	public static boolean validarSeparacaoPorPontoIP(IP ip) {
		int tamanho = ip.getEnderecoIP().length();
		int contPontos = 0;
		int contDigitos = 0;
		int j = 0;
		if (tamanho <= 15) {
			while (j <= tamanho) {
				if (j < tamanho) {
					if (ip.getEnderecoIP().charAt(j) != '.') {
						contDigitos++;
						if (contDigitos > 3) {
							return false;
						}
					} else {
						contPontos++;
						contDigitos = 0;
					}
				} else {
					if (contPontos > 3) {
						return false;
					}
				}
				j++;
			}
			return true;
		} else {
			return false;
		}
	}

	public static void defineNetIDDinamico(IP ip, IP mascara){
		int qtdUmDireita = calculaQtdUmDireita(mascara);
		int n = 32 - qtdUmDireita;
		
		calculaNetID(ip, n);	
	}
	
	public static void defineHostIDDinamico(IP ip, IP mascara){
		int qtdUmDireita = calculaQtdUmDireita(mascara);
		int n = 32 - qtdUmDireita;
		
		calculaHostID(ip, n);
	}
	
	public static boolean validarIntervaloBloco(IP ip) {
		separaBlocos(ip);
		int tamanho = ip.getBlocosInteiros().size();
		for (int i = 0; i < tamanho; i++) {
			if (Integer.parseInt(ip.getBlocosInteiros().get(i)) < 0
					|| Integer.parseInt(ip.getBlocosInteiros().get(i)) > 255) {
				return false;
			}
		}
		return true;
	}

	public static int calculaQtdUmDireita(IP ip){
		int i = 0;
		int cont = 0;
		int tamanho = ip.getEnderecoIPBinario().length();
		while(i < tamanho){
			if(ip.getEnderecoIPBinario().charAt(i) != '0'){
				cont++;
			} else {
				return cont;
			}
			i++;
		}
		return cont;
	}
	
	public static void calculaNetID(IP ip, int n) {
		String pedaco = new String();
		String arrayZero = new String();
		String aux = new String();
		String auxDecimal = new String();
		int fimPedaco = ip.getNetIDBinario().length();
		int inicioPedaco = fimPedaco - n;
		int limite;
		int i = n;
		List<String> arrayAux = new ArrayList<String>();

		while (i > 0) {
			arrayZero += '0';
			i--;
		}

		pedaco = ip.getNetIDBinario().substring(inicioPedaco, fimPedaco);
		ip.setNetIDBinario(ip.getNetIDBinario().replace(pedaco, arrayZero));

		i = 1;		
		while(i < 5){
			limite = i*8;
			for(int j = limite - 8 ; j < limite ; j++){
				aux += ip.getNetIDBinario().charAt(j);				
			}
			int str = ManipulaBinarioService.converteBinarioDecimal(aux);
			arrayAux.add(Integer.toString(str));
			auxDecimal += Integer.toString(str);
			auxDecimal += ".";
			aux = new String();
			i++;
		}
		ip.setNetID(auxDecimal);
	}

	public static void calculaHostID(IP ip, int n) {
		String pedaco = new String();
		String arrayUm = new String();	
		String aux = new String();
		String auxDecimal = new String();
		int fimPedaco = ip.getHostIDBinario().length();
		int inicioPedaco = fimPedaco - n;
		int limite;
		int i = n;
		
		List<String> arrayAux = new ArrayList<String>();

		while (i > 0) {
			arrayUm += '1';
			i--;
		}

		pedaco = ip.getHostIDBinario().substring(inicioPedaco, fimPedaco);
		ip.setHostIDBinario(ip.getHostIDBinario().replace(pedaco, arrayUm));
		
		i = 1;		
		while(i < 5){
			limite = i*8;
			for(int j = limite - 8 ; j < limite ; j++){
				aux += ip.getHostIDBinario().charAt(j);				
			}
			int str = ManipulaBinarioService.converteBinarioDecimal(aux);
			arrayAux.add(Integer.toString(str));
			auxDecimal += Integer.toString(str);
			auxDecimal += ".";
			aux = new String();
			i++;
		}
		ip.setHostID(auxDecimal);
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
			} else {
				listaBlocosInteiros.add(aux);
				inteiroAux = Integer.parseInt(aux);
				listaBlocosBinarios.add(ManipulaBinarioService.converteDecimalBinario(inteiroAux));
			}
			j++;
		}
		ip.setBlocosInteiros(listaBlocosInteiros);
		ip.setBlocosBinarios(listaBlocosBinarios);
	}

	public static void defineClasseIP(IP ip) {
		int primeiroByte = Integer.parseInt(ip.getBlocosInteiros().get(0));
		if (primeiroByte >= 0 && primeiroByte <= 127) {
			ip.setClasse('A');
			ip.setMascaraPadrao("255.0.0.0");
		} else if (primeiroByte >= 128 && primeiroByte <= 191) {
			ip.setClasse('B');
			ip.setMascaraPadrao("255.255.0.0");
		} else if (primeiroByte >= 192 && primeiroByte <= 223) {
			ip.setClasse('C');
			ip.setMascaraPadrao("255.255.255.0");
		} else if (primeiroByte >= 224 && primeiroByte <= 239) {
			ip.setClasse('D');
			ip.setMascaraPadrao("Não se aplica");
		} else if (primeiroByte >= 240 && primeiroByte <= 255) {
			ip.setClasse('E');
			ip.setMascaraPadrao("Não se aplica");
		}
	}

//	public static void defineMascaraPadraoIP(IP ip) {
//		char classe = ip.getClasse();
//		switch (classe) {
//		case 'A':
//			ip.setMascaraPadrao("255.0.0.0");
//			break;
//		case 'B':
//			ip.setMascaraPadrao("255.255.0.0");
//			break;
//		case 'C':
//			ip.setMascaraPadrao("255.255.255.0");
//			break;
//		case 'D':
//			ip.setMascaraPadrao("Não se aplica");
//			break;
//		case 'E':
//			ip.setMascaraPadrao("Não se aplica");
//			break;
//		}
//	}

	public static boolean verificaEnderecoPrivado(IP ip){
		if(ip.getClasse() == 'A'){
			if(ip.getBlocosInteiros().get(0).equals(new String("10"))){
				return true;
			}
		} else if (ip.getClasse() == 'B'){
			if(ip.getBlocosInteiros().get(0).equals(new String("172")) 
					&& Integer.parseInt(ip.getBlocosInteiros().get(1)) >= 16 
					&& Integer.parseInt(ip.getBlocosInteiros().get(1)) <= 31){
				return true;
			}
		} else if (ip.getClasse() == 'C') {
			if(ip.getBlocosInteiros().get(0).equals(new String("192")) || ip.getBlocosInteiros().get(1).equals(new String("168"))){
				return true;
			}
		}
		
		
		return false;
	}
	public static void defineNetIDSimples(IP ip) {
		char classe = ip.getClasse();
		switch (classe) {
		case 'A':
			ip.setNetID(ip.getBlocosInteiros().get(0) + ".0.0.0");
			break;
		case 'B':
			ip.setNetID(ip.getBlocosInteiros().get(0) + "." + ip.getBlocosInteiros().get(1) + ".0.0");
			break;
		case 'C':
			ip.setNetID(ip.getBlocosInteiros().get(0) + "." + ip.getBlocosInteiros().get(1) + "."
					+ ip.getBlocosInteiros().get(2) + ".0");
			break;
		case 'D':
			ip.setNetID("Não se aplica");
			break;
		case 'E':
			ip.setNetID("Não se aplica");
			break;
		}
	}

	public static void defineHostIDSimples(IP ip) {
		char classe = ip.getClasse();
		switch (classe) {
		case 'A':
			ip.setHostID("0." + ip.getBlocosInteiros().get(1) + "." + ip.getBlocosInteiros().get(2) + "."
					+ ip.getBlocosInteiros().get(3));
			break;
		case 'B':
			ip.setHostID("0.0." + ip.getBlocosInteiros().get(2) + "." + ip.getBlocosInteiros().get(3));
			break;
		case 'C':
			ip.setHostID("0.0.0." + ip.getBlocosInteiros().get(3));
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
		for (int i = 0; i < ip.getBlocosBinarios().size(); i++) {
			System.out.println("Posicao " + i + ":" + ip.getBlocosBinarios().get(i));
		}
	}

	public static void defineEnderecoIPBinarioSemPonto(IP ip) {
		String aux = new String();
		String auxBin = new String();
		int inteiroAux = 0;
		int tamanho = ip.getEnderecoIP().length();
		int j = 0;
		while (j <= tamanho) {
			if (j < tamanho) {
				if (ip.getEnderecoIP().charAt(j) != '.') {
					aux += ip.getEnderecoIP().charAt(j);
				} else {
					inteiroAux = Integer.parseInt(aux);
					auxBin += ManipulaBinarioService.converteDecimalBinario(inteiroAux);
					// auxBin+='.';
					aux = new String();
				}
			} else {
				inteiroAux = Integer.parseInt(aux);
				auxBin += ManipulaBinarioService.converteDecimalBinario(inteiroAux);
			}
			j++;
		}
		ip.setEnderecoIPBinario(auxBin);
		ip.setNetIDBinario(auxBin);
		ip.setHostIDBinario(auxBin);
	}

	public static String colocaPontosEnderecoBinario(IP ip) {
		String ipAux = new String();
		for (int i = 0; i < ipAux.length(); i++) {

		}
		return ipAux;
	}
}
