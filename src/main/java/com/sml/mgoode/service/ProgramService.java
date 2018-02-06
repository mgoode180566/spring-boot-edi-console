package com.sml.mgoode.service;

import com.sml.mgoode.dao.ConsoleDAO;
import com.sml.mgoode.entity.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelgoode on 17/01/2018.
 */
@Service
public class ProgramService implements IProgramService {


    @Autowired
    ConsoleDAO consoleDAO;

    public List<Program> getAllPrograms( boolean locked ) {
        List<Program> programs = consoleDAO.getPrograms(locked);
        return programs;
    }

    public List<Program> getAllPrograms() {
        List<Program> programs = consoleDAO.getPrograms();
        return programs;
    }

    public List<Program> getFavourites() {
        List<Program> programs = consoleDAO.getFavourites();
        return programs;
    }

}
