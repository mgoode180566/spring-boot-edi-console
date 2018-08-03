package com.sml.mgoode.service;

import com.sml.mgoode.dao.LookupConditionDAO;
import com.sml.mgoode.dao.LookupDAO;
import com.sml.mgoode.entity.Lookup;
import com.sml.mgoode.entity.LookupCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by michaelgoode on 06/02/2018.
 */
@Service
public class LookupService {

    @Autowired
    LookupDAO lookupDAO;

    public ArrayList<Lookup> getLookup(long id ) {
        return lookupDAO.getLookup(id);
    }
}
