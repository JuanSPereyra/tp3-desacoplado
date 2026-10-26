package datos;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class JsonLoader {

	public static GrafoWrapper cargarGrafo(String rutaArchivo) {
		Gson gson = new Gson();
		try (Reader reader = new FileReader(rutaArchivo)) {
			return gson.fromJson(reader, GrafoWrapper.class);
		} catch (Exception e) {
			throw new RuntimeException("Error al leer el archivo JSON:" + e.getMessage(), e);
		}
	}

	public static class GrafoWrapper {
		private List<String> vertices;
		private List<AristaJson> aristas;

		public List<String> getVertices() {
			return vertices;
		}

		public List<AristaJson> getAristas() {
			return aristas;
		}
	}

	public static class AristaJson {
		private String origen;
		private String destino;

		public String getOrigen() {
			return origen;
		}

		public String getDestino() {
			return destino;
		}
	}
}
