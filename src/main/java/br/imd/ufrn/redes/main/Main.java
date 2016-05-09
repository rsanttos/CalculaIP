package br.imd.ufrn.redes.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.imd.ufrn.redes.dominio.IP;
import br.imd.ufrn.redes.services.IPService;
/**
 * Classe que contém o método principal que roda a aplicação
 * @author ramonsantos
 *
 */
public class Main {

	public static void main(String args[]) {
		Scanner ler = new Scanner(System.in);
		
		String enderecoIP = new String();
		String mascara = new String();
		String opcao;
		System.out.println("DIGITE A OPÇÃO DESEJADA:");
		System.out.println("Informar apenas IP (A):");
		System.out.println("Informar IP e máscara e receber informações sobre o IP (B):");
		System.out.println("Informar IP e máscara e saber as subredes do endereço (C):");
		opcao = ler.nextLine();
		
		switch(opcao){
		case "A":
			System.out.println("Informe o IP:");
			enderecoIP = ler.nextLine();
			IP ip = new IP(enderecoIP);
			IP ipAux = new IP();
			ipAux = IPService.tratarIPSimples(ip);
			break;
		case "B":
			System.out.println("Informe o IP:");
			enderecoIP = ler.nextLine();
			IP ipAux2 = new IP(enderecoIP);
			System.out.println("Informe a máscara:");
			mascara = ler.nextLine();
			IP mascaraAux = new IP(mascara);
			IP ipAux3 = IPService.tratarIPComMascara(ipAux2, mascaraAux);
			break;
		case "C":
			System.out.println("Informe o IP:");
			enderecoIP = ler.nextLine();
			IP ipAux4 = new IP(enderecoIP);
			System.out.println("Informe a máscara:");
			mascara = ler.nextLine();
			IP mascaraAux2 = new IP(mascara);
			List<IP> listIPAux = new ArrayList<IP>();
			listIPAux = IPService.tratarIPComMascaraESubRedes(ipAux4, mascaraAux2);
			break;
		}
	}
}
