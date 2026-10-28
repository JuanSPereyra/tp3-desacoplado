package generadorRandomWrapper;

import java.util.Random;

public class GeneradorRandom implements Generador {
	private Random _random;

	public GeneradorRandom() {
		_random = new Random(0);
	}

	@Override
	public boolean nextBoolean() {
		return _random.nextBoolean();
	}

	@Override
	public int nextInt(int rango) {
		return _random.nextInt(rango);
	}
}
