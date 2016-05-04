package br.imd.ufrn.redes.controladores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.imd.ufrn.redes.dominio.IP;
import br.imd.ufrn.redes.services.IPService;

@ManagedBean
@SessionScoped
public class IPMBean {
	private IP ip;
	private IPService ipService;
	
	public IPMBean(){
		this.ip = new IP();
		this.ipService = new IPService();
	}
	
}