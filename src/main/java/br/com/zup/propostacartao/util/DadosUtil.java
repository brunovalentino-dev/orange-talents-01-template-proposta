package br.com.zup.propostacartao.util;

import org.apache.commons.lang3.StringUtils;

public class DadosUtil {

	public static String maskString(String texto, int inicio, int fim, char mascara) throws Exception {
        if(texto == null || texto.equals(""))
            return "";
        
        if(inicio < 0) {
        	inicio = 0;
        }
        
        if(fim > texto.length()) {
        	fim = texto.length();
        }
            
        if(inicio > fim)
            throw new Exception("Os indíces informados são inválidos para este texto.");
        
        int tamanhoMascara = fim - inicio;
        
        if(tamanhoMascara == 0) {
            return texto;
        }
        
        String mascaraTexto = StringUtils.repeat(mascara, tamanhoMascara);
        
        return StringUtils.overlay(texto, mascaraTexto, inicio, fim);
    }
	
}
