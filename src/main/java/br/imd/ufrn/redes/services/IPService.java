package br.imd.ufrn.redes.services;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import br.imd.ufrn.redes.dominio.IP;

/**
 * Classe de serviço responsável pela manipulação da classe IP e seus
 * respectivos atributos
 * 
 * @author ramonsantos
 *
 */
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
		IP ip = new IP("192.168.0.0");
		IP mascara = new IP("255.0.0.0");
		List<IP> lista = new ArrayList<IP>();
		lista = tratarIPComMascaraESubRedes(ip, mascara);
	}

	/**
	 * Método que recebe um IP e trata-o, retornando-o com as devidas
	 * informações
	 * 
	 * @param ip
	 */
	public IP tratarIPSimples(IP ip) {
		IP ipAux = new IP();
		ipAux = ip;
		// Primeiro passo: Separar os blocos dos enderecos
		separaBlocos(ipAux);
		// Segundo passo: validar se ip é válido
		if (validaEnderecoIP(ipAux)) {
			// Terceiro passo: Converter os endereços em binarios sem pontos
			// e guardar uma única string
			defineEnderecoIPBinarioSemPonto(ipAux);
			// Quarto passo: Definir a classe do IP e a máscara padrão (se
			// houver)
			defineClasseIP(ipAux);
			// Quinto passo: Verificar se o endereço IP é privado
			if (verificaEnderecoPrivado(ipAux)) {
				FacesMessage message = new FacesMessage("Atenção: O endereço é privado!");
			}
			// Sexto passo: definir netID e hostID a partir da classe do
			// endereço
			if (ipAux.getClasse() == 'A' || ipAux.getClasse() == 'B' || ipAux.getClasse() == 'C') {
				defineNetIDSimples(ipAux);
				defineHostIDSimples(ipAux);
			}
			return ipAux;
		} else {
			FacesMessage message = new FacesMessage("Por favor, digite um endereço válido");
			return null;
		}
	}

	/**
	 * Método responsável por tratar um ip com máscara e retornar informações
	 * como Endereço de Rede, Endereço de BroadCast e a Quantidade de Endereços
	 * do bloco
	 * 
	 * @param ip
	 * @return
	 */
	public static IP tratarIPComMascara(IP ip, IP mascara) {
		IP ipAux = new IP();
		ipAux = ip;
		// Primeiro passo: Separar os blocos dos enderecos
		separaBlocos(ipAux);
		separaBlocos(mascara);
		// Segundo passo: validar se ip é válido
		if (validaEnderecoIP(ipAux)) {
			// Terceiro passo: Converter os endereços em binarios sem pontos
			// e guardar uma única string
			defineEnderecoIPBinarioSemPonto(ipAux);
			defineEnderecoIPBinarioSemPonto(mascara);
			// Quarto passo: Definir a classe do IP e a máscara padrão (se
			// houver)
			defineClasseIP(ipAux);
			// Quinto passo: Verificar se o endereço IP é privado
			if (verificaEnderecoPrivado(ipAux)) {
				FacesMessage message = new FacesMessage("Atenção: O endereço é privado!");
			}
			defineNetIDDinamico(ipAux, mascara);
			defineHostIDDinamico(ipAux, mascara);
			defineEnderecoBroadcastDinamico(ipAux, mascara);
			defineEnderecoSubRedeDinamico(ipAux, mascara);
			ipAux.setQtdEnderecos(defineQtdEnderecos(ipAux, mascara));
			return ipAux;
		} else {
			FacesMessage message = new FacesMessage("Por favor, digite um endereço válido");
			return null;
		}
	}

	/**
	 * Método responsável por tratar um ip com máscara e retornar as informações
	 * de de todas as subredes possiveis
	 * 
	 * @param ip
	 * @return
	 */
	public static List<IP> tratarIPComMascaraESubRedes(IP ip, IP mascara) {
		IP ipAux = new IP();
		List<IP> listIPAux = new ArrayList<IP>();
		ipAux = ip;
		// Primeiro passo: Separar os blocos dos enderecos
		separaBlocos(ipAux);
		separaBlocos(mascara);
		// Segundo passo: validar se ip é válido
		if (validaEnderecoIP(ipAux)) {
			// Terceiro passo: Converter os endereços em binarios sem pontos
			// e guardar uma única string
			defineEnderecoIPBinarioSemPonto(ipAux);
			defineEnderecoIPBinarioSemPonto(mascara);
			// Quarto passo: Definir a classe do IP e a máscara padrão (se
			// houver)
			defineClasseIP(ipAux);
			// Quinto passo: Verificar se o endereço IP é privado
			if (verificaEnderecoPrivado(ipAux)) {
				FacesMessage message = new FacesMessage("Atenção: O endereço é privado!");
			}
			// Sexto passo: definir netID e hostID a partir da classe do
			// endereço
			defineNetIDDinamico(ipAux, mascara);
			defineHostIDDinamico(ipAux, mascara);
			defineEnderecoBroadcastDinamico(ipAux, mascara);
			defineEnderecoSubRedeDinamico(ipAux, mascara);
			ipAux.setQtdEnderecos(defineQtdEnderecos(ipAux, mascara));
			listIPAux = defineEnderecosHostPossiveis(ipAux, mascara);
			return listIPAux;
		} else {
			FacesMessage message = new FacesMessage("Por favor, digite um endereço válido");
			return null;
		}
	}

	// public static void main(String args[]) {
	// IP ip = new IP("255.255.255.255");
	// IP mascara = new IP("255.0.0.0");
	// separaBlocos(ip);
	// separaBlocos(mascara);
	// imprimeBlocosInteirosEBinarios(ip);
	// defineClasseIP(ip);
	// defineEnderecoIPBinarioSemPonto(ip);
	// defineEnderecoIPBinarioSemPonto(mascara);
	// System.out.println(ip.getClasse());
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
	// boolean testeGeral = validaEnderecoIP(ip);
	// System.out.println(testeGeral);
	// defineNetIDDinamico(ip, mascara);
	// defineNetIDDinamico(ip, mascara);
	// defineHostIDDinamico(ip, mascara);
	// System.out.println(ip.getEnderecoIPBinario());
	// System.out.println(ip.getNetIDBinario());
	// System.out.println(ip.getHostIDBinario());
	// calculaNetID(ip, 12);
	// System.out.println(ip.getNetID());
	// System.out.println(ip.getNetIDBinario());
	// calculaHostID(ip, 20);
	// System.out.println(ip.getHostID());
	// System.out.println(ip.getHostIDBinario());
	// System.out.println(verificaEnderecoPrivado(ip));
	// System.out.println(defineQtdEnderecos(ip, mascara));
	// System.out.println(calculaQtdUmDireita(mascara));
	// defineEnderecoSubRedeDinamico(ip, mascara);
	// defineEnderecosHostPossiveis(ip, mascara);
	// }
	/**
	 * Método que realiza a validação de um endereço IP
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean validaEnderecoIP(IP ip) {
		if (validarSeparacaoPorPontoIP(ip)) {
			if (validarIntervaloBloco(ip)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Método que valida se um endereço de ip está separado por pontos
	 * 
	 * @param ip
	 * @return
	 */
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
					if (contPontos != 3) {
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

	/**
	 * Método que recebe uma máscara e um ip e calcula o NetID dinamicamente
	 * 
	 * @param ip
	 * @param mascara
	 */
	public static void defineNetIDDinamico(IP ip, IP mascara) {
		int qtdUmDireita = calculaQtdUmDireita(mascara);
		int n = 32 - qtdUmDireita;

		calculaNetID(ip, n);
	}

	/**
	 * Método que recebe uma máscara e um ip e calcula o HostID dinamicamente
	 * 
	 * @param ip
	 * @param mascara
	 */
	public static void defineHostIDDinamico(IP ip, IP mascara) {
		int qtdUmDireita = calculaQtdUmDireita(mascara);
		int n = 32 - qtdUmDireita;

		calculaHostID(ip, n);
	}

	/**
	 * Método que recebe uma máscara e um ip e calcula o Endereço de Broadcast
	 * dinamicamente
	 * 
	 * @param ip
	 * @param mascara
	 */
	public static void defineEnderecoBroadcastDinamico(IP ip, IP mascara) {
		int qtdUmDireita = calculaQtdUmDireita(mascara);
		int n = 32 - qtdUmDireita;

		calculaEnderecoBroadcast(ip, n);
	}

	/**
	 * Método que recebe uma máscara e um ip e calcula o endereço de subrede
	 * dinamicamente
	 * 
	 * @param ip
	 * @param mascara
	 */
	public static void defineEnderecoSubRedeDinamico(IP ip, IP mascara) {
		int qtdUmDireita = calculaQtdUmDireita(mascara);
		int n = 32 - qtdUmDireita;

		calculaEnderecoSubRede(ip, n);
	}

	/**
	 * Método que valida se um endereço de IP está devidamente entre os
	 * intervalos de bloco
	 * 
	 * @param ip
	 */
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

	/**
	 * Método que calcula a quantidade de digitos um mais a direita em um
	 * endereço IP
	 * 
	 * @param ip
	 * @return
	 */
	public static int calculaQtdUmDireita(IP ip) {
		int i = 0;
		int cont = 0;
		int tamanho = ip.getEnderecoIPBinario().length();
		while (i < tamanho) {
			if (ip.getEnderecoIPBinario().charAt(i) != '0') {
				cont++;
			} else {
				return cont;
			}
			i++;
		}
		return cont;
	}

	/**
	 * Método que calcula o netid a partir de n, onde n é a quantidade de bits
	 * da máscara
	 * 
	 * @param ip
	 * @param n
	 */
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
		while (i < 5) {
			limite = i * 8;
			for (int j = limite - 8; j < limite; j++) {
				aux += ip.getNetIDBinario().charAt(j);
			}
			int str = ManipulaBinarioService.converteBinarioDecimal(aux);
			arrayAux.add(Integer.toString(str));
			auxDecimal += Integer.toString(str);
			if (i < 4) {
				auxDecimal += ".";
			}
			aux = new String();
			i++;
		}
		ip.setNetID(auxDecimal);
	}

	/**
	 * Método que calcula o hostid a partir de n, onde n é a quantidade de bits
	 * da máscara
	 * 
	 * @param ip
	 * @param n
	 */
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
		while (i < 5) {
			limite = i * 8;
			for (int j = limite - 8; j < limite; j++) {
				aux += ip.getHostIDBinario().charAt(j);
			}
			int str = ManipulaBinarioService.converteBinarioDecimal(aux);
			arrayAux.add(Integer.toString(str));
			auxDecimal += Integer.toString(str);
			if (i < 4) {
				auxDecimal += ".";
			}
			aux = new String();
			i++;
		}
		ip.setHostID(auxDecimal);
	}

	/**
	 * Método que calcula o endereço de broadcast a partir de n, onde n é a
	 * quantidade de bits da máscara
	 * 
	 * @param ip
	 * @param n
	 */
	public static void calculaEnderecoBroadcast(IP ip, int n) {
		String pedaco = new String();
		String arrayUm = new String();
		String aux = new String();
		String auxDecimal = new String();
		int fimPedaco = ip.getEnderecoBroadcastBinario().length();
		int inicioPedaco = fimPedaco - n;
		int limite;
		int i = n;

		List<String> arrayAux = new ArrayList<String>();

		while (i > 0) {
			arrayUm += '1';
			i--;
		}

		pedaco = ip.getEnderecoBroadcastBinario().substring(inicioPedaco, fimPedaco);
		ip.setEnderecoBroadcastBinario(ip.getEnderecoBroadcastBinario().replace(pedaco, arrayUm));

		i = 1;
		while (i < 5) {
			limite = i * 8;
			for (int j = limite - 8; j < limite; j++) {
				aux += ip.getEnderecoBroadcastBinario().charAt(j);
			}
			int str = ManipulaBinarioService.converteBinarioDecimal(aux);
			arrayAux.add(Integer.toString(str));
			auxDecimal += Integer.toString(str);
			if (i < 4) {
				auxDecimal += ".";
			}
			aux = new String();
			i++;
		}
		ip.setEnderecoBroadcast(auxDecimal);
	}

	/**
	 * Método que calcula o endereço de subrede a partir de n, onde n é a
	 * quantidade de bits da máscara
	 * 
	 * @param ip
	 * @param n
	 */
	public static void calculaEnderecoSubRede(IP ip, int n) {
		String pedaco = new String();
		String arrayZero = new String();
		String aux = new String();
		String auxDecimal = new String();
		int fimPedaco = ip.getEnderecoSubRedeBinario().length();
		int inicioPedaco = fimPedaco - n;
		int limite;
		int i = n;
		List<String> arrayAux = new ArrayList<String>();

		while (i > 0) {
			arrayZero += '0';
			i--;
		}

		pedaco = ip.getEnderecoSubRedeBinario().substring(inicioPedaco, fimPedaco);
		ip.setEnderecoSubRedeBinario(ip.getEnderecoSubRedeBinario().replace(pedaco, arrayZero));

		i = 1;
		while (i < 5) {
			limite = i * 8;
			for (int j = limite - 8; j < limite; j++) {
				aux += ip.getEnderecoSubRedeBinario().charAt(j);
			}
			int str = ManipulaBinarioService.converteBinarioDecimal(aux);
			arrayAux.add(Integer.toString(str));
			auxDecimal += Integer.toString(str);
			if (i < 4) {
				auxDecimal += ".";
			}
			aux = new String();
			i++;
		}
		ip.setEnderecoSubRede(auxDecimal);
	}

	/**
	 * Método que recebe um ip e separa seu conteúdo em blocos de números
	 * decimais e blocos de binários
	 * 
	 * @param ip
	 */
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
				aux = new String();
			}
			j++;
		}
		ip.setBlocosInteiros(listaBlocosInteiros);
		ip.setBlocosBinarios(listaBlocosBinarios);
	}

	/**
	 * Método que recebe um endereço IP e define sua classe
	 * 
	 * @param ip
	 */
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

	// public static void defineMascaraPadraoIP(IP ip) {
	// char classe = ip.getClasse();
	// switch (classe) {
	// case 'A':
	// ip.setMascaraPadrao("255.0.0.0");
	// break;
	// case 'B':
	// ip.setMascaraPadrao("255.255.0.0");
	// break;
	// case 'C':
	// ip.setMascaraPadrao("255.255.255.0");
	// break;
	// case 'D':
	// ip.setMascaraPadrao("Não se aplica");
	// break;
	// case 'E':
	// ip.setMascaraPadrao("Não se aplica");
	// break;
	// }
	// }
	/**
	 * Método que verifica se um endereço IP é privado ou não
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean verificaEnderecoPrivado(IP ip) {
		if (ip.getClasse() == 'A') {
			if (ip.getBlocosInteiros().get(0).equals(new String("10"))) {
				return true;
			}
		} else if (ip.getClasse() == 'B') {
			if (ip.getBlocosInteiros().get(0).equals(new String("172"))
					&& Integer.parseInt(ip.getBlocosInteiros().get(1)) >= 16
					&& Integer.parseInt(ip.getBlocosInteiros().get(1)) <= 31) {
				return true;
			}
		} else if (ip.getClasse() == 'C') {
			if (ip.getBlocosInteiros().get(0).equals(new String("192"))
					&& ip.getBlocosInteiros().get(1).equals(new String("168"))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Método que define o netid de forma estática
	 * 
	 * @param ip
	 */
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

	/**
	 * Método que define o hostid de forma estática
	 * 
	 * @param ip
	 */
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

	/**
	 * Método que imprime os blocos inteiros e binários de um endereço
	 * 
	 * @param ip
	 */
	public static void imprimeBlocosInteirosEBinarios(IP ip) {
		for (int i = 0; i < ip.getBlocosInteiros().size(); i++) {
			System.out.println("Posicao " + i + ":" + ip.getBlocosInteiros().get(i));
		}
		for (int i = 0; i < ip.getBlocosBinarios().size(); i++) {
			System.out.println("Posicao " + i + ":" + ip.getBlocosBinarios().get(i));
		}
	}

	/**
	 * Método que calcula um endereço binário sem a separação por pontos
	 * 
	 * @param ip
	 */
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
		ip.setEnderecoBroadcastBinario(auxBin);
		ip.setEnderecoSubRedeBinario(auxBin);
	}

	/**
	 * Método que retorna a quantidade de endereços em um bloco, a partir do ip
	 * e da máscara
	 * 
	 * @param ip
	 * @param mascara
	 * @return
	 */
	public static Long defineQtdEnderecos(IP ip, IP mascara) {
		int qtdUmDireita = calculaQtdUmDireita(mascara);
		int n = 32 - qtdUmDireita;
		Long qtdEnderecos = ((long) Math.pow(2, n)) - 2;
		return qtdEnderecos;
	}

	/**
	 * Método que define e retorna todos os endereços de host possíveis em uma
	 * subrede
	 * 
	 * @param ip
	 * @param mascara
	 * @return
	 */
	public static List<IP> defineEnderecosHostPossiveis(IP ip, IP mascara) {
		int qtdUmDireita = calculaQtdUmDireita(mascara);
		int n = 32 - qtdUmDireita;
		int qtdSubRedes = 0;

		List<IP> subRedes = new ArrayList<IP>();
		IP ipAux = new IP(ip.getEnderecoSubRede());
		ipAux.setEnderecoSubRede(ip.getEnderecoSubRede());
		subRedes.add(ipAux);
		separaBlocos(subRedes.get(0));

		if (n <= 8) {
			IP newIPAux = new IP();
			newIPAux = subRedes.get(0);
			qtdSubRedes = 255 - (Integer.parseInt(newIPAux.getBlocosInteiros().get(3)));
			for (int i = 0; i < qtdSubRedes; i++) {
				int intAux = 0;
				String oldStrAux = new String();
				if (i != 0) {
					newIPAux = subRedes.get(i - 1);
					oldStrAux = newIPAux.getBlocosInteiros().get(3);
					intAux = Integer.parseInt(oldStrAux);
					intAux++;
					String strAux = new String();
					strAux = Integer.toString(intAux);
					newIPAux.getBlocosInteiros().set(3, strAux);
					String strEndIP = newIPAux.getBlocosInteiros().get(0) + "." + newIPAux.getBlocosInteiros().get(1)
							+ "." + newIPAux.getBlocosInteiros().get(2) + "." + newIPAux.getBlocosInteiros().get(3);
					newIPAux.setEnderecoIP(strEndIP);
					separaBlocos(newIPAux);
					defineEnderecoIPBinarioSemPonto(newIPAux);
					defineEnderecoIPBinarioSemPonto(mascara);
					defineEnderecoBroadcastDinamico(newIPAux, mascara);
					subRedes.add(newIPAux);
				}
				System.out.println(i);
				imprimeBlocosInteirosEBinarios(subRedes.get(i));
			}
			return subRedes;
		} else if (n > 8 && n <= 16) {
			IP newIPAux = new IP();
			newIPAux = subRedes.get(0);
			qtdSubRedes = 255 - (Integer.parseInt(newIPAux.getBlocosInteiros().get(2))) + 255;
			for (int i = 0; i <= qtdSubRedes; i++) {
				int intAux = 0;
				int intAux2 = 0;
				String oldStrAux = new String();
				String oldStrAux2 = new String();

				if (i != 0) {
					newIPAux = subRedes.get(i - 1);
					oldStrAux = newIPAux.getBlocosInteiros().get(2);
					intAux = Integer.parseInt(oldStrAux);
					oldStrAux2 = newIPAux.getBlocosInteiros().get(3);
					intAux2 = Integer.parseInt(oldStrAux2);
					if (intAux + 1 >= 0 && intAux + 1 <= 255) {
						intAux++;
						String strAux = new String();
						strAux = Integer.toString(intAux);
						newIPAux.getBlocosInteiros().set(2, strAux);
						String strEndIP = newIPAux.getBlocosInteiros().get(0) + "."
								+ newIPAux.getBlocosInteiros().get(1) + "." + newIPAux.getBlocosInteiros().get(2) + "."
								+ newIPAux.getBlocosInteiros().get(3);
						newIPAux.setEnderecoIP(strEndIP);
						separaBlocos(newIPAux);
						defineEnderecoIPBinarioSemPonto(newIPAux);
						defineEnderecoIPBinarioSemPonto(mascara);
						defineEnderecoBroadcastDinamico(newIPAux, mascara);
						subRedes.add(newIPAux);
					} else if (intAux2 + 1 >= 0 && intAux2 + 1 <= 255) {
						intAux2++;
						String strAux = new String();
						strAux = Integer.toString(intAux2);
						newIPAux.getBlocosInteiros().set(3, strAux);
						String strEndIP = newIPAux.getBlocosInteiros().get(0) + "."
								+ newIPAux.getBlocosInteiros().get(1) + "." + newIPAux.getBlocosInteiros().get(2) + "."
								+ newIPAux.getBlocosInteiros().get(3);
						newIPAux.setEnderecoIP(strEndIP);
						separaBlocos(newIPAux);
						defineEnderecoIPBinarioSemPonto(newIPAux);
						defineEnderecoIPBinarioSemPonto(mascara);
						defineEnderecoBroadcastDinamico(newIPAux, mascara);
						subRedes.add(newIPAux);
					}
				}
				System.out.println(i);
				if (intAux2 + 1 >= 0 && intAux2 + 1 <= 255) {
					imprimeBlocosInteirosEBinarios(subRedes.get(i));
				}
			}
			return subRedes;
		} else if (n > 16 && n <= 24) {
			IP newIPAux = new IP();
			newIPAux = subRedes.get(0);
			qtdSubRedes = 255 - (Integer.parseInt(newIPAux.getBlocosInteiros().get(1))) + (2 * 255);
			for (int i = 0; i < qtdSubRedes; i++) {
				int intAux = 0;
				int intAux2 = 0;
				int intAux3 = 0;
				String oldStrAux = new String();
				String oldStrAux2 = new String();
				String oldStrAux3 = new String();

				if (i != 0) {
					newIPAux = subRedes.get(i - 1);
					oldStrAux = newIPAux.getBlocosInteiros().get(1);
					intAux = Integer.parseInt(oldStrAux);
					oldStrAux2 = newIPAux.getBlocosInteiros().get(2);
					intAux2 = Integer.parseInt(oldStrAux2);
					oldStrAux3 = newIPAux.getBlocosInteiros().get(3);
					intAux3 = Integer.parseInt(oldStrAux3);
					if (intAux + 1 >= 0 && intAux + 1 <= 255) {
						intAux++;
						String strAux = new String();
						strAux = Integer.toString(intAux);
						newIPAux.getBlocosInteiros().set(1, strAux);
						String strEndIP = newIPAux.getBlocosInteiros().get(0) + "."
								+ newIPAux.getBlocosInteiros().get(1) + "." + newIPAux.getBlocosInteiros().get(2) + "."
								+ newIPAux.getBlocosInteiros().get(3);
						newIPAux.setEnderecoIP(strEndIP);
						separaBlocos(newIPAux);
						defineEnderecoIPBinarioSemPonto(newIPAux);
						defineEnderecoIPBinarioSemPonto(mascara);
						defineEnderecoBroadcastDinamico(newIPAux, mascara);
						subRedes.add(newIPAux);
					} else if (intAux2 + 1 >= 0 && intAux2 + 1 <= 255) {
						intAux2++;
						String strAux = new String();
						strAux = Integer.toString(intAux2);
						newIPAux.getBlocosInteiros().set(2, strAux);
						String strEndIP = newIPAux.getBlocosInteiros().get(0) + "."
								+ newIPAux.getBlocosInteiros().get(1) + "." + newIPAux.getBlocosInteiros().get(2) + "."
								+ newIPAux.getBlocosInteiros().get(3);
						newIPAux.setEnderecoIP(strEndIP);
						separaBlocos(newIPAux);
						defineEnderecoIPBinarioSemPonto(newIPAux);
						defineEnderecoIPBinarioSemPonto(mascara);
						defineEnderecoBroadcastDinamico(newIPAux, mascara);
						subRedes.add(newIPAux);
					} else if (intAux3 + 1 >= 0 && intAux3 + 1 <= 255) {
						intAux3++;
						String strAux = new String();
						strAux = Integer.toString(intAux3);
						newIPAux.getBlocosInteiros().set(3, strAux);
						String strEndIP = newIPAux.getBlocosInteiros().get(0) + "."
								+ newIPAux.getBlocosInteiros().get(1) + "." + newIPAux.getBlocosInteiros().get(2) + "."
								+ newIPAux.getBlocosInteiros().get(3);
						newIPAux.setEnderecoIP(strEndIP);
						separaBlocos(newIPAux);
						defineEnderecoIPBinarioSemPonto(newIPAux);
						defineEnderecoIPBinarioSemPonto(mascara);
						defineEnderecoBroadcastDinamico(newIPAux, mascara);
						subRedes.add(newIPAux);
					}
				}
				System.out.println(i);
				if (intAux3 + 1 >= 0 && intAux3 + 1 <= 255) {
					imprimeBlocosInteirosEBinarios(subRedes.get(i));
				}
			}
			return subRedes;
		}
		return subRedes;
	}

}
