import java.io.Serializable;
import java.util.AbstractList;
import java.util.Objects;

public class GroupSortedList<E> extends AbstractList<E> implements Serializable {
    //Основной массив будет тут, он будет собираться тута типо, готовый массив который будет собиратся из групп или независимых элеметов
    private Group[] groupList = new Group[0];
    private int groupSize;
    private int size;


    public GroupSortedList(){
        this.size = size();
        this.groupSize = groupList.length;
    }



    private Group createGroup(Object obj){
         return new Group<Object>();
    }


    private void putGroup(Group group){
        Group[] newGroupList = new Group[this.groupSize + 1];
        for (int i = 0; i < groupSize; i++) {
            newGroupList[i] = groupList[i];
        }
        groupList = newGroupList;
        this.groupSize++;
        groupList[groupSize - 1] = group;
    }


    private void createGroug(Object object){
        putGroup(createGroup(object));
    }

    private Group groupIndex(int index){
        return this.groupList[index];
    }

   public GroupView<E> getGroup(int index){
       Objects.checkIndex(index, this.groupSize);
       return this.groupIndex(index);
   }


    @Override
    public E get(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    public int groupSize(){
      return this.groupSize;
    }




    //Тут будет Группы которые собираются из подгруп или так
    private static class Group<E> extends AbstractList<E> implements Cloneable, Serializable, GroupView<E> {
        private Object[] elements = new Object[0];
        private int size;

        public Group(){
            size = elements.length;
        }

        public void put(E obj){
            this.size++;
            Object[] elements = this.elements;
            this.elements = new Object[size];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            this.elements[size - 1] = obj;
        }


        private E index(int index){
            return (E) this.elements[index];
        }

        @Override
        public E get(int index) {
            Objects.checkIndex(index, this.size);
            return this.index(index);
        }

        @Override
        public int size() {
            return size;
        }

    }

}
