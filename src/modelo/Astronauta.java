package modelo;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

/**
 * @author Erasmo Leite Jr
 * @version 0.7
 * 
 * Date: 2015.07.11
 * 
 * class Astronauta - modelo 
 * 
 *
 */
public class Astronauta {
	
	private int 			idAstronauta;
	
	private String 			Primeiro_Nome, 
							Nome_do_Meio, 
							Sobrenome,
							Pais_Nasc,
							Estado_Nasc,
							Cidade_Nasc,
							Foto,
							Sexo,
							Info,
							Info_eng;
	
	private Date 			DtNasc,
							DtFalec;
	
	private List<String> 	missao;
	
	private Blob			Imagem;
	
	// construtor
	public Astronauta(
			int idAstronauta, 
			String primeiro_Nome,
			String nome_do_Meio, 
			String sobrenome, 
			String pais_Nasc,
			String estado_Nasc, 
			String cidade_Nasc, 
			String foto, 
			String sexo,
			String info, 
			String info_eng, 
			Date dtNasc, 
			Date dtFalec,
			List<String> missao,
			Blob imagem) {
		super();
		this.idAstronauta = idAstronauta;
		Primeiro_Nome = primeiro_Nome;
		Nome_do_Meio = nome_do_Meio;
		Sobrenome = sobrenome;
		Pais_Nasc = pais_Nasc;
		Estado_Nasc = estado_Nasc;
		Cidade_Nasc = cidade_Nasc;
		Foto = foto;
		Sexo = sexo;
		Info = info;
		Info_eng = info_eng;
		DtNasc = dtNasc;
		DtFalec = dtFalec;
		this.missao = missao;
		Imagem = imagem;
	}

	public Astronauta() {
		// TODO Auto-generated constructor stub
	}

	// GETTERS e SETTERS
	public int getIdAstronauta() {
		return idAstronauta;
	}
	public void setIdAstronauta(int idAstronauta) {
		this.idAstronauta = idAstronauta;
	}
	public String getPrimeiro_Nome() {
		return Primeiro_Nome;
	}
	public void setPrimeiro_Nome(String primeiro_Nome) {
		Primeiro_Nome = primeiro_Nome;
	}
	public String getNome_do_Meio() {
		return Nome_do_Meio;
	}
	public void setNome_do_Meio(String nome_do_Meio) {
		Nome_do_Meio = nome_do_Meio;
	}
	public String getSobrenome() {
		return Sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		Sobrenome = sobrenome;
	}
	public String getPais_Nasc() {
		return Pais_Nasc;
	}
	public void setPais_Nasc(String pais_Nasc) {
		Pais_Nasc = pais_Nasc;
	}
	public String getEstado_Nasc() {
		return Estado_Nasc;
	}
	public void setEstado_Nasc(String estado_Nasc) {
		Estado_Nasc = estado_Nasc;
	}
	public String getCidade_Nasc() {
		return Cidade_Nasc;
	}
	public void setCidade_Nasc(String cidade_Nasc) {
		Cidade_Nasc = cidade_Nasc;
	}
	public String getFoto() {
		return Foto;
	}
	public void setFoto(String foto) {
		Foto = foto;
	}
	public String getSexo() {
		return Sexo;
	}
	public void setSexo(String sexo) {
		Sexo = sexo;
	}
	public String getInfo() {
		return Info;
	}
	public void setInfo(String info) {
		Info = info;
	}
	public String getInfo_eng() {
		return Info_eng;
	}
	public void setInfo_eng(String info_eng) {
		Info_eng = info_eng;
	}
	public Date getDtNasc() {
		return DtNasc;
	}
	public void setDtNasc(Date dtNasc) {
		DtNasc = dtNasc;
	}
	public Date getDtFalec() {
		return DtFalec;
	}
	public void setDtFalec(Date dtFalec) {
		DtFalec = dtFalec;
	}
	public List<String> getMissao() {
		return missao;
	}
	public void setMissao(List<String> missao) {
		this.missao = missao;
	}
	
	public Blob getImagem() {
		return Imagem;
	}

	public void setImagem(Blob imagem) {
		Imagem = imagem;
	}

	@Override
	public String toString() {
		return this.getSobrenome() + ", " + this.getPrimeiro_Nome();
	}
	
}
