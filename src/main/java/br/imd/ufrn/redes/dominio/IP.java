package br.imd.ufrn.redes.dominio;

import java.util.ArrayList;
import java.util.List;

public class IP {
	private String enderecoIP;
	private String mascaraPadrao;
	private String netID;
	private String hostID;
	private char classe;
	private List<String> blocosInteiros;
	private List<String> blocosBinarios;
	
	public IP(){
		this.enderecoIP = new String();
		this.mascaraPadrao = new String();
		this.netID = new String();
		this.hostID = new String();
		this.classe = '0';
		this.blocosInteiros = new ArrayList<String>();
		this.blocosBinarios = new ArrayList<String>();
	}
	
	public IP(String enderecoIP){
		this.enderecoIP = new String(enderecoIP);
		this.mascaraPadrao = new String();
		this.netID = new String();
		this.hostID = new String();
		this.classe = '0';
		this.blocosInteiros = new ArrayList<String>();
		this.blocosBinarios = new ArrayList<String>();
	}

	public String getEnderecoIP() {
		return enderecoIP;
	}

	public void setEnderecoIP(String enderecoIP) {
		this.enderecoIP = enderecoIP;
	}

	public String getMascaraPadrao() {
		return mascaraPadrao;
	}

	public void setMascaraPadrao(String mascaraPadrao) {
		this.mascaraPadrao = mascaraPadrao;
	}

	public String getNetID() {
		return netID;
	}

	public void setNetID(String netID) {
		this.netID = netID;
	}

	public String getHostID() {
		return hostID;
	}

	public void setHostID(String hostID) {
		this.hostID = hostID;
	}

	public char getClasse() {
		return classe;
	}

	public void setClasse(char classe) {
		this.classe = classe;
	}

	public List<String> getBlocosInteiros() {
		return blocosInteiros;
	}

	public void setBlocosInteiros(List<String> blocos) {
		this.blocosInteiros = blocos;
	}

	public List<String> getBlocosBinarios() {
		return blocosBinarios;
	}

	public void setBlocosBinarios(List<String> blocosBinarios) {
		this.blocosBinarios = blocosBinarios;
	}	
	
}
