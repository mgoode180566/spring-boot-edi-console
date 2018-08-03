package com.sml.mgoode.service;

import com.sml.mgoode.entity.Lookup;
import com.sml.mgoode.entity.LookupCondition;

import java.util.ArrayList;

/**
 * Created by michaelgoode on 06/02/2018.
 */
public interface ILookupService {
    public ArrayList<Lookup> getLookup(long id);
}
