package game.bounceball;

import java.io.InputStream;
import java.io.OutputStream;

public interface Saveable<T> {

    void save(OutputStream output);
    T restore(InputStream input);
}
