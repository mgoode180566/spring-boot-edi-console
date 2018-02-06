package com.sml.mgoode.service;

import com.sml.mgoode.entity.Program;

import java.util.List;

/**
 * Created by michaelgoode on 17/01/2018.
 */
public interface IProgramService {
    public List<Program> getAllPrograms( boolean locked );
    public List<Program> getAllPrograms();
    public List<Program> getFavourites();
}
