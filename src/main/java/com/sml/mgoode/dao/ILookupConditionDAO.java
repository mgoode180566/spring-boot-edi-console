package com.sml.mgoode.dao;

import com.sml.mgoode.entity.LookupCondition;

import java.util.ArrayList;

/**
 * Created by michaelgoode on 06/02/2018.
 */
public interface ILookupConditionDAO {
    public ArrayList<LookupCondition> getLookupCondition( long id );
}
