import java.io.Serializable;
import java.util.AbstractList;
import java.util.Objects;

///List what is sorted by groups, but get a list of common items.
public class GroupSortedList<E> extends AbstractList<E> implements Serializable {
    private Group<E>[] groupList;
    private int groupSize;
    private int size;


    @SuppressWarnings("unchecked")
    public GroupSortedList(){
        groupList = new Group[0];
        this.size = 0;
        this.groupSize = groupList.length;
    }



    private Group<E> create_group(String name){
        Group<E> group = new Group<>();
        group.setName(name);
         return group;
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


    public void createGroup(String name){
        putGroup(create_group(name));
    }

    private Group<E> groupIndex(int index){
        return this.groupList[index];
    }

   public GroupView<E> getGroup(int index){
       Objects.checkIndex(index, this.groupSize);
       return this.groupIndex(index);
   }

   public GroupView<E> getGroup(String name){
        for(int i = 0; i < groupSize; i++){
        if(getGroup(i).getName().equals(name)){
            return getGroup(i);
             }
        }

        return null;
   }
   
   public GroupView<E> getLastGroup(){
        return getGroup(groupSize - 1);
   }

   public GroupView<E> getFirstGroup(){
        return getGroup(0);
   }


    int getGroupIndex(String name){
        for(int i = 0; i < groupSize; i++){
            if(Objects.equals(getGroup(i).getName(), name)){
                return i;
            }
        }
        return -1;
    }

    public void moveGroupPos(int targetIndex, int sourceIndex) {
        Objects.checkIndex(targetIndex, groupSize);
        Objects.checkIndex(sourceIndex, groupSize);

        Group<E> group = groupList[sourceIndex];
        if(sourceIndex > targetIndex){
            for (int i = sourceIndex; i > targetIndex; i--) {
                groupList[i] = groupList[i - 1];
            }
        }else {
            for (int i = sourceIndex; i < targetIndex; i++) {
                groupList[i] = groupList[i + 1];
            }
        }


        groupList[targetIndex] = group;
    }

    public void moveGroupPos(int targetIndex, String groupName) {
        moveGroupPos(targetIndex, getGroupIndex(groupName));
    }

    @Override
    public int size() {
        for(int i = 0; i < groupSize; i++){
            this.size = getGroup(i).size() + this.size;
        }

        return size;
    }

    public int groupSize(){
      return this.groupSize;
    }


    @Override
    public E get(int i) {
        Object[] ellements = new Object[size];
        for(i = 0; i < groupSize; i++){
            for(int k = 0; k < getGroup(i).size(); k++){
                for (int n = 0; n < size; n++){
                    ellements[n] = getGroup(i).get(k);
                }
            }
        }

        return (E)  ellements[0];
    }

    private static class Group< E> extends AbstractList<E> implements Cloneable, Serializable, GroupView<E>, GlobalIndexMap {
        private Object[] elements = new Object[0];
        private String name;
        private int size;
        private int[] globalIndexList = new int[size];
        private int[] range = new int[2];

        public Group(){
            size = elements.length;
        }


        @Override
        public String getName(){
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
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

        @Override
        public E getLast() {
            return get(size - 1);
        }

        @Override
        public E getFirst() {
            return get(0);
        }

        public void setRange(int s,int f){
            this.range[0] = s;
            this.range[1] = f;
        }

        public void setGlobalIndex(int index){

        }

        @Override
        public int[] getRange() {
            return range;
        }

        @Override
        public int getGlobalIndex() {
            return 0;
        }
    }

    private interface GlobalIndexMap{
    int[] getRange();
    int getGlobalIndex();
    }

}
