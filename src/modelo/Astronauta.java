package modelo;

import java.time.LocalDate;
import java.util.ArrayList;


public class Astronauta {

	int id;
	String nome;
	String m_nome;
	String sobrenome;
	String pais;
	String estado;
	String cidade;
	String sexo;
	LocalDate data_Nasc;
	LocalDate data_Falec;
	String foto;
	String bio;
	ArrayList<String> missao;

	public Astronauta(String nome, String m_nome, String sobrenome,
			String pais, String estado, String cidade, String sexo, String data_Nasc,
			String data_Falec, String foto, String bio,
			ArrayList<String> missao) {
		super();
		this.nome = nome;
		this.m_nome = m_nome;
		this.sobrenome = sobrenome;
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.sexo = sexo;

		if (data_Nasc.isEmpty()) this.data_Nasc = LocalDate.parse("1900-01-01"); else this.data_Nasc = LocalDate.parse(data_Nasc);

		if (data_Falec == null) this.data_Falec = LocalDate.parse("1900-01-01"); else this.data_Falec = LocalDate.parse(data_Falec);

		this.foto = foto;
		this.bio = bio;
		this.missao = missao;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getM_nome() {
		return m_nome;
	}
	public void setM_nome(String m_nome) {
		this.m_nome = m_nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public LocalDate getData_Nasc() {
		return data_Nasc;
	}
	public void setData_Nasc(LocalDate data_Nasc) {
		this.data_Nasc = data_Nasc;
	}
	public LocalDate getData_Falec() {
		return data_Falec;
	}
	public void setData_Falec(LocalDate data_Falec) {
		this.data_Falec = data_Falec;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public ArrayList<String> getMissao() {
		return missao;
	}
	public void setMissao(ArrayList<String> missao) {
		this.missao = missao;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder(); 

		sb.append(id + " " 
				+ pais + " " + nome + " " + m_nome + " " + sobrenome);

		return sb.toString(); 
	}


}
