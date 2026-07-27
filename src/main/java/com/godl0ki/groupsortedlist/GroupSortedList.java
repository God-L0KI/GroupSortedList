package com.godl0ki.groupsortedlist;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Objects;

///List what is sorted by groups, but get a list of common items.
public class GroupSortedList<E> extends AbstractList<E> implements Serializable {
    private Group<E>[] groupList;
    private int groupSize;

    @SuppressWarnings("unchecked")
    public GroupSortedList(){
        groupList = new Group[0];
        this.groupSize = groupList.length;
    }



    private Group<E> createGroupInternal(String name){
        Group<E> group = new Group<>();
        group.setName(name);
         return group;
    }

    @SuppressWarnings("unchecked")
    private void putGroup(Group<E> group){
        Group<E>[] newGroupList = new Group[this.groupSize + 1];
        for (int i = 0; i < groupSize; i++) {
            newGroupList[i] = groupList[i];
        }
        groupList = newGroupList;
        this.groupSize++;
        groupList[groupSize - 1] = group;
    }


    public GroupView<E> createGroup(String name){
        putGroup(createGroupInternal(name));
        updateGlobalRanges();
         return getGroup(name);
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
        updateGlobalRanges();
    }

    public void moveGroupPos(int targetIndex, String groupName) {
        moveGroupPos(targetIndex, getGroupIndex(groupName));
    }

    @Override
    public int size() {
        int total = 0;

        for(Group<E> g : groupList){
            total += g.size();
        }

        return total;
    }

    public int groupSize(){
      return this.groupSize;
    }


    private void updateGlobalRanges(){
        int usedIndexes = -1;
        for(int i = 0; i < groupSize; i++){
            groupList[i].setRange(usedIndexes + 1, groupList[i].size() + usedIndexes);
            usedIndexes = usedIndexes + groupList[i].size();
        }
    }

    private boolean isInRange(Group<E> group, int index){
        int n1 = group.getRange()[0], n2 = group.getRange()[1];
        return index >= n1 && index <= n2;
    }

    public int toLocalIndex(Group<E> group, int i){
        if(isInRange(group, i)){
            return i - group.getRange()[0];
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int i) {
        Objects.checkIndex(i, size());
        E obj = null;
        for(Group<E> g : groupList){
            if(isInRange(g, i)){
                return g.get(toLocalIndex(g, i));
            }
        }

        return null;
    }



    private class Group< E> extends AbstractList<E> implements Cloneable, Serializable, GroupView<E>, GlobalIndexMap {
        private Object[] elements = new Object[0];
        private String name;
        private int size;
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
            updateGlobalRanges();
        }


        @SuppressWarnings("unchecked")
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

        public E getLast() {
            return get(size - 1);
        }

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
