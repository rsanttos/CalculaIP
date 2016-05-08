package br.imd.ufrn.redes.dominio;

import java.util.ArrayList;
import java.util.List;

public class IP {
	private String enderecoIP;
	private String enderecoIPBinario;
	private String mascaraPadrao;
	private String netID;
	private String netIDBinario;
	private String hostID;
	private String hostIDBinario;
	private char classe;
	private List<String> blocosInteiros;
	private List<String> blocosBinarios;
	private boolean privado;
	
	public IP(){
		this.enderecoIP = new String();
		this.enderecoIPBinario = new String();
		this.mascaraPadrao = new String();
		this.netID = new String();
		this.netIDBinario = new String();
		this.hostID = new String();
		this.hostIDBinario = new String();
		this.classe = '0';
		this.blocosInteiros = new ArrayList<String>();
		this.blocosBinarios = new ArrayList<String>();
		this.privado = false;
	}
	
	public IP(String enderecoIP){
		this.enderecoIP = new String(enderecoIP);
		this.enderecoIPBinario = new String();
		this.mascaraPadrao = new String();
		this.netID = new String();
		this.netIDBinario = new String();
		this.hostID = new String();
		this.hostIDBinario = new String();
		this.classe = '0';
		this.blocosInteiros = new ArrayList<String>();
		this.blocosBinarios = new ArrayList<String>();
		this.privado = false;
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

	public String getEnderecoIPBinario() {
		return enderecoIPBinario;
	}

	public void setEnderecoIPBinario(String enderecoIPBinario) {
		this.enderecoIPBinario = enderecoIPBinario;
	}

	public String getNetIDBinario() {
		return netIDBinario;
	}

	public void setNetIDBinario(String netIDBinario) {
		this.netIDBinario = netIDBinario;
	}

	public String getHostIDBinario() {
		return hostIDBinario;
	}

	public void setHostIDBinario(String hostIDBinario) {
		this.hostIDBinario = hostIDBinario;
	}

	public boolean isPrivado() {
		return privado;
	}

	public void setPrivado(boolean privado) {
		this.privado = privado;
	}	
}
