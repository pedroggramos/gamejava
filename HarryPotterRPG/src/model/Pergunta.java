package model;

public class Pergunta {

	private int id;
	private String pergunta;
	private String alt_a;
	private String alt_b;
	private String alt_c;
	private String alt_d;
	private String resposta_correta;
	
	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public String getAlt_a() {
		return alt_a;
	}
	public void setAlt_a(String alt_a) {
		this.alt_a = alt_a;
	}
	public String getAlt_b() {
		return alt_b;
	}
	public void setAlt_b(String alt_b) {
		this.alt_b = alt_b;
	}
	public String getAlt_c() {
		return alt_c;
	}
	public void setAlt_c(String alt_c) {
		this.alt_c = alt_c;
	}
	public String getAlt_d() {
		return alt_d;
	}
	public void setAlt_d(String alt_d) {
		this.alt_d = alt_d;
	}
	public String getResposta_correta() {
		return resposta_correta;
	}
	public void setResposta_correta(String resposta_correta) {
		this.resposta_correta = resposta_correta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
