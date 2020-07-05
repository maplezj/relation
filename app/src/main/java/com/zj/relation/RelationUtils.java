package com.zj.relation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RelationUtils
{
    public static RelationBean getRelation(Resident a, Resident b, int level)
    {
        RelationBean relationBean = new RelationBean();
        if (!dfs(a, b, level, relationBean) && a.getCouple() != b)
        {
            relationBean.clear();
        }
        return relationBean;
    }

    /**
     * 深度优先遍历，通过a查找b；
     */
    private static boolean dfs(Resident a, Resident b, int level, RelationBean relationBean)
    {
        if (a == b) return true;
        if (level<=0) return false;
        if (a.getFather() != null)
        {
            relationBean.addRelation(RelationType.FATHER, a.getFather());
            if (dfs(a.getFather(), b, --level, relationBean))
            {
                return true;
            }
            level++;
            relationBean.removeRelation();
        }

        if (a.getMother() != null)
        {
            relationBean.addRelation(RelationType.MOTHER, a.getMother());
            if (dfs(a.getMother(), b, --level, relationBean))
            {
                return true;
            }
            level++;
            relationBean.removeRelation();
        }

        if (a.getSonList() != null && !a.getSonList().isEmpty())
        {
            for (Resident resident : a.getSonList())
            {
                relationBean.addRelation(RelationType.SON, resident);
                if (dfs(resident, b, --level, relationBean))
                {
                    return true;
                }
                level++;
                relationBean.removeRelation();
            }
        }

        if (a.getDaughterList() != null && !a.getDaughterList().isEmpty())
        {
            for (Resident resident : a.getDaughterList())
            {
                relationBean.addRelation(RelationType.DAUGHTER, resident);
                if (dfs(resident, b, --level, relationBean))
                {
                    return true;
                }
                level++;
                relationBean.removeRelation();
            }
        }

        return false;
    }

    /**
     * @param a
     * @param b
     * @param level 代数
     * @return a和b是否是level代的直系亲属
     */
    public static boolean isDirectRelation(Resident a, Resident b, int level)
    {
        return directDfs(a, b, level, true) || directDfs(a, b, level, false) || b == a.getCouple();
    }

    /**
     * @param a
     * @param b
     * @param level
     * @return a和b是否是level代旁系亲属
     */
    public static boolean isSideRelation(Resident a, Resident b, int level)
    {
        List<Resident> sideList = getAllSideRelation(a, level);
        return sideList.contains(b);
    }

    private static boolean directDfs(Resident a, Resident b, int level, boolean up)
    {
        if (a == b) return true;
        if (level <= 0) return false;
        if (a.getFather() != null && up)
        {
            if (directDfs(a.getFather(), b, --level, up))
            {
                return true;
            }
            level++;
        }

        if (a.getMother() != null && up)
        {
            if (directDfs(a.getMother(), b, --level, up))
            {
                return true;
            }
            level++;
        }

        if (a.getSonList() != null && !up)
        {
            for (Resident resident : a.getSonList())
            {
                if (directDfs(resident, b, --level, up))
                {
                    return true;
                }
                level++;
            }
        }

        if (a.getDaughterList() != null && !up)
        {
            for (Resident resident : a.getDaughterList())
            {
                if (directDfs(resident, b, --level, up))
                {
                    return true;
                }
                level++;
            }
        }

        return false;
    }

    public static List<Resident> getAllDirectResident(Resident a, int level)
    {
        List<Resident> residentList = new ArrayList<>();
        directDfsGet(a, level, true, residentList);
        directDfsGet(a, level, false, residentList);
        residentList.remove(a);
        if (a.getCouple() != null)
            residentList.add(a.getCouple());
        return residentList;
    }

    /**
     * 获取当前居民向上的直系亲属或向下的直系亲属
     * @param resident
     * @param level 几代直系
     * @param up 是否是长辈的直系
     * @param residentList 直系亲属结果
     */
    private static void directDfsGet(Resident resident, int level, boolean up, List<Resident> residentList)
    {
        if (level <= 0) return;
        if (!residentList.contains(resident))
            residentList.add(resident);
        if (resident.getFather() != null && up)
        {
            directDfsGet(resident.getFather(), --level, up, residentList);
            level++;
        }

        if (resident.getMother() != null && up)
        {
            directDfsGet(resident.getMother(), --level, up, residentList);
            level++;
        }

        if (resident.getSonList() != null && !up)
        {
            for (Resident son : resident.getSonList())
            {
                directDfsGet(son, --level, up, residentList);
                level++;
            }
        }

        if (resident.getDaughterList() != null && !up)
        {
            for (Resident daughter : resident.getDaughterList())
            {
                directDfsGet(daughter, --level, up, residentList);
                level++;
            }
        }
    }

    public static List<Resident> getResidentByRelation(Resident a, int relationType)
    {
        Log.d("relation", "relationType: " + Integer.toBinaryString(relationType));
        List<Resident> residentList = new ArrayList<>();
        int stand = (1 << 3) - 1;
        //状态从最左边开始取
        int[] types = new int[9];
        int index = 0;
        while ((relationType & stand) != 0)
        {
            types[index] = (relationType & stand) >> index * 3;
            index++;
            stand <<= 3;
        }
        dfsByType(a, index - 1, types, residentList, null);
        return residentList;
    }

    private static void dfsByType(Resident resident, int index, int[] types, List<Resident> residentList, Resident pre)
    {
        if (index < 0)
        {
            residentList.add(resident);
            return;
        }
        switch (types[index])
        {
            case RelationType.FATHER:
                if (resident.getFather() != null)
                    dfsByType(resident.getFather(), --index, types, residentList,resident);
                break;
            case RelationType.MOTHER:
                if (resident.getMother() != null)
                    dfsByType(resident.getMother(), --index, types, residentList,resident);
                break;
            case RelationType.COUPLE:
                if (resident.getCouple() != null)
                    dfsByType(resident.getCouple(), --index, types, residentList,resident);
                break;
            case RelationType.SON:
                if (resident.getSonList() != null && !resident.getSonList().isEmpty())
                {
                    for (Resident son : resident.getSonList())
                    {
                        if (pre != null && pre == son) continue;
                        dfsByType(son, --index, types, residentList,resident);
                        index++;
                    }
                }
                break;
            case RelationType.DAUGHTER:
                if (resident.getDaughterList() != null && !resident.getDaughterList().isEmpty())
                {
                    for (Resident daughter : resident.getDaughterList())
                    {
                        if (pre != null && pre == daughter) continue;
                        dfsByType(daughter, --index, types, residentList,resident);
                        index++;
                    }
                }
                break;
        }
    }

    /**
     * 实现：先获取居民向上的level代直系亲属，再获取每个直系亲属向下的level代直系亲属，再将这些亲属减去当前居民的直系亲属
     * 即为当前居民的所有旁系；
     * @param resident
     * @param level
     * @return 获取居民的所有level代的旁系亲属
     */
    public static List<Resident> getAllSideRelation(Resident resident, int level)
    {
        List<Resident> directList = new ArrayList<>();
        List<Resident> allList = new ArrayList<>();
        directDfsGet(resident, level, true, directList);
        for (Resident resident1 : directList)
        {
            directDfsGet(resident1, level, false, allList);
        }
        directDfsGet(resident, level, false, directList);
        allList.removeAll(directList);
        allList.remove(resident);
        return allList;
    }

    public static boolean isBasicRelation(int relation)
    {
        return relation == RelationType.FATHER
                || relation == RelationType.MOTHER
                || relation == RelationType.COUPLE
                || relation == RelationType.SON
                || relation == RelationType.DAUGHTER;
    }

}
