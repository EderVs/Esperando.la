package com.esperando_la.esperandola.model;

import java.io.Serializable;

/**
 * Created by eder on 06/02/2015.
 */
public class Codigos implements Serializable
{
    private String id;
    private int status;
    private String description;

    private String TABLE_NAME = "Codigos";

    public Codigos()
    {
    }

    public Codigos(String id, int status, String description)
    {
        super();
        this.id = id;
        this.status = status;
        this.description = description;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return this.id;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public int getStatus()
    {
        return this.status;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return this.description;
    }
}
