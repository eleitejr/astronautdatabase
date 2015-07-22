package modelo;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

@SuppressWarnings("serial")
public class ListaDeAstronautas extends JList<Astronauta>{
	
	public void atualizaLista(ArrayList<Astronauta> listaDeAstronautas, String strSexo, String strPais){
	
		DefaultListModel<Astronauta> modelAstroConsulta = new DefaultListModel<>();
		
		switch(strPais){
							case "ALL":	switch(strSexo){
															case "ALL": {
																for (Astronauta a : listaDeAstronautas) {
																	modelAstroConsulta.addElement(a);
																	};
																break;
																}
															
															default: {
																for (Astronauta a : listaDeAstronautas) {
															
																if (a.getSexo().equals(strSexo)){
																	modelAstroConsulta.addElement(a);
																	}
																}
																break;
															}
															
															
										}
							
										break;
							
							default:	switch(strSexo){
															case "ALL": {
																for (Astronauta a : listaDeAstronautas) {
																	if (a.getPais_Nasc().equals(strPais)){
																		modelAstroConsulta.addElement(a);
																	}
																}
																break;
															}
															
															default: {
																for (Astronauta a : listaDeAstronautas){
																	if (a.getPais_Nasc().equals(strPais) && (a.getSexo().equals(strSexo))){
																		modelAstroConsulta.addElement(a);
																	}
																}
																break;
															}
										}
										break;
		}
		
		/*
		
		if (strSexo.equals("ALL") && (strPais.equals("ALL")))  {
			for (Astronauta a : listaDeAstronautas) {
				modelAstroConsulta.addElement(a);
				}
			
		}
		
		else if (strSexo.equals("ALL") && (!strPais.equals("ALL"))) {
			for (Astronauta a : listaDeAstronautas) {
				if (a.getPais_Nasc().equals(strPais)){
					modelAstroConsulta.addElement(a);
				}
			}
		}
		
		else if (!strSexo.equals("ALL") && (strPais.equals("ALL"))) {
			for (Astronauta a : listaDeAstronautas) {
				if (a.getSexo().equals(strSexo)){
					modelAstroConsulta.addElement(a);
					}
				}
		}
		
		else if (!strSexo.equals("ALL") && (!strPais.equals("ALL"))) {
			for (Astronauta a : listaDeAstronautas){
				if (a.getPais_Nasc().equals(strPais) && (a.getSexo().equals(strSexo))){
					modelAstroConsulta.addElement(a);
				}
			}
		}
		
		*/
		
		setModel(modelAstroConsulta);
		setSelectedIndex(0);
		ensureIndexIsVisible(0);
	
	}

}
