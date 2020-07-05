package com.zj.relation;

import java.util.ArrayList;
import java.util.List;

/**
 * A-B两个Resident之间的关系链
 */
public class RelationBean
{
    /**
     * a与b的关系类型 {@link RelationType}
     */
    private int relation;

    /**
     * 类型链中每个类型对应的居民信息
     */
    private List<Resident> residentList = new ArrayList<>();

    public int getRelation()
    {
        return relation;
    }

    public List<Resident> getResidentList()
    {
        return residentList;
    }

    public void addRelation(int type, Resident resident)
    {
        if (!RelationUtils.isBasicRelation(type))
        {
            throw new IllegalArgumentException("unsupported type----->" + type);
        }
        relation = (relation << 3) + type;
        residentList.add(resident);
    }

    public void removeRelation()
    {
        if (relation == 0)
        {
            throw new IllegalArgumentException("can not remove when relation is empty");
        }
        relation = (relation >> 3);
        residentList.remove(residentList.size() - 1);
    }

    public void clear()
    {
        relation = 0;
        residentList.clear();
    }

    public boolean hasRelation()
    {
        return relation != 0 && !residentList.isEmpty();
    }

    public boolean isInRelation(Resident resident)
    {
        return residentList.contains(resident);
    }
}
