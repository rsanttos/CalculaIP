package br.imd.ufrn.redes.controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.imd.ufrn.redes.dominio.IP;
import br.imd.ufrn.redes.services.IPService;
/**
 * Controladora responsável por realizar a ligação entre a interface de usuário e 
 * a camada de serviço
 * @author ramonsantos
 *
 */
@ManagedBean
@SessionScoped
public class IPMBean {
	private IP ip;
	private IP mascara;
	private IPService ipService;
	private DataModel<IP> subRedes;
	
	public IPMBean(){
		this.ip = new IP();
		this.mascara = new IP();
		this.ipService = new IPService();
	}

	public IP getIp() {
		return ip;
	}

	public void setIp(IP ip) {
		this.ip = ip;
	}

	public IPService getIpService() {
		return ipService;
	}

	public void setIpService(IPService ipService) {
		this.ipService = ipService;
	}
	
	public IP getMascara() {
		return mascara;
	}

	public void setMascara(IP mascara) {
		this.mascara = mascara;
	}

	public DataModel<IP> getSubRedes() {
		return subRedes;
	}

	public void setSubRedes(DataModel<IP> subRedes) {
		this.subRedes = subRedes;
	}

	public String getEnderecoIPSimples(){
		IP ipAux = this.ipService.tratarIPSimples(ip);
		this.ip = ipAux;
		return null;
	}
	
	public String getEnderecoIPComMascara(){
		IP ipAux = this.ipService.tratarIPComMascara(ip, mascara);
		this.ip = ipAux;
		return null;
	}
	
	public DataModel<IP> getEnderecoIPComMascaraESubRedes(){
		this.subRedes = new ListDataModel(this.ipService.tratarIPComMascaraESubRedes(ip, mascara));
		return this.subRedes;
	}
}
