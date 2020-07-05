package com.zj.relation;

import java.util.List;

public class RelationFunc
{
    /**
     * 对于给定a,b两人，得出其亲属关系
     * @param a 居民a
     * @param b 居民b
     * @return 他们的关系
     */
    public static RelationBean getRelation(Resident a, Resident b)
    {
        return RelationUtils.getRelation(a, b, 4);
    }

    /**
     * @param a
     * @param b
     * @return a与b是否是3代直系亲属
     */
    public static boolean isDirectRelation(Resident a, Resident b)
    {
        return RelationUtils.isDirectRelation(a, b, 2);
    }

    /**
     * @param a
     * @param b
     * @return a与b是否是3代旁系亲属
     */
    public static boolean isSideRelation(Resident a, Resident b)
    {
        return RelationUtils.isSideRelation(a, b, 2);
    }

    /**
     * @param a
     * @return a所有的3代直系亲属
     */
    public static List<Resident> getAllDirectResident(Resident a)
    {
        return RelationUtils.getAllDirectResident(a, 3);
    }

    /**
     * @param a
     * @return a所有的3代旁系亲属
     */
    public static List<Resident> getAllSideRelation(Resident a)
    {
        return RelationUtils.getAllSideRelation(a, 3);
    }

    /**
     * @param a
     * @return 根据关系找到对应的人
     */
    public static List<Resident> getResidentByRelation(Resident a, int relationType)
    {
        return RelationUtils.getResidentByRelation(a, relationType);
    }

    /**
     * @param a
     * @param b
     * @param relationType 关系类型
     * @return a和b是否是relationType的关系
     */
    public static boolean isRelation(Resident a, Resident b, int relationType)
    {
        return RelationUtils.getRelation(a, b, 4).getRelation() == relationType;
    }
}
