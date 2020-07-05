package com.zj.relation;

import java.util.List;

public class Resident
{
    public static final int MALE = 1;
    public static final int FEMALE = 0;

    private Resident father;
    private Resident mother;
    /*妻子或丈夫*/
    private Resident couple;
    private List<Resident> sonList;
    private List<Resident> daughterList;

    private String name;
    private int age;
    private int gender;

    public Resident(String name, int age, int gender)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Resident getFather()
    {
        return father;
    }

    public Resident getMother()
    {
        return mother;
    }

    public Resident getCouple()
    {
        return couple;
    }

    public List<Resident> getSonList()
    {
        return sonList;
    }

    public List<Resident> getDaughterList()
    {
        return daughterList;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public int getGender()
    {
        return gender;
    }

    public void setFather(Resident father)
    {
        this.father = father;
    }

    public void setMother(Resident mother)
    {
        this.mother = mother;
    }

    public void setCouple(Resident couple)
    {
        this.couple = couple;
    }

    public void setSonList(List<Resident> sonList)
    {
        this.sonList = sonList;
    }

    public void setDaughterList(List<Resident> daughterList)
    {
        this.daughterList = daughterList;
    }
}
