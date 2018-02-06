package com.sml.mgoode.dao;

import com.sml.mgoode.entity.Program;

import java.util.List;

/**
 * Created by michaelgoode on 17/01/2018.
 */
public interface IConsoleDAO {
    public List<Program> getPrograms( boolean locked );
    public List<Program> getPrograms();
}
