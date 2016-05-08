package br.imd.ufrn.redes.dominio;

import java.util.ArrayList;
import java.util.List;
/**
 * Entidade que representa um endereço de IP e seus respectivos atributos
 * @author ramonsantos
 *
 */
public class IP {
	private String enderecoIP;
	private String enderecoIPBinario;
	private String enderecoBroadcast;
	private String enderecoBroadcastBinario;
	private String enderecoSubRede;
	private String enderecoSubRedeBinario;
	private String mascaraPadrao;
	private String netID;
	private String netIDBinario;
	private String hostID;
	private String hostIDBinario;
	private char classe;
	private List<String> blocosInteiros;
	private List<String> blocosBinarios;
	private boolean privado;
	private Long qtdEnderecos;
	
	public IP(){
		this.enderecoIP = new String();
		this.enderecoIPBinario = new String();
		this.mascaraPadrao = new String();
		this.netID = new String();
		this.netIDBinario = new String();
		this.hostID = new String();
		this.hostIDBinario = new String();
		this.enderecoBroadcast = new String();
		this.enderecoBroadcastBinario = new String();
		this.enderecoSubRede = new String();
		this.enderecoSubRedeBinario = new String();
		this.classe = '0';
		this.blocosInteiros = new ArrayList<String>();
		this.blocosBinarios = new ArrayList<String>();
		this.privado = false;
		this.qtdEnderecos = null;
	}
	
	public IP(String enderecoIP){
		this.enderecoIP = new String(enderecoIP);
		this.enderecoIPBinario = new String();
		this.mascaraPadrao = new String();
		this.netID = new String();
		this.netIDBinario = new String();
		this.hostID = new String();
		this.hostIDBinario = new String();
		this.enderecoBroadcast = new String();
		this.enderecoBroadcastBinario = new String();
		this.enderecoSubRede = new String();
		this.enderecoSubRedeBinario = new String();
		this.classe = '0';
		this.blocosInteiros = new ArrayList<String>();
		this.blocosBinarios = new ArrayList<String>();
		this.privado = false;
		this.qtdEnderecos = null;
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

	public Long getQtdEnderecos() {
		return qtdEnderecos;
	}

	public void setQtdEnderecos(Long qtdEnderecos) {
		this.qtdEnderecos = qtdEnderecos;
	}

	public String getEnderecoBroadcast() {
		return enderecoBroadcast;
	}

	public void setEnderecoBroadcast(String enderecoBroadcast) {
		this.enderecoBroadcast = enderecoBroadcast;
	}

	public String getEnderecoBroadcastBinario() {
		return enderecoBroadcastBinario;
	}

	public void setEnderecoBroadcastBinario(String enderecoBroadcastBinario) {
		this.enderecoBroadcastBinario = enderecoBroadcastBinario;
	}

	public String getEnderecoSubRede() {
		return enderecoSubRede;
	}

	public void setEnderecoSubRede(String enderecoSubRede) {
		this.enderecoSubRede = enderecoSubRede;
	}

	public String getEnderecoSubRedeBinario() {
		return enderecoSubRedeBinario;
	}

	public void setEnderecoSubRedeBinario(String enderecoSubRedeBinario) {
		this.enderecoSubRedeBinario = enderecoSubRedeBinario;
	}	
	
}
