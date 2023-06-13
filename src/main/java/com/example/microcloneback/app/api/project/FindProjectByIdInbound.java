package com.example.microcloneback.app.api.project;

import com.example.microcloneback.model.project.Project;

public interface FindProjectByIdInbound {
    Project execute(Long id);
}
