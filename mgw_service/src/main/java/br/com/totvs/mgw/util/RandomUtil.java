package br.com.totvs.mgw.util;

import java.util.UUID;

public class RandomUtil {

	public static String gerarID() {
		UUID uniqueKey = UUID.randomUUID();
		return uniqueKey.toString();
	}
}
