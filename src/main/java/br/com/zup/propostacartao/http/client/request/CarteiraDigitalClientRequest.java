package br.com.zup.propostacartao.http.client.request;

import br.com.zup.propostacartao.request.CarteiraDigitalRequest;

public class CarteiraDigitalClientRequest {

    private String email;
    private String carteira;

    public CarteiraDigitalClientRequest(CarteiraDigitalRequest request) {
        this.email = request.getEmail();
        this.carteira = request.getTipoCarteira().toString();
    }

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}

}
