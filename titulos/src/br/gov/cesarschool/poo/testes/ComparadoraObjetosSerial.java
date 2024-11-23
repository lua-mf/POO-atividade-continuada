package br.gov.cesarschool.poo.testes;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class ComparadoraObjetosSerial {
	static boolean compareObjectsSerial(Serializable s1, Serializable s2) {
		ByteArrayOutputStream bos1 = null;
		ByteArrayOutputStream bos2 = null;
		ObjectOutputStream oos1 = null;
		ObjectOutputStream oos2 = null;
		boolean ret = true;
		try {
			// Serializando os dois objetos
			bos1 = new ByteArrayOutputStream();
			bos2 = new ByteArrayOutputStream();
			oos1 = new ObjectOutputStream(bos1);
			oos2 = new ObjectOutputStream(bos2);
			oos1.writeObject(s1);
			oos2.writeObject(s2);
			byte[] b1 = bos1.toByteArray();
			byte[] b2 = bos2.toByteArray();

			// Comparando os bytes dos objetos serializados
			if (b1.length != b2.length) {
				ret = false;
			} else {
				for (int i = 0; i < b1.length; i++) {
					if (b1[i] != b2[i]) {
						ret = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// Fechando streams
			try {
				if (oos1 != null) oos1.close();
				if (oos2 != null) oos2.close();
				if (bos1 != null) bos1.close();
				if (bos2 != null) bos2.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
}
