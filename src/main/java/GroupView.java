import java.io.Serializable;

public interface GroupView<E> extends Serializable {
    void put(E obj);
     E get(int index);
    int size();
}
